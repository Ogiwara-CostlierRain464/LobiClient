package jp.ogiwara.test.lobitest

import android.content.Context
import android.support.annotation.IdRes
import android.support.annotation.IntegerRes
import android.util.Log
import android.view.View
import android.widget.Toast
import org.jetbrains.anko.find

fun View.hide(){
    this.layoutParams.width = 0
    this.layoutParams.height = 0
    this.requestLayout()
}

fun <T: View> View.bindView(@IdRes id: Int): Lazy<T> = lazy {
    findViewById<T>(id)
}

val Context.E: (Throwable) -> Unit
    get() = { Toast.makeText(this,R.string.network_error,Toast.LENGTH_SHORT).show() }

val Context.DEBUG: (Throwable) -> Unit
    get() = { println(it.message) }
