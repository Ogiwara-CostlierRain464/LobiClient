package jp.ogiwara.test.lobitest.user_icon

import android.content.Context
import android.support.constraint.ConstraintLayout
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso
import io.reactivex.android.schedulers.AndroidSchedulers
import jp.ogiwara.lobiapi.model.User
import jp.ogiwara.lobirepository.extention.ReactiveVal
import jp.ogiwara.test.lobitest.E
import jp.ogiwara.test.lobitest.R
import jp.ogiwara.test.lobitest.user_activity.UserActivity
import org.jetbrains.anko.find
import org.jetbrains.anko.intentFor

class UserIconView : FrameLayout {

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

    val icon by lazy {
        find<ImageView>(R.id.image_user_icon)
    }

    val name by lazy{
        find<TextView>(R.id.text_user_name)
    }

    init {
        LayoutInflater.from(context).inflate(R.layout.user_icon_view,this,true)
    }

    fun setItem(e: ReactiveVal<User>){
        e.observeOn(AndroidSchedulers.mainThread())
                .subscribe({ user ->
                    name.text = user.name
                    Picasso.with(context).load(user.icon.highQuality).into(icon)

                    findViewById<ConstraintLayout>(R.id.container).setOnClickListener {
                        context.startActivity(context.intentFor<UserActivity>(UserActivity.EXTRA.USER_ID to user.uid))
                    }
                },context.E)
    }
}