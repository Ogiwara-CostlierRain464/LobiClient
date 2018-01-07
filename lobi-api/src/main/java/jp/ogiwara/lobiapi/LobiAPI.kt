package jp.ogiwara.lobiapi

import com.squareup.moshi.JsonDataException
import com.squareup.moshi.Moshi
import io.reactivex.Observable
import jp.ogiwara.lobiapi.adapter.ImageAdapter
import jp.ogiwara.lobiapi.method.LoginMethod
import jp.ogiwara.lobiapi.method.TwitterLoginMethod
import jp.ogiwara.lobiapi.model.*
import okhttp3.JavaNetCookieJar
import okhttp3.OkHttpClient
import okhttp3.ResponseBody
import okio.BufferedSource
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import java.net.CookieManager
import java.net.SocketException
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
            .addConverterFactory(MoshiConverterFactory.create(Moshi.Builder().add(ImageAdapter()).build()))
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
    fun getActivities(): Activities
            = client.getActivities(token = token!!).execute().body()!!

    fun getActivities(lastCursor: String): Activities
            = client.getActivities(token = token!!,lastCursor = lastCursor).execute().body()!!

    @Deprecated("use V2")
    fun getGroup(groupId: String): Group
            = client.getGroup(token = token!!,groupId = groupId).execute().body()!!

    fun getGroupBookmarks(groupId: String): Bookmarks
            = client.getGroupBookmarks(token = token!!,groupId = groupId).execute().body()!!

    fun getGroupChatEditable(groupId: String,chatId: String)
            = client.getGroupChatEditable(token = token!!,groupId = groupId,chatId = chatId).execute().body()!!

    fun getGroupChatPokes(groupId: String,chatId: String): Pokes
            = client.getGroupChatPokes(token = token!!,groupId = groupId,chatId = chatId).execute().body()!!

    fun getGroupChatPokes(groupId: String,nextCursor: String,`_`: Boolean = false): Pokes
            = client.getGroupChatPokes(token = token!!,groupId = groupId,nextCursor = nextCursor).execute().body()!!

    fun getGroupChatReplies(groupId: String,chatId: String): Reply
            = client.getGroupChatReplies(token = token!!,groupId = groupId,chatId = chatId).execute().body()!!

    fun getGroupChatV2(groupId: String): List<Chat>
            = client.getGroupChatV2(token = token!!,groupId = groupId).execute().body()!!

    fun getGroupChatsSearch(groupId: String,query: String): GroupChatsSearchResult
            = client.getGroupChatsSearch(token = token!!,groupId = groupId,query = query).execute().body()!!

    fun getGroupChatsSearch(groupId: String,nextCursor: String,`_`: Boolean = false): GroupChatsSearchResult
            = client.getGroupChatsSearch(token = token!!,groupId = groupId,nextCursor = nextCursor).execute().body()!!

    fun getGroupMemberChats(groupId: String,userId: String): List<Chat>
            = client.getGroupMemberChats(token = token!!,groupId = groupId,userId = userId).execute().body()!!

    fun getGroupMembers(groupId: String): Members
            = client.getGroupMembers(token = token!!,groupId = groupId).execute().body()!!

    fun getGroupV2(groupId: String): Group
            = client.getGroupV2(token = token!!,groupId = groupId).execute().body()!!

    //どうやらPrivateのみ??
    @Deprecated("use V3")
    fun getGroups(): List<Group>
            = client.getGroups(token = token!!).execute().body()!!.first().items

    @Deprecated("use V2")
    fun getGroupsV2(): List<Group>
            = client.getGroupsV2(token = token!!).execute().body()!!.first().items

    fun getGroupsV3(): List<Group>
            = client.getGroupsV3(token = token!!).execute().body()!!.first().items

    fun getMe(): Me
            = client.getMe(token = token!!).execute().body()!!

    fun getMeActivities(): Activities
            = client.getMeActivities(token = token!!).execute().body()!!

    fun getMeActivities(lastCursor: String): Activities
            = client.getMeActivities(token = token!!,lastCursor = lastCursor).execute().body()!!

    fun getMeBookmarks(): Bookmarks
            = client.getMeBookmarks(token = token!!).execute().body()!!

    fun getMeBookmarks(nextCursor: String): Bookmarks
            = client.getMeBookmarks(token = token!!,nextCursor = nextCursor).execute().body()!!

    fun getMeContacts(): Contacts
            = client.getMeContacts(token = token!!).execute().body()!!

    fun getMeContacts(nextCursor: String): Contacts
            = client.getMeContacts(token = token!!,nextCursor = nextCursor).execute().body()!!

    fun getMeFollowers(): Followers
            = client.getMeFollowers(token = token!!).execute().body()!!

    fun getMeFollowers(nextCursor: String): Followers
            = client.getMeFollowers(token = token!!,nextCursor = nextCursor).execute().body()!!

    fun getMeNotifications(count: Int = 20): Notifications
            = client.getMeNotifications(count = count,token = token!!).execute().body()!!

    fun getMeNotifications(lastCursor: String): Notifications
            = client.getMeNotifications(lastCursor = lastCursor,token = token!!).execute().body()!!

    fun getPublicGroups(): List<Group>
            = client.getPublicGroups(token = token!!).execute().body()!!.first().items

    fun getPublicGroupsChatsSearch(query: String): ChatsSearchResult
            = client.getPublicGroupsChatsSearch(query = query,token = token!!).execute().body()!!

    fun getPublicGroupsChatsSearch(nextCursor: String,`_`: Boolean = false): ChatsSearchResult
            = client.getPublicGroupsChatsSearch(token = token!!,nextCursor = nextCursor).execute().body()!!

    fun getPublicGroupsSearch(query: String): GroupSearchResult
            = client.getPublicGroupsSearch(query = query,token = token!!).execute().body()!!

    fun getPublicGroupsSearch(query: String,nextPage: String): GroupSearchResult
            = client.getPublicGroupsSearch(query = query,token = token!!,nextPage = nextPage).execute().body()!!

    @Deprecated("use v2")
    fun getUser(userId: String): User
            = client.getUser(token = token!!,userId = userId).execute().body()!!

    fun getUserActivities(userId: String): Activities
            = client.getUserActivities(token = token!!,userId = userId).execute().body()!!

    fun getUserActivities(userId: String,lastCursor: String): Activities
            = client.getUserActivities(token = token!!,userId = userId,lastCursor = lastCursor).execute().body()!!

    fun getUserContacts(userId: String): Contacts
            = client.getUserContacts(userId = userId,token = token!!).execute().body()!!

    fun getUserContacts(userId: String,nextCursor: String): Contacts
            = client.getUserContacts(userId = userId,token = token!!,nextCursor = nextCursor).execute().body()!!

    fun getUserFollowers(userId: String): Followers
            = client.getUserFollowers(userId = userId,token = token!!).execute().body()!!

    fun getUserFollowers(userId: String,nextCursor: String): Followers
            = client.getUserFollowers(userId = userId,token = token!!,nextCursor = nextCursor).execute().body()!!

    fun getUserV2(userId: String): User
            = client.getUserV2(token = token!!,userId = userId).execute().body()!!

    fun getUsersVisibleGroups(userId: String): List<Group>
            = client.getUserVisibleGroups(userId = userId,token = token!!).execute().body()!!.public_groups.map { it.group }

    fun getUsersSearch(query: String): UsersSearchResult
            = client.getUsersSearch(query = query,token = token!!).execute().body()!!

    fun getUsersSearch(query: String,page: Int): UsersSearchResult
            = client.getUsersSearch(query = query,token = token!!,page = page).execute().body()!!
    //Shadow
    fun getGroupLeader(groupId: String): Members
            = client.getGroupLeader(groupId = groupId,token = token!!).execute().body()!!

    fun getGroupSubLeader(groupId: String): Members
            = client.getGroupSubLeader(groupId = groupId,token = token!!).execute().body()!!

    fun getGroupStream(groupId: String): Observable<Chat> {

        fun toChat(source: BufferedSource): Observable<Chat>{
            return Observable.create { subscriber ->
                try{
                    while(!source.exhausted()){
                        val str = source.readUtf8Line()

                        if(str?.startsWith("{") ?: false){
                            val moshi = Moshi.Builder()
                                    .add(ImageAdapter())
                                    .build()
                            val adapter = moshi.adapter(StreamingChat::class.java)


                            val result = adapter.fromJson(str).chat
                            if(result != null){
                                subscriber.onNext(result)
                            }
                        }
                    }
                    subscriber.onComplete()
                }catch (t: Throwable){
                    //subscriber.onError(ex)
                    t.printStackTrace()
                }
            }
        }

        val okHttp = OkHttpClient.Builder()
                .cookieJar(JavaNetCookieJar(CookieManager()))
                .addInterceptor { chain ->
                    val original = chain.request()
                    val newReq = original.newBuilder()
                            //.header("Accept-Encoding","gzip")
                            .header("User-Agent", USER_AGENT)
                            //.header("Host","api.lobi.co")
                            .method(original.method(),original.body())
                            .build()

                    chain.proceed(newReq)
                }
                .connectTimeout(1L,TimeUnit.HOURS)
                .readTimeout(1L,TimeUnit.HOURS)
                .writeTimeout(1L,TimeUnit.HOURS)
                .build()

        val retrofit = Retrofit.Builder()
                .baseUrl("https://stream.lobi.co/")
                .client(okHttp)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(MoshiConverterFactory.create())
                .build()
                .create(LobiService.StreamingService::class.java)
        return retrofit.getGroupStream(groupId = groupId,token = token as String)
                .flatMap {
                    toChat(it.source())
                }
    }
    //endregion

    //region post
    fun postGroupChat(groupId: String,message: String,shout: Boolean = false): ChatResult
            = client.postGroupChat(groupId = groupId,message = message,type = if(shout) "shout" else "normal",token = token!!).execute().body()!!

    fun postGroupChatBoo(groupId: String,chatId: String): RequestResult
            = client.postGroupChatBoo(groupId = groupId,chatId = chatId,token = token!!).execute().body()!!

    fun postGroupChatLike(groupId: String,chatId: String): RequestResult
            = client.postGroupChatLike(groupId = groupId,chatId = chatId,token = token!!).execute().body()!!

    fun postGroupChatUnboo(groupId: String,chatId: String): RequestResult
            = client.postGroupChatUnboo(groupId = groupId,chatId = chatId,token = token!!).execute().body()!!

    fun postGroupChatUnlike(groupId: String,chatId: String): RequestResult
            = client.postGroupChatUnlike(groupId = groupId,chatId = chatId,token = token as String).execute().body()!!

    @Deprecated("use v2")
    fun postGroupJoinWithGroupUid(groupId: String): Group
            = client.postGroupJoinWithGroupUid(groupId = groupId,token = token!!).execute().body()!!

    fun postGroupJoinWithGroupUidV2(groupId: String): Group
            = client.postGroupJoinWithGroupUidV2(groupId = groupId,token = token!!).execute().body()!!

    //Quit
    fun postGroupPart(groupId: String): RequestResult
            = client.postGroupPart(groupId = groupId,token = token!!).execute().body()!!

    fun postMeBookmarks(chatId: String): RequestResult
            = client.postMeBookmarks(chatId = chatId,token = token!!).execute().body()!!

    fun postMeBookmarkRemove(chatId: String): RequestResult
            = client.postMeBookmarkRemove(chatId = chatId,token = token!!).execute().body()!!

    fun postChatReply(groupId: String,chatId: String,message: String): ChatResult
            = client.postChatReply(replyTo = chatId,token = token!!,message = message,groupId = groupId).execute().body()!!
    //endregion
}