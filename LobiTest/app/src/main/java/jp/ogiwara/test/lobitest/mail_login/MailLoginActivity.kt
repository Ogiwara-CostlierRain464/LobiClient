package jp.ogiwara.test.lobitest.mail_login

import android.app.Activity
import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.AppCompatEditText
import android.util.Log
import android.widget.Button
import android.widget.EditText
import jp.ogiwara.lobiapi.exception.LoginException
import jp.ogiwara.test.lobitest.R
import jp.ogiwara.test.lobitest.repo
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.async
import kotlinx.coroutines.experimental.run
import org.jetbrains.anko.*


class MailLoginActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mail_login)

        setUpView()
    }

    private fun setUpView(){
        val editMail = find<EditText>(R.id.edit_mail_address)
        val editPass = find<EditText>(R.id.edit_password)
        val loginButton = find<Button>(R.id.button_login)

        loginButton.setOnClickListener {
            val mail = editMail.text.toString()
            val pass = editPass.text.toString()

            async(UI){
                val dialog = indeterminateProgressDialog(message = getString(R.string.loading))
                dialog.show()
                try {
                    repo.login(mail, pass)
                    dialog.hide()
                    val intent = Intent()
                    setResult(Activity.RESULT_OK, intent)
                    finish()
                }catch (ex: LoginException){
                    dialog.hide()

                    AlertDialog.Builder(this@MailLoginActivity,R.style.AlertDialog)
                            .setTitle(R.string.login_failed)
                            .setMessage(R.string.check_mail)
                            .show()
                }
            }
        }
    }
}