package jp.ogiwara.test.lobitest.other.util.math

import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView

class RecyclerViewBottomListener(val callBack: () -> Unit): RecyclerView.OnScrollListener(){
    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)

        val totalCount = recyclerView.getAdapter().itemCount //合計のアイテム数
        val childCount = recyclerView.getChildCount() // RecyclerViewに表示されてるアイテム数
        val layoutManager = recyclerView.getLayoutManager()


        if (layoutManager is GridLayoutManager) { // GridLayoutManager
            val firstPosition = layoutManager.findFirstVisibleItemPosition() // RecyclerViewに表示されている一番上のアイテムポジション
            if (totalCount <= childCount + firstPosition) {
                // ページング処理
                // GridLayoutManagerを指定している時のページング処理
                callBack()
            }
        } else if (layoutManager is LinearLayoutManager) { // LinearLayoutManager
            val firstPosition = layoutManager.findFirstVisibleItemPosition() // RecyclerViewの一番上に表示されているアイテムのポジション
            if (totalCount <= childCount + firstPosition) {
                // ページング処理
                // LinearLayoutManagerを指定している時のページング処理
                callBack()
            }
        }
    }
}