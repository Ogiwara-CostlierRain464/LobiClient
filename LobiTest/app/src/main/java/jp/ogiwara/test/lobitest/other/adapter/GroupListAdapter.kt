package jp.ogiwara.test.lobitest.other.adapter

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import jp.ogiwara.lobiapi.model.Group
import jp.ogiwara.test.lobitest.group_view.GroupView

class GroupListAdapter(c: Context, groupArrayList: ArrayList<Group>): ArrayAdapter<Group>(c,0,groupArrayList){
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?) =
            ((convertView as? GroupView) ?: GroupView(context)).apply {
                setGroup(getItem(position))
            }
}