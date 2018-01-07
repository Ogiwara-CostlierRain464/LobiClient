package jp.ogiwara.test.lobitest.other.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import jp.ogiwara.lobiapi.model.Activity
import jp.ogiwara.test.lobitest.activity.ActivityView

class ActivityListAdapter(val list: ArrayList<Activity>): RecyclerView.Adapter<ActivityListAdapter.VH>(){

    class VH(val v: ActivityView): RecyclerView.ViewHolder(v){

        fun onBind(e: Activity){
            v.setItem(e)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int) = VH(ActivityView(parent!!.context))

    override fun onBindViewHolder(holder: VH?, position: Int) = holder!!.onBind(list.get(position))

    override fun getItemCount() = list.size
}