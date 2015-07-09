package org.damianw.huehuehue.api.net

import com.facebook.stetho.okhttp.StethoInterceptor
import com.squareup.okhttp.OkHttpClient
import org.damianw.huehuehue.api.model.Config
import org.damianw.huehuehue.api.model.Light
import retrofit.http.Body
import retrofit.http.GET
import retrofit.http.POST
import retrofit.http.Path
import rx.Observable

/**
 * @author Damian Wieczorek {@literal <damian@farmlogs.com>}
 * @since 7/8/15
 * (C) 2015 Damian Wieczorek
 */
public interface ClipAdapter {

  companion object {
    val USERNAME = "username"
    val CLIENT = OkHttpClient()

    init {
      CLIENT.networkInterceptors().add(StethoInterceptor())
    }
  }

  GET("/api/{$USERNAME}") fun getUser(Path(USERNAME) username: String): Observable<Any?>
  GET("/api/{$USERNAME}/lights") fun getLights(Path(USERNAME) username: String): Observable<List<Light>>
  GET("/api/{$USERNAME}/config") fun getConfig(Path(USERNAME) username: String): Observable<Config>

  POST("/api") private fun createUser(Body createUser: CreateUser)
  fun createUser(devicetype: String) = createUser(CreateUser(devicetype))


  private data class CreateUser (val devicetype: String)

}