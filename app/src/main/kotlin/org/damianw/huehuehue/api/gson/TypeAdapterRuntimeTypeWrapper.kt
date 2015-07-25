package org.damianw.huehuehue.api.gson

import com.google.gson.Gson
import com.google.gson.TypeAdapter
import com.google.gson.reflect.TypeToken
import com.google.gson.stream.JsonReader
import com.google.gson.stream.JsonWriter
import org.damianw.huehuehue.util.token
import java.lang.reflect.Type
import java.lang.reflect.TypeVariable

/**
 * @author Damian Wieczorek {@literal <damian@farmlogs.com>}
 * @since 7/22/15
 * (C) 2015 Damian Wieczorek
 */
class TypeAdapterRuntimeTypeWrapper<T>(val gson: Gson, val delegate: TypeAdapter<T>, val type: Type) : TypeAdapter<T>() {

  override fun read(reader: JsonReader?): T = delegate.read(reader)

  suppress("UNCHECKED_CAST")
  override fun write(out: JsonWriter?, value: T) {
    // Order of preference for choosing type adapters
    // First preference: a type adapter registered for the runtime type
    // Second preference: a type adapter registered for the declared type
    // Third preference: reflective type adapter for the runtime type (if it is a sub class of the declared type)
    // Fourth preference: reflective type adapter for the declared type
    val runtimeType = getRuntimeTypeIfMoreSpecific(type, value)
    val chosen = if (runtimeType == type) delegate else gson.getAdapter(runtimeType.token()) as TypeAdapter<T> let {
      when {
        it !is KotlinReflectiveTypeAdapterFactory.Adapter -> it
        delegate !is KotlinReflectiveTypeAdapterFactory.Adapter -> delegate
        else -> it
      }
    }
    chosen.write(out, value)
  }

  private fun getRuntimeTypeIfMoreSpecific(type: Type, value: Any?) =
      if (type == javaClass<Object>() || type is TypeVariable<*> || type is Class<*>) value?.javaClass ?: type
      else type

}