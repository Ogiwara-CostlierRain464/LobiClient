package jp.ogiwara.test.lobitest.public_group_list

import android.content.Context
import jp.ogiwara.cycle.Action
import jp.ogiwara.cycle.BasicStore
import jp.ogiwara.cycle.Reducer
import jp.ogiwara.lobiapi.model.Group
import jp.ogiwara.lobirepository.extention.publicGroupAll
import jp.ogiwara.test.lobitest.other.view.group_list.State
import jp.ogiwara.test.lobitest.repo
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.launch

internal val reducer = object:  Reducer<State>{
    override fun reduce(store: BasicStore<State>, action: Action,c: Context) {
        when(action){
            is LOAD -> {
                store.state.loading.set(true)
                launch(UI){
                    val groups = repo.me.await().publicGroupAll(repo).await()
                    store.dispatch(LOADED(groups))
                }
            }
            is LOADED -> {
                store.state.groups.clear()
                store.state.groups.addAll(action.list)
                store.state.loading.set(false)
            }
        }
    }
}

