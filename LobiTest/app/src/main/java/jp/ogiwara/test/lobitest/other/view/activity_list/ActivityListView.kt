package jp.ogiwara.test.lobitest.other.view.activity_list

import android.content.Context
import android.databinding.ObservableArrayList
import android.util.AttributeSet
import android.widget.ListView
import jp.ogiwara.lobiapi.model.Activity
import jp.ogiwara.test.lobitest.other.adapter.ActivityListAdapter

class ActivityListView(c: Context, attrs: AttributeSet): ListView(c,attrs){

    var adapter: ActivityListAdapter? = null

    //ダックタイピングで解決
    fun setList(groupList: ObservableArrayList<Activity>){
        if(adapter == null){
            adapter = ActivityListAdapter(context,groupList)
            setAdapter(adapter)
        }

        adapter?.notifyDataSetChanged()
    }

}