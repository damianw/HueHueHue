package org.damianw.huehuehue.api.gson

import com.google.gson.*
import com.google.gson.stream.JsonReader
import com.google.gson.stream.JsonToken
import com.google.gson.stream.JsonWriter
import java.lang.reflect.Type

/**
 * @author Damian Wieczorek {@literal <damian@farmlogs.com>}
 * @since 7/8/15
 * (C) 2015 Damian Wieczorek
 */
class NamedEnumSerializer<T : Enum<*>?>(values: Array<T>,
                                        val toSerialName: String.() -> String = { toLowerCase() }
) : JsonSerializer<T>, JsonDeserializer<T>, TypeAdapter<T>() {

  companion object {
    fun <T : Enum<T>> invoke(values: Array<T>,
                             toSerialName: String.() -> String = { toLowerCase() })
        = NamedEnumSerializer(values, toSerialName)
  }

  private val values = values.toMap { it.name().toLowerCase() }

  override fun serialize(src: T, typeOfSrc: Type, context: JsonSerializationContext): JsonElement
      = if (src == null) JsonNull.INSTANCE else JsonPrimitive(src.name().toSerialName())

  override fun deserialize(json: JsonElement, typeOfT: Type, context: JsonDeserializationContext): T?
      = if (json.isJsonNull()) null else values[json.getAsString()]

  override fun write(out: JsonWriter, value: T) {
    if (value == null) out.nullValue() else out.value(value.name().toSerialName())
  }

  override fun read(reader: JsonReader): T?
      = if (reader.peek() == JsonToken.NULL) null else values[reader.nextString()]

}