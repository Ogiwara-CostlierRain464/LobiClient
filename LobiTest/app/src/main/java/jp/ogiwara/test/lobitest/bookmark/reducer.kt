package jp.ogiwara.test.lobitest.bookmark

import android.content.Context
import jp.ogiwara.cycle.Action
import jp.ogiwara.cycle.BasicStore
import jp.ogiwara.cycle.Reducer
import jp.ogiwara.lobiapi.model.Chat
import jp.ogiwara.lobirepository.extention.bookmark
import jp.ogiwara.test.lobitest.repo
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.launch

internal val reducer = object: Reducer<State>{
    override fun reduce(store: BasicStore<State>, action: Action,c: Context) {
        when(action){
            is LOAD -> {
                launch(UI){
                    store.dispatch(LOADED(repo.me.await().bookmark(repo).await().data))
                }
                store.state.loading.set(true)
            }
            is LOADED -> {
                store.state.chats.clear()
                store.state.chats.addAll(action.list)
                store.state.loading.set(false)
            }
        }
    }
}
