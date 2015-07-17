package org.damianw.huehuehue.api

import android.graphics.PointF
import android.net.Uri
import com.google.gson.GsonBuilder
import org.damianw.huehuehue.api.gson.*
import org.damianw.huehuehue.api.model.*
import org.damianw.huehuehue.api.model.response.Response
import org.damianw.huehuehue.api.model.response.Status
import org.damianw.huehuehue.api.net.HueApi
import org.damianw.huehuehue.api.net.SetName
import org.damianw.huehuehue.util.*
import retrofit.RestAdapter
import retrofit.client.OkClient
import retrofit.converter.GsonConverter
import rx.Observable
import rx.Scheduler
import rx.android.schedulers.AndroidSchedulers
import java.util.TimeZone

/**
 * @author Damian Wieczorek {@literal <damian@farmlogs.com>}
 * @since 7/15/15
 * (C) 2015 Damian Wieczorek
 */
class Bridge(api: HueApi? = null, val username: String, val uri: Uri, val scheduler: Scheduler = AndroidSchedulers.mainThread()) {

  companion object {
    val GSON = GsonBuilder()
        .registerTypeAdapterFactory(KotlinReflectiveTypeAdapterFactory)
        .registerTypeAdapter<PointF>(PointFSerializer)
        .registerTypeAdapter<Uri>(UriSerializer)
        .registerTypeAdapter<TimeZone>(TimeZoneSerializer)
        .registerIndexedEnum(Config.SoftwareUpdate.State)
        .registerNamedEnum(Group.Type, String::toCamelCase)
        .registerNamedEnum(Alert)
        .registerNamedEnum(Light.ColorMode)
        .registerNamedEnum(Light.Effect)
        .registerNamedEnum(Status, { toLowerCase() })
        .registerTypeAdapter<List<Light>>(InjectIdSerializer(javaClass<Light>()))
        .registerTypeAdapter<List<Group>>(InjectIdSerializer(javaClass<Group>()))
        .registerTypeAdapter<List<Response>>(ResponseAsListSerializer)
        .setDateFormat("yyyy-MM-dd'T'hh:mm:ss")
        .create()
  }

  private val adapter = restAdapter(
      endpoint = uri.toString(),
      converter = GsonConverter(GSON),
      client = OkClient(HueApi.CLIENT)
  )

  private val api = api ?: adapter.create<HueApi>()

  val config: Observable<Config> get() = api.getConfig(username).observeOn(scheduler)
  val lights: Observable<List<Light>> get() = api.getLights(username).observeOn(scheduler)
  val groups: Observable<List<Group>> get() = api.getGroups(username).observeOn(scheduler)
  fun light(id: Int): Observable<Light> = api.getLight(username, id).observeOn(scheduler)
  fun group(id: Int): Observable<Group> = api.getGroup(username, id).observeOn(scheduler)

  fun setName(light: Light, name: String) = api.setName(username, light.id, SetName(name)).observeOn(scheduler)

}