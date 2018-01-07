package jp.ogiwara.test.lobitest.search

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.Button
import android.widget.EditText
import jp.ogiwara.test.lobitest.R
import jp.ogiwara.test.lobitest.test.ScrollingActivity
import org.apache.commons.lang3.StringUtils
import org.jetbrains.anko.find
import org.jetbrains.anko.intentFor


class SearchFragment : Fragment(){

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val root = inflater!!.inflate(R.layout.fragment_search,container,false)

        setUpView(root)

        return root
    }

    private fun setUpView(v: View){
        val editText = v.find<EditText>(R.id.edit_search)

        editText.setOnEditorActionListener { v, actionId, event ->
            if(/*actionId == EditorInfo.IME_ACTION_SEARCH
                    || actionId == EditorInfo.IME_ACTION_DONE
                    && */event?.action == KeyEvent.ACTION_DOWN
                    && StringUtils.isNotEmpty(v.text)){

                startActivity(context.intentFor<SearchActivity>(SearchActivity.EXTRA_QUERY to v.text.toString()))

                true
            }else{
                false
            }
        }

        v.find<Button>(R.id.button_life).setOnClickListener {
            startActivity(context.intentFor<ScrollingActivity>())
        }
    }

}