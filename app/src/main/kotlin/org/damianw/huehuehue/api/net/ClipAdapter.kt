package org.damianw.huehuehue.api.net

import org.damianw.huehuehue.api.model.Config
import org.damianw.huehuehue.api.model.Light
import org.damianw.huehuehue.util.RetrofitCallback
import org.damianw.huehuehue.util.rfCallback
import retrofit.Callback
import retrofit.http.Body
import retrofit.http.GET
import retrofit.http.POST
import retrofit.http.Path

/**
 * @author Damian Wieczorek {@literal <damian@farmlogs.com>}
 * @since 7/8/15
 * (C) 2015 Damian Wieczorek
 */
public interface ClipAdapter {

  companion object {
    val USERNAME = "username"
  }

  GET("/api/{$USERNAME}") fun getUser(Path(USERNAME) username: String, callback: Callback<*>)
  fun getUser(username: String, callback: RetrofitCallback<*>.() -> Unit) = getUser(username, rfCallback<Any?>(callback))

  GET("/api/{$USERNAME}/lights") fun getLights(Path(USERNAME) username: String, callback: Callback<in List<Light>>)
  fun getLights(username: String, callback: RetrofitCallback<in List<Light>>.() -> Unit) = getLights(username, rfCallback<List<Light>>(callback))

  GET("/api/{$USERNAME}/config") fun getConfig(Path(USERNAME) username: String, callback: Callback<in Config>)
  fun getConfig(username: String, callback: RetrofitCallback<in Config>.() -> Unit) = getConfig(username, rfCallback<Config>(callback))

  POST("/api") private fun createUser(Body createUser: CreateUser)
  fun createUser(devicetype: String) = createUser(CreateUser(devicetype))


  private data class CreateUser (val devicetype: String)

}