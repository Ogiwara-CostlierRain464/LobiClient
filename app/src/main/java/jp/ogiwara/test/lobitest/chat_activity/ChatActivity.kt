package jp.ogiwara.test.lobitest.chat_activity

import android.app.Activity
import android.content.Intent
import android.databinding.ObservableArrayList
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.Toolbar
import android.view.ViewGroup
import android.widget.ImageView
import com.squareup.picasso.Picasso
import com.squareup.picasso.Target
import io.reactivex.android.schedulers.AndroidSchedulers
import jp.ogiwara.lobiapi.model.Chat
import jp.ogiwara.lobirepository.extention.ReactiveChat
import jp.ogiwara.lobirepository.extention.postReply
import jp.ogiwara.test.lobitest.R
import jp.ogiwara.test.lobitest.chat_edit.ChatEditActivity
import jp.ogiwara.test.lobitest.chat_view.ChatView
import jp.ogiwara.test.lobitest.other.view.chat_list.ChatListView
import jp.ogiwara.test.lobitest.repo
import org.jetbrains.anko.find
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.toast


class ChatActivity : AppCompatActivity(){

    object REQUEST_CODE{
        const val CHAT_EDIT = 1
    }

    object EXTRA{
        const val GROUP_ID = "group_id"
        const val CHAT_ID = "chat_id"
    }

    lateinit var groupId: String
    lateinit var chatId: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)

        if(savedInstanceState == null){
            groupId = intent.getStringExtra(EXTRA.GROUP_ID)
            chatId = intent.getStringExtra(EXTRA.CHAT_ID)
        }else{
            groupId = savedInstanceState.getString(EXTRA.GROUP_ID)
            chatId = savedInstanceState.getString(EXTRA.CHAT_ID)
        }

        setUpView()
    }

    private fun setUpView(){
        val root = find<ChatView>(R.id.chat_root)
        val list = find<ChatListView>(R.id.list)
        val toolbar = find<Toolbar>(R.id.toolbar)
        val chatBg = find<ImageView>(R.id.chat_background)
        val replyFab = find<FloatingActionButton>(R.id.button_reply)

        list.layoutManager = GridLayoutManager(applicationContext,1)
        list.isNestedScrollingEnabled = false


        repo.environment.getChatAndReplies(repo,groupId,chatId).observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    root.setItem(repo.environment.getChat(repo,it.to.id,it.to.group.uid))
                    val es = ObservableArrayList<ReactiveChat>()
                    es.addAll(it.chats.map { repo.environment.getChat(repo,it.id,it.group.uid) })
                    list.setList(es)

                    toolbar.setNavigationIcon(R.drawable.back)
                    toolbar.setNavigationOnClickListener {
                        finish()
                    }

                    toolbar.title = it.to.group.name

                    Picasso.with(applicationContext).load(it.to.group.wallpaper?.veryHighQuality).into(chatBg)
                },{})

        replyFab.setOnClickListener {
            startActivityForResult(intentFor<ChatEditActivity>(),REQUEST_CODE.CHAT_EDIT)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        when (requestCode) {
            REQUEST_CODE.CHAT_EDIT -> {
                if (resultCode == Activity.RESULT_OK) {
                    val chatText = data!!.getStringExtra(ChatEditActivity.EXTRA_CHAT)

                    repo.environment.getChatAndReplies(repo, groupId, chatId).observeOn(AndroidSchedulers.mainThread())
                            .subscribe({
                                it.to.postReply(repo, chatText).observeOn(AndroidSchedulers.mainThread()).subscribe({},{ toast(R.string.cannot_interfere) })
                            },{})
                }

            }
        }
    }
}