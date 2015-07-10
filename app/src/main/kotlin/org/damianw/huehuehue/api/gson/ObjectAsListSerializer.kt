package org.damianw.huehuehue.api.gson

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import org.damianw.huehuehue.api.model.Identifiable
import java.lang.reflect.Type
import kotlin.reflect.KClass

/**
 * @author Damian Wieczorek {@literal <damian@farmlogs.com>}
 * @since 7/8/15
 * (C) 2015 Damian Wieczorek
 */
class ObjectAsListSerializer<T : Identifiable>(val type: KClass<out T>) : JsonDeserializer<List<Identifiable>> {

  private companion object {
    val ID = Identifiable::id.name
  }

  override fun deserialize(json: JsonElement, typeOfT: Type, context: JsonDeserializationContext): List<Identifiable>? =
      if (json.isJsonNull())
        null
      else
        json.getAsJsonObject().entrySet().map {
          val item = it.value.getAsJsonObject()
          item.addProperty(ID, it.key)
          context.deserialize<Identifiable>(item, type)
        }.toList()
}