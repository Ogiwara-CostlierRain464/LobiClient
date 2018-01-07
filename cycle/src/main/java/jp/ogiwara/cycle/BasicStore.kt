package jp.ogiwara.cycle

import android.content.Context

//sample implement of Store
class BasicStore<S: State>(override val state: S,
                           override val dispatcher: Dispatcher<S>,
                           override val context: Context): Store{

    var beforeDispatch: (Action) -> Unit = {} //ログキャッチとかに
    var afterDispatch: (Action) -> Unit = {}

    override fun dispatch(action: Action){
        beforeDispatch(action)
        dispatcher.dispatch(this,action,context)
        afterDispatch(action)
    }
}