package jp.ogiwara.test.lobitest.other.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import jp.ogiwara.lobiapi.model.Chat
import jp.ogiwara.lobirepository.extention.ReactiveChat
import jp.ogiwara.test.lobitest.chat_view.ChatView
import jp.ogiwara.test.lobitest.update_view.UpdateView

//View Typeの設定
class ChatListAdapter(val list: ArrayList<ReactiveChat>): RecyclerView.Adapter<ChatListAdapter.ViewHolder>() {

    object Type{
        const val NORMAL = 0
        const val UPDATE = 1
    }

    init {
        setHasStableIds(true)
    }

    class ViewHolder(v: View) : RecyclerView.ViewHolder(v)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        if(viewType == Type.NORMAL){
            ViewHolder(ChatView(parent.context))
        }else{
            ViewHolder(UpdateView(parent.context))
        }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if(holder.itemViewType == Type.NORMAL){
            (holder.itemView as ChatView).setItem(list.get(position))
        }else{
            (holder.itemView as UpdateView).setItem(list.get(position))
        }
    }

    override fun getItemCount() = list.size

    override fun getItemId(position: Int): Long = position.toLong()

    //TODO Performance
    override fun getItemViewType(position: Int)
            = Type.NORMAL
            //= if(list.get(position).blockingSingle().type == Chat.Type.NORMAL || list.get(position).blockingSingle().type == Chat.Type.SHOUT) Type.NORMAL else Type.UPDATE

}