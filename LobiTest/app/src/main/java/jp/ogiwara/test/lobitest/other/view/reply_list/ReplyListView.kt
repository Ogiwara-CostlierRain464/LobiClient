package jp.ogiwara.test.lobitest.other.view.reply_list

import android.content.Context
import android.databinding.ObservableArrayList
import android.util.AttributeSet
import android.widget.ListView
import jp.ogiwara.lobiapi.model.Chat
import jp.ogiwara.test.lobitest.other.adapter.ReplyListAdapter


class ReplyListView(c: Context, attrs: AttributeSet): ListView(c,attrs){

    var adapter: ReplyListAdapter? = null

    fun setList(replyList: ObservableArrayList<Chat>){
        if(adapter == null){
            adapter = ReplyListAdapter(context,replyList)
            setAdapter(adapter)
        }

        adapter
    }

}