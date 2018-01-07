package jp.ogiwara.test.lobitest.activity_list

import android.content.Context
import android.util.Log
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import jp.ogiwara.cycle.Action
import jp.ogiwara.cycle.BasicStore
import jp.ogiwara.cycle.Dispatcher
import jp.ogiwara.test.lobitest.other.strategy.activity_list.MeActivitiesStrategy
import jp.ogiwara.test.lobitest.repo

internal val dispatcher = object : Dispatcher<State>{

    val disposers = mutableListOf<Disposable>()

    override fun dispatch(store: BasicStore<State>, action: Action, context: Context) {
        Log.i("ActivityListDispatcher",action.toString())
        when(action){
            is LOAD -> {
                val state = store.state
                state.loading.set(true)
                val stream = state.strategy.execute({
                    state.token = it.token
                    store.dispatch(LOADED(it.list))
                },state.token,false)
                disposers.add(stream)
            }
            is RELOAD -> {
                val state = store.state
                state.list.clear()
                state.token = null
                state.loading.set(true)
                val stream = store.state.strategy.execute({
                    state.token = it.token
                    store.dispatch(LOADED(it.list))
                },null,true)
                disposers.add(stream)
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