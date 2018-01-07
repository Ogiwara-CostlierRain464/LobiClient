package jp.ogiwara.test.lobitest.other.view.notification_list

import android.content.Context
import android.databinding.ObservableArrayList
import android.support.v7.widget.RecyclerView
import android.util.AttributeSet
import jp.ogiwara.lobiapi.model.Notification
import jp.ogiwara.test.lobitest.other.adapter.NotificationListAdapter
import jp.ogiwara.test.lobitest.other.view.AdvancedRecyclerView

class NotificationListView(c: Context, attrs: AttributeSet): AdvancedRecyclerView(c,attrs){

    var adapter: NotificationListAdapter? = null

    fun setList(list: ObservableArrayList<Notification>) {
        if(adapter == null){
            adapter = NotificationListAdapter(list)
            setAdapter(adapter)
        }

        adapter?.notifyDataSetChanged()
    }
}