package jp.ogiwara.test.lobitest.base

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.view.ViewPager
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.Gravity
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso
import io.reactivex.android.schedulers.AndroidSchedulers
import jp.ogiwara.test.lobitest.E
import jp.ogiwara.test.lobitest.R
import jp.ogiwara.test.lobitest.activity_list.ActivityFragment
import jp.ogiwara.test.lobitest.group_tab.GroupTabFragment
import jp.ogiwara.test.lobitest.other.strategy.activity_list.InfoActivitiesStrategy
import jp.ogiwara.test.lobitest.repo
import jp.ogiwara.test.lobitest.search.SearchFragment
import org.jetbrains.anko.find

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setUpView()
    }

    private fun setUpView(){
        val toolBar = find<Toolbar>(R.id.toolbar)
        val title = find<TextView>(R.id.text_toolbar)
        val navigation = find<BottomNavigationView>(R.id.navigation)
        val pager = find<ViewPager>(R.id.view_pager)
        val image = find<ImageView>(R.id.image_user_icon)
        val drawer = find<DrawerLayout>(R.id.drawer_layout)

        pager.adapter = MainPagerAdapter(supportFragmentManager)
        pager.offscreenPageLimit = 3

        setSupportActionBar(toolBar)

        navigation.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.navigation_group -> showTab1(pager,title)
                R.id.navigation_activity -> showTab2(pager,title)
                R.id.navigation_search -> showTab3(pager,title)
                else -> throw IllegalArgumentException()
            }
            true
        }


        repo.me().observeOn(AndroidSchedulers.mainThread())
                .subscribe({ me ->
                    Picasso.with(applicationContext).load(me.icon.lowQuality).into(image)

                    image.setOnClickListener {
                        drawer.openDrawer(Gravity.RIGHT)
                    }
                },E)

        loadFirstTitle(title)

    }


    override fun onDestroy() {
        super.onDestroy()

        repo.save()
    }

    private fun loadFirstTitle(t: TextView){
        t.text = getString(R.string.group)
    }

    private fun showTab1(pager: ViewPager,t: TextView){
        pager.setCurrentItem(0,false)
        t.text = getString(R.string.group)
    }

    private fun showTab2(pager: ViewPager,t: TextView){
        pager.setCurrentItem(1,false)
        t.text = getString(R.string.activity)
    }

    private fun showTab3(pager: ViewPager,t: TextView){
        pager.setCurrentItem(2,false)
        t.text = getString(R.string.search)
    }
}

class MainPagerAdapter(fm: FragmentManager): FragmentPagerAdapter(fm){

    override fun getCount() = 3
    override fun getItem(position: Int): Fragment {
        return when(position){
            0 -> GroupTabFragment()
            1 -> ActivityFragment.newInstance(InfoActivitiesStrategy())
            2 -> SearchFragment()
            else -> throw IllegalArgumentException()
        }
    }
}
