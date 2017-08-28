package jp.ogiwara.test.lobitest.activity

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso
import jp.ogiwara.lobiapi.model.Activity
import jp.ogiwara.test.lobitest.R
import org.jetbrains.anko.find


//goo,boo,bookmark
class ActivityView : FrameLayout {

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

    val icon: ImageView by lazy{
        find<ImageView>(R.id.image_user_icon)
    }

    val title: TextView by lazy{
        find<TextView>(R.id.text_activity_title)
    }

    init{
        LayoutInflater.from(context).inflate(R.layout.activity_view,this,true)
    }

    fun setActivity(a: Activity){
        Picasso.with(context).load(a.user.first().icon).into(icon)
        title.text = a.title.template //todo Activity- Text - maker
    }
}