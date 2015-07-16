package org.damianw.huehuehue.util

import retrofit.*
import retrofit.client.Client
import retrofit.converter.Converter
import java.util.concurrent.Executor

/**
 * @author Damian Wieczorek {@literal <damian@farmlogs.com>}
 * @since 7/15/15
 * (C) 2015 Damian Wieczorek
 */

inline fun <reified T> RestAdapter.create() = create(javaClass<T>())

fun restAdapter(
    endpoint: String,
    client: Client? = null,
    httpExecutor: Executor? = null,
    callbackExecutor: Executor? = null,
    requestInterceptor: RequestInterceptor? = null,
    converter: Converter? = null,
    profiler: Profiler<*>? = null,
    errorHandler: ErrorHandler? = null,
    log: RestAdapter.Log? = null,
    logLevel: RestAdapter.LogLevel = RestAdapter.LogLevel.NONE
): RestAdapter {
  val builder = RestAdapter.Builder()
  if (client != null) builder.setClient(client)
  if (httpExecutor != null) builder.setExecutors(httpExecutor, callbackExecutor)
  if (requestInterceptor != null) builder.setRequestInterceptor(requestInterceptor)
  if (converter != null) builder.setConverter(converter)
  if (profiler != null) builder.setProfiler(profiler)
  if (errorHandler != null) builder.setErrorHandler(errorHandler)
  if (log != null) builder.setLog(log)
  return builder.setEndpoint(endpoint).setLogLevel(logLevel).build()
}