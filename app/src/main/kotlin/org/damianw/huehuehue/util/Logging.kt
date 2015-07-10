package org.damianw.huehuehue.util

import android.util.Log

/**
 * @author Damian Wieczorek {@literal <damian@farmlogs.com>}
 * @since 7/14/15
 * (C) 2015 Damian Wieczorek
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
