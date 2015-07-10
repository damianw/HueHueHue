package org.damianw.huehuehue.app.common

import android.support.v7.widget.RecyclerView
import android.view.View
import org.damianw.huehuehue.util.sparseArray
import org.jetbrains.anko.find

/**
 * @author Damian Wieczorek {@literal <damian@farmlogs.com>}
 * @since 7/13/15
 * (C) 2015 Damian Wieczorek
 */
suppress("UNCHECKED_CAST")
class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
  val cache = sparseArray { itemView.find<View>(it) }
  fun <T : View> get(id: Int) = cache[id] as T
}
