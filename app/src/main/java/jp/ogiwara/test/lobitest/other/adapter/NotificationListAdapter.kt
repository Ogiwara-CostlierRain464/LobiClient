package jp.ogiwara.test.lobitest.other.adapter

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import jp.ogiwara.lobiapi.model.Notification
import jp.ogiwara.test.lobitest.notification_view.NotificationView


class NotificationListAdapter(val list: ArrayList<Notification>): RecyclerView.Adapter<NotificationListAdapter.VH>(){

    init {
        setHasStableIds(true)
    }

    class VH(val v: NotificationView): RecyclerView.ViewHolder(v){

        fun onBind(e: Notification){
            v.setItem(e)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int) = VH(NotificationView(parent!!.context))

    override fun onBindViewHolder(holder: VH?, position: Int) = holder!!.onBind(list.get(position))

    override fun getItemCount() = list.size

    override fun getItemId(position: Int) = position.toLong()
}