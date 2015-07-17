package org.damianw.huehuehue.api.net

import com.facebook.stetho.okhttp.StethoInterceptor
import com.squareup.okhttp.OkHttpClient
import org.damianw.huehuehue.api.annotation.reflective
import org.damianw.huehuehue.api.model.Config
import org.damianw.huehuehue.api.model.Group
import org.damianw.huehuehue.api.model.Light
import org.damianw.huehuehue.api.model.response.Response
import org.damianw.huehuehue.util.DEBUG
import retrofit.http.*
import rx.Observable

/**
 * @author Damian Wieczorek {@literal <damian@farmlogs.com>}
 * @since 7/8/15
 * (C) 2015 Damian Wieczorek
 */
public interface HueApi {

  companion object {
    val USERNAME = "username"
    val ID = "id"
    val CLIENT = OkHttpClient()

    init {
      if (DEBUG) {
        CLIENT.networkInterceptors().add(StethoInterceptor())
      }
    }
  }

  GET("/api/{$USERNAME}") fun getUser(Path(USERNAME) username: String): Observable<Any?>
  GET("/api/{$USERNAME}/config") fun getConfig(Path(USERNAME) username: String): Observable<Config>
  GET("/api/{$USERNAME}/lights") fun getLights(Path(USERNAME) username: String): Observable<List<Light>>
  GET("/api/{$USERNAME}/groups") fun getGroups(Path(USERNAME) username: String): Observable<List<Group>>
  GET("/api/{$USERNAME}/lights/{$ID}") fun getLight(Path(USERNAME) username: String, Path(ID) id: Int): Observable<Light>
  GET("/api/{$USERNAME}/groups/{$ID}") fun getGroup(Path(USERNAME) username: String, Path(ID) id: Int): Observable<Group>

  PUT("/api/{$USERNAME}/groups/{$ID}") fun updateGroup(Path(USERNAME) username: String, Path(ID) id: String, Body group: Group)
  PUT("/api/{$USERNAME}/lights/{$ID}") fun setName(Path(USERNAME) username: String, Path(ID) id: Int, Body setName: SetName): Observable<List<Response>>
  PUT("/api/{$USERNAME}/lights/{$ID}/state") fun setState(Path(USERNAME) username: String, Path(ID) id: Int, Body state: Light.State): Observable<List<Response>>


  POST("/api") fun createUser(Body createUser: CreateUser): Observable<List<Response>>

}