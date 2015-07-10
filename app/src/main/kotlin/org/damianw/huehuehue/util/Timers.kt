package org.damianw.huehuehue.util

import java.util.*

/**
 * @author Damian Wieczorek {@literal <damian@farmlogs.com>}
 * @since 7/14/15
 * (C) 2015 Damian Wieczorek
 */

inline fun timerTask(inlineOptions(InlineOption.ONLY_LOCAL_RETURN) task: () -> Unit) = object : TimerTask() {
  override fun run() = task()
}

inline fun Timer.schedule(delay: Long, period: Long, inlineOptions(InlineOption.ONLY_LOCAL_RETURN) task: () -> Unit) = schedule(timerTask(task), delay, period)
inline fun Timer.schedule(date: Date, period: Long, inlineOptions(InlineOption.ONLY_LOCAL_RETURN) task: () -> Unit) = schedule(timerTask(task), date, period)
inline fun Timer.schedule(date: Date, inlineOptions(InlineOption.ONLY_LOCAL_RETURN) task: () -> Unit) = schedule(timerTask(task), date)
inline fun Timer.schedule(delay: Long, inlineOptions(InlineOption.ONLY_LOCAL_RETURN) task: () -> Unit) = schedule(timerTask(task), delay)
