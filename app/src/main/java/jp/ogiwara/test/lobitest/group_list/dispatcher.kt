package jp.ogiwara.test.lobitest.group_list

import android.content.Context
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.functions.BiConsumer
import jp.ogiwara.cycle.Action
import jp.ogiwara.cycle.BasicStore
import jp.ogiwara.cycle.Dispatcher
import jp.ogiwara.lobiapi.model.Group
import jp.ogiwara.lobirepository.extention.ReactiveVal
import jp.ogiwara.test.lobitest.repo

internal val dispatcher = object: Dispatcher<State>{

    val disposers = mutableListOf<Disposable>()

    override fun dispatch(store: BasicStore<State>, action: Action, context: Context) {
        when(action){
            is LOAD -> {
                val state = store.state
                state.loading.set(true)
                val s = state.strategy.execute({
                    state.token = it.token
                    store.dispatch(LOADED(it.list))
                },state.token,false)
                disposers.add(s)
            }
            is RELOAD -> {
                val state = store.state
                store.state.list.clear()
                state.token = null
                store.state.loading.set(true)
                val s = store.state.strategy.execute({
                    state.token = it.token
                    store.dispatch(LOADED(it.list))
                },null,true)
                disposers.add(s)
            }
            is LOADED -> {
                store.state.list.addAll(action.list)
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