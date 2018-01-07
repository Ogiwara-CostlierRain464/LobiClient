package jp.ogiwara.test.lobitest.other.view.user_list

import android.content.Context
import android.content.res.Resources
import android.databinding.ObservableArrayList
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.SimpleItemAnimator
import android.util.AttributeSet
import jp.ogiwara.lobiapi.model.User
import jp.ogiwara.lobirepository.extention.ReactiveVal
import jp.ogiwara.test.lobitest.other.adapter.UserListAdapter
import jp.ogiwara.test.lobitest.other.util.math.getUserGridSpanCount
import jp.ogiwara.test.lobitest.other.view.AdvancedRecyclerView

class UserListView(c: Context, attrs: AttributeSet): AdvancedRecyclerView(c,attrs){

    init {
        val dm = Resources.getSystem().displayMetrics
        val width = dm.widthPixels
        val height = dm.heightPixels
        layoutManager = GridLayoutManager(context, getUserGridSpanCount(width,height))
    }

    var adapter: UserListAdapter? = null

    fun setList(list: ObservableArrayList<ReactiveVal<User>>){
        if(adapter == null){
            adapter = UserListAdapter(list)
            setAdapter(adapter)
        }

        adapter?.notifyDataSetChanged()
    }
}