package jp.ogiwara.lobiapi

import jp.ogiwara.lobiapi.model.*
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.*
import rx.Observable
import java.util.*


interface LobiService {

    //Note タイプパラメータは不可。

    //region http-get method
    @GET("1/me")
    fun me(@Query("platform") platform: String = "android",
           @Query("lang") lang: String = "ja",
           @Query("token") token: String): Call<Me>

    @GET("3/me/contacts")
    fun contacts(
            @Query("platform") platform: String = "android",
            @Query("lang") lang: String = "ja",
            @Query("token") token: String): Call<Contacts>

    @GET("1/user/{userId}/contacts")
    fun contacts(@Path("userId") userId: String,
                 @Query("platform") platform: String = "android",
                 @Query("lang") lang: String = "ja",
                 @Query("token") token: String): Call<Contacts>

    @GET("2/me/followers")
    fun followers(@Query("platform") platform: String = "android",
                 @Query("lang") lang: String = "ja",
                 @Query("token") token: String): Call<Followers>

    @GET("1/user/{userId}/followers")
    fun followers(
            @Path("userId") userId: String,
            @Query("platform") platform: String = "android",
            @Query("lang") lang: String = "ja",
            @Query("token") token: String): Call<Contacts>

    @GET("2/user/{userId}")
    fun user(
            @Path("userId") userId: String,
            @Query("platform") platform: String = "android",
            @Query("lang") lang: String = "ja",
            @Query("token") token: String): Call<User>

    @GET("1/users/search")
    fun usersSearch(
            @Query("q") query: String,
            @Query("platform") platform: String = "android",
            @Query("lang") lang: String = "ja",
            @Query("token") token: String): Call<UsersSearchResult>

    @GET("2/me/blocking_users")
    fun blockingUsersAll(
            @Query("platform") platform: String = "android",
            @Query("lang") lang: String = "ja",
            @Query("token") token: String): Call<Users>

    @GET("1/groups/invited")
    fun invited(
            @Query("platform") platform: String = "android",
            @Query("lang") lang: String = "ja",
            @Query("token") token: String): Call<Groups>

    @GET("1/public_groups")
    fun publicGroupAll(
            @Query("platform") platform: String = "android",
            @Query("lang") lang: String = "ja",
            @Query("token") token: String): Call<List<Groups>>

    @GET("1/user/{userId}/visible_groups")
    fun publicGroupAll(
            @Path("userId") userId: String,
            @Query("platform") platform: String = "android",
            @Query("lang") lang: String = "ja",
            @Query("token") token: String): Call<VisibleGroups>

    @GET("3/public_groups")
    fun publicGroupsV3(
            @Query("with_archived") withArchived: Boolean = true,
            @Query("count") count: Int,
            @Query("page") page: Int,
            @Query("platform") platform: String = "android",
            @Query("lang") lang: String = "ja",
            @Query("token") token: String): Call<List<Groups>>

    @GET("1/public_groups/search")
    fun publicGroupsSearch(
            @Query("q") query: String,
            @Query("platform") platform: String = "android",
            @Query("lang") lang: String = "ja",
            @Query("token") token: String): Call<GroupSearchResult>

    @GET("2/public_groups/tree")
    fun publicGroupsTree(
            @Query("platform") platform: String = "android",
            @Query("lang") lang: String = "ja",
            @Query("token") token: String): Call<List<Groups>>

    @GET("3/groups")
    fun privateGroupAll(
            @Query("platform") platform: String = "android",
            @Query("lang") lang: String = "ja",
            @Query("token") token: String): Call<List<Groups>>

    @GET("3/groups")
    fun privateGroup(
            @Query("with_archived") withArchived: Boolean = true,
            @Query("count") count: Int,
            @Query("page") page: Int,
            @Query("platform") platform: String = "android",
            @Query("lang") lang: String = "ja",
            @Query("token") token: String): Call<List<Groups>>

    @GET("2/group/{groupId}")
    fun group(
            @Path("groupId") groupId: String,
            @Query("members_count") membersCount: Int = 1,
            @Query("fields") fields: String = "subleaders,group_bookmark_info",
            @Query("platform") platform: String = "android",
            @Query("lang") lang: String = "ja",
            @Query("token") token: String): Call<Group>

    @GET("2/group/{groupId}/members")
    fun groupLeader(
            @Path("groupId") groupId: String,
            @Query("members_count") membersCount: Int = 1,
            @Query("fields") fields: String = "owner",
            @Query("platform") platform: String = "android",
            @Query("lang") lang: String = "ja",
            @Query("token") token: String): Call<Members>

    @GET("2/group/{groupId}/members")
    fun groupSubLeader(
            @Path("groupId") groupId: String,
            @Query("members_count") membersCount: Int = 1,
            @Query("fields") fields: String = "subleaders",
            @Query("platform") platform: String = "android",
            @Query("lang") lang: String = "ja",
            @Query("token") token: String): Call<Members>

    @GET("1/group/{groupId}/members")
    fun groupMembersAll(
            @Path("groupId") groupId: String,
            @Query("members_count") membersCount: Int = 1000,
            @Query("platform") platform: String = "android",
            @Query("lang") lang: String = "ja",
            @Query("token") token: String): Call<Members>

    @GET("1/group/{groupId}/chats/search")
    fun groupChatsSearch(
            @Path("groupId") groupId: String,
            @Query("q") query: String,
            @Query("platform") platform: String = "android",
            @Query("lang") lang: String = "ja",
            @Query("token") token: String): Call<GroupChatsSearchResult>

    @GET("2/group/{groupId}/chats")
    fun threads(
            @Path("groupId") groupId: String,
            @Query("count") count: Int,
            @Query("older_than") olderThan: String,
            @Query("newer_than") newerThan: String,
            @Query("platform") platform: String = "android",
            @Query("lang") lang: String = "ja",
            @Query("token") token: String): Call<List<Chat>>

    @GET("1/group/{groupId}/chats/replies")
    fun repliesAll(
            @Path("groupId") groupId: String,
            @Query("to") chatId: String,
            @Query("platform") platform: String = "android",
            @Query("lang") lang: String = "ja",
            @Query("token") token: String): Call<Reply>

    @GET("2/info/notifications")
    fun notifications(
            @Query("count") count: Int,
            @Query("platform") platform: String = "android",
            @Query("lang") lang: String = "ja",
            @Query("token") token: String): Call<Notifications>

    @GET("1/me/bookmarks")
    fun bookmarks(
            @Query("platform") platform: String = "android",
            @Query("lang") lang: String = "ja",
            @Query("token") token: String): Call<Bookmarks>

    @GET("2/me/activity")
    fun activityMe(
            @Query("platform") platform: String = "android",
            @Query("lang") lang: String = "ja",
            @Query("token") token: String): Call<Activities>

    @GET("2/info/activity")
    fun activityInfo(
            @Query("platform") platform: String = "android",
            @Query("lang") lang: String = "ja",
            @Query("token") token: String): Call<Activities>

    @GET("2/user/{userId}/activity")
    fun activity(
            @Path("userId") userId: String,
            @Query("platform") platform: String = "android",
            @Query("lang") lang: String = "ja",
            @Query("token") token: String): Call<ResponseBody>

    //endregion

    //region http-post method
    @FormUrlEncoded
    @POST("1/group/{groupId}/chats/like")
    fun like(
            @Path("groupId") groupId: String,
            @Field("id") chatId: String,
            @Field("lang") lang: String = "ja",
            @Field("token") token: String): Call<RequestResult>

    @FormUrlEncoded
    @POST("1/group/{groupId}/chats/unlike")
    fun unlike(
            @Path("groupId") groupId: String,
            @Field("id") chatId: String,
            @Field("lang") lang: String = "ja",
            @Field("token") token: String): Call<RequestResult>

    @FormUrlEncoded
    @POST("1/group/{groupId}/chats/boo")
    fun boo(
            @Path("groupId") groupId: String,
            @Field("id") chatId: String,
            @Field("lang") lang: String = "ja",
            @Field("token") token: String): Call<RequestResult>

    @FormUrlEncoded
    @POST("1/group/{groupId}/chats/unboo")
    fun unboo(
            @Path("groupId") groupId: String,
            @Field("id") chatId: String,
            @Field("lang") lang: String = "ja",
            @Field("token") token: String): Call<RequestResult>

    @FormUrlEncoded
    @POST("1/me/contacts")
    fun follow(
            @Field("users") userId: String,
            @Field("lang") lang: String = "ja",
            @Field("token") token: String): Call<RequestResult>


    @FormUrlEncoded
    @POST("1/me/contacts/remove")
    fun unfollow(
            @Field("users") userId: String,
            @Field("lang") lang: String = "ja",
            @Field("token") token: String): Call<RequestResult>

    //TODO Enable Assets
    @FormUrlEncoded
    @POST("1/assets")
    fun assets(
            @Field("users") chatId: String,
            @Field("lang") lang: String = "ja",
            @Field("token") token: String): Call<RequestResult>

    @FormUrlEncoded
    @POST("1/group/{groupId}/chats")
    fun chat(
            @Path("groupId") groupId: String,
            @Field("type") type: String,// "shout" or "normal"
            @Field("message") message: String,
            @Field("lang") lang: String = "ja",
            @Field("token") token: String): Call<Chat>

    @FormUrlEncoded
    @POST("1/group/{groupId}/chats")
    fun chatWithPhoto(
            @Path("groupId") groupId: String,
            @Field("type") type: String,// "shout" or "normal"
            @Field("message") message: String,
            @Field("assets") assets: String,
            @Field("lang") lang: String = "ja",
            @Field("token") token: String): Call<Chat>

    @FormUrlEncoded
    @POST("1/group/{groupId}/chats")
    fun chat(
            @Path("groupId") groupId: String,
            @Field("type") type: String = "normal",
            @Field("reply_to") replyTo: String,
            @Field("message") message: String,
            @Field("lang") lang: String = "ja",
            @Field("token") token: String): Call<Chat>

    @FormUrlEncoded
    @POST("1/group/{groupId}/chats/remove")
    fun removeChat(
            @Path("groupId") groupId: String,
            @Field("id") chatId: String,
            @Field("lang") lang: String = "ja",
            @Field("token") token: String): Call<RequestResult>

    @FormUrlEncoded
    @POST("2/group/{groupId}/join")
    fun join(
            @Path("groupId") groupId: String,
            @Field("lang") lang: String = "ja",
            @Field("token") token: String): Call<Group>

    @FormUrlEncoded
    @POST("1/group/{groupId}/refuse_invitation")
    fun refuseInvitation(
            @Path("groupId") groupId: String,
            @Field("lang") lang: String = "ja",
            @Field("token") token: String): Call<RequestResult>

    @FormUrlEncoded
    @POST("1/group/{groupId}/kick")
    fun kick(
            @Path("groupId") groupId: String,
            @Field("target_user") userId: String,
            @Field("lang") lang: String = "ja",
            @Field("token") token: String): Call<RequestResult>

    @FormUrlEncoded
    @POST("1/me/blocking_users")
    fun block(
            @Field("users") userId: String,
            @Field("lang") lang: String = "ja",
            @Field("token") token: String): Call<RequestResult>

    @FormUrlEncoded
    @POST("1/me/blocking_users/remove")
    fun unblock(
            @Field("users") userId: String,
            @Field("lang") lang: String = "ja",
            @Field("token") token: String): Call<RequestResult>

    @FormUrlEncoded
    @POST("1/me/bookmarks")
    fun bookmark(
            @Field("chat_id") chatId: String,
            @Field("lang") lang: String = "ja",
            @Field("token") token: String): Call<RequestResult>

    @FormUrlEncoded
    @POST("1/me/bookmark/remove")
    fun unBookmark(
            @Field("chat_id") chatId: String,
            @Field("lang") lang: String = "ja",
            @Field("token") token: String): Call<RequestResult>

    //endregion
    interface StreamingService{
        @GET("2/group/{groupId}")
        @Streaming
        fun groupStream(
                @Path("groupId") groupId: String,
                @Query("lang") lang: String = "ja",
                @Query("token") token: String): Observable<ResponseBody>
    }
}