package org.damianw.huehuehue.api.model.response

import org.damianw.huehuehue.api.annotation.reflective

/**
 * @author Damian Wieczorek {@literal <damian@farmlogs.com>}
 * @since 7/15/15
 * (C) 2015 Damian Wieczorek
 */
reflective data class Response(var status: Status, var values: Map<String, Any>)
