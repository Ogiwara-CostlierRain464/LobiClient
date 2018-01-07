package jp.ogiwara.lobirepository.extention

import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import jp.ogiwara.lobiapi.model.*
import jp.ogiwara.lobirepository.LobiRepository
import jp.ogiwara.lobirepository.model.UserListTokenWrapper
import kotlin.reflect.KClass
import kotlin.reflect.full.functions
import kotlin.reflect.memberFunctions

fun makeChat(gid: String,cid: String) = Chat().also {
    it.id = cid
    it.group = Group().also {
        it.uid = gid
    }
}


fun Chat.postLike(client: LobiRepository): Single<RequestResult> {
    return Single.fromCallable {
        client.lobiAPI.postGroupChatLike(group.uid, id)
    }.subscribeOn(Schedulers.io()).doAfterSuccess {
        client.environment.reloadChat(client,group.uid,id)
    }
}


fun Chat.postUnlike(client: LobiRepository): Single<RequestResult> {
    return Single.fromCallable {
        client.lobiAPI.postGroupChatUnlike(group.uid, id)
    }.subscribeOn(Schedulers.io()).doAfterSuccess {
        client.environment.reloadChat(client,group.uid,id)
    }
}

fun Chat.postBoo(client: LobiRepository): Single<RequestResult> {
    return Single.fromCallable {
        client.lobiAPI.postGroupChatBoo(group.uid, id)
    }.subscribeOn(Schedulers.io()).doAfterSuccess {
        client.environment.reloadChat(client,group.uid,id)
    }
}

fun Chat.postUnBoo(client: LobiRepository): Single<RequestResult> {
    return Single.fromCallable {
        client.lobiAPI.postGroupChatUnboo(group.uid, id)
    }.subscribeOn(Schedulers.io()).doAfterSuccess {
        client.environment.reloadChat(client,group.uid,id)
    }
}

fun Chat.postBookmark(c: LobiRepository): Single<RequestResult> {
    return Single.fromCallable {
        c.lobiAPI.postMeBookmarks(id)
    }.subscribeOn(Schedulers.io()).doAfterSuccess {
        c.environment.reloadChat(c,group.uid,id)
    }
}

fun Chat.postUnBookmark(c: LobiRepository): Single<RequestResult> {
    return Single.fromCallable {
        c.lobiAPI.postMeBookmarkRemove(id)
    }.subscribeOn(Schedulers.io()).doAfterSuccess {
        c.environment.reloadChat(c,group.uid,id)
    }
}

fun Chat.postReply(c: LobiRepository,message: String): Single<ChatResult> {
    return Single.fromCallable {
        c.lobiAPI.postChatReply(this.group.uid, this.id, message)
    }.subscribeOn(Schedulers.io()).doAfterSuccess {
        c.environment.reloadChat(c,group.uid,id)
    }
}

fun Chat.getGooUser(c: LobiRepository, token: String? = null): Single<UserListTokenWrapper> {

    return Single.fromCallable {
        if (token != null) c.lobiAPI.getGroupChatPokes(group.uid, token) else c.lobiAPI.getGroupChatPokes(group.uid, id)
    }.subscribeOn(Schedulers.io())
            .flatMap { e ->
                Single.create<UserListTokenWrapper> {
                    val data = e.users.filter {
                        it.type == "like"
                    }
                    it.onSuccess(UserListTokenWrapper(data.map { c.environment.getUser(c, it.user.uid, newOne = it.user as User) }, if(e.next_cursor != "-1") e.next_cursor else null))
                }
            }
}

fun Chat.getBooUser(c: LobiRepository, token: String? = null): Single<UserListTokenWrapper> {
    return Single.fromCallable {
        if (token != null) c.lobiAPI.getGroupChatPokes(group.uid, token) else c.lobiAPI.getGroupChatPokes(group.uid, id)
    }.subscribeOn(Schedulers.io())
            .flatMap { e ->
                Single.create<UserListTokenWrapper> {
                    val data = e.users.filter {
                        it.type != "like"
                    }
                    it.onSuccess(UserListTokenWrapper(data.map { c.environment.getUser(c, it.user.uid, newOne = it.user as User) }, if(e.next_cursor != "-1") e.next_cursor else null))
                }
            }
}


fun Chat.group(c: LobiRepository): ReactiveGroup
    = c.environment.getGroup(c,group.uid)

fun Chat.user(c: LobiRepository): ReactiveUser
    = c.environment.getUser(c,user.uid)