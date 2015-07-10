package org.damianw.huehuehue.util

import android.util.SparseArray

/**
 * @author Damian Wieczorek {@literal <damian@farmlogs.com>}
 * @since 7/13/15
 * (C) 2015 Damian Wieczorek
 */

inline fun <reified T: Any> sparseArray(
    inlineOptions(InlineOption.ONLY_LOCAL_RETURN) default: (Int) -> T
) = object: SparseArray<T>() {
  override fun get(key: Int): T = get(key, default(key))
}
