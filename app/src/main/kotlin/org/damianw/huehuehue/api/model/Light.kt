package org.damianw.huehuehue.api.model

import android.graphics.PointF
import kotlin.properties.Delegates

/**
 * @author Damian Wieczorek {@literal <damian@farmlogs.com>}
 * @since 7/8/15
 * (C) 2015 Damian Wieczorek
 */
data class Light() {

  var name: String by Delegates.notNull()
  var state: State by Delegates.notNull()

  var id: String by Delegates.notNull()
    private set
  var uniqueid: String by Delegates.notNull()
    private set
  var manufacturername: String by Delegates.notNull()
    private set
  var type: String by Delegates.notNull()
    private set
  var modelid: String by Delegates.notNull()
    private set
  var swversion: String by Delegates.notNull()
    private set

  data class State (
      var on: Boolean = true,
      var bri: Byte = 0,
      var hue: Short = 0,
      var sat: Byte = 0,
      var xy: PointF = PointF(0f, 0f),
      var ct: Short = 0,
      var alert: Alert = Alert.NONE,
      var effect: Effect = Effect.NONE,
      var colormode: ColorMode? = null,
      var reachable: Boolean = true
  )

  enum class Alert { NONE, SELECT, LSELECT }
  enum class Effect { NONE, COLORLOOP }
  enum class ColorMode { HS, XY, CT }

}