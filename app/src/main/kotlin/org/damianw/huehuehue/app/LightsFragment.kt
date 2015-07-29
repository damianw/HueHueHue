package org.damianw.huehuehue.app

import android.os.Bundle
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import kotlinx.android.synthetic.fragment_lights.lightsView
import kotlinx.android.synthetic.fragment_lights.refreshLayout
import org.damianw.huehuehue.R
import org.damianw.huehuehue.api.model.Light
import org.damianw.huehuehue.app.common.ListAdapter
import org.damianw.huehuehue.app.lights.LightCardView
import org.damianw.huehuehue.util.*
import org.jetbrains.anko.ctx
import org.jetbrains.anko.layoutParams
import org.jetbrains.anko.text

/**
 * @author Damian Wieczorek {@literal <damian@farmlogs.com>}
 * @since 7/13/15
 * (C) 2015 Damian Wieczorek
 */
class LightsFragment : BridgeFragment(R.layout.fragment_lights), SwipeRefreshLayout.OnRefreshListener {

  val adapter: ListAdapter<Light> = listAdapter<Light> {
    bindableView<LightCardView> {
      layoutParams = RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
    }
    onClick {
      alertDialog(ctx) {
        titleResource = R.string.light_name
        val editText = EditText(ctx)
        view = editText
        negativeButton(android.R.string.cancel)
        positiveButton(android.R.string.yes) {
          dismiss()
          bridge.setName(this@onClick, editText.text).boundTo(this@LightsFragment).subscribe {
            refresh()
          }
        }
      }
    }
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    lightsView.adapter = adapter
    refreshLayout.onRefreshListener = this
  }

  override fun onResume() {
    super<BridgeFragment>.onResume()
    refreshLayout.refreshing = true
    onRefresh()
  }

  private fun refresh() = onRefresh() let {
    refreshLayout.refreshing = true
  }

  override fun onRefresh() = bridge.lights.boundTo(this).subscribe {
    adapter.items = it
    refreshLayout.refreshing = false
  } let { Unit }

}