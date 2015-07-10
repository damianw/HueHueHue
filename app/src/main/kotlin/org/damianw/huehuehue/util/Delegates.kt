package org.damianw.huehuehue.util

import android.app.Fragment
import rx.Observable
import rx.android.app.AppObservable
import kotlin.properties.Delegates
import kotlin.properties.ReadWriteProperty

/**
 * @author Damian Wieczorek {@literal <damian@farmlogs.com>}
 * @since 7/14/15
 * (C) 2015 Damian Wieczorek
 */

fun <T : Any> notNull() = Delegates.notNull<T>()

fun <R: Any, T: Any> Fragment.boundObservableProperty(onNext: (T) -> Unit = {}) = this let {
  object: ReadWriteProperty<R, Observable<T>> {
    var value: Observable<T>? = null
    override fun get(thisRef: R, desc: PropertyMetadata): Observable<T>
        = value ?: throw IllegalStateException("Property ${desc.name} should be initialized before get")
    override fun set(thisRef: R, desc: PropertyMetadata, value: Observable<T>) {
      val observable = value.boundTo(it)
      observable.subscribe(onNext)
      this.value = observable
    }
  }
}

