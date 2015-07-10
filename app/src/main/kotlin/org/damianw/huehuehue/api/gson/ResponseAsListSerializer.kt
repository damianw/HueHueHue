package org.damianw.huehuehue.api.gson

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import org.damianw.huehuehue.api.model.response.Response
import org.damianw.huehuehue.api.model.response.Status
import org.damianw.huehuehue.util.deserialize
import java.lang.reflect.Type

/**
 * @author Damian Wieczorek {@literal <damian@farmlogs.com>}
 * @since 7/15/15
 * (C) 2015 Damian Wieczorek
 */
open class ResponseAsListSerializer protected constructor(): JsonDeserializer<List<Response>> {

  companion object : ResponseAsListSerializer()

  override fun deserialize(json: JsonElement, typeOfT: Type, context: JsonDeserializationContext): List<Response>? {
    if (json.isJsonNull()) return null
    return json.getAsJsonArray().map {
      val item = it.getAsJsonObject()
      val statusName = item.entrySet().first().key
      val status = Status.first { it.name().toLowerCase() == statusName }
      Response(status, context.deserialize<Map<String, Any>>(item[statusName]))
    }
  }
}