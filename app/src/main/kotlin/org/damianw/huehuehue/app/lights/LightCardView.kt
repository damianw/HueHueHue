package org.damianw.huehuehue.app.lights

import android.content.Context
import android.support.v7.widget.CardView
import android.util.AttributeSet
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import org.damianw.huehuehue.R
import org.damianw.huehuehue.api.model.Light
import org.damianw.huehuehue.app.common.Bindable
import org.damianw.huehuehue.util.notNull
import org.jetbrains.anko.find
import org.jetbrains.anko.text
import org.jetbrains.anko.visibility

/**
 * @author Damian Wieczorek {@literal <damian@farmlogs.com>}
 * @since 7/25/15
 * (C) 2015 Damian Wieczorek
 */
class LightCardView : CardView, Bindable<Light> {

  var lightId: TextView by notNull()
  var lightName: TextView by notNull()
  var lightProgress: ProgressBar by notNull()

  var inProgress: Boolean
    get() = lightProgress.visibility == View.VISIBLE
    set(inProgress) = lightProgress.setVisibility(if (inProgress) View.VISIBLE else View.INVISIBLE)

  constructor(context: Context): super(context) {
    initialize(context)
  }

  constructor(context: Context, attrs: AttributeSet): super(context, attrs) {
    initialize(context)
  }
  constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int): super(context, attrs, defStyleAttr) {
    initialize(context)
  }

  private fun initialize(context: Context) {
    View.inflate(context, R.layout.card_light, this)
    lightId = find<TextView>(R.id.lightId)
    lightName = find<TextView>(R.id.lightName)
    lightProgress = find<ProgressBar>(R.id.lightProgress)
  }

  override fun bind(item: Light) {
    lightId.text = item.id.toString()
    lightName.text = item.name
  }

}