package jp.ogiwara.test.lobitest.chat_view

import android.content.Context
import android.databinding.DataBindingUtil
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import com.squareup.picasso.Picasso
import jp.ogiwara.cycle.BasicStore
import jp.ogiwara.lobiapi.model.Chat
import jp.ogiwara.test.lobitest.R
import jp.ogiwara.test.lobitest.databinding.ChatViewBinding

//goo,boo,bookmark,replies
class ChatView : FrameLayout{

    val binding: ChatViewBinding
    val store: BasicStore<State>

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

    init{
        val inflater = LayoutInflater.from(context)
        binding = DataBindingUtil.inflate(inflater,R.layout.chat_view,this,true)
        store = BasicStore(State(), reducer, context)
        binding.store = store
        binding.handler = this
    }

    fun setChat(chat: Chat){
        store.dispatch(LOADED(chat))
        Picasso.with(context).load(chat.user.icon).into(binding.userImage)
    }
}