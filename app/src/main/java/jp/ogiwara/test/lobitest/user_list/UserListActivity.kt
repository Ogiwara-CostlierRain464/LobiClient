package jp.ogiwara.test.lobitest.user_list

import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.widget.FrameLayout
import io.reactivex.android.schedulers.AndroidSchedulers
import jp.ogiwara.test.lobitest.E
import jp.ogiwara.test.lobitest.R
import jp.ogiwara.test.lobitest.other.strategy.user_list.GroupLeaderUserListStrategy
import jp.ogiwara.test.lobitest.other.strategy.user_list.GroupMemberUserListStrategy
import jp.ogiwara.test.lobitest.other.strategy.user_list.GroupSubLeaderUserListStrategy
import jp.ogiwara.test.lobitest.repo
import org.jetbrains.anko.find

class UserListActivity: AppCompatActivity(){

    object EXTRA{
        const val GROUP_ID = "group_id"
    }

    lateinit var groupId: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_list)

        if(savedInstanceState == null){
            groupId = intent.getStringExtra(EXTRA.GROUP_ID)
        }else{
            groupId = savedInstanceState.getString(EXTRA.GROUP_ID)
        }

        setUpView()
    }

    private fun setUpView(){
        val toolbar = find<Toolbar>(R.id.toolbar)
        val tabLayout = find<TabLayout>(R.id.tab_layout)
        val viewPager = find<ViewPager>(R.id.view_pager)

        repo.environment.getGroup(repo,groupId).observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    toolbar.title = getString(R.string.group_member_of).replace("%q", it.name)
                },E)

        viewPager.adapter = ViewPagerAdapter(supportFragmentManager)
        viewPager.offscreenPageLimit = 3

        tabLayout.setupWithViewPager(viewPager)

        toolbar.setNavigationIcon(R.drawable.back)
        toolbar.setNavigationOnClickListener {
            finish()
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        outState.putString(EXTRA.GROUP_ID,groupId)
    }

    private inner class ViewPagerAdapter(fm: FragmentManager): FragmentPagerAdapter(fm){
        override fun getCount() = 3

        override fun getItem(position: Int): Fragment {
            return UserListFragment.newInstance(when(position){
                0 -> GroupLeaderUserListStrategy(groupId)
                1 -> GroupSubLeaderUserListStrategy(groupId)
                2 -> GroupMemberUserListStrategy(groupId)
                else -> throw IllegalArgumentException()
            })
        }

        override fun getPageTitle(position: Int): CharSequence {
            return when(position){
                0 -> getString(R.string.leader)
                1 -> getString(R.string.sub_leader)
                2 -> getString(R.string.member)
                else -> throw IllegalArgumentException()
            }
        }
    }
}
