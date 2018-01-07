package jp.ogiwara.test.lobitest.user_list

import android.content.Context
import jp.ogiwara.cycle.Action
import jp.ogiwara.cycle.BasicStore
import jp.ogiwara.cycle.Dispatcher

internal val dispatcher = Dispatcher<State>{ store, action, context ->
    val state = store.state
    when(action){
        is LOAD -> {
            state.loading.set(true)
            state.strategy.execute({
                state.token = it.token

                store.dispatch(LOADED(it.list))
            },state.token,false)
        }
        is RELOAD -> {
            state.token = null
            state.list.clear()
            state.loading.set(true)
            state.strategy.execute({
                state.token = it.token

                store.dispatch(LOADED(it.list))
            },null,false)
        }
        is LOADED -> {
            state.list.addAll(action.list)

            //state.list.addAll(action.list)
            state.loading.set(false)
        }
    }
}