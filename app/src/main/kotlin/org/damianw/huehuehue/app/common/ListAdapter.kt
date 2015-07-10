package org.damianw.huehuehue.app.common

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import org.damianw.huehuehue.util.mainThread

/**
 * @author Damian Wieczorek {@literal <damian@farmlogs.com>}
 * @since 7/14/15
 * (C) 2015 Damian Wieczorek
 */
class ListAdapter<T>(
    val newView: (ViewGroup?, Int) -> View,
    val bind: T.(ViewHolder) -> Unit,
    items: List<T> = listOf()
) : RecyclerView.Adapter<ViewHolder>() {

  var items = items
    set(items) {
      $items = items
      notifyDataSetChanged()
    }

  override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder?
      = ViewHolder(newView(parent, viewType))

  override fun onBindViewHolder(holder: ViewHolder, position: Int)
      = items[position].bind(holder)

  override fun getItemCount(): Int = items.size()

}