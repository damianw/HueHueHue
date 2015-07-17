package org.damianw.huehuehue.api.net

import org.damianw.huehuehue.api.model.Config
import org.damianw.huehuehue.api.model.Group
import org.damianw.huehuehue.api.model.Light
import org.damianw.huehuehue.api.model.response.Response
import rx.Observable
import rx.lang.kotlin.toSingletonObservable
import java.util.*

/**
 * @author Damian Wieczorek {@literal <damian@farmlogs.com>}
 * @since 7/17/15
 * (C) 2015 Damian Wieczorek
 */
class MockApi : HueApi {

  private fun randomLight() = Light() let {
    it.name = UUID.randomUUID().toString()
    it.state = Light.State()
    it.id = UUID.randomUUID().hashCode()
    it.uniqueid = UUID.randomUUID().toString()
    it.manufacturername = UUID.randomUUID().toString()
    it.type = UUID.randomUUID().toString()
    it.modelid = UUID.randomUUID().toString()
    it.swversion = UUID.randomUUID().toString()
    it
  }

  override fun getLights(username: String): Observable<List<Light>> = (1..20).map { randomLight() }.toSingletonObservable()

  override fun getUser(username: String): Observable<Any?> = throw UnsupportedOperationException()

  override fun getConfig(username: String): Observable<Config> = throw UnsupportedOperationException()

  override fun getGroups(username: String): Observable<List<Group>> = throw UnsupportedOperationException()

  override fun getLight(username: String, id: Int): Observable<Light> = throw UnsupportedOperationException()

  override fun getGroup(username: String, id: Int): Observable<Group> = throw UnsupportedOperationException()

  override fun updateGroup(username: String, id: String, group: Group) = throw UnsupportedOperationException()

  override fun setName(username: String, id: Int, setName: SetName): Observable<List<Response>> = throw UnsupportedOperationException()

  override fun setState(username: String, id: Int, state: Light.State): Observable<List<Response>> = throw UnsupportedOperationException()

  override fun createUser(createUser: CreateUser): Observable<List<Response>> = throw UnsupportedOperationException()

}