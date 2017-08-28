package jp.ogiwara.test.lobiclient

import retrofit2.Call
import retrofit2.http.*


interface LobiService {

    @GET("{version}/{requestUrl}")
    fun <T> get(@Path("requestUrl") requestUrl: String,
                @Query("platfrom") platform: String,
                @Query("lang") lang: String,
                @Query("token") token: String,
                @Path("version") version: String = "1"): Call<T>

    @FormUrlEncoded
    @POST("{version}/{requestUrl}")
    fun <T> post(@Path("requestUrl") requestUrl: String,
                 @Field("token") token: String,
                 @Field("lang") lang: String,
                 @Path("version") version: String = "1"): Call<T>

}