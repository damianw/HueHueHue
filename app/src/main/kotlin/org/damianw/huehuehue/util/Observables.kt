package org.damianw.huehuehue.util

import android.app.Activity
import android.app.Fragment
import rx.Observable
import rx.android.app.AppObservable

/**
 * @author Damian Wieczorek {@literal <damian@farmlogs.com>}
 * @since 7/14/15
 * (C) 2015 Damian Wieczorek
 */

fun <T> Observable<T>.boundTo(fragment: Fragment) = AppObservable.bindFragment(fragment, this)
fun <T> Observable<T>.boundTo(activity: Activity) = AppObservable.bindActivity(activity, this)

