package org.damianw.huehuehue.app.common

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import org.damianw.huehuehue.util.mainThread
import org.jetbrains.anko.onClick

/**
 * @author Damian Wieczorek {@literal <damian@farmlogs.com>}
 * @since 7/14/15
 * (C) 2015 Damian Wieczorek
 */
class ListAdapter<T>(
    val newView: (ViewGroup, Int) -> View,
    val bind: T.(ViewHolder) -> Unit,
    val onItemClick: T.(ViewHolder) -> Unit = {},
    items: List<T> = listOf()
) : RecyclerView.Adapter<ViewHolder>() {

  var items = items
    set(items) {
      $items = items
      notifyDataSetChanged()
    }

  suppress("UNCHECKED_CAST")
  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder? {
    val view = newView(parent, viewType)
    val viewHolder = ViewHolder(view)
    view.onClick { (viewHolder.item as T).onItemClick(viewHolder) }
    return viewHolder
  }

  override fun onBindViewHolder(holder: ViewHolder, position: Int) {
    val item = items[position]
    holder.item = item
    item.bind(holder)
  }

  override fun getItemCount(): Int = items.size()

}