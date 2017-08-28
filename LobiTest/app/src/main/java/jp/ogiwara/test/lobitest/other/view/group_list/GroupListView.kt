package jp.ogiwara.test.lobitest.other.view.group_list

import android.content.Context
import android.databinding.ObservableArrayList
import android.util.AttributeSet
import android.widget.ListView
import jp.ogiwara.lobiapi.model.Group
import jp.ogiwara.test.lobitest.other.adapter.GroupListAdapter

class GroupListView(c: Context, attrs: AttributeSet): ListView(c,attrs){

    var adapter: GroupListAdapter? = null

    //ダックタイピングで解決
    fun setList(groupList: ObservableArrayList<Group>){
        if(adapter == null){
            adapter = GroupListAdapter(context,groupList)
            setAdapter(adapter)
        }

        adapter?.notifyDataSetChanged()
    }

}