package jp.ogiwara.test.lobitest.other.adapter

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import jp.ogiwara.test.lobitest.other.view.CustomContainerView

class CommonListAdapter<E, out EV : CustomContainerView<E>>(c: Context, list: ArrayList<E>): ArrayAdapter<E>(c,0,list){
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?) =
            (convertView as? EV)?.apply {
                setItem(getItem(position))
            }
}
