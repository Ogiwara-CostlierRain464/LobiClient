package jp.ogiwara.test.lobitest.activity

import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.LifecycleOwner
import android.arch.lifecycle.LifecycleRegistry
import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso
import jp.ogiwara.lobiapi.model.Activity
import jp.ogiwara.lobiapi.util.ActivityUtil
import jp.ogiwara.test.lobitest.R
import jp.ogiwara.test.lobitest.bindView
import jp.ogiwara.test.lobitest.other.view.LifecycleCustomView
import org.jetbrains.anko.find


//goo,boo,bookmark
class ActivityView(context: Context,attrs: AttributeSet? = null) : FrameLayout(context,attrs){

    val icon: ImageView by bindView(R.id.image_user_icon)
    val title: TextView by bindView(R.id.text_activity_title)

    init{
        LayoutInflater.from(context).inflate(R.layout.activity_view,this,true)
    }

    fun setItem(a: Activity){
        Picasso.with(context).load(a.user.first().icon.highQuality).into(icon)
        title.text = ActivityUtil.makeTitle(a)
    }
}