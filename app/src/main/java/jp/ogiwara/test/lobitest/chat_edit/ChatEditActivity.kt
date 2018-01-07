package jp.ogiwara.test.lobitest.chat_edit

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.EditText
import android.widget.ImageButton
import jp.ogiwara.test.lobitest.R
import org.jetbrains.anko.find

class ChatEditActivity: AppCompatActivity() {

    companion object{
        const val EXTRA_CHAT = "chat"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat_edit)

        setUpView()
    }

    private fun setUpView(){
        val ok = find<ImageButton>(R.id.button_ok)
        val cancel = find<ImageButton>(R.id.button_cancel)
        val chat = find<EditText>(R.id.edit_chat)

        ok.setOnClickListener {
            val text = chat.text.toString()
            if(text.isNotEmpty()){
                val intent = Intent()
                intent.putExtra(EXTRA_CHAT,text)
                setResult(Activity.RESULT_OK,intent)
                finish()
            }
        }
        cancel.setOnClickListener {
            val intent = Intent()
            setResult(Activity.RESULT_CANCELED,intent)
            finish()
        }
    }

}