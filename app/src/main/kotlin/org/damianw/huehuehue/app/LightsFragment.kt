package org.damianw.huehuehue.app

import android.app.Activity
import android.os.Bundle
import android.support.v4.widget.SwipeRefreshLayout
import android.view.View
import android.widget.TextView
import kotlinx.android.synthetic.fragment_lights.lightsView
import kotlinx.android.synthetic.fragment_lights.refreshLayout
import org.damianw.huehuehue.R
import org.damianw.huehuehue.api.Bridge
import org.damianw.huehuehue.api.model.Light
import org.damianw.huehuehue.util.adapter
import org.damianw.huehuehue.util.listAdapter
import org.damianw.huehuehue.util.onRefreshListener
import org.damianw.huehuehue.util.refreshing
import org.jetbrains.anko.text
import java.util.Timer
import kotlin.properties.Delegates

/**
 * @author Damian Wieczorek {@literal <damian@farmlogs.com>}
 * @since 7/13/15
 * (C) 2015 Damian Wieczorek
 */
class LightsFragment : BridgeFragment(R.layout.fragment_lights), SwipeRefreshLayout.OnRefreshListener {

  val adapter = listAdapter<View, Light>(R.layout.cell_light) {
    it.get<TextView>(R.id.lightName).text = name
    it.get<TextView>(R.id.lightId).text = id.toString()
  }

  override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
    lightsView.adapter = adapter
    refreshLayout.onRefreshListener = this
  }

  override fun onResume() {
    super<BridgeFragment>.onResume()
    refreshLayout.refreshing = true
    onRefresh()
  }

  override fun onRefresh() = bridge.lights.subscribe {
    adapter.items = it
    refreshLayout.refreshing = false
  } let { Unit }

}