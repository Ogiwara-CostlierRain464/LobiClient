package jp.ogiwara.test.lobitest.group_view

import android.content.Context
import android.content.Intent
import android.databinding.DataBindingUtil
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.support.constraint.ConstraintLayout
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso
import io.reactivex.android.schedulers.AndroidSchedulers
import jp.ogiwara.lobiapi.model.Group
import jp.ogiwara.lobirepository.extention.ReactiveVal
import jp.ogiwara.test.lobitest.R
import jp.ogiwara.test.lobitest.group_activity.GroupActivity
import jp.ogiwara.test.lobitest.other.view.CustomContainerView
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.find
import org.jetbrains.anko.info
import org.jetbrains.anko.intentFor
import com.squareup.picasso.Target
import jp.ogiwara.cycle.BasicStore
import jp.ogiwara.lobirepository.extention.ReactiveGroup
import jp.ogiwara.test.lobitest.DEBUG
import jp.ogiwara.test.lobitest.E
import jp.ogiwara.test.lobitest.bindView
import jp.ogiwara.test.lobitest.databinding.GroupViewBinding


class GroupView @JvmOverloads constructor(
        context: Context,
        attrs: AttributeSet? = null
) : FrameLayout(context,attrs){

    val store:  BasicStore<State>

    init{
        val inflater = LayoutInflater.from(context)
        val binding: GroupViewBinding =
                DataBindingUtil.inflate(inflater,R.layout.group_view,this,true)
        store = BasicStore(State(),DispatcherImp(),context)

        binding.store = store
        binding.handler = this
    }

    val image: ImageView by bindView(R.id.image_group_icon)
    val name: TextView by bindView(R.id.text_group_name)
    val newChatImage: ImageView by bindView(R.id.image_new_chat)

    fun setItem(e: ReactiveGroup) {

        store.dispatch(OBSERVE(e,{ g ->
            name.text = g.name
            Picasso.with(context).load(g.icon.highQuality).into(image)

            this.setOnClickListener {
                context.startActivity(context.intentFor<GroupActivity>(GroupActivity.EXTRA_GROUP_ID to g.uid))

                newChatImage.visibility = View.INVISIBLE
            }

            /*if(store.state.lastChatAt < g.last_chat_at){
                newChatImage.visibility = View.VISIBLE
            }else{
                newChatImage.visibility = View.INVISIBLE
            }*/
        },context.DEBUG))
    }

    override fun onDetachedFromWindow() {
        store.dispatch(DESTROY)

        super.onDetachedFromWindow()
    }
}