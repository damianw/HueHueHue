package org.damianw.huehuehue.api.gson

import com.google.gson.*
import com.google.gson.stream.JsonReader
import com.google.gson.stream.JsonToken
import com.google.gson.stream.JsonWriter
import java.lang.reflect.Type
import java.util.*

/**
 * @author Damian Wieczorek {@literal <damian@farmlogs.com>}
 * @since 7/9/15
 * (C) 2015 Damian Wieczorek
 */
class TimeZoneSerializer : JsonSerializer<TimeZone>, JsonDeserializer<TimeZone>, TypeAdapter<TimeZone>() {

  override fun serialize(src: TimeZone?, typeOfSrc: Type, context: JsonSerializationContext): JsonElement?
      = if (src == null) JsonNull.INSTANCE else JsonPrimitive(src.getDisplayName()) // TODO this is wrong

  override fun deserialize(json: JsonElement, typeOfT: Type, context: JsonDeserializationContext): TimeZone?
      = if (json.isJsonNull()) null else TimeZone.getTimeZone(json.getAsString())

  override fun write(out: JsonWriter, value: TimeZone?) {
    if (value == null) out.nullValue() else out.value(value.getDisplayName()) // TODO this is wrong
  }

  override fun read(reader: JsonReader): TimeZone?
      = if (reader.peek() == JsonToken.NULL) null else TimeZone.getTimeZone(reader.nextString())

}