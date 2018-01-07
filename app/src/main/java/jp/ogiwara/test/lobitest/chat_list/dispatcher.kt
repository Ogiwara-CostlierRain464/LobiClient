package jp.ogiwara.test.lobitest.chat_list

import android.content.Context
import io.reactivex.disposables.Disposable
import jp.ogiwara.cycle.Action
import jp.ogiwara.cycle.BasicStore
import jp.ogiwara.cycle.Dispatcher

internal val dispatcher = object : Dispatcher<State>{

    val disposers = mutableListOf<Disposable>()

    override fun dispatch(store: BasicStore<State>, action: Action, context: Context) {
        when(action){
            is LOAD -> {
                store.state.loading.set(true)
                val s = store.state.strategy.execute({
                    store.state.token = it.token

                    store.dispatch(LOADED(it.list))
                },store.state.token,false)
                disposers.add(s)
            }
            is RELOAD -> {
                store.state.token = null
                store.state.list.clear()
                store.state.loading.set(true)
                val s = store.state.strategy.execute({
                    store.state.token = it.token

                    store.dispatch(LOADED(it.list))
                },null,false)
                disposers.add(s)
            }
            is LOADED -> {
                action.list.forEach {
                    store.state.list.add(it)
                }
                //store.state.list.addAll(action.list)
                store.state.loading.set(false)
            }
            is DESTROY -> {
                disposers.forEach {
                    it.dispose()
                }
            }
        }
    }
}
