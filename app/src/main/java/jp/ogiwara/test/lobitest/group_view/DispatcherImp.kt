package jp.ogiwara.test.lobitest.group_view

import android.content.Context
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import jp.ogiwara.cycle.Action
import jp.ogiwara.cycle.BasicStore
import jp.ogiwara.cycle.Dispatcher


class DispatcherImp : Dispatcher<State> {

    var alive: Boolean = true
    var disposer: Disposable? = null

    override fun dispatch(store: BasicStore<State>, action: Action, context: Context) {
        when(action){
            is OBSERVE -> {
                if(alive){
                    disposer = action.e.observeOn(AndroidSchedulers.mainThread()).subscribe({
                        action.callBack(it)
                    }, action.error)
                }
            }
            is DESTROY -> {
                disposer?.dispose()
                alive = false
            }
        }
    }
}