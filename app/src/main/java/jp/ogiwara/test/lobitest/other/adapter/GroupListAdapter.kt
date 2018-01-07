package jp.ogiwara.test.lobitest.other.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import jp.ogiwara.lobiapi.model.Group
import jp.ogiwara.lobirepository.extention.ReactiveGroup
import jp.ogiwara.lobirepository.extention.ReactiveVal
import jp.ogiwara.test.lobitest.group_view.GroupView

class GroupListAdapter(val list: ArrayList<ReactiveGroup>): RecyclerView.Adapter<GroupListAdapter.VH>(){

    init {
        setHasStableIds(true)
    }

    class VH(val v: GroupView): RecyclerView.ViewHolder(v){

        fun onBind(group: ReactiveGroup){
            v.setItem(group)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int) = VH(GroupView(parent!!.context))

    override fun onBindViewHolder(holder: VH?, position: Int) = holder!!.onBind(list.get(position))

    override fun getItemCount() = list.size

    override fun getItemId(position: Int): Long = position.toLong()
}