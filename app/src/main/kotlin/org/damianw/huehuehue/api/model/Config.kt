package org.damianw.huehuehue.api.model

import android.net.Uri
import org.damianw.huehuehue.api.annotation.reflective
import org.damianw.huehuehue.util.notNull
import java.util.Date
import java.util.TimeZone

/**
 * @author Damian Wieczorek {@literal <damian@farmlogs.com>}
 * @since 7/8/15
 * (C) 2015 Damian Wieczorek
 */
reflective data class Config {

  var name: String by notNull(); private set
  var swupdate: SoftwareUpdate by notNull(); private set
  var whitelist: Map<String, Whitelist> by notNull(); private set
  var apiversion: String by notNull(); private set
  var swversion: String by notNull(); private set
  var proxyaddress: String by notNull(); private set
  var proxyport: Short by notNull(); private set
  var linkbutton: Boolean by notNull(); private set
  var ipaddress: String by notNull(); private set
  var mac: String by notNull(); private set
  var netmask: String by notNull(); private set
  var gateway: String by notNull(); private set
  var dhcp: Boolean by notNull(); private set
  var portalservices: String by notNull(); private set
  var UTC: Date by notNull(); private set
  var localtime: Date by notNull(); private set
  var timezone: TimeZone by notNull(); private set
  var zigbeechannel: Int by notNull(); private set

  data class SoftwareUpdate {

    var updatestate: State by notNull(); private set
    var url: Uri? = null; private set
    var text: String? = null;
    var notify: Boolean by notNull(); private set

    enum class State {
      NONE_AVAILABLE,
      DOWNLOADING,
      READY,
      INSTALLING;
      companion object : List<State> by values().asList()
    }

  }

  data class Whitelist {
    var lastUseDate: Date by notNull(); private set
    var createDate: Date by notNull(); private set
    var name: String by notNull(); private set
  }

}