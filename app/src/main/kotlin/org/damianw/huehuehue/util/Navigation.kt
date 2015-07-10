package org.damianw.huehuehue.util

import android.support.design.widget.NavigationView
import android.view.MenuItem

/**
 * @author Damian Wieczorek {@literal <damian@farmlogs.com>}
 * @since 7/13/15
 * (C) 2015 Damian Wieczorek
 */

fun NavigationView.onSelected(onSelected: (MenuItem) -> Boolean)
    = setNavigationItemSelectedListener(onSelected)
