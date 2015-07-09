package org.damianw.huehuehue.util

import android.util.Log
import retrofit.Callback
import retrofit.RetrofitError
import retrofit.client.Response
import java.util.Date
import java.util.Timer
import java.util.TimerTask

/**
 * @author Damian Wieczorek {@literal <damian@farmlogs.com>}
 * @since 7/8/15
 * (C) 2015 Damian Wieczorek
 */

/*
* Logging
*/

fun Any.w(msg: String) = Log.w(this.javaClass.getSimpleName(), msg)
fun Any.w(tr: Throwable) = Log.w(this.javaClass.getSimpleName(), tr)
fun Any.w(msg: String, tr: Throwable) = Log.w(this.javaClass.getSimpleName(), msg, tr)

fun Any.d(msg: String) = Log.d(this.javaClass.getSimpleName(), msg)
fun Any.d(msg: String, tr: Throwable) = Log.d(this.javaClass.getSimpleName(), msg, tr)

fun Any.e(msg: String) = Log.e(this.javaClass.getSimpleName(), msg)
fun Any.e(msg: String, tr: Throwable) = Log.e(this.javaClass.getSimpleName(), msg, tr)

fun Any.i(msg: String) = Log.i(this.javaClass.getSimpleName(), msg)
fun Any.i(msg: String, tr: Throwable) = Log.i(this.javaClass.getSimpleName(), msg, tr)

/*
* Retrofit
*/

class RetrofitCallback<T> : Callback<T> {
  override fun success(t: T, response: Response?) = onSuccess?.invoke(t, response)
  override fun failure(error: RetrofitError?) = onFailure?.invoke(error)
  private var onSuccess: ((T, Response?) -> Unit)? = null
  private var onFailure: ((RetrofitError?) -> Unit)? = null
  fun success(onSuccess: (T, Response?) -> Unit) { this.onSuccess = onSuccess }
  fun failure(onFailure: (RetrofitError?) -> Unit) { this.onFailure = onFailure }
}

fun <T> rfCallback(init: RetrofitCallback<T>.() -> Unit): RetrofitCallback<T> {
  val callback = RetrofitCallback<T>()
  callback.init()
  return callback
}

/*
* Context
*/



/*
* Timer
*/

fun timerTask(task: () -> Unit) = object: TimerTask() {
  override fun run() = task()
}

fun Timer.schedule(delay: Long, period: Long, task: () -> Unit) = schedule(timerTask(task), delay, period)
fun Timer.schedule(date: Date, period: Long, task: () -> Unit) = schedule(timerTask(task), date, period)
fun Timer.schedule(date: Date, task: () -> Unit) = schedule(timerTask(task), date)
fun Timer.schedule(delay: Long, task: () -> Unit) = schedule(timerTask(task), delay)