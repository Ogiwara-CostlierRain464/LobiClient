package jp.ogiwara.test.lobitest.activity_list

import android.content.Context
import jp.ogiwara.cycle.Action
import jp.ogiwara.cycle.BasicStore
import jp.ogiwara.cycle.Reducer
import jp.ogiwara.test.lobitest.repo
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.run

internal val reducer = object: Reducer<State>{
    override fun reduce(store: BasicStore<State>, action: Action, context: Context) {
        when(action){
            is LOAD -> {
                run(UI){
                    val a = repo.me.activity(repo)
                }
                store.state.loading.set(true)
            }
            is LOADED -> {
                store.state.loading.set(false)
            }
        }
    }
}