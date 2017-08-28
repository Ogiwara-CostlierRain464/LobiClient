package jp.ogiwara.lobiapi

import jp.ogiwara.lobiapi.method.LoginMethod
import jp.ogiwara.lobiapi.method.TwitterLoginMethod
import jp.ogiwara.lobiapi.model.*
import okhttp3.JavaNetCookieJar
import okhttp3.OkHttpClient
import okio.BufferedSource
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import rx.Observable
import java.net.CookieManager
import java.util.concurrent.TimeUnit

/**
 * TODO Twitter,Facebookログイン
 *
 * 各メソッドは基本同期的なもので
 */
class LobiAPI {

    var token: String? = null
    var client = Retrofit.Builder()
            .baseUrl(LOBI_API)
            .addConverterFactory(MoshiConverterFactory.create())
            .client(OkHttpClient.Builder()
                    .cookieJar(JavaNetCookieJar(CookieManager()))
                    .addInterceptor { chain ->
                        val original = chain.request()
                        val newReq = original.newBuilder()
                                //.header("Accept-Encoding","gzip")
                                .header("User-Agent", USER_AGENT)
                                .header("Host","api.lobi.co")
                                .method(original.method(),original.body())
                                .build()

                        chain.proceed(newReq)
                    }.build())
            .build()
            .create(LobiService::class.java)

    fun login(mail: String,pass: String){
        token = LoginMethod(mail,pass).execute()
    }

    fun twitterLogin(accessToken: String,accessTokenSecret: String){
        token = TwitterLoginMethod(accessToken,accessTokenSecret).execute()
    }

    //region get

    fun getMe() = client.me(token = token!!).execute().body()!!

    //自分がFollow
    fun getContacts() = client.contacts(token = token!!).execute().body()!!
    fun getContacts(userId: String) = client.contacts(userId = userId,token = token!!).execute().body()!!

    //自分をFollow
    fun getFollowers() = client.followers(token = token!!).execute().body()!!
    fun getFollowers(userId: String) =client.followers(token = token!!,userId = userId).execute().body()!!

    fun getUser(userId: String) = client.user(token = token!!,userId = userId).execute().body()!!

    fun searchUser(query: String) = client.usersSearch(query = query,token = token as String).execute().body() as UsersSearchResult

    fun getBlockingUsersAll() = client.blockingUsersAll(token = token as String).execute().body() as Users

    fun getInvited() = client.invited(token = token as String).execute().body() as Groups

    fun getPublicGroupAll() = client.publicGroupAll(token = token as String).execute().body() as List<Groups>
    fun getPublicGroupAll(userId: String) = client.publicGroupAll(token = token as String,userId = userId).execute().body() as VisibleGroups

    fun getPublicGroup(page: Int,count: Int) = client.publicGroupsV3(token = token as String,count = count,page = page).execute().body() as List<Groups>

    fun searchPublicGroups(query: String) = client.publicGroupsSearch(query = query,token = token as String).execute().body() as GroupSearchResult

    fun getPrivateGroupAll() = client.privateGroupAll(token = token as String).execute().body() as List<Groups>

    fun getPrivateGroup(count: Int,page: Int) = client.privateGroup(count = count,page = (page + 1),token = token as String).execute().body() as List<Groups>

    fun getGroup(groupId: String) = client.group(groupId = groupId,token = token as String).execute().body() as Group

    fun getGroupLeader(groupId: String) = client.groupLeader(groupId = groupId,token = token as String).execute().body() as Members

    fun getGroupSubLeader(groupId: String) = client.groupSubLeader(groupId = groupId,token = token as String).execute().body() as Members

    fun getGroupMembersAll(groupId: String) = client.groupMembersAll(groupId = groupId,token = token as String).execute().body() as Members

    fun searchGroupChats(query: String,groupId: String) = client.groupChatsSearch(groupId = groupId,query = query,token = token as String).execute().body() as GroupChatsSearchResult

    fun getThreads(groupId: String,count: Int,olderThan: String,newerThan: String) = client.threads(groupId = groupId,count = count,olderThan = olderThan,newerThan = newerThan,token = token as String).execute().body() as List<Chat>

    fun getRepliesAll(groupId: String,chatId: String) = client.repliesAll(groupId = groupId,chatId = chatId,token = token as String).execute().body() as Reply

    fun getNotifications(count: Int) = client.notifications(count = count,token = token!!).execute().body() as Notifications

    @SuppressWarnings("Deprecated")
    fun getGroupStream(groupId: String): Observable<String>{

        fun events(source: BufferedSource): Observable<String>{
            return Observable.create { subscriber ->
                try{
                    while(!source.exhausted()){
                        subscriber.onNext(source.readUtf8Line())
                    }
                    subscriber.onCompleted()
                }catch (t: Throwable){
                    subscriber.onError(t)
                }
            }
        }

        val okHttp = OkHttpClient.Builder()
                .connectTimeout(1L,TimeUnit.HOURS)
                .readTimeout(1L,TimeUnit.HOURS)
                .writeTimeout(1L,TimeUnit.HOURS)
                .build()

        val retrofit = Retrofit.Builder()
                .baseUrl("https://stream.lobi.co/")
                .client(okHttp)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build()
                .create(LobiService.StreamingService::class.java)
        return retrofit.groupStream(groupId = groupId,token = token as String)
                .flatMap {
                    events(it.source())
                }
    }

    fun getBookmarks() = client.bookmarks(token = token !!).execute().body()!!

    fun getActivityMe() = client.activityMe(token = token!!).execute().body()!!

    fun getActivityInfo() = client.activityInfo(token = token!!).execute().body()!!

    fun getActivity(userId: String) = client.activity(token = token!!,userId = userId).execute().body()!!
    //endregion

    //region post
    fun like(groupId: String,chatId: String) = client.like(groupId = groupId,chatId = chatId,token = token as String).execute().body() as RequestResult

    fun unlike(groupId: String,chatId: String) = client.unlike(groupId = groupId,chatId = chatId,token = token as String).execute().body() as RequestResult

    fun boo(groupId: String,chatId: String) = client.boo(groupId = groupId,chatId = chatId,token = token as String).execute().body() as RequestResult

    fun unboo(groupId: String,chatId: String) = client.unboo(groupId = groupId,chatId = chatId,token = token as String).execute().body() as RequestResult

    fun follow(userId: String) = client.follow(userId = userId,token = token as String).execute().body() as RequestResult

    fun unfollow(userId: String) = client.unfollow(userId = userId,token = token as String).execute().body() as RequestResult

    fun chat(groupId: String,message: String,shout: Boolean = false) = client.chat(groupId = groupId,message = message,type = if(shout) "shout" else "normal",token = token as String).execute().body() as Chat

    fun removeChat(groupId: String,chatId: String) = client.removeChat(groupId = groupId,chatId = chatId,token = token as String).execute().body() as RequestResult

    fun join(groupId: String) = client.join(groupId = groupId,token = token as String).execute().body() as Group

    fun kick(groupId: String,userId: String) = client.kick(groupId = groupId,userId = userId,token = token as String).execute().body() as RequestResult

    fun block(userId: String) = client.block(userId = userId,token = token as String).execute().body() as RequestResult
    fun unblock(userId: String) = client.unblock(userId = userId,token = token as String).execute().body()!!

    fun bookmark(chatId: String) = client.bookmark(chatId = chatId,token = token!!).execute().body()!!
    fun unBookmark(chatId: String) = client.unBookmark(chatId = chatId,token = token!!).execute().body()!!
    //endregion
}