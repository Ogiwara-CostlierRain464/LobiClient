package jp.ogiwara.test.lobitest.other.view.chat_list

import android.content.Context
import android.databinding.ObservableArrayList
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.AttributeSet
import android.widget.ListView
import jp.ogiwara.lobiapi.model.Chat
import jp.ogiwara.lobirepository.extention.ReactiveChat
import jp.ogiwara.test.lobitest.other.adapter.ChatListAdapter
import jp.ogiwara.test.lobitest.other.view.AdvancedRecyclerView

class ChatListView(c: Context, attrs: AttributeSet): AdvancedRecyclerView(c,attrs){

    init {
        layoutManager = GridLayoutManager(context,1)
    }

    var adapter: ChatListAdapter? = null

    //ダックタイピングで解決
    fun setList(list: ObservableArrayList<ReactiveChat>){
        if(adapter == null){
            adapter = ChatListAdapter(list)
            setAdapter(adapter)
        }

        adapter?.notifyDataSetChanged()
    }
}