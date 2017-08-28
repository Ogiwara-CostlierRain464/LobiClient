package jp.ogiwara.test.lobitest.login

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.Button
import android.widget.Toast
import jp.ogiwara.test.lobitest.R
import jp.ogiwara.test.lobitest.base.MainActivity
import jp.ogiwara.test.lobitest.mail_login.MailLoginActivity
import jp.ogiwara.test.lobitest.repo
import jp.ogiwara.test.lobitest.twitter_login.TwitterLoginActivity
import jp.ogiwara.test.lobitest.twitter_login.TwitterLoginActivity.Companion.EXTRA_ACCESS_TOKEN
import jp.ogiwara.test.lobitest.twitter_login.TwitterLoginActivity.Companion.EXTRA_ACCESS_TOKEN_SECRET
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.launch
import org.jetbrains.anko.*

class LoginActivity: AppCompatActivity(),AnkoLogger{

    companion object{
        const val MAIL_RESULT = 0
        const val TWITTER_RESULT = 1
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        setUpView()
    }

    private fun setUpView(){
        find<Button>(R.id.login_twitter).setOnClickListener {
            startActivityForResult<TwitterLoginActivity>(TWITTER_RESULT)
        }
        find<Button>(R.id.login_mail).setOnClickListener {
            startActivityForResult<MailLoginActivity>(MAIL_RESULT)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        when(requestCode){
            MAIL_RESULT -> {
                if(resultCode == Activity.RESULT_OK){
                    repo.save()
                    longToast(R.string.login_succeed)
                    //startActivity<MainActivity>()
                    startActivity(Intent(this,MainActivity::class.java))
                    this@LoginActivity.finish()
                }
            }
            TWITTER_RESULT -> {
                if(resultCode == Activity.RESULT_OK && data != null){
                    val token = data.getStringExtra(EXTRA_ACCESS_TOKEN)
                    val secret = data.getStringExtra(EXTRA_ACCESS_TOKEN_SECRET)
                    launch(UI){
                        val dialog = indeterminateProgressDialog(message =  getString(R.string.loading))
                        dialog.show()
                        repo.twitterLogin(token,secret).join()
                        repo.save()
                        dialog.hide()
                        longToast(R.string.login_succeed)
                        startActivity(Intent(this@LoginActivity,MainActivity::class.java))
                        this@LoginActivity.finish()
                    }
                }
            }
        }
    }
}