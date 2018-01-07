package jp.ogiwara.lobirepository.extention

import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import jp.ogiwara.lobiapi.model.*
import jp.ogiwara.lobirepository.LobiRepository
import jp.ogiwara.lobirepository.model.ChatListTokenWrapper
import jp.ogiwara.lobirepository.model.GroupListTokenWrapper
import jp.ogiwara.lobirepository.model.ListTokenWrapper
import jp.ogiwara.lobirepository.model.UserListTokenWrapper

fun Group.postChat(client: LobiRepository, message: String, shout: Boolean = false): Single<ChatResult> =
    Single.fromCallable {
        client.lobiAPI.postGroupChat(uid,message,shout)
    }.subscribeOn(Schedulers.io()).doAfterSuccess {
        client.environment.reloadGroup(client,uid)
    }

fun Group.searchChat(client: LobiRepository,query: String,token: String? = null): Single<ChatListTokenWrapper> =
    Single.fromCallable {
        if(token == null){client.lobiAPI.getGroupChatsSearch(query = query,groupId = uid)}
        else{client.lobiAPI.getGroupChatsSearch(nextCursor = token,groupId = uid)}
    }.subscribeOn(Schedulers.io())
    .flatMap { e ->
        Single.create<ChatListTokenWrapper> {
            it.onSuccess(ChatListTokenWrapper(e.chats.map {
                val g = Group()
                g.uid = uid
                it.group = g

                client.environment.getChat(client,it.id,it.group.uid, newOne = it)
            },e.next_cursor))
        }
    }

fun Group.getChats(client: LobiRepository): Single<ChatListTokenWrapper> =
    Single.fromCallable {
        client.lobiAPI.getGroupChatV2(uid).map {
            val g = Group()
            g.uid = uid
            it.group = g
            it
        } //Optimize
    }.subscribeOn(Schedulers.io())
    .flatMap { e ->
        Single.create<ChatListTokenWrapper> {
            it.onSuccess(ChatListTokenWrapper(e.map { client.environment.getChat(client,it.id,it.group.uid,newOne = it) },null))
        }
    }


fun Group.getBookmarks(c: LobiRepository): Single<ChatListTokenWrapper> =
    Single.fromCallable {
        val data = c.lobiAPI.getGroupBookmarks(uid)
        data.data = data.data.map {
            val g = Group()
            g.uid = uid
            it.group = g
            it
        }// optimize
        data
    }.subscribeOn(Schedulers.io())
    .flatMap { e ->
        Single.create<ChatListTokenWrapper>{
            it.onSuccess(ChatListTokenWrapper(e.data.map { c.environment.getChat(c,it.id,it.group.uid, newOne = it) },if(e.next_cursor != "-1") e.next_cursor else null))
        }
    }

fun Group.getMembers(c: LobiRepository,reload: Boolean = false): Single<UserListTokenWrapper> =
    Single.fromCallable {
        c.lobiAPI.getGroupMembers(uid)
    }.subscribeOn(Schedulers.io())
    .flatMap { e ->
        Single.create<UserListTokenWrapper> {
            it.onSuccess(UserListTokenWrapper(e.members.map { c.environment.getUser(c,it.uid,reload, newOne = it as User) },if(e.next_cursor != "-1") e.next_cursor else null))
        }
    }

fun Group.postGroupJoin(c: LobiRepository): Single<Group> =
        Single.fromCallable {
            c.lobiAPI.postGroupJoinWithGroupUidV2(uid)
        }.subscribeOn(Schedulers.io()).doAfterSuccess {
            c.environment.reloadGroup(c,uid, it)
        }

fun Group.getLeader(c: LobiRepository,reload: Boolean = false): Single<UserListTokenWrapper> =
   Single.fromCallable {
       c.lobiAPI.getGroupLeader(uid)
   }.subscribeOn(Schedulers.io())
   .flatMap { e ->
       Single.create<UserListTokenWrapper>{
           it.onSuccess(UserListTokenWrapper(e.members.map { c.environment.getUser(c,it.uid,reload, newOne = it as User) },if(e.next_cursor != "-1") e.next_cursor else null))
       }
   }

fun Group.getSubLeader(c: LobiRepository,reload: Boolean = false): Single<UserListTokenWrapper> =
        Single.fromCallable {
            c.lobiAPI.getGroupSubLeader(uid)
        }.subscribeOn(Schedulers.io())
                .flatMap { e ->
                    Single.create<UserListTokenWrapper>{
                        it.onSuccess(UserListTokenWrapper(e.members.map { c.environment.getUser(c,it.uid,reload,newOne = it as User) },if(e.next_cursor != "-1") e.next_cursor else null))
                    }
                }

val Group.isJoined: Boolean
    get() = this.type != "not_joined"

fun Group.postGroupQuit(c: LobiRepository): Single<RequestResult> =
        Single.fromCallable {
            c.lobiAPI.postGroupPart(uid)
        }.subscribeOn(Schedulers.io()).doAfterSuccess {
            c.environment.reloadGroup(c,uid)
        }