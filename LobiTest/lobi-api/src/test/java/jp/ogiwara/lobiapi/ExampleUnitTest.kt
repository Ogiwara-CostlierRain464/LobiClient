package jp.ogiwara.lobiapi

import jp.ogiwara.lobiapi.util.QueryStringBuilder
import okhttp3.JavaNetCookieJar
import okhttp3.OkHttpClient
import okhttp3.ResponseBody
import org.apache.commons.codec.binary.Base64
import org.junit.Test

import org.junit.Assert.*
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.http.Field
import retrofit2.http.POST
import java.net.CookieManager
import java.net.URLDecoder
import java.net.URLEncoder
import java.nio.charset.Charset
import java.util.*
import javax.crypto.Mac
import javax.crypto.spec.SecretKeySpec

/**
 * Example local unit test, which will execute on the development machine (host).

 * @see [Testing documentation](http://d.android.com/tools/testing)
 */
class ExampleUnitTest {

    @Test
    @Throws(Exception::class)
    fun loginTest() {
        val client = LobiAPI()
        client.login("mail","password")
        client.getActivityMe().activity.forEach {
            println(it.unit.type)
        }
    }
}