package org.damianw.huehuehue.api.model

import org.damianw.huehuehue.api.annotation.reflective
import org.damianw.huehuehue.util.notNull

/**
 * @author Damian Wieczorek {@literal <damian@farmlogs.com>}
 * @since 7/9/15
 * (C) 2015 Damian Wieczorek
 */
reflective data class Group {

  var id: Int by notNull(); private set
  var name: String by notNull(); private set
  var lights: List<String> by notNull(); private set
  var action: Light.State by notNull(); private set

  enum class Type {
    LIGHT_SOURCE,
    LIGHT_GROUP,
    LUMINAIRE;
    companion object : List<Type> by values().asList()
  }

}
