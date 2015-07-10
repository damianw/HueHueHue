package org.damianw.huehuehue.api.model

/**
 * @author Damian Wieczorek {@literal <damian@farmlogs.com>}
 * @since 7/13/15
 * (C) 2015 Damian Wieczorek
 */
enum class Alert {
  NONE,
  SELECT,
  LSELECT;
  companion object : List<Alert> by values().asList()
}
