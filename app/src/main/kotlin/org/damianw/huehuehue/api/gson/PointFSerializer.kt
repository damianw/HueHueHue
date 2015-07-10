package org.damianw.huehuehue.api.gson

import android.graphics.PointF
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
open class PointFSerializer protected constructor() : JsonSerializer<PointF>, JsonDeserializer<PointF>, TypeAdapter<PointF>() {

  companion object : PointFSerializer()

  override fun write(out: JsonWriter, value: PointF?) {
    if (value == null) {
      out.nullValue()
      return
    }
    out.beginArray()
    out.value(value.x)
    out.value(value.y)
    out.endArray()
  }

  override fun read(reader: JsonReader): PointF? {
    if (reader.peek() == JsonToken.NULL) return null
    reader.beginArray()
    val x = reader.nextDouble().toFloat()
    val y = reader.nextDouble().toFloat()
    reader.endArray()
    return PointF(x, y)
  }

  override fun serialize(src: PointF?, typeOfSrc: Type, context: JsonSerializationContext): JsonElement {
    if (src == null) return JsonNull.INSTANCE
    val result = JsonArray()
    result.add(JsonPrimitive(src.x))
    result.add(JsonPrimitive(src.y))
    return result
  }

  override fun deserialize(json: JsonElement, typeOfT: Type, context: JsonDeserializationContext): PointF? =
      if (json.isJsonNull()) null else json.getAsJsonArray() let {
        PointF(it.get(0).getAsFloat(), it.get(1).getAsFloat())
      }

}