package org.damianw.huehuehue.app.common

import android.view.View

/**
 * @author Damian Wieczorek {@literal <damian@farmlogs.com>}
 * @since 7/25/15
 * (C) 2015 Damian Wieczorek
 */
interface Bindable<T> {
  fun bind(item: T)
}