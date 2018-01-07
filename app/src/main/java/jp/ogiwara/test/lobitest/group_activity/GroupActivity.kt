package jp.ogiwara.test.lobitest.group_activity

import android.app.Activity
import android.content.Intent
import android.content.res.ColorStateList
import android.graphics.Bitmap
import android.graphics.Color
import android.graphics.ColorFilter
import android.graphics.PorterDuff
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.support.constraint.ConstraintLayout
import android.support.design.widget.AppBarLayout
import android.support.design.widget.FloatingActionButton
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.view.ViewPager
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.widget.ListView
import android.widget.RelativeLayout
import com.squareup.picasso.Picasso
import com.squareup.picasso.Target
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import jp.ogiwara.cycle.BasicStore
import jp.ogiwara.lobiapi.model.Chat
import jp.ogiwara.lobiapi.model.Group
import jp.ogiwara.test.lobitest.R
import jp.ogiwara.test.lobitest.repo
import android.support.v7.graphics.Palette
import android.view.View
import android.widget.Button
import jp.ogiwara.lobirepository.extention.isJoined
import jp.ogiwara.lobirepository.extention.postChat
import jp.ogiwara.lobirepository.extention.postGroupJoin
import jp.ogiwara.test.lobitest.E
import jp.ogiwara.test.lobitest.chat_edit.ChatEditActivity
import jp.ogiwara.test.lobitest.chat_list.ChatListFragment
import jp.ogiwara.test.lobitest.group_info.GroupInfoFragment
import jp.ogiwara.test.lobitest.other.strategy.chat_list.GroupBookmarkChatListStrategy
import jp.ogiwara.test.lobitest.other.strategy.chat_list.GroupNormalChatListStrategy
import jp.ogiwara.test.lobitest.other.util.math.isWhite
import org.jetbrains.anko.*

class GroupActivity: AppCompatActivity(){

    object REQUEST_CODE{
        const val CHAT_EDIT = 1
    }

    companion object{
        const val EXTRA_GROUP_ID = "group_id"
    }

    lateinit var groupId: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_group)

        if(savedInstanceState == null){
            groupId = intent.getStringExtra(EXTRA_GROUP_ID)
        }else{
            groupId = savedInstanceState.getString(EXTRA_GROUP_ID)
        }

        setUpView()
    }

    private fun setUpView(){
        val toolbar = find<Toolbar>(R.id.toolbar)
        val appbar = find<AppBarLayout>(R.id.app_bar)
        val tabLayout = find<TabLayout>(R.id.tab_layout)
        val viewPager = find<ViewPager>(R.id.view_pager)
        val bg = find<RelativeLayout>(R.id.group_background)
        val chat = find<FloatingActionButton>(R.id.button_chat)
        val joinLayout = find<ConstraintLayout>(R.id.layout_join)
        val joinButton = find<Button>(R.id.button_join)

        viewPager.adapter = ViewPagerAdapter(supportFragmentManager)
        viewPager.offscreenPageLimit = 3

        tabLayout.setupWithViewPager(viewPager)

        repo.environment.getGroup(repo,groupId).observeOn(AndroidSchedulers.mainThread())
                .subscribe({ group ->
                    toolbar.title = group.name

                    if(group.isJoined){
                        chat.visibility = View.VISIBLE
                        joinLayout.visibility = View.INVISIBLE
                    }else{
                        chat.visibility = View.INVISIBLE
                        joinLayout.visibility = View.VISIBLE
                    }

                    joinButton.setOnClickListener {
                        group.postGroupJoin(repo).observeOn(AndroidSchedulers.mainThread())
                                .subscribe({
                                    chat.visibility = View.VISIBLE
                                    joinLayout.visibility = View.INVISIBLE
                                    toast(R.string.could_join_group)
                                },{toast(R.string.cannot_join_group)})
                    }

                    Picasso.with(applicationContext).load(group.wallpaper?.veryHighQuality)
                            .into(object: Target{
                                override fun onBitmapLoaded(bitmap: Bitmap?, from: Picasso.LoadedFrom?){
                                    bg.background = BitmapDrawable(resources,bitmap)

                                    val p = Palette.from(bitmap).generate()

                                    val sp = p.darkVibrantSwatch
                                    if(sp != null){
                                        appbar.setBackgroundColor(sp.rgb)

                                        if(isWhite(sp.hsl[2])){
                                            //set black
                                            toolbar.navigationIcon?.setColorFilter(resources.getColor(android.R.color.black),PorterDuff.Mode.SRC_ATOP)
                                            toolbar.setTitleTextColor(Color.BLACK)
                                            tabLayout.setTabTextColors(Color.BLACK,Color.BLACK)
                                            tabLayout.setSelectedTabIndicatorColor(sp.titleTextColor)
                                            chat.backgroundTintList = ColorStateList.valueOf(sp.rgb)
                                            chat.imageTintList = ColorStateList.valueOf(Color.BLACK)
                                        }else{
                                            //set WHITE
                                            toolbar.navigationIcon?.setColorFilter(resources.getColor(android.R.color.white),PorterDuff.Mode.SRC_ATOP)
                                            toolbar.setTitleTextColor(Color.WHITE)
                                            tabLayout.setTabTextColors(Color.WHITE,Color.WHITE)
                                            tabLayout.setSelectedTabIndicatorColor(sp.titleTextColor)
                                            chat.backgroundTintList = ColorStateList.valueOf(sp.rgb)
                                            chat.imageTintList = ColorStateList.valueOf(Color.WHITE)
                                        }
                                    }
                                }

                                override fun onPrepareLoad(placeHolderDrawable: Drawable?) = Unit
                                override fun onBitmapFailed(errorDrawable: Drawable?) = Unit
                            })
                },E)

        toolbar.setNavigationIcon(R.drawable.back)
        toolbar.setNavigationOnClickListener {
            this@GroupActivity.finish()
        }

        chat.setOnClickListener {
            startActivityForResult(intentFor<ChatEditActivity>(),REQUEST_CODE.CHAT_EDIT)
        }
    }

    private fun setColor(isWhite: Boolean){

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when(requestCode){
            REQUEST_CODE.CHAT_EDIT -> {
                if(resultCode == Activity.RESULT_OK){
                    val chatText = data!!.getStringExtra(ChatEditActivity.EXTRA_CHAT)

                    repo.environment.getGroup(repo,groupId).observeOn(AndroidSchedulers.mainThread())
                            .subscribe({
                                it.postChat(repo,chatText).observeOn(AndroidSchedulers.mainThread())
                                        .subscribe({},{})
                            },E)
                }
            }
        }
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)

        outState?.putString(EXTRA_GROUP_ID,groupId)
    }

    private inner class ViewPagerAdapter(fm: FragmentManager): FragmentPagerAdapter(fm){
        override fun getCount() = 3
        override fun getItem(position: Int): Fragment {
            return when(position){
                0 -> ChatListFragment.newInstance(GroupNormalChatListStrategy(groupId))
                1 -> ChatListFragment.newInstance(GroupBookmarkChatListStrategy(groupId))
                2 -> GroupInfoFragment.newInstance(groupId)
                else -> throw IllegalArgumentException()
            }
        }

        override fun getPageTitle(position: Int): CharSequence {
            return when(position){
                0 -> getString(R.string.chat)
                1 -> getString(R.string.group_bookmarks)
                2 -> getString(R.string.group_info)
                else -> throw IllegalArgumentException()
            }
        }
    }
}