package jp.ogiwara.lobirepository.extention

import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.annotations.Experimental
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.Subject
import jp.ogiwara.lobiapi.model.*
import jp.ogiwara.lobirepository.LobiRepository
import jp.ogiwara.lobirepository.model.ActivityListTokenWrapper
import jp.ogiwara.lobirepository.model.GroupListTokenWrapper
import jp.ogiwara.lobirepository.model.UserListTokenWrapper
import jp.ogiwara.lobirepository.util.cleanGroup
import java.util.concurrent.Future

fun User.getFollowers(client: LobiRepository, token: String? = null): Single<UserListTokenWrapper> =
    (if(this is Me){
        Single.fromCallable {
            if(token == null) client.lobiAPI.getMeFollowers() else client.lobiAPI.getMeFollowers(token)
        }.subscribeOn(Schedulers.io())
    }else{
        Single.fromCallable {
            if(token == null) client.lobiAPI.getUserFollowers(uid) else client.lobiAPI.getUserFollowers(uid,token)
        }.subscribeOn(Schedulers.io())
    }).flatMap { e ->
        Single.create<UserListTokenWrapper>{
            it.onSuccess(UserListTokenWrapper(e.users.map {
                client.environment.getUser(client,it.uid, newOne = it)
            },if(e.next_cursor != "-1") e.next_cursor else null))
        }
    }

fun User.getContacts(client: LobiRepository, token: String? = null): Single<UserListTokenWrapper> =
    (if(this is Me){
        Single.fromCallable {
            if(token == null) client.lobiAPI.getMeContacts() else client.lobiAPI.getMeContacts(token)
        }.subscribeOn(Schedulers.io())
    }else{
        Single.fromCallable {
            if(token == null) client.lobiAPI.getUserContacts(uid) else client.lobiAPI.getUserContacts(uid,token)
        }.subscribeOn(Schedulers.io())
    }).flatMap { e ->
        Single.create<UserListTokenWrapper>{
            it.onSuccess(UserListTokenWrapper(e.users.map {
                client.environment.getUser(client,it.uid, newOne = it)
            },if(e.next_cursor != "-1") e.next_cursor else null))
        }
    }

fun User.getPublicGroups(client: LobiRepository, reload: Boolean = false): Single<GroupListTokenWrapper> =
    (if(this is Me){
        Single.fromCallable {
            client.lobiAPI.getPublicGroups()
        }.subscribeOn(Schedulers.io())
    }else{
        Single.fromCallable {
            client.lobiAPI.getUsersVisibleGroups(uid)
        }.subscribeOn(Schedulers.io())
    }).flatMap {
        val list = it.map {
            client.environment.getGroup(client,it.uid,reload/*,newOne = it*/)
        }

        Single.create<GroupListTokenWrapper> {
            it.onSuccess(GroupListTokenWrapper(list, null))
        }
    }

fun User.getUserActivities(c: LobiRepository,token: String? = null): Single<ActivityListTokenWrapper> =
    Single.fromCallable {
        if(token == null) c.lobiAPI.getUserActivities(uid) else c.lobiAPI.getUserActivities(uid,token)
    }.subscribeOn(Schedulers.io())
    .flatMap { e ->
        Single.create<ActivityListTokenWrapper>{
            it.onSuccess(ActivityListTokenWrapper(e.activity,e.last_cursor.toString()))
        }
    }