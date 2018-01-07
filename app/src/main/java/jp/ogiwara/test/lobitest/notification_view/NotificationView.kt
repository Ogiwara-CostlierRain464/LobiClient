package jp.ogiwara.test.lobitest.notification_view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import jp.ogiwara.lobiapi.model.Notification
import jp.ogiwara.test.lobitest.R

class NotificationView : FrameLayout{

    constructor(context: Context?): super(context)

    constructor(context: Context?,
                attrs: AttributeSet?): super(context,attrs)

    constructor(context: Context?,
                attrs: AttributeSet?,
                defStyleAttr: Int): super(context,attrs,defStyleAttr)

    constructor(context: Context?,
                attrs: AttributeSet?,
                defStyleAttr: Int,
                defStyleRes: Int): super(context, attrs, defStyleAttr, defStyleRes)

    init {
        LayoutInflater.from(context).inflate(R.layout.notification_view,this,true)
    }

    fun setItem(e: Notification){

    }

}