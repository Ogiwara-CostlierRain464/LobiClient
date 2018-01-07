package jp.ogiwara.test.lobitest.chat_view

import android.app.ActivityManager
import android.content.Context
import android.content.Intent
import android.databinding.DataBindingUtil
import android.support.v7.widget.PopupMenu
import android.text.method.LinkMovementMethod
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso
import com.stfalcon.frescoimageviewer.ImageViewer
import io.reactivex.android.schedulers.AndroidSchedulers
import jp.ogiwara.cycle.BasicStore
import jp.ogiwara.lobiapi.model.Chat
import jp.ogiwara.lobirepository.extention.ReactiveChat
import jp.ogiwara.test.lobitest.E
import jp.ogiwara.test.lobitest.R
import jp.ogiwara.test.lobitest.chat_activity.ChatActivity
import jp.ogiwara.test.lobitest.databinding.ChatViewBinding
import jp.ogiwara.test.lobitest.hide
import jp.ogiwara.test.lobitest.user_activity.UserActivity

import org.jetbrains.anko.find
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.startActivity

class ChatView(context: Context,attrs: AttributeSet? = null) : FrameLayout(context,attrs){

    val binding: ChatViewBinding
    val store: BasicStore<State>
    lateinit var c: ReactiveChat

    init{
        val inflater = LayoutInflater.from(context)
        binding = DataBindingUtil.inflate(inflater,R.layout.chat_view,this,true)
        store = BasicStore(State(), dispatcher, context)

        store.dispatch(SET_UP_VIEW({
            val otherButton = binding.buttonOther

            otherButton.setOnClickListener {
                val popupMenu = PopupMenu(context,otherButton)
                popupMenu.menuInflater.inflate(R.menu.chat_menu,popupMenu.menu)
                popupMenu.setOnMenuItemClickListener {
                    when(it.itemId){
                        R.id.menu_share -> {
                            val intent = Intent(Intent.ACTION_SEND)
                            intent.type = "text/plain"
                            intent.putExtra(Intent.EXTRA_TEXT,"")//TODO make url
                            context.startActivity(Intent.createChooser(intent,context.getString(R.string.share)))
                        }
                        R.id.menu_see_goo_boo -> {

                        }
                    }
                    true
                }
                popupMenu.show()
            }
        }))

        binding.store = store
        binding.handler = this
    }

    fun setItem(chat: ReactiveChat){
        c = chat

        store.dispatch(LOADED(c,{ e ->
            Picasso.with(context).load(e.user.icon.lowQuality).into(binding.userImage)

            if(e.assets.isEmpty()){
                binding.imagePhotos.hide()
            }else{
                Picasso.with(context).load(e.assets.first().url.middleQuality).into(binding.imagePhotos)
                binding.imagePhotos.setOnClickListener {
                    ImageViewer.Builder(context, e.assets.map { it.url.rawQuality })
                            .setStartPosition(0)
                            .show()
                }
            }

            binding.base.setOnClickListener {
                //ChatActivityないで押された時は無効にする

                //TODO this is deprecated
                val am = context.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
                val cn = am.getRunningTasks(1).get(0).topActivity

                if(!cn.shortClassName.contains("ChatActivity")){
                    context.startActivity(context.intentFor<ChatActivity>(
                            ChatActivity.EXTRA.CHAT_ID to if(e.reply_to == null) e.id else e.reply_to,
                            ChatActivity.EXTRA.GROUP_ID to e.group.uid
                    ))
                }
            }

            binding.userImage.setOnClickListener {
                context.startActivity(context.intentFor<UserActivity>(UserActivity.EXTRA.USER_ID to e.user.uid))
            }
        },context.E))
    }

    fun onClickGoo(){
        store.dispatch(GOO(c))
    }

    fun onClickBoo(){
        store.dispatch(BOO(c))
    }

    fun onClickBookmark(){
        store.dispatch(BOOKMARK(c))
    }

    override fun onDetachedFromWindow() {
        store.dispatch(DESTROY)

        super.onDetachedFromWindow()
    }
}