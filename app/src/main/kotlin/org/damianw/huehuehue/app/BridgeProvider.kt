package org.damianw.huehuehue.app

import org.damianw.huehuehue.api.Bridge

/**
 * @author Damian Wieczorek {@literal <damian@farmlogs.com>}
 * @since 7/15/15
 * (C) 2015 Damian Wieczorek
 */
interface BridgeProvider {
  val bridge: Bridge
}