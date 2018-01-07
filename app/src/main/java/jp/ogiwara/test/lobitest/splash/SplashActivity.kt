package jp.ogiwara.test.lobitest.splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.support.v7.app.AppCompatActivity
import android.view.KeyEvent
import io.reactivex.android.schedulers.AndroidSchedulers
import jp.ogiwara.lobirepository.extention.getPublicGroups
import jp.ogiwara.test.lobitest.R
import jp.ogiwara.test.lobitest.base.MainActivity
import jp.ogiwara.test.lobitest.login.LoginActivity
import jp.ogiwara.test.lobitest.repo
import org.jetbrains.anko.intentFor

class SplashActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        Handler().postDelayed({
            val intent =  Intent(this@SplashActivity,if(repo.isLogined()) MainActivity::class.java else LoginActivity::class.java)
            startActivity(intent)
            finish()
        },1500)
    }

    //バックキー無効
    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if(keyCode == KeyEvent.KEYCODE_BACK){
            return false
        }

        return super.onKeyDown(keyCode, event)
    }
}