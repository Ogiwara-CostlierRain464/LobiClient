package jp.ogiwara.lobiapi.method

import jp.ogiwara.lobiapi.LOBI_API
import jp.ogiwara.lobiapi.LOBI_INAPP
import jp.ogiwara.lobiapi.USER_AGENT
import jp.ogiwara.lobiapi.exception.LoginException
import jp.ogiwara.lobiapi.model.LoginResponse
import okhttp3.JavaNetCookieJar
import okhttp3.OkHttpClient
import okhttp3.ResponseBody
import org.apache.commons.codec.binary.Base64
import org.jsoup.Jsoup
import org.jsoup.nodes.FormElement
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.*
import java.net.CookieManager
import java.util.*
import javax.crypto.Mac
import javax.crypto.spec.SecretKeySpec


class LoginMethod(val mail: String,val pass: String){

    fun execute(): String{
        val spell = getSpell(mail,pass)
        val sig = makeSig(spell)
        return getToken(spell,sig,UUID.randomUUID().toString())
    }

    private fun getSpell(mail: String,pass: String): String{
        var spell: String?
        val cookieHandler = CookieManager()
        val okHttp = OkHttpClient.Builder()
                .cookieJar(JavaNetCookieJar(cookieHandler))
                .addInterceptor { chain ->
                    val original = chain.request()
                    val newReq = original.newBuilder()
                            .header("User-Agent","Mozilla/5.0 (Linux; Android 6.0; Nexus 5 Build/MRA58N) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/59.0.3071.115 Mobile Safari/537.36")
                            .header("Content-Type","application/x-www-form-urlencoded")
                            .method(original.method(),original.body())
                            .build()

                    chain.proceed(newReq)
                }
                .build()

        val retrofit = Retrofit.Builder()
                .baseUrl(LOBI_INAPP)
                .client(okHttp)
                .build()
                .create(SpellService::class.java)

        val res = retrofit.getCsrf().execute()
        val htmlString = res.body()?.string()
        val html = Jsoup.parse(htmlString)
        val form = html.select("form").first() as FormElement
        val csrf = form.formData()[0].value()

        val res2 = retrofit.getSpell(csrf,mail,pass).execute()
        spell = res2.headers().get("Location")?.replace("nakamapbridge://signin?spell=","")

        if(spell == null){
            throw LoginException.INVALID_MAIL_ADDRESS
        }else{
            return spell
        }
    }

    private fun getToken(spell: String,sig: String,uuid: String): String{
        var token: String?
        val cookieHandler = CookieManager()
        val okHttp = OkHttpClient.Builder()
                .cookieJar(JavaNetCookieJar(cookieHandler))
                .addInterceptor { chain ->
                    val original = chain.request()
                    val newReq = original.newBuilder()
                            .header("User-Agent", USER_AGENT)
                            .header("Host","api.lobi.co")
                            .method(original.method(),original.body())
                            .build()

                    chain.proceed(newReq)
                }
                .build()

        val retrofit = Retrofit.Builder()
                .baseUrl(LOBI_API)
                .addConverterFactory(MoshiConverterFactory.create())
                .client(okHttp)
                .build()
                .create(TokenService::class.java)

        val res = retrofit.getToken(uuid,sig,spell,"ja").execute()
        token = res?.body()?.token

        if(token == null){
            throw LoginException.INVALID_MAIL_ADDRESS
        }else{
            return token
        }
    }

    private fun makeSig(spell: String): String{
        val secret = "db6db1788023ce4703eecf6aa33f5fcde35a458c"
        val algo = "HmacSHA1"

        val keySpec = SecretKeySpec(secret.toByteArray(Charsets.US_ASCII),algo)
        val mac = Mac.getInstance(algo)
        mac.init(keySpec)
        val dataBytes = spell.toByteArray(Charsets.US_ASCII)
        val sigBytes = mac.doFinal(dataBytes)
        return String(Base64.encodeBase64(sigBytes),Charsets.US_ASCII)
    }

    private interface SpellService{
        @GET("signin/password?webview=1")
        fun getCsrf(): Call<ResponseBody>

        @FormUrlEncoded
        @Headers("Referer: https://lobi.co/inapp/signin/password",
                "Origin: https://lobi.co")
        @POST("signin/password")
        fun getSpell(@Field("csrf_token") csrf: String,
                     @Field("email") email: String,
                     @Field("password") password: String): Call<ResponseBody>
    }

    private interface TokenService{
        @FormUrlEncoded
        @POST("{version}/signin_confirmation")
        @Headers("Content-Type: application/x-www-form-urlencoded")
        fun getToken(@Field("device_uuid") uuid: String,
                     @Field("sig") sig: String,
                     @Field("spell") spell: String,
                     @Field("lang") lang: String,
                     @Path("version") version: String = "1"): Call<LoginResponse>
    }
}