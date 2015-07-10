package org.damianw.huehuehue.api.gson

import android.net.Uri
import com.google.gson.*
import com.google.gson.stream.JsonReader
import com.google.gson.stream.JsonToken
import com.google.gson.stream.JsonWriter
import java.lang.reflect.Type

/**
 * @author Damian Wieczorek {@literal <damian@farmlogs.com>}
 * @since 7/9/15
 * (C) 2015 Damian Wieczorek
 */
open class UriSerializer protected constructor() : JsonSerializer<Uri>, JsonDeserializer<Uri>, TypeAdapter<Uri>() {

  companion object : UriSerializer()

  override fun serialize(src: Uri?, typeOfSrc: Type, context: JsonSerializationContext): JsonElement?
      = if (src == null) JsonNull.INSTANCE else JsonPrimitive(src.toString())

  override fun deserialize(json: JsonElement, typeOfT: Type, context: JsonDeserializationContext): Uri?
      = if (json.isJsonNull()) null else Uri.parse(json.getAsString())

  override fun write(out: JsonWriter, value: Uri?) {
    if (value == null) out.nullValue() else out.value(value.toString())
  }

  override fun read(reader: JsonReader): Uri?
      = if (reader.peek() == JsonToken.NULL) null else Uri.parse(reader.nextString())

}