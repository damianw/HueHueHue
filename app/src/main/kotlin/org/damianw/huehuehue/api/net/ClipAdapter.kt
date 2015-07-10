package org.damianw.huehuehue.api.net

import com.facebook.stetho.okhttp.StethoInterceptor
import com.squareup.okhttp.OkHttpClient
import org.damianw.huehuehue.api.model.Config
import org.damianw.huehuehue.api.model.Group
import org.damianw.huehuehue.api.model.Light
import retrofit.http.*
import rx.Observable

/**
 * @author Damian Wieczorek {@literal <damian@farmlogs.com>}
 * @since 7/8/15
 * (C) 2015 Damian Wieczorek
 */
public interface ClipAdapter {

  companion object {
    val USERNAME = "username"
    val ID = "id"
    val CLIENT = OkHttpClient()

    init {
      CLIENT.networkInterceptors().add(StethoInterceptor())
    }
  }

  GET("/api/{$USERNAME}") fun getUser(Path(USERNAME) username: String): Observable<Any?>
  GET("/api/{$USERNAME}/config") fun getConfig(Path(USERNAME) username: String): Observable<Config>
  GET("/api/{$USERNAME}/lights") fun getLights(Path(USERNAME) username: String): Observable<List<Light>>
  GET("/api/{$USERNAME}/groups") fun getGroups(Path(USERNAME) username: String): Observable<List<Group>>
  GET("/api/{$USERNAME}/lights/{$ID}") fun getLight(Path(USERNAME) username: String, Path(ID) id: String): Observable<Light>
  GET("/api/{$USERNAME}/groups/{$ID}") fun getGroup(Path(USERNAME) username: String, Path(ID) id: String): Observable<Group>
  PUT("/api/{$USERNAME}/groups/{$ID}") fun updateGroup(Path(USERNAME) username: String, Path(ID) id: String, Body update: UpdateGroup)

  POST("/api") private fun createUser(Body createUser: CreateUser)
  fun createUser(devicetype: String) = createUser(CreateUser(devicetype))


  private data class CreateUser (val devicetype: String)
  private data class UpdateGroup (val name: String? = null, lights: Collection<String>? = null) {
    val lights: Set<String>? = lights?.toSet()
  }

}