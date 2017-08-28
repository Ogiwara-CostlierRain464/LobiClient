package jp.ogiwara.test.lobiclient

import jp.ogiwara.test.lobiclient.exception.LoginException
import jp.ogiwara.test.lobiclient.model.User
import okhttp3.JavaNetCookieJar
import okhttp3.OkHttpClient
import okhttp3.ResponseBody
import org.apache.commons.codec.binary.Base64
import org.jsoup.Jsoup
import org.jsoup.nodes.FormElement
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.*
import rx.Observable
import java.net.CookieManager
import java.util.*
import javax.crypto.Mac
import javax.crypto.spec.SecretKeySpec

//TODO Singleton with DI
//帰り値は全てObservableで?
class LobiRepository {
}