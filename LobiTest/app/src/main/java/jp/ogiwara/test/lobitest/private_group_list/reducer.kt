package jp.ogiwara.test.lobitest.private_group_list

import android.content.Context
import jp.ogiwara.cycle.Action
import jp.ogiwara.cycle.BasicStore
import jp.ogiwara.cycle.Reducer
import jp.ogiwara.lobirepository.extention.privateGroupAll
import jp.ogiwara.test.lobitest.other.view.group_list.State
import jp.ogiwara.test.lobitest.repo
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.delay
import kotlinx.coroutines.experimental.launch
import java.util.concurrent.TimeUnit

internal val reducer = object: Reducer<State> {
    override fun reduce(store: BasicStore<State>, action: Action, c: Context) {
        when(action){
            is LOAD -> {
                store.state.loading.set(true)
                launch(UI){
                    val groups = repo.me.await().privateGroupAll(repo).await()
                    store.dispatch(LOADED(groups))
                }
            }
            is LOADED -> {
                store.state.groups.clear()
                store.state.groups.addAll(action.list.first().items)
                store.state.loading.set(false)
            }
        }
    }
}

