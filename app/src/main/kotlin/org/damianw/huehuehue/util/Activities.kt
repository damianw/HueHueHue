package org.damianw.huehuehue.util

import android.app.Activity
import android.app.FragmentManager

/**
 * @author Damian Wieczorek {@literal <damian@farmlogs.com>}
 * @since 7/13/15
 * (C) 2015 Damian Wieczorek
 */

inline val Activity.fragmentManager: FragmentManager get() = getFragmentManager()
