package jp.ogiwara.test.lobitest.other.view

import android.content.Context
import android.databinding.ObservableArrayList
import android.util.AttributeSet
import android.widget.ListView
import jp.ogiwara.test.lobitest.other.adapter.CommonListAdapter


class CommonListView<E,EV: CustomContainerView<E>>(c: Context,attrs: AttributeSet): ListView(c,attrs){

    var adapter: CommonListAdapter<E,EV>? = null

    fun setList(list: ObservableArrayList<E>){
        if(adapter == null){
            adapter = CommonListAdapter(context,list)
            setAdapter(adapter)
        }

        adapter?.notifyDataSetChanged()
    }
}