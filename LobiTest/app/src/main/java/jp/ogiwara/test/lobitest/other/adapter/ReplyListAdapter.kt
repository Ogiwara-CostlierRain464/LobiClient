package jp.ogiwara.test.lobitest.other.adapter

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import jp.ogiwara.lobiapi.model.Chat
import jp.ogiwara.test.lobitest.reply.ReplyView
import java.lang.reflect.Array


class ReplyListAdapter(c: Context, chatArrayList: ArrayList<Chat>): ArrayAdapter<Chat>(c,0,chatArrayList){
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?) =
            ((convertView as? ReplyView) ?: ReplyView(context)).apply {
                setReply(getItem(position))
            }
}