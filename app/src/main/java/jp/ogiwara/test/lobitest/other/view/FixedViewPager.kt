package jp.ogiwara.test.lobitest.other.view

import android.content.Context
import android.support.v4.view.ViewPager
import android.util.AttributeSet
import android.view.MotionEvent

class FixedViewPager @JvmOverloads constructor(
        context: Context,
        attrs: AttributeSet? = null
): ViewPager(context, attrs){
    //タッチを無効化
    override fun onTouchEvent(ev: MotionEvent?) = false
    override fun onInterceptTouchEvent(ev: MotionEvent?) = false
}