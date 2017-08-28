package jp.ogiwara.cycle

import android.content.Context

interface Reducer<S>{
    fun reduce(store: BasicStore<S>,action: Action,context: Context)
}