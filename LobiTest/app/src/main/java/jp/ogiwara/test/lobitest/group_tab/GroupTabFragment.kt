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
import jp.ogiwara.test.lobitest.R
import jp.ogiwara.test.lobitest.bookmark.BookMarkFragment
import jp.ogiwara.test.lobitest.private_group_list.PrivateGroupListFragment
import jp.ogiwara.test.lobitest.public_group_list.PublicGroupListFragment
import org.jetbrains.anko.find


class GroupTabFragment constructor() : Fragment() {

    companion object{
        fun newInstance() = GroupTabFragment()
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val root =  inflater!!.inflate(R.layout.fragment_group,container,false)
        setUpView(root)
        return root
    }

    private fun setUpView(root: View){
        val tabLayout = root.find<TabLayout>(R.id.tab_layout)
        val viewPager = root.find<ViewPager>(R.id.view_pager)

        //TODO Smooth-Scroll

        viewPager.adapter = ViewPagerAdapter(fragmentManager)
        ///viewPager.offscreenPageLimit = 3

        tabLayout.setupWithViewPager(viewPager)

        tabLayout.getTabAt(0)?.setText(R.string.public_group)
        tabLayout.getTabAt(1)?.setText(R.string.private_group)
        tabLayout.getTabAt(2)?.setText(R.string.bookmark)
    }

    private class ViewPagerAdapter(fm: FragmentManager): FragmentPagerAdapter(fm){
        override fun getCount() = 3

        override fun getItem(position: Int): Fragment {
            return when(position){
                0 -> PublicGroupListFragment.newInstance()
                1 -> PrivateGroupListFragment.newInstance()
                else -> BookMarkFragment.newInstance()
            }
        }
    }
}