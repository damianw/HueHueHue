package org.damianw.huehuehue.util

import android.os.Handler
import android.os.Looper
import org.damianw.huehuehue.BuildConfig

/**
 * @author Damian Wieczorek {@literal <damian@farmlogs.com>}
 * @since 7/14/15
 * (C) 2015 Damian Wieczorek
 */

val MAIN_HANDLER = Handler(Looper.getMainLooper())

val DEBUG = BuildConfig.DEBUG

fun Any?.mainThread(function: () -> Unit) = MAIN_HANDLER.post(function)

inline fun <T> T.with(then: (T) -> Unit) = then(this) let { this }
