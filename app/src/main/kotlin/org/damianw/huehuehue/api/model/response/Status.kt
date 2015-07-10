package org.damianw.huehuehue.api.model.response

/**
 * @author Damian Wieczorek {@literal <damian@farmlogs.com>}
 * @since 7/15/15
 * (C) 2015 Damian Wieczorek
 */
enum class Status {
  SUCCESS,
  ERROR;
  companion object : List<Status> by values().asList()
}