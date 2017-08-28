package jp.ogiwara.test.lobitest.base

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.support.v7.widget.Toolbar
import android.view.View
import jp.ogiwara.test.lobitest.R
import jp.ogiwara.test.lobitest.account.AccountFragment
import jp.ogiwara.test.lobitest.activity_list.ActivityFragment
import jp.ogiwara.test.lobitest.group_tab.GroupTabFragment
import jp.ogiwara.test.lobitest.login.LoginActivity
import jp.ogiwara.test.lobitest.repo
import org.jetbrains.anko.find

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setUpView()
    }

    private fun setUpView(){
        val toolBar = find<Toolbar>(R.id.toolbar)
        val navigation = find<BottomNavigationView>(R.id.navigation)

        setSupportActionBar(toolBar)
        navigation.setOnNavigationItemSelectedListener {
            var fragment: Fragment
            when(it.itemId){
                R.id.navigation_group -> {
                    toolBar.title = getString(R.string.group)
                    fragment = GroupTabFragment.newInstance()
                }
                R.id.navigation_activity -> {
                    toolBar.title = getString(R.string.activity)
                    fragment = ActivityFragment()
                }
                R.id.navigation_account -> {
                    toolBar.title = getString(R.string.account)
                    fragment = AccountFragment.newInstance()
                }
                else -> {
                    fragment = AccountFragment.newInstance()
                }
            }
            supportFragmentManager.beginTransaction().replace(R.id.fragment_container,fragment).commit()
            true
        }
        loadFirstFragment()
    }

    private fun loadFirstFragment(){
        supportFragmentManager.beginTransaction().replace(R.id.fragment_container,GroupTabFragment.newInstance()).commit()
    }

    override fun onDestroy() {
        super.onDestroy()

        repo.save()
    }
}
