package jp.ogiwara.test.lobitest.update_view

import android.content.Context
import android.graphics.drawable.BitmapDrawable
import android.support.constraint.ConstraintLayout
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.RelativeLayout
import android.widget.TextView
import io.reactivex.android.schedulers.AndroidSchedulers
import jp.ogiwara.lobiapi.model.Chat
import jp.ogiwara.lobirepository.extention.ReactiveChat
import jp.ogiwara.test.lobitest.E
import jp.ogiwara.test.lobitest.R
import org.jetbrains.anko.find
import java.util.*


class UpdateView: FrameLayout {

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

    val time by lazy {
        find<TextView>(R.id.text_time)
    }

    val updateMessage by lazy {
        find<TextView>(R.id.text_update_message)
    }

    init{
        LayoutInflater.from(context).inflate(R.layout.update_view,this,true)
    }

    fun setItem(e: ReactiveChat){
        e.observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    time.text = it.created_date.toString()
                    updateMessage.text = Date(it.created_date / 1000).toString()
                },context.E)
    }
}