package jp.ogiwara.test.lobitest.twitter_login

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.webkit.WebChromeClient
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import jp.ogiwara.test.lobitest.R
import kotlinx.coroutines.experimental.CommonPool
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.launch
import org.apache.commons.lang3.StringUtils
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.indeterminateProgressDialog
import org.jetbrains.anko.info
import twitter4j.TwitterFactory


class TwitterLoginActivity : AppCompatActivity(),AnkoLogger {

    companion object{
        const val API_KEY = "ZrgdukWwDeXVg9NCWG7rA"
        const val API_SECRET = "WYNDf3OcvrWD31tnKnGbQHXqmZq1fohwBuvMnIJSs"
        const val CALLBACK_URL = "https://api.nakamap.com/oauth/twitter"

        const val EXTRA_ACCESS_TOKEN = "access_token"
        const val EXTRA_ACCESS_TOKEN_SECRET = "access_token_secret"
    }

    private lateinit var webView: WebView
    private val twitter = TwitterFactory.getSingleton()

    private val webChromeClient = WebChromeClient()
    private val webViewClient = object: WebViewClient(){

        override fun shouldOverrideUrlLoading(view: WebView?, request: WebResourceRequest?): Boolean { // 3
            var result = true
            val url = request!!.url.toString()
            if(url.startsWith(CALLBACK_URL)){

                launch(CommonPool){
                    val res = twitter.getOAuthAccessToken(StringUtils.substringAfter(url,"&oauth_verifier=")) // 4
                    launch(UI){
                        val intent = Intent()
                        intent.putExtra(EXTRA_ACCESS_TOKEN,res.token)
                        intent.putExtra(EXTRA_ACCESS_TOKEN_SECRET,res.tokenSecret)
                        setResult(Activity.RESULT_OK,intent)
                        finish()
                    }
                }
            }else{
                result = super.shouldOverrideUrlLoading(view, request)
            }
            return result
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        webView = WebView(this)
        webView.setWebViewClient(webViewClient)
        webView.setWebChromeClient(webChromeClient)

        setContentView(webView)

        twitter.setOAuthConsumer(API_KEY, API_SECRET)
        launch(CommonPool){
            val res = twitter.getOAuthRequestToken(CALLBACK_URL) // 1
            launch(UI){
                webView.loadUrl(res.authorizationURL) // 2
            }
        }
    }
}