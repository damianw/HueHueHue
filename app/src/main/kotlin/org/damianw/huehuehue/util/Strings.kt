package org.damianw.huehuehue.util

import android.net.Uri
import kotlin.text.Regex

/**
 * @author Damian Wieczorek {@literal <damian@farmlogs.com>}
 * @since 7/14/15
 * (C) 2015 Damian Wieczorek
 */

fun String.toCamelCase(): String = toLowerCase().split('_').map { it.capitalize() }.join()

fun CharSequence.toConstantCase() = Regex("([A-Z][a-z]*)").matchAll(this) let {
  it.map { it.value.toUpperCase() }.join("_")
}

fun String.toUri() = Uri.parse(this)
