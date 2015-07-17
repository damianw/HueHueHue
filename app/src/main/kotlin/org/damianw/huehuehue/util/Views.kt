package org.damianw.huehuehue.util

import android.content.Context
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.RecyclerView
import android.view.View

/**
 * @author Damian Wieczorek {@literal <damian@farmlogs.com>}
 * @since 7/13/15
 * (C) 2015 Damian Wieczorek
 */

var RecyclerView.adapter: RecyclerView.Adapter<*>?
  get() = getAdapter()
  set(value: RecyclerView.Adapter<*>?) = setAdapter(value)

var RecyclerView.layoutManager: RecyclerView.LayoutManager?
  get() = getLayoutManager()
  set(value: RecyclerView.LayoutManager?) = setLayoutManager(value)

var SwipeRefreshLayout.refreshing: Boolean
  get() = isRefreshing()
  set(refreshing) = setRefreshing(refreshing)

var SwipeRefreshLayout.onRefreshListener: SwipeRefreshLayout.OnRefreshListener?
  get() = null
  set(listener) = setOnRefreshListener(listener)

inline fun <reified T : View> construct(context: Context) =
    javaClass<T>().getDeclaredConstructor(javaClass<Context>()).newInstance(context)

inline fun <reified T : View> constructor(context: Context) = { construct<T>(context) }