package org.damianw.huehuehue.util

import android.app.Fragment
import android.support.annotation.LayoutRes
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import org.damianw.huehuehue.app.common.Bindable
import org.damianw.huehuehue.app.common.ListAdapter
import org.damianw.huehuehue.app.common.ViewHolder
import org.jetbrains.anko.act
import org.jetbrains.anko.layoutInflater
import org.jetbrains.anko.layoutParams

/**
 * @author Damian Wieczorek {@literal <damian@farmlogs.com>}
 * @since 7/13/15
 * (C) 2015 Damian Wieczorek
 */

class ListAdapterBuilder<T> {
  var getView: (ViewGroup, Int) -> View = { viewGroup, type -> View(viewGroup.context )}
  var onItemClick: T.(ViewHolder) -> Unit = {}
  var bind: T.(ViewHolder) -> Unit = {}

  fun view(LayoutRes layoutId: Int) {
    this.getView = { viewGroup, type -> viewGroup.context.layoutInflater.inflate(layoutId, viewGroup)}
  }

  inline fun view<reified ViewT: View>(noinline with: ViewT.() -> Unit = {}) {
    this.getView = { viewGroup, type -> construct<ViewT>(viewGroup.context).with(with) }
  }

  inline fun bindableView<reified ViewT: View>(noinline with: ViewT.() -> Unit = {}) where ViewT: Bindable<in T> {
    this.getView = { viewGroup, type -> construct<ViewT>(viewGroup.context).with(with) }
    this.bind = { (it.itemView as Bindable<in T>).bind(this) }
  }

  fun view(getView: (ViewGroup, Int) -> View) {
    this.getView = getView
  }

  fun onClick(onItemClick: T.(ViewHolder) -> Unit) {
    this.onItemClick = onItemClick
  }

  fun onBind(bind: T.(ViewHolder) -> Unit) {
    this.bind = bind
  }
}

fun <ItemT> Fragment.listAdapter(init: ListAdapterBuilder<ItemT>.() -> Unit): ListAdapter<ItemT> {
  val builder = ListAdapterBuilder<ItemT>()
  builder.init()
  return ListAdapter(builder.getView, builder.bind, builder.onItemClick)
}

fun <ItemT> Fragment.listAdapter(
    layoutId: Int,
    noinline bind: ItemT.(ViewHolder) -> Unit
) = ListAdapter({ container, type -> act.layoutInflater.inflate(layoutId, container, false) }, bind)

inline fun <ItemT, reified ViewT : View> Fragment.listAdapter()
    where ViewT : Bindable<in ItemT> =
    ListAdapter<ItemT>({ c, t -> construct<ViewT>(act) }, { (it.itemView as ViewT).bind(this) })

inline fun <ItemT, reified ViewT : View> Fragment.listAdapter(
    inlineOptions(InlineOption.ONLY_LOCAL_RETURN) layoutParams: () -> RecyclerView.LayoutParams
) where ViewT : Bindable<in ItemT> =
    ListAdapter<ItemT>({ c, t ->
      construct<ViewT>(act) with { it.layoutParams = layoutParams() }
    }, { (it.itemView as ViewT).bind(this) })
