package jp.ogiwara.test.lobitest.group_tab

import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.view.ViewPager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.RelativeLayout
import jp.ogiwara.test.lobitest.R
import jp.ogiwara.test.lobitest.chat_list.ChatListFragment
import jp.ogiwara.test.lobitest.group_list.GroupListFragment
import jp.ogiwara.test.lobitest.other.strategy.chat_list.MeBookmarkChatListStrategy
import jp.ogiwara.test.lobitest.other.strategy.group_list.PrivateGroupsStrategy
import jp.ogiwara.test.lobitest.other.strategy.group_list.PublicGroupsStrategy
import org.jetbrains.anko.find


class GroupTabFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val root =  inflater!!.inflate(R.layout.fragment_group,container,false)
        setUpView(root)
        return root
    }

    private fun setUpView(root: View){
        val tabLayout = root.find<TabLayout>(R.id.tab_layout)
        val viewPager = root.find<ViewPager>(R.id.view_pager)

        viewPager.adapter = ViewPagerAdapter(childFragmentManager)
        viewPager.offscreenPageLimit = 3

        tabLayout.setupWithViewPager(viewPager)
    }

    private inner class ViewPagerAdapter(fm: FragmentManager): FragmentPagerAdapter(fm){
        override fun getCount() = 3

        override fun getItem(position: Int): Fragment = when(position){
            0 -> GroupListFragment.newInstance(PublicGroupsStrategy())
            1 -> GroupListFragment.newInstance(PrivateGroupsStrategy())
            2 -> ChatListFragment.newInstance(MeBookmarkChatListStrategy())
            else -> throw IllegalArgumentException()
        }

        override fun getPageTitle(position: Int): CharSequence = when(position){
            0 -> getString(R.string.public_group)
            1 -> getString(R.string.private_group)
            2 -> getString(R.string.bookmark)
            else -> throw IllegalArgumentException()
        }
    }
}