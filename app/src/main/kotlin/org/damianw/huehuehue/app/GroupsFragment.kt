package org.damianw.huehuehue.app

import android.os.Bundle
import android.support.v4.widget.SwipeRefreshLayout
import android.view.View
import android.widget.TextView
import kotlinx.android.synthetic.fragment_groups.groupsView
import kotlinx.android.synthetic.fragment_groups.refreshLayout
import org.damianw.huehuehue.R
import org.damianw.huehuehue.api.model.Group
import org.damianw.huehuehue.util.*
import org.jetbrains.anko.text

/**
 * @author Damian Wieczorek {@literal <damian@farmlogs.com>}
 * @since 7/13/15
 * (C) 2015 Damian Wieczorek
 */
class GroupsFragment : BridgeFragment(R.layout.fragment_groups), SwipeRefreshLayout.OnRefreshListener {

  val adapter = listAdapter<Group, View>(R.layout.cell_group) {
    it.get<TextView>(R.id.groupName).text = name
    it.get<TextView>(R.id.groupId).text = id.toString()
  }

  override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
    groupsView.adapter = adapter
    refreshLayout.onRefreshListener = this
  }

  override fun onResume() {
    super<BridgeFragment>.onResume()
    refreshLayout.refreshing = true
    onRefresh()
  }

  override fun onRefresh() = bridge.groups.boundTo(this).subscribe {
    adapter.items = it
    refreshLayout.refreshing = false
  } let { Unit }

}