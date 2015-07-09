package org.damianw.huehuehue.api.model

import java.util.Date
import kotlin.properties.Delegates

/**
 * @author Damian Wieczorek {@literal <damian@farmlogs.com>}
 * @since 7/8/15
 * (C) 2015 Damian Wieczorek
 */
data class Scan {

  var results: List<Result> by Delegates.notNull()
    private set
  var lastscan: State by Delegates.notNull()
    private set

  data class Result {
    var id: String by Delegates.notNull()
    var name: String by Delegates.notNull()
  }
  data class State (var date: Date?, var active: Boolean)

}