package org.damianw.huehuehue.app

import android.app.Service
import android.content.Intent
import android.graphics.PointF
import android.net.Uri
import android.os.Binder
import android.os.Handler
import android.provider.Settings
import com.github.salomonbrys.kotson.registerTypeAdapter
import com.google.gson.GsonBuilder
import org.damianw.huehuehue.api.gson.*
import org.damianw.huehuehue.api.model.Config
import org.damianw.huehuehue.api.model.Group
import org.damianw.huehuehue.api.model.Light
import org.damianw.huehuehue.api.net.ClipAdapter
import org.damianw.huehuehue.util.*
import retrofit.RestAdapter
import retrofit.client.OkClient
import retrofit.converter.GsonConverter
import rx.lang.kotlin.subscribeWith
import java.util.TimeZone
import java.util.Timer
import kotlin.properties.Delegates

/**
 * @author Damian Wieczorek {@literal <damian@farmlogs.com>}
 * @since 7/8/15
 * (C) 2015 Damian Wieczorek
 */
class HueService : Service() {

  companion object {
    val REFRESH_PERIOD = 10000L
    val USERNAME = "3c86d652d4ea867c4a59311dbbd793" // TODO
    val GSON = GsonBuilder()
        .registerTypeAdapterFactory(KotlinReflectiveTypeAdapterFactory)
        .registerTypeAdapter<PointF>(PointFSerializer)
        .registerTypeAdapter<Uri>(UriSerializer)
        .registerTypeAdapter<TimeZone>(TimeZoneSerializer)
        .registerIndexedEnum(Config.SoftwareUpdate.State)
        .registerNamedEnum(Group.Type, String::toCamelCase)
        .registerEnum(Light.Alert)
        .registerEnum(Light.ColorMode)
        .registerEnum(Light.Effect)
        .typeInListAsObject<Light>()
        .typeInListAsObject<Group>()
        .setDateFormat("yyyy-MM-dd'T'hh:mm:ss")
        .create()
    val ADAPTER = RestAdapter.Builder()
        .setEndpoint("http://10.0.0.141") // TODO
        .setConverter(GsonConverter(GSON))
        .setClient(OkClient(ClipAdapter.CLIENT))
        .build()
    val API = ADAPTER.create(javaClass<ClipAdapter>())
  }

  val deviceId: String by Delegates.lazy {
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
    timer.cancel()
  }

  fun checkConnected() {
    API.getConfig(USERNAME).subscribeWith {
      onNext {
        d(it.toString())
      }
      onError { e("Error getting lights", it) }
    }
  }

  inner class HueBinder : Binder() {

  }

  interface Listener {
    fun onLightsUpdated(lights: List<Light>)
  }

}