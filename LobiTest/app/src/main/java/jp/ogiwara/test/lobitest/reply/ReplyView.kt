package jp.ogiwara.test.lobitest.reply

import android.content.Context
import android.databinding.DataBindingUtil
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import com.squareup.picasso.Picasso
import jp.ogiwara.cycle.BasicStore
import jp.ogiwara.lobiapi.model.Chat
import jp.ogiwara.test.lobitest.R
import jp.ogiwara.test.lobitest.databinding.ReplyViewBinding

//goo,boo,bookmark
class ReplyView : FrameLayout{

    val binding: ReplyViewBinding
    val store: BasicStore<State>
    var chat: Chat? = null

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
        binding = DataBindingUtil.inflate(inflater,R.layout.reply_view,this,true)
        store = BasicStore(State(),reducer,context)
        binding.store = store
        binding.handler = this
    }


    fun setReply(c: Chat){
        store.dispatch(LOADED(c))
        Picasso.with(context).load(c.user.icon).into(binding.imageUserIcon)
        chat = c
    }

    fun onClickGoo(){
        store.dispatch(GOO(chat!!))
    }

    fun onClickBoo(){
        store.dispatch(BOO(chat!!))
    }

    fun onClickBookmark(){
        store.dispatch(BOOKMARK(chat!!))
    }

}