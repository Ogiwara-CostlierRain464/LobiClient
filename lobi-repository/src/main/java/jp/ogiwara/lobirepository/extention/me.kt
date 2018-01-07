package jp.ogiwara.lobirepository.extention

import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import jp.ogiwara.lobiapi.model.*
import jp.ogiwara.lobirepository.LobiRepository
import jp.ogiwara.lobirepository.model.ActivityListTokenWrapper
import jp.ogiwara.lobirepository.model.ChatListTokenWrapper
import jp.ogiwara.lobirepository.model.GroupListTokenWrapper
import jp.ogiwara.lobirepository.model.NotificationListTokenWrapper

fun Me.getPrivateGroups(client: LobiRepository,reload: Boolean = false): Single<GroupListTokenWrapper> =
    Single.fromCallable {
        client.lobiAPI.getGroupsV3()
    }.subscribeOn(Schedulers.io())
    .flatMap {
        val list = it.map {
            client.environment.getGroup(client,it.uid,reload, newOne = it)
        }

        Single.create<GroupListTokenWrapper> {
            it.onSuccess(GroupListTokenWrapper(list,null))
        }
    }

fun Me.getBookmarks(client: LobiRepository,token: String? = null): Single<ChatListTokenWrapper> =
    Single.fromCallable {
        if(token == null) client.lobiAPI.getMeBookmarks() else client.lobiAPI.getMeBookmarks(token)
    }.subscribeOn(Schedulers.io())
    .flatMap { e ->
        Single.create<ChatListTokenWrapper>{
            it.onSuccess(ChatListTokenWrapper(e.data.map{ client.environment.getChat(client,it.id,it.group.uid ,newOne = it) } ,if(e.next_cursor != "-1") e.next_cursor else null))
        }
    }


fun Me.getMeActivities(c: LobiRepository,token: String? = null): Single<ActivityListTokenWrapper> =
    Single.fromCallable {
        if(token == null) c.lobiAPI.getMeActivities() else c.lobiAPI.getMeActivities(token)
    }.subscribeOn(Schedulers.io())
    .flatMap { e ->
        Single.create<ActivityListTokenWrapper> {
            it.onSuccess(ActivityListTokenWrapper(e.activity,e.last_cursor.toString()))
        }
    }

fun Me.getInfoActivities(c: LobiRepository,token: String? = null): Single<ActivityListTokenWrapper> =
    Single.fromCallable {
        if(token == null) c.lobiAPI.getActivities() else c.lobiAPI.getActivities(token)
    }.subscribeOn(Schedulers.io())
    .flatMap { e ->
        Single.create<ActivityListTokenWrapper> {
            it.onSuccess(ActivityListTokenWrapper(e.activity,e.last_cursor.toString()))
        }
    }

fun Me.getMeNotifications(c: LobiRepository,token: String? = null): Single<NotificationListTokenWrapper> =
    Single.fromCallable {
        if(token == null) c.lobiAPI.getMeNotifications() else c.lobiAPI.getMeNotifications(token)
    }.subscribeOn(Schedulers.io())
    .flatMap { e ->
        Single.create<NotificationListTokenWrapper>{
            it.onSuccess(NotificationListTokenWrapper(e.notifications,e.last_cursor.toString()))
        }
    }