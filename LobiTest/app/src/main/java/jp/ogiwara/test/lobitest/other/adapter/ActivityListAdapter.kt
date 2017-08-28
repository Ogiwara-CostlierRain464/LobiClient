package jp.ogiwara.test.lobitest.other.adapter

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import jp.ogiwara.lobiapi.model.Activity
import jp.ogiwara.test.lobitest.activity.ActivityView


class ActivityListAdapter(c: Context, arrayList: ArrayList<Activity>): ArrayAdapter<Activity>(c,0,arrayList){
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?) =
            ((convertView as? ActivityView) ?: ActivityView(context)).apply {
                setActivity(getItem(position))
            }
}