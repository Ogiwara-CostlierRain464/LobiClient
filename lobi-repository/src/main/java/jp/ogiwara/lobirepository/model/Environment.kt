package jp.ogiwara.lobirepository.model

import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.annotations.Experimental
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.BehaviorSubject
import io.reactivex.subjects.PublishSubject
import jp.ogiwara.lobiapi.model.Chat
import jp.ogiwara.lobiapi.model.Group
import jp.ogiwara.lobiapi.model.Reply
import jp.ogiwara.lobiapi.model.User
import jp.ogiwara.lobirepository.LobiRepository
import jp.ogiwara.lobirepository.extention.*

//meとその他という分類
class Environment{

    fun getGroup(c: LobiRepository,groupId: String,reload: Boolean = false, newOne: Group? = null): ReactiveGroup{
        if(!c.lobiCache.groups.contains(groupId)) {
            val sub = BehaviorSubject.create<Group>().toSerialized()
            c.lobiCache.groups.set(groupId,sub)

            if(newOne != null){
                c.lobiCache.groups[groupId]?.onNext(newOne)
            }else{
                reloadGroup(c,groupId)
            }
        }else if(reload) {
            reloadGroup(c,groupId)
        }

        return c.lobiCache.groups[groupId]!!
    }

    fun reloadGroup(c: LobiRepository,groupId: String, newOne: Group? = null){
        if(newOne != null){
            c.lobiCache.groups[groupId]?.onNext(newOne)
        }

        Observable.fromCallable {
            c.lobiAPI.getGroupV2(groupId)
        }.subscribeOn(Schedulers.io()).subscribe {
            c.lobiCache.groups[groupId]?.onNext(it)
        }
    }

    fun getUser(c: LobiRepository,userId: String, reload: Boolean = false, newOne: User? = null): ReactiveUser{
        if(!c.lobiCache.users.contains(userId)){
            val sub = BehaviorSubject.create<User>().toSerialized()
            c.lobiCache.users[userId] = sub

            if(newOne != null){
                c.lobiCache.users[userId]?.onNext(newOne)
            }else{
                reloadUser(c,userId)
            }
        }else if(reload){
            reloadUser(c,userId)
        }

        return c.lobiCache.users[userId]!!
    }

    fun reloadUser(c: LobiRepository,userId: String){
        Observable.fromCallable {
            c.lobiAPI.getUserV2(userId)
        }.subscribeOn(Schedulers.io()).subscribe {
            c.lobiCache.users[userId]?.onNext(it)
        }
    }

    fun getChat(c: LobiRepository,chatId: String,groupId: String,reload: Boolean = false, newOne: Chat? = null): ReactiveChat{
        if(!c.lobiCache.chats.contains(chatId)){
            val sub = BehaviorSubject.create<Chat>().toSerialized()
            c.lobiCache.chats[chatId] = sub //TODO ここをラップしたRealmオブジェクトにすれば…

            if(newOne != null){
                   c.lobiCache.chats[chatId]?.onNext(newOne)
            }else{
                reloadChat(c,groupId,chatId)
            }
        }else if(reload){
            reloadChat(c,groupId,chatId)
        }

        return c.lobiCache.chats[chatId]!!

    }

    fun reloadChat(c: LobiRepository,groupId: String,chatId: String){
        Observable.fromCallable {
            val data = c.lobiAPI.getGroupChatReplies(groupId,chatId).to
            data.group = Group().apply { this.uid = groupId }
            data
        }.subscribeOn(Schedulers.io()).subscribe({
            c.lobiCache.chats[chatId]?.onNext(it)
        },{ println("groupId: $groupId, chatId: $chatId, ${it.message}") })
    }

    fun searchGroups(c: LobiRepository,query: String,reload: Boolean = false): Single<GroupListTokenWrapper> =
        Single.fromCallable {
            c.lobiAPI.getPublicGroupsSearch(query)
        }.subscribeOn(Schedulers.io())
        .flatMap { i1 ->
            //1 list<g> -> list<o<g>>
            val list = i1.items.map {
                c.environment.getGroup(c,it.uid,reload, newOne = it)
            }

            Single.create<GroupListTokenWrapper>{
                it.onSuccess(GroupListTokenWrapper(list,i1.next_page.toString()))
            }
        }

    fun searchChats(c: LobiRepository,query: String,token: String? = null): Single<ChatListTokenWrapper> =
        Single.fromCallable {
            if(token == null) c.lobiAPI.getPublicGroupsChatsSearch(query) else c.lobiAPI.getPublicGroupsChatsSearch(nextCursor = token)
        }.subscribeOn(Schedulers.io())
        .flatMap { e ->
            Single.create<ChatListTokenWrapper> {
                it.onSuccess(ChatListTokenWrapper(e.chats.map { c.environment.getChat(c,it.id,it.group.uid, newOne = it) },if(e.next_cursor != "-1") e.next_cursor else null))
            }
        }

    fun searchUsers(c: LobiRepository, query: String, reload: Boolean = false,token: String? = null): Single<UserListTokenWrapper> =
        Single.fromCallable {
            if(token == null) c.lobiAPI.getUsersSearch(query) else c.lobiAPI.getUsersSearch(query,token.toInt())
        }.subscribeOn(Schedulers.io())
        .flatMap { e ->
            Single.create<UserListTokenWrapper>{
                it.onSuccess(UserListTokenWrapper(e.users.map {
                    c.environment.getUser(c,it.uid,reload ,newOne = it)
                },if(e.has_next == 1) (e.page + 1).toString() else null ))
            }
        }

    fun getChatAndReplies(c: LobiRepository,groupId: String,chatId: String): Single<Reply>{
        return Single.fromCallable {
            val result = c.lobiAPI.getGroupChatReplies(groupId,chatId)
            val group = c.lobiAPI.getGroupV2(groupId)
            result.chats = result.chats.map {
                it.group = group
                it
            }
            result.to.group = group
            result
        }.subscribeOn(Schedulers.io())
    }
}