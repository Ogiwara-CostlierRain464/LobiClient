package jp.ogiwara.lobiapi.method

import jp.ogiwara.lobiapi.LOBI_API
import jp.ogiwara.lobiapi.LOBI_INAPP
import jp.ogiwara.lobiapi.model.LoginResponse
import jp.ogiwara.lobiapi.model.UserMinimal
import okhttp3.JavaNetCookieJar
import okhttp3.OkHttpClient
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.Headers
import retrofit2.http.POST
import java.net.CookieManager
import java.util.*

class TwitterLoginMethod(val token: String,val secret: String){

    fun execute(): String{
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
                .baseUrl(LOBI_API)
                .client(okHttp)
                .addConverterFactory(MoshiConverterFactory.create())
                .build()
                .create(TwitterLoginService::class.java)

        return retrofit.getToken(
                uuid = UUID.randomUUID().toString(),
                accessToken = token,
                accessTokenSecret = secret).execute().body()!!.token
    }

    private interface TwitterLoginService{

        @FormUrlEncoded
        @POST("1/signup")
        fun getToken(@Field("device_uuid") uuid: String,
                     @Field("access_token") accessToken: String,
                     @Field("access_token_secret") accessTokenSecret: String,
                     @Field("service") service: String = "twitter"): Call<LoginResponse>
    }
}