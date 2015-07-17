package org.damianw.huehuehue.app

import android.app.Activity
import android.support.annotation.LayoutRes
import org.damianw.huehuehue.api.Bridge
import org.damianw.huehuehue.util.notNull

/**
 * @author Damian Wieczorek {@literal <damian@farmlogs.com>}
 * @since 7/16/15
 * (C) 2015 Damian Wieczorek
 */
open class BridgeFragment(LayoutRes layoutId: Int) : LayoutFragment(layoutId) {

  var bridge: Bridge by notNull()

  override fun onAttach(activity: Activity?) {
    super.onAttach(activity)
    bridge = (activity as BridgeProvider).bridge
  }

}