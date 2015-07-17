package org.damianw.huehuehue.app

import android.app.Activity
import android.app.Fragment
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import org.damianw.huehuehue.R
import org.damianw.huehuehue.api.Bridge
import org.damianw.huehuehue.api.net.MockApi
import kotlinx.android.synthetic.activity_main.*
import org.damianw.huehuehue.util.*

/**
 * @author Damian Wieczorek {@literal <damian@farmlogs.com>}
 * @since 7/8/15
 * (C) 2015 Damian Wieczorek
 */
class MainActivity : AppCompatActivity(), BridgeProvider {

  override val bridge: Bridge = Bridge(MockApi(), "3c86d652d4ea867c4a59311dbbd793", "http://10.0.0.141".toUri())

  override fun onCreate(savedInstanceState: Bundle?) {
    super<AppCompatActivity>.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)
    setSupportActionBar(toolbar)

    if (savedInstanceState == null) replace(LightsFragment())

    navigationView.onSelected {
      it.checked = true
      drawerLayout.closeDrawers()
      replace(when (it.itemId) {
        R.id.navigation_item_lights -> LightsFragment()
        R.id.navigation_item_groups -> GroupsFragment()
        else -> null
      })
    }
  }

  private fun replace(fragment: Fragment?): Boolean {
    if (fragment == null) return false;
    fragmentManager.beginTransaction()
        .replace(R.id.content, fragment)
        .commit()
    return true;
  }

}