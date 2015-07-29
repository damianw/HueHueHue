package org.damianw.huehuehue.util

import android.content.Context
import android.content.DialogInterface
import android.support.annotation.LayoutRes
import android.support.annotation.StringRes
import android.support.v7.app.AlertDialog
import android.view.View

/**
 * @author Damian Wieczorek {@literal <damian@farmlogs.com>}
 * @since 7/28/15
 * (C) 2015 Damian Wieczorek
 */
class AlertDialogBuilder(context: Context) {

  val builder = AlertDialog.Builder(context)

  fun title(text: CharSequence) = builder.setTitle(text)

  fun view(view: View) = builder.setView(view)
  fun view(LayoutRes layoutId: Int) = builder.setView(layoutId)
  fun viewWithSpacing(view: View, left: Int = 0, top: Int = 0, right: Int = 0, bottom: Int = 0) =
      builder.setView(view, left, top, right, bottom)

  fun negativeButton(text: CharSequence, onClick: (DialogInterface.(Int) -> Unit)? = null) =
      builder.setNegativeButton(text, onClick)
  fun negativeButton(StringRes textId: Int, onClick: (DialogInterface.(Int) -> Unit)? = null) =
      builder.setNegativeButton(textId, onClick)
  fun positiveButton(text: CharSequence, onClick: (DialogInterface.(Int) -> Unit)? = null) =
      builder.setPositiveButton(text, onClick)
  fun positiveButton(StringRes textId: Int, onClick: (DialogInterface.(Int) -> Unit)? = null) =
      builder.setPositiveButton(textId, onClick)
  fun neutralButton(text: CharSequence, onClick: (DialogInterface.(Int) -> Unit)? = null) =
      builder.setNeutralButton(text, onClick)
  fun neutralButton(StringRes textId: Int, onClick: (DialogInterface.(Int) -> Unit)? = null) =
      builder.setNeutralButton(textId, onClick)

}

inline fun alertDialog(context: Context, init: AlertDialogBuilder.() -> Unit): AlertDialog {
  val builder = AlertDialogBuilder(context)
  builder.init()
  return builder.builder.show()
}
