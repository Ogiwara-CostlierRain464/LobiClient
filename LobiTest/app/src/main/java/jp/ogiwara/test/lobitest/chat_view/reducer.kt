package jp.ogiwara.test.lobitest.chat_view

import android.content.Context
import jp.ogiwara.cycle.Action
import jp.ogiwara.cycle.BasicStore
import jp.ogiwara.cycle.Reducer

internal val reducer = object: Reducer<State>{
    override fun reduce(store: BasicStore<State>, action: Action,c: Context) {
        when(action){
            is LOADED -> {
                //chatから抜き出し
                store.state.replyCount.set(action.chat.replies?.count ?: 0)
                store.state.userName.set(action.chat.user.name)
                store.state.message.set(action.chat.message)
                //store.state.gooIng.set(action.chat.)
                store.state.booIng.set(if(action.chat.booed == 1) true else false)
                store.state.booCount.set(action.chat.boos_count)
                store.state.bookmarkIng.set(if(action.chat.is_me_bookmarked == 1) true else false)
                store.state.bookmarkCount.set(action.chat.bookmarks_count)
            }
        }
    }
}