package jp.ogiwara.test.lobitest.other.view.group_list

import android.content.Context
import android.content.res.Resources
import android.databinding.ObservableArrayList
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.AttributeSet
import android.view.View
import android.widget.ListView
import jp.ogiwara.lobiapi.model.Group
import jp.ogiwara.lobirepository.extention.ReactiveGroup
import jp.ogiwara.lobirepository.extention.ReactiveVal
import jp.ogiwara.test.lobitest.other.adapter.GroupListAdapter
import jp.ogiwara.test.lobitest.other.util.math.getGroupGridSpanCount
import jp.ogiwara.test.lobitest.other.view.AdvancedRecyclerView

class GroupListView(c: Context, attrs: AttributeSet): AdvancedRecyclerView(c,attrs){

    init {
        val dm = Resources.getSystem().displayMetrics
        val width = dm.widthPixels
        val height = dm.heightPixels
        layoutManager = GridLayoutManager(context, getGroupGridSpanCount(width,height))
    }

    var adapter: GroupListAdapter? = null

    //ダックタイピングで解決
    fun setList(groupList: ObservableArrayList<ReactiveGroup>){

        if(adapter == null){
            adapter = GroupListAdapter(groupList)
            setAdapter(adapter)
        }

        adapter?.notifyDataSetChanged()
    }

}