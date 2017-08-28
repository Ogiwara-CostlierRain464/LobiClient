package jp.ogiwara.cycle

import android.content.Context

class BasicStore<S>(val state: S,val reducer: Reducer<S>, val context: Context){

    var beforeDispatch: (Action) -> Unit = {} //ログキャッチとかに
    var afterDispatch: (Action) -> Unit = {}

    fun dispatch(action: Action){
        beforeDispatch(action)
        reducer.reduce(this,action,context)
        afterDispatch(action)
    }
}