package jp.ogiwara.test.lobitest.other.view.chat_list

import android.content.Context
import android.databinding.ObservableArrayList
import android.util.AttributeSet
import android.widget.ListView
import jp.ogiwara.lobiapi.model.Chat
import jp.ogiwara.test.lobitest.other.adapter.ChatListAdapter

class ChatListView(c: Context, attrs: AttributeSet): ListView(c,attrs){

    var adapter: ChatListAdapter? = null

    //ダックタイピングで解決
    fun setList(groupList: ObservableArrayList<Chat>){
        if(adapter == null){
            adapter = ChatListAdapter(context,groupList)
            setAdapter(adapter)
        }

        adapter?.notifyDataSetChanged()
    }

}