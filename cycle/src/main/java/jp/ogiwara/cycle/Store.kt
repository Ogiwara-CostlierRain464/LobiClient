package jp.ogiwara.cycle

import android.content.Context

interface Store {
    val state: State
    val dispatcher: Dispatcher<out State>
    val context: Context

    fun dispatch(action: Action)
}