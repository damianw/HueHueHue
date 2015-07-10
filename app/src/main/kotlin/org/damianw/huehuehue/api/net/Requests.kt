package org.damianw.huehuehue.api.net

import org.damianw.huehuehue.api.annotation.reflective

/**
 * @author Damian Wieczorek {@literal <damian@farmlogs.com>}
 * @since 7/15/15
 * (C) 2015 Damian Wieczorek
 */
reflective data class SetName(var name: String)
reflective data class CreateUser(var deviceId: String)