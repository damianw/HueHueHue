package org.damianw.huehuehue.api.model

import android.graphics.PointF
import org.damianw.huehuehue.util.notNull

/**
 * @author Damian Wieczorek {@literal <damian@farmlogs.com>}
 * @since 7/8/15
 * (C) 2015 Damian Wieczorek
 */
data class Light() {

  var name: String by notNull()
  var state: State by notNull()

  var id: String by notNull(); private set
  var uniqueid: String by notNull(); private set
  var manufacturername: String by notNull(); private set
  var type: String by notNull(); private set
  var modelid: String by notNull(); private set
  var swversion: String by notNull(); private set

  data class State(
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

  enum class Alert {
    NONE,
    SELECT,
    LSELECT;
    companion object : List<Alert> by values().asList()
  }

  enum class Effect {
    NONE,
    COLORLOOP;
    companion object : List<Effect> by values().asList()
  }

  enum class ColorMode {
    HS,
    XY,
    CT;
    companion object : List<ColorMode> by values().asList()
  }

}