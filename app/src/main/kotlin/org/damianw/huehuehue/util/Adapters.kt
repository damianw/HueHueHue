package org.damianw.huehuehue.util

import android.app.Fragment
import android.content.Context
import android.view.View
import org.damianw.huehuehue.app.common.ListAdapter
import org.damianw.huehuehue.app.common.ViewHolder
import org.jetbrains.anko.act
import org.jetbrains.anko.layoutInflater

/**
 * @author Damian Wieczorek {@literal <damian@farmlogs.com>}
 * @since 7/13/15
 * (C) 2015 Damian Wieczorek
 */

inline fun <reified ViewT : View, ItemT> Fragment.listAdapter(
    layoutId: Int,
    noinline bind: ItemT.(ViewHolder) -> Unit
) = ListAdapter({ container, type -> act.layoutInflater.inflate(layoutId, container, false) }, bind)