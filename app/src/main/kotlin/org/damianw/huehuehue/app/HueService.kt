package org.damianw.huehuehue.app

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.Handler
import android.provider.Settings
import org.damianw.huehuehue.api.model.Light
import org.damianw.huehuehue.api.net.ClipAdapter
import org.damianw.huehuehue.util.i
import org.damianw.huehuehue.util.schedule
import retrofit.RestAdapter
import retrofit.RetrofitError
import java.util.Timer

/**
 * @author Damian Wieczorek {@literal <damian@farmlogs.com>}
 * @since 7/8/15
 * (C) 2015 Damian Wieczorek
 */
class HueService : Service() {

  companion object {
    val REFRESH_PERIOD = 10000L
    val ADAPTER = RestAdapter.Builder()
        .setEndpoint("10.1.10.141") // TODO
        .build()
    val API = ADAPTER.create(javaClass<ClipAdapter>())
  }

  val deviceId: String by lazy {
    Settings.Secure.getString(getContentResolver(), Settings.Secure.ANDROID_ID)
  }

  val timer = Timer()
  val handler = Handler()
  val binder = HueBinder()

  override fun onBind(intent: Intent): HueBinder = binder

  override fun onCreate() {
    super.onCreate()
    timer.schedule(0, REFRESH_PERIOD) {
      checkConnected()
    }
  }

  override fun onDestroy() {
    super.onDestroy()
  }

  fun checkConnected() {
    try {
      i("User: ${API.get}")
    } catch (e: RetrofitError) {

    }
  }

  inner class HueBinder : Binder() {

  }

  interface Listener {
    fun onLightsUpdated(lights: List<Light>)
  }

}