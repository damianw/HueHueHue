package org.damianw.huehuehue.api.gson

import com.google.gson.Gson
import com.google.gson.JsonSyntaxException
import com.google.gson.TypeAdapter
import com.google.gson.TypeAdapterFactory
import com.google.gson.internal.ConstructorConstructor
import com.google.gson.internal.Primitives
import com.google.gson.reflect.TypeToken
import com.google.gson.stream
import com.google.gson.stream.JsonReader
import com.google.gson.stream.JsonToken
import com.google.gson.stream.JsonWriter
import org.damianw.huehuehue.gson.GsonTypes
import org.damianw.huehuehue.gson.TypeAdapterRuntimeTypeWrapper
import kotlin.reflect.KClass
import kotlin.reflect.KMutableProperty
import kotlin.reflect.KProperty
import kotlin.reflect.jvm.*

/**
 * @author Damian Wieczorek {@literal <damian@farmlogs.com>}
 * @since 7/8/15
 * (C) 2015 Damian Wieczorek
 */
class KotlinReflectiveTypeAdapterFactory(
    val constructorConstructor: ConstructorConstructor = ConstructorConstructor(mapOf())
) : TypeAdapterFactory {

  // TODO
  public fun excludeProperty(property: KProperty<*>, serialize: Boolean): Boolean = false

  // TODO
  private fun getPropertyName(property: KProperty<*>) = property.name

  override fun <T> create(gson: Gson, type: TypeToken<T>): TypeAdapter<T>? {
    val raw = type.getRawType() as Class<Any?>

    if (!javaClass<Object>().isAssignableFrom(raw) || javaClass<String>().equals(raw)) {
      return null // It's a primitive! (or a String...)
    }

    val constructor = constructorConstructor[type]
    return Adapter({ constructor.construct() }, getBoundProperties(gson, type, raw.kotlin))
  }

  private fun createBoundProperty (
      context: Gson,
      property: KMutableProperty<*>,
      name: String,
      propertyType: TypeToken<Any?>,
      serialize: Boolean,
      deserialize: Boolean) = object: BoundProperty(name, serialize, deserialize) {

    val isPrimitive = Primitives.isPrimitive(propertyType.getRawType())
    val typeAdapter = getPropertyAdapter(context, property, propertyType)
    val javaGetter = property.javaGetter
        ?: throw IllegalArgumentException("Property ${property.name} must have a Java getter")
    val javaSetter = property.javaSetter
        ?: throw IllegalArgumentException("Property ${property.name} must have a Java setter")

    override fun write(writer: JsonWriter, value: Any?) {
      val propertyValue = javaGetter(value)
      val t = TypeAdapterRuntimeTypeWrapper(context, typeAdapter, propertyType.getType())
          as TypeAdapterRuntimeTypeWrapper<Any?>
      t.write(writer, propertyValue)
    }

    override fun read(reader: JsonReader, value: Any?) = typeAdapter.read(reader) let {
      if (it != null || !isPrimitive) {
        javaSetter(value, it)
      }
    }

    override fun writeProperty(value: Any?): Boolean
        = if (!serialized) false else javaGetter(value) let { it != value }

  }

  // TODO: check JsonAdapter annotation
  private fun getPropertyAdapter(
      gson: Gson,
      property: KMutableProperty<*>,
      propertyType: TypeToken<*>
  ) : TypeAdapter<*> = gson.getAdapter(propertyType)

  private fun getBoundProperties(context: Gson, type: TypeToken<*>, raw: KClass<Any?>): Map<String, BoundProperty> {
    val result = linkedMapOf<String, BoundProperty>()
    if (raw.java.isInterface()) {
      return result
    }

    val declaredType = type.getType()
    for (property in raw.properties) {
      val serialize = !excludeProperty(property, true)
      val deserialize = !excludeProperty(property, false)
      if (!serialize && !deserialize) {
        continue
      }
      if (property !is KMutableProperty<*>) {
        throw IllegalArgumentException("Property ${property.name} must be mutable")
      }
      property.accessible = true
      val javaSetter = property.javaSetter
          ?: throw IllegalArgumentException("Property ${(property as KMutableProperty<*>).name} must have a Java setter")
      javaSetter.setAccessible(true)
      val propertyType = GsonTypes.resolve(type.getType(), raw.java, javaSetter.getGenericParameterTypes()[0])
      val boundProperty = createBoundProperty (
          context,
          property,
          getPropertyName(property),
          TypeToken.get(propertyType) as TypeToken<Any?>,
          serialize,
          deserialize)
      val previous = result.put(boundProperty.name, boundProperty)
      if (previous != null) {
        throw IllegalArgumentException("$declaredType declares multiple JSON fields named ${previous.name}")
      }
    }
    return result
  }

  private abstract class BoundProperty(
      val name: String,
      val serialized: Boolean,
      val deserialized: Boolean
  ) {
    abstract fun writeProperty(value: Any?): Boolean
    abstract fun write(writer: JsonWriter, value: Any?)
    abstract fun read(reader: JsonReader, value: Any?)
  }

  public class Adapter<T>(
      val constructor: () -> T,
      val boundProperties: Map<String, BoundProperty>
  ) : TypeAdapter<T>() {

    override fun read(reader: stream.JsonReader): T {
      if (reader.peek() == JsonToken.NULL) {
        reader.nextNull()
        return null
      }

      val instance: T = constructor()

      try {
        reader.beginObject()
        while (reader.hasNext()) {
          val name = reader.nextName()
          val property = boundProperties[name]
          if (property == null || !property.deserialized) {
            reader.skipValue()
          } else {
            property.read(reader, instance)
          }
        }
      } catch (e: IllegalStateException) {
        throw JsonSyntaxException(e)
      } catch (e: IllegalAccessException) {
        throw AssertionError(e)
      }
      reader.endObject()
      return instance
    }

    override fun write(out: stream.JsonWriter, value: T) {
      if (value == null) {
        out.nullValue()
        return
      }

      out.beginObject()
      try {
        for (boundProperty in boundProperties.values()) {
          if (boundProperty.writeProperty(value)) {
            out.name(boundProperty.name)
            boundProperty.write(out, value)
          }
        }
      } catch (e: IllegalAccessException) {
        throw AssertionError(e)
      }
      out.endObject()
    }

  }
}