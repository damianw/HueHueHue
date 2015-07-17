package org.damianw.huehuehue.app

import android.app.Fragment
import android.os.Bundle
import android.support.annotation.LayoutRes
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

/**
 * @author Damian Wieczorek {@literal <damian@farmlogs.com>}
 * @since 7/14/15
 * (C) 2015 Damian Wieczorek
 */
open class LayoutFragment(LayoutRes val layoutId: Int) : Fragment() {

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
    return inflater.inflate(layoutId, container, false)
  }

}