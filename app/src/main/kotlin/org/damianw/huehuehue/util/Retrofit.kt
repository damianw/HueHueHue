package org.damianw.huehuehue.util

import retrofit.RestAdapter

/**
 * @author Damian Wieczorek {@literal <damian@farmlogs.com>}
 * @since 7/15/15
 * (C) 2015 Damian Wieczorek
 */

inline fun <reified T> RestAdapter.create() = create(javaClass<T>())