package jp.ogiwara.test.lobitest.other.view.activity_list

import android.content.Context
import android.databinding.ObservableArrayList
import android.support.v7.widget.RecyclerView
import android.util.AttributeSet
import android.widget.ListView
import jp.ogiwara.lobiapi.model.Activity
import jp.ogiwara.test.lobitest.other.adapter.ActivityListAdapter
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.GridLayoutManager
import jp.ogiwara.test.lobitest.other.view.AdvancedRecyclerView


class ActivityListView(c: Context, attrs: AttributeSet): AdvancedRecyclerView(c,attrs){

    init {
        layoutManager = GridLayoutManager(context,1)
    }

    var adapter: ActivityListAdapter? = null

    //ダックタイピングで解決
    fun setList(list: ObservableArrayList<Activity>){
        if(adapter == null){
            adapter = ActivityListAdapter(list)
            setAdapter(adapter)
        }

        adapter?.notifyDataSetChanged()
    }

}