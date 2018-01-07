package jp.ogiwara.test.lobitest.other.view

import android.content.Context
import android.databinding.ObservableArrayList
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.AttributeSet
import android.view.View
import io.reactivex.Observable

open class AdvancedRecyclerView(c: Context,attrs: AttributeSet) : RecyclerView(c,attrs){

    var emptyView: View? = null

    private val emptyObserver = object: RecyclerView.AdapterDataObserver(){
        override fun onChanged() {
            if(adapter != null && emptyView != null){
                emptyView!!.visibility = if(adapter.itemCount == 0) View.VISIBLE else View.GONE
                visibility = if(adapter.itemCount == 0) View.GONE else View.VISIBLE
            }
        }
    }

    init {
        addOnScrollListener(object: RecyclerView.OnScrollListener(){
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                val totalCount = recyclerView.adapter.itemCount //合計のアイテム数
                val childCount = recyclerView.childCount // RecyclerViewに表示されてるアイテム数
                val layoutManager = recyclerView.layoutManager

                val firstPos = when(layoutManager){
                    is GridLayoutManager -> layoutManager.findFirstVisibleItemPosition()
                    is LinearLayoutManager -> layoutManager.findFirstVisibleItemPosition()
                    else -> throw IllegalStateException()
                }

                if(totalCount <= childCount + firstPos){
                    onReachLast?.invoke()
                }
            }
        })
    }

    private var onReachLast: (() -> Unit)? = null

    fun setOnReachLastListener(f: () -> Unit){
        onReachLast = f
    }

    override fun setAdapter(adapter: Adapter<*>?) {
        super.setAdapter(adapter)

        adapter?.registerAdapterDataObserver(emptyObserver)
        emptyObserver.onChanged()
    }
}