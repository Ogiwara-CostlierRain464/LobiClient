package jp.ogiwara.test.lobitest.other.adapter

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import jp.ogiwara.lobiapi.model.User
import jp.ogiwara.lobirepository.extention.ReactiveVal
import jp.ogiwara.test.lobitest.user_icon.UserIconView


class UserListAdapter(val list: ArrayList<ReactiveVal<User>>): RecyclerView.Adapter<UserListAdapter.ViewHolder>() {

    init {
        setHasStableIds(true)
    }

    class ViewHolder(val v: UserIconView) : RecyclerView.ViewHolder(v){

        fun onBind(user: ReactiveVal<User>){
            v.setItem(user)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        return ViewHolder(UserIconView(parent!!.context))
    }

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        holder!!.onBind(list.get(position))
    }

    override fun getItemCount() = list.size

    //TEST
    override fun getItemId(position: Int): Long {
        return position.toLong()
    }
}