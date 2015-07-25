package org.damianw.huehuehue.util

import com.google.gson.reflect.TypeToken
import java.lang.reflect.GenericDeclaration
import java.lang.reflect.Type
import java.lang.reflect.TypeVariable

/**
 * @author Damian Wieczorek {@literal <damian@farmlogs.com>}
 * @since 7/25/15
 * (C) 2015 Damian Wieczorek
 */

val Type.token: TypeToken<*> get() = TypeToken.get(this)
