package org.damianw.huehuehue.util

import com.google.gson.GsonBuilder
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonElement
import com.google.gson.reflect.TypeToken
import org.damianw.huehuehue.api.gson.CodedEnumSerializer
import org.damianw.huehuehue.api.gson.NamedEnumSerializer
import java.lang.reflect.Type

/**
 * @author Damian Wieczorek {@literal <damian@farmlogs.com>}
 * @since 7/14/15
 * (C) 2015 Damian Wieczorek
 */

inline fun <reified TokenT : Any> typeToken(): Type = object : TypeToken<TokenT>() {}.getType()

inline fun <reified TT> JsonDeserializationContext.deserialize(json: JsonElement) = deserialize<TT>(json, javaClass<TT>())

inline fun <reified TypeT> GsonBuilder.registerTypeAdapter(adapter: Any)
    = registerTypeAdapter(typeToken<TypeT>(), adapter)

inline fun <reified TT : Enum<TT>> GsonBuilder.registerNamedEnum(values: Collection<TT>)
    = registerTypeAdapter<TT>(NamedEnumSerializer(values.toTypedArray()))

inline fun <reified TT : Enum<TT>> GsonBuilder.registerNamedEnum(values: Collection<TT>,
                                                                 noinline toSerialName: String.() -> String)
    = registerTypeAdapter<TT>(NamedEnumSerializer(values.toTypedArray(), toSerialName))

inline fun <reified TT : Enum<TT>> GsonBuilder.registerCodedEnum(values: Map<Int, TT>)
    = registerTypeAdapter<TT>(CodedEnumSerializer(values))

inline fun <reified TT : Enum<TT>> GsonBuilder.registerIndexedEnum(values: Collection<TT>)
    = registerTypeAdapter<TT>(CodedEnumSerializer(values.withIndex().toMap()))

// TODO: begin using once kotlin fixes inline reflection bugs
//inline fun <reified TT : Identifiable> GsonBuilder.registerIdMap()
//    = registerTypeAdapter<Map<String, TT>>(InjectIdSerializer(javaClass<TT>()))
