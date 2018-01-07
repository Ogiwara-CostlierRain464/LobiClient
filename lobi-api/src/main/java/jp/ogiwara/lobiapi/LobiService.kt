package jp.ogiwara.lobiapi

import jp.ogiwara.lobiapi.model.*
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.*
import io.reactivex.Observable

/**
 * 本家の名前に従うこと!
 *
 * 順番を守る!
 */
interface LobiService {

    //Note タイプパラメータは不可。

    //region http-get method

    /**
     * 8/29 全API実装チェック
     */
    @GET("1/accusation_categories")
    fun getAccusationCategories(
            @Query("platform") platform: String = "android",
            @Query("lang") lang: String = "ja",
            @Query("token") token: String): Call<ResponseBody>

    @GET("2/info/activity")
    fun getActivities(
            @Query("platform") platform: String = "android",
            @Query("lang") lang: String = "ja",
            @Query("token") token: String): Call<Activities>

    @GET("2/info/activity")
    fun getActivities(
            @Query("platform") platform: String = "android",
            @Query("lang") lang: String = "ja",
            @Query("token") token: String,
            @Query("last_cursor") lastCursor: String): Call<Activities>

    @GET("2/info/announce")
    fun getAnnouncements(
            @Query("platform") platform: String = "android",
            @Query("lang") lang: String = "ja",
            @Query("token") token: String): Call<ResponseBody>

    @GET("1/app/{uid}")
    fun getApp(
            @Path("uid") uid: String,
            @Query("platform") platform: String = "android",
            @Query("lang") lang: String = "ja",
            @Query("token") token: String): Call<ResponseBody>

    @GET("1/banners")
    fun getBanners(
            @Query("platform") platform: String = "android",
            @Query("lang") lang: String = "ja",
            @Query("token") token: String): Call<ResponseBody>

    @GET("1/group/{groupId}")
    fun getGroup(
            @Path("groupId") groupId: String,
            @Query("platform") platform: String = "android",
            @Query("lang") lang: String = "ja",
            @Query("token") token: String): Call<Group>

    @GET("1/group/{groupId}/bookmarks")
    fun getGroupBookmarks(
            @Path("groupId") groupId: String,
            @Query("platform") platform: String = "android",
            @Query("lang") lang: String = "ja",
            @Query("token") token: String): Call<Bookmarks>

    //自分のchat以外の場合はエラーがでる
    @GET("1/group/{groupId}/chats/editable")
    fun getGroupChatEditable(
            @Path("groupId") groupId: String,
            @Query("id") chatId: String,
            @Query("platform") platform: String = "android",
            @Query("lang") lang: String = "ja",
            @Query("token") token: String): Call<ChatResult>

    @GET("1/group/{groupId}/chats/pokes")
    fun getGroupChatPokes(
            @Path("groupId") groupId: String,
            @Query("id") chatId: String,
            @Query("platform") platform: String = "android",
            @Query("lang") lang: String = "ja",
            @Query("token") token: String): Call<Pokes>

    @GET("1/group/{groupId}/chats/pokes")
    fun getGroupChatPokes(
            @Path("groupId") groupId: String,
            @Query("next_cursor") nextCursor: String,
            @Query("platform") platform: String = "android",
            @Query("token") token: String): Call<Pokes>

    @GET("1/group/{groupId}/chats/replies")
    fun getGroupChatReplies(
            @Path("groupId") groupId: String,
            @Query("to") chatId: String,
            @Query("platform") platform: String = "android",
            @Query("lang") lang: String = "ja",
            @Query("token") token: String): Call<Reply>

    @GET("2/group/{groupId}/chats")
    fun getGroupChatV2(
            @Path("groupId") groupId: String,
            @Query("platform") platform: String = "android",
            @Query("lang") lang: String = "ja",
            @Query("token") token: String): Call<List<Chat>>

    @GET("1/group/{groupId}/chats/search")
    fun getGroupChatsSearch(
            @Path("groupId") groupId: String,
            @Query("q") query: String,
            @Query("platform") platform: String = "android",
            @Query("lang") lang: String = "ja",
            @Query("token") token: String): Call<GroupChatsSearchResult>

    @GET("1/group/{groupId}/chats/search")
    fun getGroupChatsSearch(
            @Path("groupId") groupId: String,
            @Query("next_cursor") nextCursor: String,
            @Query("lang") lang: String = "ja",
            @Query("token") token: String): Call<GroupChatsSearchResult>

    @GET("1/group/{groupId}/member/{userId}/chats")
    fun getGroupMemberChats(
            @Path("groupId") groupId: String,
            @Path("userId") userId: String,
            @Query("platform") platform: String = "android",
            @Query("lang") lang: String = "ja",
            @Query("token") token: String): Call<List<Chat>>

    @GET("1/group/{groupId}/members")
    fun getGroupMembers(
            @Path("groupId") groupId: String,
            @Query("platform") platform: String = "android",
            @Query("lang") lang: String = "ja",
            @Query("token") token: String): Call<Members>

    @GET("1/group/{groupId}/ranking")
    fun getGroupRanking(
            @Path("groupId") groupId: String,
            @Query("platform") platform: String = "android",
            @Query("lang") lang: String = "ja",
            @Query("token") token: String): Call<ResponseBody>

    @GET("2/group/{groupId}")
    fun getGroupV2(
            @Path("groupId") groupId: String,
            @Query("members_count") membersCount: Int = 1,
            @Query("platform") platform: String = "android",
            @Query("lang") lang: String = "ja",
            @Query("token") token: String): Call<Group>

    @GET("1/groups")
    fun getGroups(
            @Query("platform") platform: String = "android",
            @Query("lang") lang: String = "ja",
            @Query("token") token: String): Call<List<Groups>>

    @GET("1/groups/invited")
    fun getGroupsInvited(
            @Query("platform") platform: String = "android",
            @Query("lang") lang: String = "ja",
            @Query("token") token: String): Call<ResponseBody>

    @GET("2/groups")
    fun getGroupsV2(
            @Query("platform") platform: String = "android",
            @Query("lang") lang: String = "ja",
            @Query("token") token: String): Call<List<Groups>>

    @GET("3/groups")
    fun getGroupsV3(
            @Query("platform") platform: String = "android",
            @Query("lang") lang: String = "ja",
            @Query("token") token: String): Call<List<Groups>>

    @GET("1/me")
    fun getMe(@Query("platform") platform: String = "android",
           @Query("lang") lang: String = "ja",
           @Query("token") token: String): Call<Me>

    @GET("2/me/activity")
    fun getMeActivities(
            @Query("platform") platform: String = "android",
            @Query("lang") lang: String = "ja",
            @Query("token") token: String): Call<Activities>

    @GET("2/me/activity")
    fun getMeActivities(
            @Query("platform") platform: String = "android",
            @Query("lang") lang: String = "ja",
            @Query("token") token: String,
            @Query("last_cursor") lastCursor: String): Call<Activities>


    @GET("1/me/auths")
    fun getMeAuths(
            @Query("platform") platform: String = "android",
            @Query("lang") lang: String = "ja",
            @Query("token") token: String): Call<ResponseBody>

    @GET("2/me/blocking_users")
    fun getMeBlockingUsers(
            @Query("platform") platform: String = "android",
            @Query("lang") lang: String = "ja",
            @Query("token") token: String): Call<ResponseBody>

    @GET("2/me/blocking_users")
    fun getMeBlockingUsersWithPaging(
            @Query("platform") platform: String = "android",
            @Query("lang") lang: String = "ja",
            @Query("token") token: String): Call<ResponseBody>

    @GET("1/me/bookmarks")
    fun getMeBookmarks(
            @Query("platform") platform: String = "android",
            @Query("lang") lang: String = "ja",
            @Query("token") token: String): Call<Bookmarks>

    @GET("1/me/bookmarks")
    fun getMeBookmarks(
            @Query("platform") platform: String = "android",
            @Query("lang") lang: String = "ja",
            @Query("token") token: String,
            @Query("next_cursor") nextCursor: String): Call<Bookmarks>

    @GET("1/me/bookmarks/groups")
    fun getMeBookmarksGroups(
            @Query("platform") platform: String = "android",
            @Query("lang") lang: String = "ja",
            @Query("token") token: String): Call<ResponseBody>

    @GET("3/me/contacts")
    fun getMeContacts(
            @Query("platform") platform: String = "android",
            @Query("lang") lang: String = "ja",
            @Query("token") token: String): Call<Contacts>

    @GET("3/me/contacts")
    fun getMeContacts(
            @Query("next_cursor") nextCursor: String,
            @Query("platform") platform: String = "android",
            @Query("lang") lang: String = "ja",
            @Query("token") token: String): Call<Contacts>

    @GET("1/me/contacts/recommended")
    fun getMeContactsRecommended(
            @Query("platform") platform: String = "android",
            @Query("lang") lang: String = "ja",
            @Query("token") token: String): Call<ResponseBody>


    @GET("2/me/followers")
    fun getMeFollowers(
            @Query("platform") platform: String = "android",
            @Query("lang") lang: String = "ja",
            @Query("token") token: String): Call<Followers>

    @GET("2/me/followers")
    fun getMeFollowers(
            @Query("next_cursor") nextCursor: String,
            @Query("platform") platform: String = "android",
            @Query("lang") lang: String = "ja",
            @Query("token") token: String): Call<Followers>

    @GET("1/me/settings")
    fun getMeSettings(
            @Query("platform") platform: String = "android",
            @Query("lang") lang: String = "ja",
            @Query("token") token: String): Call<ResponseBody>

    @GET("2/me/settings/notifications")
    fun getMeSettingsNotifications(
            @Query("platform") platform: String = "android",
            @Query("lang") lang: String = "ja",
            @Query("token") token: String): Call<ResponseBody>

    @GET("2/me/settings")
    fun getMeSettingsV2(
            @Query("platform") platform: String = "android",
            @Query("lang") lang: String = "ja",
            @Query("token") token: String): Call<ResponseBody>


    @GET("2/me/users")
    fun getMeUsers(
            @Query("platform") platform: String = "android",
            @Query("lang") lang: String = "ja",
            @Query("token") token: String): Call<ResponseBody>

    @GET("1/groups/chats/search")
    fun getMyGroupsChatsSearch(
            @Query("q") query: String,
            @Query("platform") platform: String = "android",
            @Query("lang") lang: String = "ja",
            @Query("token") token: String): Call<ResponseBody>

    @GET("2/info/notifications")
    fun getMeNotifications(
            @Query("count") count: Int,
            @Query("platform") platform: String = "android",
            @Query("lang") lang: String = "ja",
            @Query("token") token: String): Call<Notifications>

    @GET("2/info/notifications")
    fun getMeNotifications(
            @Query("last_cursor") lastCursor: String,
            @Query("platform") platform: String = "android",
            @Query("lang") lang: String = "ja",
            @Query("token") token: String): Call<Notifications>


    @GET("1/playlist/{uid}/items")
    fun getPlaylistItems(
            @Path("uid") uid: String,
            @Query("platform") platform: String = "android",
            @Query("lang") lang: String = "ja",
            @Query("token") token: String): Call<ResponseBody>

    @GET("1/public_groups")
    fun getPublicGroups(
            @Query("platform") platform: String = "android",
            @Query("lang") lang: String = "ja",
            @Query("token") token: String): Call<List<Groups>>

    @GET("1/public_groups/chats/search")
    fun getPublicGroupsChatsSearch(
            @Query("q") query: String,
            @Query("platform") platform: String = "android",
            @Query("lang") lang: String = "ja",
            @Query("token") token: String): Call<ChatsSearchResult>

    @GET("1/public_groups/chats/search")
    fun getPublicGroupsChatsSearch(
            @Query("next_cursor") nextCursor: String,
            @Query("lang") lang: String = "ja",
            @Query("token") token: String): Call<ChatsSearchResult>

    @GET("1/public_groups/search")
    fun getPublicGroupsSearch(
            @Query("q") query: String,
            @Query("platform") platform: String = "android",
            @Query("lang") lang: String = "ja",
            @Query("token") token: String): Call<GroupSearchResult>

    @GET("1/public_groups/search")
    fun getPublicGroupsSearch(
            @Query("q") query: String,
            @Query("platform") platform: String = "android",
            @Query("lang") lang: String = "ja",
            @Query("token") token: String,
            @Query("next_page") nextPage: String): Call<GroupSearchResult>

    @GET("2/public_groups/spike")
    fun getPublicGroupsSpike(
            @Query("platform") platform: String = "android",
            @Query("lang") lang: String = "ja",
            @Query("token") token: String): Call<ResponseBody>

    @GET("2/public_groups/tree")
    fun getPublicGroupsTreeV2(
            @Query("platform") platform: String = "android",
            @Query("lang") lang: String = "ja",
            @Query("token") token: String): Call<ResponseBody>

    @GET("3/public_groups/tree")
    fun getPublicGroupsTreeV3(
            @Query("platform") platform: String = "android",
            @Query("lang") lang: String = "ja",
            @Query("token") token: String): Call<ResponseBody>

    @GET("1/regions")
    fun getRegion(
            @Query("platform") platform: String = "android",
            @Query("lang") lang: String = "ja",
            @Query("token") token: String): Call<ResponseBody>

    @GET("1/reward/stamp")
    fun getRewardStamp(
            @Query("platform") platform: String = "android",
            @Query("lang") lang: String = "ja",
            @Query("token") token: String): Call<ResponseBody>


    @GET("1/stamps")
    fun getStamps(
            @Query("platform") platform: String = "android",
            @Query("lang") lang: String = "ja",
            @Query("token") token: String): Call<ResponseBody>

    @GET("2/startup")
    fun getStartUp(
            @Query("platform") platform: String = "android",
            @Query("lang") lang: String = "ja",
            @Query("token") token: String): Call<ResponseBody>

    @GET("1/store")
    fun getStore(
            @Query("platform") platform: String = "android",
            @Query("lang") lang: String = "ja",
            @Query("token") token: String): Call<ResponseBody>

    @GET("1/store/stamp/{stampId}")
    fun getStoreStampDetail(
            @Path("stampId") stampId: String,
            @Query("platform") platform: String = "android",
            @Query("lang") lang: String = "ja",
            @Query("token") token: String): Call<ResponseBody>

    @GET("1/store/subscription/list")
    fun getStoreSubscriptionList(
            @Query("platform") platform: String = "android",
            @Query("lang") lang: String = "ja",
            @Query("token") token: String): Call<ResponseBody>

    @GET("2/tickets")
    fun getTickets(
            @Query("platform") platform: String = "android",
            @Query("lang") lang: String = "ja",
            @Query("token") token: String): Call<ResponseBody>

    @GET("1/user/{userId}")
    fun getUser(
            @Path("userId") userId: String,
            @Query("platform") platform: String = "android",
            @Query("lang") lang: String = "ja",
            @Query("token") token: String): Call<User>

    @GET("2/user/{userId}/activity")
    fun getUserActivities(
            @Path("userId") userId: String,
            @Query("platform") platform: String = "android",
            @Query("lang") lang: String = "ja",
            @Query("token") token: String): Call<Activities>

    @GET("2/user/{userId}/activity")
    fun getUserActivities(
            @Path("userId") userId: String,
            @Query("platform") platform: String = "android",
            @Query("lang") lang: String = "ja",
            @Query("token") token: String,
            @Query("last_cursor") lastCursor: String): Call<Activities>

    @GET("2/user/{userId}/contacts")
    fun getUserContacts(
            @Path("userId") userId: String,
            @Query("platform") platform: String = "android",
            @Query("lang") lang: String = "ja",
            @Query("token") token: String): Call<Contacts>

    @GET("2/user/{userId}/contacts")
    fun getUserContacts(
            @Path("userId") userId: String,
            @Query("next_cursor") nextCursor: String,
            @Query("platform") platform: String = "android",
            @Query("lang") lang: String = "ja",
            @Query("token") token: String): Call<Contacts>

    @GET("1/user/{userId}/default_user")
    fun getUserDefaultUser(
            @Path("userId") userId: String,
            @Query("platform") platform: String = "android",
            @Query("lang") lang: String = "ja",
            @Query("token") token: String): Call<ResponseBody>

    @GET("2/user/{userId}/followers")
    fun getUserFollowers(
            @Path("userId") userId: String,
            @Query("platform") platform: String = "android",
            @Query("lang") lang: String = "ja",
            @Query("token") token: String): Call<Followers>

    @GET("2/user/{userId}/followers")
    fun getUserFollowers(
            @Path("userId") userId: String,
            @Query("next_cursor") nextCursor: String,
            @Query("platform") platform: String = "android",
            @Query("lang") lang: String = "ja",
            @Query("token") token: String): Call<Followers>

    @GET("2/user/{userId}")
    fun getUserV2(
            @Path("userId") userId: String,
            @Query("platform") platform: String = "android",
            @Query("lang") lang: String = "ja",
            @Query("token") token: String): Call<User>

    @GET("1/user/{userId}/visible_groups")
    fun getUserVisibleGroups(
            @Path("userId") userId: String,
            @Query("platform") platform: String = "android",
            @Query("lang") lang: String = "ja",
            @Query("token") token: String): Call<VisibleGroups>

    @GET("1/users/search")
    fun getUsersSearch(
            @Query("q") query: String,
            @Query("platform") platform: String = "android",
            @Query("lang") lang: String = "ja",
            @Query("token") token: String): Call<UsersSearchResult>

    @GET("1/users/search")
    fun getUsersSearch(
            @Query("q") query: String,
            @Query("page") page: Int,
            @Query("platform") platform: String = "android",
            @Query("lang") lang: String = "ja",
            @Query("token") token: String): Call<UsersSearchResult>

    @GET("version")
    fun getVersion(
            @Query("platform") platform: String = "android",
            @Query("lang") lang: String = "ja",
            @Query("token") token: String): Call<ResponseBody>

    @GET("1/video/{videoId}")
    fun getVideo(
            @Path("videoId") videoId: String,
            @Query("platform") platform: String = "android",
            @Query("lang") lang: String = "ja",
            @Query("token") token: String): Call<ResponseBody>

    //Shadow
    @GET("3/public_groups")
    fun getPublicGroupsV3(
            @Query("with_archived") withArchived: Boolean = true,
            @Query("count") count: Int,
            @Query("page") page: Int,
            @Query("platform") platform: String = "android",
            @Query("lang") lang: String = "ja",
            @Query("token") token: String): Call<List<Groups>>

    //TODO Query Param
    @GET("3/groups")
    fun getPrivateGroup(
            @Query("with_archived") withArchived: Boolean = true,
            @Query("count") count: Int,
            @Query("page") page: Int,
            @Query("platform") platform: String = "android",
            @Query("lang") lang: String = "ja",
            @Query("token") token: String): Call<List<Groups>>

    /*
    @GET("2/group/{groupId}")
    fun group(
            @Path("groupId") groupId: String,
            @Query("members_count") membersCount: Int = 1,
            @Query("fields") fields: String = "subleaders,group_bookmark_info",
            @Query("platform") platform: String = "android",
            @Query("lang") lang: String = "ja",
            @Query("token") token: String): Call<Group>

    @GET("1/group/{groupId}/members")
    fun groupMembersAll(
            @Path("groupId") groupId: String,
            @Query("members_count") membersCount: Int = 1000,
            @Query("platform") platform: String = "android",
            @Query("lang") lang: String = "ja",
            @Query("token") token: String): Call<Members>

    @GET("2/group/{groupId}/chats")
    fun threads(
            @Path("groupId") groupId: String,
            @Query("count") count: Int,
            @Query("older_than") olderThan: String,
            @Query("newer_than") newerThan: String,
            @Query("platform") platform: String = "android",
            @Query("lang") lang: String = "ja",
            @Query("token") token: String): Call<List<Chat>>*/
    //endregion

    @GET("2/group/{groupId}/members")
    fun getGroupLeader(
            @Path("groupId") groupId: String,
            @Query("members_count") membersCount: Int = 1,
            @Query("fields") fields: String = "owner",
            @Query("platform") platform: String = "android",
            @Query("lang") lang: String = "ja",
            @Query("token") token: String): Call<Members>

    @GET("2/group/{groupId}/members")
    fun getGroupSubLeader(
            @Path("groupId") groupId: String,
            @Query("members_count") membersCount: Int = 1,
            @Query("fields") fields: String = "subleaders",
            @Query("platform") platform: String = "android",
            @Query("lang") lang: String = "ja",
            @Query("token") token: String): Call<Members>

    @GET("1/groups/chats/search")
    fun getChatSearch(
            //@Query("q") query: String,
            @Query("id") chatId: String,
            @Query("platform") platform: String = "android",
            @Query("lang") lang: String = "ja",
            @Query("token") token: String): Call<ResponseBody>


    //region http-post method
    @FormUrlEncoded
    @POST("1/group/{groupId}/chats")
    fun postGroupChat(
            @Path("groupId") groupId: String,
            @Field("type") type: String,// "shout" or "normal"
            @Field("message") message: String,
            @Field("lang") lang: String = "ja",
            @Field("token") token: String): Call<ChatResult>

    @FormUrlEncoded
    @POST("1/group/{groupId}/chats/boo")
    fun postGroupChatBoo(
            @Path("groupId") groupId: String,
            @Field("id") chatId: String,
            @Field("lang") lang: String = "ja",
            @Field("token") token: String): Call<RequestResult>

    @FormUrlEncoded
    @POST("1/group/{groupId}/chats/edit")
    fun postGroupChatEdit(
            @Path("groupId") groupId: String,
            @Field("id") chatId: String,
            @Field("lang") lang: String = "ja",
            @Field("token") token: String): Call<RequestResult>

    @FormUrlEncoded
    @POST("1/group/{groupId}/chats/like")
    fun postGroupChatLike(
            @Path("groupId") groupId: String,
            @Field("id") chatId: String,
            @Field("lang") lang: String = "ja",
            @Field("token") token: String): Call<RequestResult>

    @FormUrlEncoded
    @POST("1/group/{groupId}/chats/remove")
    fun postGroupChatRemove(
            @Path("groupId") groupId: String,
            @Field("id") chatId: String,
            @Field("lang") lang: String = "ja",
            @Field("token") token: String): Call<RequestResult>

    @FormUrlEncoded
    @POST("1/group/{groupId}/chats/unboo")
    fun postGroupChatUnboo(
            @Path("groupId") groupId: String,
            @Field("id") chatId: String,
            @Field("lang") lang: String = "ja",
            @Field("token") token: String): Call<RequestResult>

    @FormUrlEncoded
    @POST("1/group/{groupId}/chats/unlike")
    fun postGroupChatUnlike(
            @Path("groupId") groupId: String,
            @Field("id") chatId: String,
            @Field("lang") lang: String = "ja",
            @Field("token") token: String): Call<RequestResult>

    @FormUrlEncoded
    @POST("1/group/{groupId}/extract")
    fun postGroupExtract(
            @Path("groupId") groupId: String,
            @Field("lang") lang: String = "ja",
            @Field("token") token: String): Call<RequestResult>

    @FormUrlEncoded
    @POST("1/group/{groupId}/icon")
    fun postGroupIcon(
            @Path("groupId") groupId: String,
            @Field("icon") icon: String,
            @Field("lang") lang: String = "ja",
            @Field("token") token: String): Call<RequestResult>


    @FormUrlEncoded
    @POST("1/group/{groupId}/join")
    fun postGroupJoinWithGroupUid(
            @Path("groupId") groupId: String,
            @Field("lang") lang: String = "ja",
            @Field("token") token: String): Call<Group>

    @FormUrlEncoded
    @POST("2/group/{groupId}/join")
    fun postGroupJoinWithGroupUidV2(
            @Path("groupId") groupId: String,
            @Field("lang") lang: String = "ja",
            @Field("token") token: String): Call<Group>

    @FormUrlEncoded
    @POST("1/group/{groupId}/kick")
    fun postGroupKick(
            @Path("groupId") groupId: String,
            @Field("target_user") userId: String,
            @Field("lang") lang: String = "ja",
            @Field("token") token: String): Call<RequestResult>

    @FormUrlEncoded
    @POST("1/group/{groupId}/members")
    fun postGroupMembers(
            @Path("groupId") groupId: String,
            @Field("lang") lang: String = "ja",
            @Field("token") token: String): Call<RequestResult>


    // == quit
    @FormUrlEncoded
    @POST("1/group/{groupId}/part")
    fun postGroupPart(
            @Path("groupId") groupId: String,
            @Field("lang") lang: String = "ja",
            @Field("token") token: String): Call<RequestResult>

    @FormUrlEncoded
    @POST("1/group/{groupId}/privacy")
    fun postGroupPrivacy(
            @Path("groupId") groupId: String,
            @Field("lang") lang: String = "ja",
            @Field("token") token: String): Call<RequestResult>

    @FormUrlEncoded
    @POST("2/group/{groupId}/privacy")
    fun postGroupPrivacyV2(
            @Path("groupId") groupId: String,
            @Field("lang") lang: String = "ja",
            @Field("token") token: String): Call<RequestResult>

    @FormUrlEncoded
    @POST("1/group/{groupId}/push")
    fun postGroupPush(
            @Path("groupId") groupId: String,
            @Field("lang") lang: String = "ja",
            @Field("token") token: String): Call<RequestResult>


    @FormUrlEncoded
    @POST("1/group/{groupId}/remove")
    fun postGroupRemove(
            @Path("groupId") groupId: String,
            @Field("lang") lang: String = "ja",
            @Field("token") token: String): Call<RequestResult>

    @FormUrlEncoded
    @POST("1/group/{groupId}/restrictions")
    fun postGroupRestrictions(
            @Path("groupId") groupId: String,
            @Field("lang") lang: String = "ja",
            @Field("token") token: String): Call<RequestResult>

    @FormUrlEncoded
    @POST("1/group/{groupId}/subleaders")
    fun postGroupSubleaders(
            @Path("groupId") groupId: String,
            @Field("lang") lang: String = "ja",
            @Field("token") token: String): Call<RequestResult>


    @FormUrlEncoded
    @POST("1/group/{groupId}/subleaders/remove")
    fun postGroupSubleadersRemove(
            @Path("groupId") groupId: String,
            @Field("lang") lang: String = "ja",
            @Field("token") token: String): Call<RequestResult>

    @FormUrlEncoded
    @POST("1/group/{groupId}/transfer")
    fun postGroupTransfer(
            @Path("groupId") groupId: String,
            @Field("lang") lang: String = "ja",
            @Field("token") token: String): Call<RequestResult>

    @FormUrlEncoded
    @POST("1/group/{groupId}/visibility")
    fun postGroupVisibility(
            @Path("groupId") groupId: String,
            @Field("lang") lang: String = "ja",
            @Field("token") token: String): Call<RequestResult>

    @FormUrlEncoded
    @POST("1/group/{groupId}/wallpaper")
    fun postGroupWallpaper(
            @Path("groupId") groupId: String,
            @Field("wallpaper") wallpaper: String,
            @Field("lang") lang: String = "ja",
            @Field("token") token: String): Call<RequestResult>

    @FormUrlEncoded
    @POST("1/group/{groupId}/wallpaper/remove")
    fun postGroupWallpaperRemove(
            @Path("groupId") groupId: String,
            @Field("lang") lang: String = "ja",
            @Field("token") token: String): Call<RequestResult>

    @FormUrlEncoded
    @POST("1/groups")
    fun postGroups(
            @Field("lang") lang: String = "ja",
            @Field("token") token: String): Call<RequestResult>


    @FormUrlEncoded
    @POST("1/groups/1on1s")
    fun postGroups1on1s(
            @Field("lang") lang: String = "ja",
            @Field("token") token: String): Call<RequestResult>


    @FormUrlEncoded
    @POST("1/invitations")
    fun postInvitations(
            @Field("lang") lang: String = "ja",
            @Field("token") token: String): Call<RequestResult>

    @FormUrlEncoded
    @POST("1/me/blocking_users")
    fun postMeBlockingUsers(
            @Field("users") userId: String,
            @Field("lang") lang: String = "ja",
            @Field("token") token: String): Call<RequestResult>

    @FormUrlEncoded
    @POST("1/me/blocking_users/remove")
    fun postMeBlockingUsersRemove(
            @Field("users") userId: String,
            @Field("lang") lang: String = "ja",
            @Field("token") token: String): Call<RequestResult>

    @FormUrlEncoded
    @POST("1/me/bookmark/remove")
    fun postMeBookmarkRemove(
            @Field("chat_id") chatId: String,
            @Field("lang") lang: String = "ja",
            @Field("token") token: String): Call<RequestResult>

    @FormUrlEncoded
    @POST("1/me/bookmarks")
    fun postMeBookmarks(
            @Field("chat_id") chatId: String,
            @Field("lang") lang: String = "ja",
            @Field("token") token: String): Call<RequestResult>

    @FormUrlEncoded
    @POST("1/me/contacts")
    fun postMeContacts(
            @Field("users") userId: String,
            @Field("lang") lang: String = "ja",
            @Field("token") token: String): Call<RequestResult>

    @FormUrlEncoded
    @POST("1/me/contacts/remove")
    fun postMeContactsRemove(
            @Field("users") userId: String,
            @Field("lang") lang: String = "ja",
            @Field("token") token: String): Call<RequestResult>

    @FormUrlEncoded
    @POST("1/me/cover")
    fun postMeCover(
            @Field("cover") cover: String,
            @Field("lang") lang: String = "ja",
            @Field("token") token: String): Call<RequestResult>

    @FormUrlEncoded
    @POST("1/me/icon")
    fun postMeIcon(
            @Field("icon") icon: String,
            @Field("lang") lang: String = "ja",
            @Field("token") token: String): Call<RequestResult>

    @FormUrlEncoded
    @POST("1/me/profile")
    fun postMeProfile(
            @Field("lang") lang: String = "ja",
            @Field("token") token: String): Call<RequestResult>

    @FormUrlEncoded
    @POST("1/me/remove/complete")
    fun postMeRemoveComplete(
            @Field("lang") lang: String = "ja",
            @Field("token") token: String): Call<RequestResult>

    @FormUrlEncoded
    @POST("1/me/remove/confirm")
    fun postMeRemoveConfirm(
            @Field("lang") lang: String = "ja",
            @Field("token") token: String): Call<RequestResult>

    @FormUrlEncoded
    @POST("1/me/settings/auto_add_contacts")
    fun postMeSettingsAutoAddContacts(
            @Field("lang") lang: String = "ja",
            @Field("token") token: String): Call<RequestResult>

    @FormUrlEncoded
    @POST("1/notify_contacts")
    fun postNotifyContacts(
            @Field("lang") lang: String = "ja",
            @Field("token") token: String): Call<RequestResult>

    @FormUrlEncoded
    @POST("1/public_groups")
    fun postPublicGroups(
            @Field("lang") lang: String = "ja",
            @Field("token") token: String): Call<RequestResult>

    @FormUrlEncoded
    @POST("1/me/region")
    fun postRegion(
            @Field("lang") lang: String = "ja",
            @Field("token") token: String): Call<RequestResult>

    @FormUrlEncoded
    @POST("1/me/activity/remove")
    fun postRemoveActivity(
            @Field("lang") lang: String = "ja",
            @Field("token") token: String): Call<RequestResult>


    @FormUrlEncoded
    @POST("1/user/{userId}/visibility")
    fun postUserVisibility(
            @Path("userId") userId: String,
            @Field("lang") lang: String = "ja",
            @Field("token") token: String): Call<RequestResult>

    @FormUrlEncoded
    @POST("2/group/{groupId}/chats")
    fun postChatReply(
            @Path("groupId") groupId: String,
            @Field("token") token: String,
            @Field("reply_to") replyTo: String,
            @Field("message") message: String): Call<ChatResult>



    /*
    //TODO Enable Assets
    @FormUrlEncoded
    @POST("1/assets")
    fun assets(
            @Field("users") chatId: String,
            @Field("lang") lang: String = "ja",
            @Field("token") token: String): Call<RequestResult>

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
    @POST("1/group/{groupId}/refuse_invitation")
    fun refuseInvitation(
            @Path("groupId") groupId: String,
            @Field("lang") lang: String = "ja",
            @Field("token") token: String): Call<RequestResult>*/


    //endregion
    interface StreamingService{
        @GET("2/group/{groupId}")
        @Streaming
        fun getGroupStream(
                @Path("groupId") groupId: String,
                @Query("lang") lang: String = "ja",
                @Query("token") token: String): Observable<ResponseBody>
    }
}