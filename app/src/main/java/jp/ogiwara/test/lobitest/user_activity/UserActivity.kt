package jp.ogiwara.test.lobitest.user_activity

import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.support.design.widget.CollapsingToolbarLayout
import android.support.design.widget.FloatingActionButton
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso
import com.squareup.picasso.Target
import com.stfalcon.frescoimageviewer.ImageViewer
import io.reactivex.android.schedulers.AndroidSchedulers
import jp.ogiwara.test.lobitest.E
import jp.ogiwara.test.lobitest.R
import jp.ogiwara.test.lobitest.activity_list.ActivityFragment
import jp.ogiwara.test.lobitest.other.strategy.activity_list.MeActivitiesStrategy
import jp.ogiwara.test.lobitest.other.strategy.activity_list.UserActivitiesStrategy
import jp.ogiwara.test.lobitest.repo
import org.jetbrains.anko.find


class UserActivity: AppCompatActivity(){

    object EXTRA{
        const val USER_ID = "user_id"
    }

    lateinit var userId: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user)

        if(savedInstanceState == null){
            userId = intent.getStringExtra(EXTRA.USER_ID)
        }else{
            userId = savedInstanceState.getString(EXTRA.USER_ID)
        }

        setUpView()
    }

    private fun setUpView(){
        val icon = find<ImageView>(R.id.image_user_icon)
        val container = find<FrameLayout>(R.id.container)
        val toolbar = find<Toolbar>(R.id.toolbar)
        val coolasp = find<CollapsingToolbarLayout>(R.id.toolbar_layout)
        val userDescription = find<TextView>(R.id.text_user_description)

        repo.environment.getUser(repo,userId).observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    Picasso.with(applicationContext).load(it.icon.highQuality).into(icon)
                    Picasso.with(applicationContext).load(it.cover.veryHighQuality).into(object: Target{
                        override fun onBitmapLoaded(bitmap: Bitmap?, from: Picasso.LoadedFrom?) {
                            coolasp.background = BitmapDrawable(resources,bitmap)

                            icon.setOnClickListener { j ->
                                ImageViewer.Builder(this@UserActivity, arrayListOf(it.icon.rawQuality))
                                        .setStartPosition(0)
                                        .show()
                            }
                        }

                        override fun onBitmapFailed(errorDrawable: Drawable?) = Unit
                        override fun onPrepareLoad(placeHolderDrawable: Drawable?) = Unit
                    })

                    coolasp.title = it.name

                    userDescription.text = it.description
                },E)

        supportFragmentManager.beginTransaction()
                .replace(R.id.container,ActivityFragment.newInstance(UserActivitiesStrategy(userId)))
                .commit()

        toolbar.setNavigationIcon(R.drawable.back)
        toolbar.setNavigationOnClickListener {
            this@UserActivity.finish()
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        outState.putString(EXTRA.USER_ID,userId)
    }
}