package jp.ogiwara.test.lobitest.search

import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import jp.ogiwara.test.lobitest.R
import jp.ogiwara.test.lobitest.chat_list.ChatListFragment
import jp.ogiwara.test.lobitest.group_list.GroupListFragment
import jp.ogiwara.test.lobitest.other.strategy.chat_list.SearchChatListStrategy
import jp.ogiwara.test.lobitest.other.strategy.group_list.SearchGroupsStrategy
import jp.ogiwara.test.lobitest.other.strategy.user_list.GroupMemberUserListStrategy
import jp.ogiwara.test.lobitest.other.strategy.user_list.SearchUserListStrategy
import jp.ogiwara.test.lobitest.user_list.UserListFragment
import org.jetbrains.anko.find

class SearchActivity: AppCompatActivity() {

    companion object{
        const val EXTRA_QUERY = "q"
    }

    lateinit var query: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        if(savedInstanceState == null){
            query = intent.getStringExtra(EXTRA_QUERY)
        }else{
            query = savedInstanceState.getString(EXTRA_QUERY)
        }

        setUpView()
    }

    private fun setUpView(){
        val toolbar = find<Toolbar>(R.id.toolbar)
        val tabLayout = find<TabLayout>(R.id.tab_layout)
        val viewPager = find<ViewPager>(R.id.view_pager)

        toolbar.title = getString(R.string.search_result_for).replace("%q",query)

        viewPager.adapter = ViewPagerAdapter(supportFragmentManager)
        viewPager.offscreenPageLimit = 3

        tabLayout.setupWithViewPager(viewPager)
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)

        outState?.putString(EXTRA_QUERY,query)
    }

    private inner class ViewPagerAdapter(fm: FragmentManager): FragmentPagerAdapter(fm){
        override fun getCount() = 3
        override fun getItem(position: Int): Fragment {
            return when(position){
                0 -> GroupListFragment.newInstance(SearchGroupsStrategy(query))
                1 -> ChatListFragment.newInstance(SearchChatListStrategy(query))
                2 -> UserListFragment.newInstance(SearchUserListStrategy(query))
                else -> throw IllegalArgumentException()
            }
        }

        override fun getPageTitle(position: Int): CharSequence {
            return when(position){
                0 -> getString(R.string.group)
                1 -> getString(R.string.chat)
                2 -> getString(R.string.user)
                else -> throw IllegalArgumentException()
            }
        }
    }
}