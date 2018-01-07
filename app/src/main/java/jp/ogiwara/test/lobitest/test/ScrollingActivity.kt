package jp.ogiwara.test.lobitest.test
import android.arch.lifecycle.LifecycleRegistry
import android.arch.lifecycle.LifecycleRegistryOwner
import android.os.Bundle
import android.support.design.widget.CollapsingToolbarLayout
import android.support.design.widget.FloatingActionButton
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.View
import android.widget.TextView

import jp.ogiwara.test.lobitest.R
import org.jetbrains.anko.find

class ScrollingActivity : AppCompatActivity(){

    val number: TextView by lazy {
        find<TextView>(R.id.text_number)
    }

   // private val lifecycleRegistry = LifecycleRegistry(this)

    private lateinit var listener : TestListener

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scrolling)

        listener = TestListener(this,lifecycle,{
            number.text = it.toString()
        })

        lifecycle.addObserver(listener)
    }
}
