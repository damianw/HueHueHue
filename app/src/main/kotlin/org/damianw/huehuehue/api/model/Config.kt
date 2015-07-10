package org.damianw.huehuehue.api.model

import android.net.Uri
import java.util.*
import kotlin.properties.Delegates

/**
 * @author Damian Wieczorek {@literal <damian@farmlogs.com>}
 * @since 7/8/15
 * (C) 2015 Damian Wieczorek
 */
data class Config {

  var name: String by Delegates.notNull()
    private set
  var swupdate: SoftwareUpdate by Delegates.notNull()
    private set
  var whitelist: Map<String, Whitelist> by Delegates.notNull()
    private set
  var apiversion: String by Delegates.notNull()
    private set
  var swversion: String by Delegates.notNull()
    private set
  var proxyaddress: String by Delegates.notNull()
    private set
  var proxyport: Short by Delegates.notNull()
    private set
  var linkbutton: Boolean by Delegates.notNull()
    private set
  var ipaddress: String by Delegates.notNull()
    private set
  var mac: String by Delegates.notNull()
    private set
  var netmask: String by Delegates.notNull()
    private set
  var gateway: String by Delegates.notNull()
    private set
  var dhcp: Boolean by Delegates.notNull()
    private set
  var portalservices: String by Delegates.notNull()
    private set
  var UTC: Date by Delegates.notNull()
    private set
  var localtime: Date by Delegates.notNull()
    private set
  var timezone: TimeZone by Delegates.notNull()
    private set
  var zigbeechannel: Int by Delegates.notNull()
    private set

  data class SoftwareUpdate {

    var updatestate: State by Delegates.notNull()
      private set
    var url: Uri? = null
      private set
    var text: String? = null
      private set
    var notify: Boolean by Delegates.notNull()
      private set

    enum class State {
      NONE_AVAILABLE,
      DOWNLOADING,
      READY,
      INSTALLING;
      companion object : List<State> by values().asList()
    }

  }

  data class Whitelist {

    var lastUseDate: Date by Delegates.notNull()
      private set
    var createDate: Date by Delegates.notNull()
      private set
    var name: String by Delegates.notNull()
      private set

  }

}