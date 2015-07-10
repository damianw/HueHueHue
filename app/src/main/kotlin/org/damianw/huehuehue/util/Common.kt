package org.damianw.huehuehue.util

import android.os.Handler
import android.os.Looper
import kotlin.properties.Delegates
import kotlin.properties.ReadWriteProperty

/**
 * @author Damian Wieczorek {@literal <damian@farmlogs.com>}
 * @since 7/14/15
 * (C) 2015 Damian Wieczorek
 */

val MAIN_HANDLER = Handler(Looper.getMainLooper())

fun Any?.mainThread(function: () -> Unit) = MAIN_HANDLER.post(function)
