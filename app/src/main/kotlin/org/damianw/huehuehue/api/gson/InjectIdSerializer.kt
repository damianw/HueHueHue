package org.damianw.huehuehue.api.gson

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import org.damianw.huehuehue.api.model.Identifiable
import java.lang.reflect.Type

/**
 * @author Damian Wieczorek {@literal <damian@farmlogs.com>}
 * @since 7/8/15
 * (C) 2015 Damian Wieczorek
 */
class InjectIdSerializer<T>(val type: Class<T>) : JsonDeserializer<List<T>> {

  private companion object {
    val ID = "id"
  }

  override fun deserialize(json: JsonElement, typeOfT: Type, context: JsonDeserializationContext): List<T>? =
      if (json.isJsonNull())
        null
      else
        json.getAsJsonObject().entrySet().map {
          val item = it.value.getAsJsonObject()
          val id = it.key.toInt()
          item.addProperty(ID, id)
          context.deserialize<T>(item, type)
        }
}