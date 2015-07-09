package org.damianw.huehuehue.api.gson

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import org.damianw.huehuehue.api.model.Light
import java.lang.reflect.Type

/**
 * @author Damian Wieczorek {@literal <damian@farmlogs.com>}
 * @since 7/8/15
 * (C) 2015 Damian Wieczorek
 */
class LightListSerializer : JsonDeserializer<List<Light>> {
  override fun deserialize(json: JsonElement, typeOfT: Type, context: JsonDeserializationContext): List<Light>? =
      if (json.isJsonNull())
        null
      else
        json.getAsJsonObject().entrySet().map {
          val light = it.value.getAsJsonObject()
          light.addProperty(Light::id.name, it.key)
          context.deserialize<Light>(light, javaClass<Light>())
        }.toList()
}