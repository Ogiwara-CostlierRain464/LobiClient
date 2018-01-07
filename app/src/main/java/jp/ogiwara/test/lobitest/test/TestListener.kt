package jp.ogiwara.test.lobitest.test

import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.LifecycleObserver
import android.arch.lifecycle.OnLifecycleEvent
import android.content.Context
import android.util.Log
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import java.util.concurrent.TimeUnit

class TestListener(val context: Context,
                   val lifecycle: Lifecycle,
                   val callback: (Long) -> Unit) : LifecycleObserver{

    companion object {
        val stream = Observable.interval(100L,TimeUnit.MILLISECONDS)
    }

    private var enabled = false

    private var disposable: Disposable? = null

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun start(){
        Log.i("TestListener","start")
            disposable = stream.observeOn(AndroidSchedulers.mainThread()).subscribe {
                callback(it)
            }
    }

    /*fun enable(){
        enabled = true
        if(lifecycle.currentState.isAtLeast(Lifecycle.State.STARTED)){ //later call back...

        }
    }*/

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun stop(){
        Log.i("TestListener","stop")
        disposable?.dispose()
    }
}