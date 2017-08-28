package jp.ogiwara.test.lobitest.other.adapter

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import jp.ogiwara.lobiapi.model.Chat
import jp.ogiwara.test.lobitest.chat_view.ChatView


class ChatListAdapter(c: Context, groupArrayList: ArrayList<Chat>): ArrayAdapter<Chat>(c,0,groupArrayList){
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?) =
            ((convertView as? ChatView) ?: ChatView(context)).apply {
                setChat(getItem(position))
            }
}