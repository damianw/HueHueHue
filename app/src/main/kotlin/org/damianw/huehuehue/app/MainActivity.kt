package org.damianw.huehuehue.app

import android.app.Activity
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import org.jetbrains.anko.intent
import org.jetbrains.anko.intentFor

/**
 * @author Damian Wieczorek {@literal <damian@farmlogs.com>}
 * @since 7/8/15
 * (C) 2015 Damian Wieczorek
 */
class MainActivity : Activity(), ServiceConnection {

  var service: HueService.HueBinder? = null

  override fun onCreate(savedInstanceState: Bundle?) {
    super<Activity>.onCreate(savedInstanceState)
  }

  override fun onResume() {
    super<Activity>.onResume()
    bindService(intentFor<HueService>(), this, Context.BIND_AUTO_CREATE)
  }

  override fun onPause() {
    super<Activity>.onPause()
    unbindService(this)
    this.service = null
  }

  override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
    this.service = service as HueService.HueBinder?
  }

  override fun onServiceDisconnected(name: ComponentName?) {
    this.service = null
  }

}