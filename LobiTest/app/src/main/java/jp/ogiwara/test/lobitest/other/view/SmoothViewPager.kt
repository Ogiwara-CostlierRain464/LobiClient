package jp.ogiwara.test.lobitest.other.view

import android.content.Context
import android.support.v4.view.ViewPager
import android.util.AttributeSet
import android.view.animation.DecelerateInterpolator
import android.widget.Scroller

//It doesn't work...
class SmoothViewPager: ViewPager{

    constructor(context: Context?): super(context)

    constructor(context: Context?,
                attrs: AttributeSet?): super(context,attrs)

    init {
        setSmoothScroller()
    }

    private fun setSmoothScroller(){
        val clazz = ViewPager::class.java
        val field = clazz.getDeclaredField("mScroller")
        field.isAccessible = true
        field.set(this,SmoothScroller(context))
    }

    class SmoothScroller(c: Context): Scroller(c,DecelerateInterpolator()){
        override fun startScroll(startX: Int, startY: Int, dx: Int, dy: Int, duration: Int) {
            super.startScroll(startX, startY, dx, dy, 1000)
        }
    }
}