package org.damianw.huehuehue.app

import android.os.Bundle
import android.support.v4.widget.SwipeRefreshLayout
import android.view.View
import kotlinx.android.synthetic.fragment_lights.lightsView
import kotlinx.android.synthetic.fragment_lights.refreshLayout
import org.damianw.huehuehue.R
import org.damianw.huehuehue.api.model.Light
import org.damianw.huehuehue.app.lights.LightCardView
import org.damianw.huehuehue.util.*

/**
 * @author Damian Wieczorek {@literal <damian@farmlogs.com>}
 * @since 7/13/15
 * (C) 2015 Damian Wieczorek
 */
class LightsFragment : BridgeFragment(R.layout.fragment_lights), SwipeRefreshLayout.OnRefreshListener {

  val adapter = listAdapter<Light, LightCardView>(::verticalRecycler)

  override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
    lightsView.adapter = adapter
    refreshLayout.onRefreshListener = this
  }

  override fun onResume() {
    super<BridgeFragment>.onResume()
    refreshLayout.refreshing = true
    onRefresh()
  }

  override fun onRefresh() = bridge.lights.boundTo(this).subscribe {
    adapter.items = it
    refreshLayout.refreshing = false
  } let { Unit }

}