package org.damianw.huehuehue.util

import android.view.MenuItem

/**
 * @author Damian Wieczorek {@literal <damian@farmlogs.com>}
 * @since 7/13/15
 * (C) 2015 Damian Wieczorek
 */

val MenuItem.itemId: Int get() = getItemId()
val MenuItem.groupId: Int get() = getGroupId()

var MenuItem.checkable: Boolean
  get() = isCheckable()
  set(checkable) = setCheckable(checkable) let { Unit }

var MenuItem.checked: Boolean
  get() = isChecked()
  set(checked) = setChecked(checked) let { Unit }
