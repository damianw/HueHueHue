package org.damianw.huehuehue.util

/**
 * @author Damian Wieczorek {@literal <damian@farmlogs.com>}
 * @since 7/14/15
 * (C) 2015 Damian Wieczorek
 */

fun <T> Iterable<IndexedValue<T>>.toMap(): Map<Int, T> = toMap({ it.index }, { it.value })
