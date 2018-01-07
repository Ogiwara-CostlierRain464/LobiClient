package jp.ogiwara.test.lobitest.other.view

import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.LifecycleOwner
import android.arch.lifecycle.LifecycleRegistry
import android.content.Context
import android.util.AttributeSet
import android.widget.FrameLayout

@Deprecated("")
open class LifecycleCustomView @JvmOverloads constructor(
        context: Context,
        attrs: AttributeSet? = null
): FrameLayout(context,attrs), LifecycleOwner{

    private val lifecycleRegistry = LifecycleRegistry(this)

    override fun getLifecycle(): Lifecycle = lifecycleRegistry

    override fun onAttachedToWindow() {
        lifecycleRegistry.markState(Lifecycle.State.STARTED)

        super.onAttachedToWindow()
    }

    override fun onDetachedFromWindow() {
        lifecycleRegistry.markState(Lifecycle.State.DESTROYED)

        super.onDetachedFromWindow()
    }
}