package jp.ogiwara.lobirepository.extention

import jp.ogiwara.lobiapi.model.Me
import jp.ogiwara.lobirepository.LobiRepository
import kotlinx.coroutines.experimental.CommonPool
import kotlinx.coroutines.experimental.async

fun Me.privateGroupAll(client: LobiRepository) = async(CommonPool){
    client.lobiAPI.getPrivateGroupAll()
}

fun Me.bookmark(client: LobiRepository) = async(CommonPool){
    client.lobiAPI.getBookmarks()
}

fun Me.activity(c: LobiRepository) = async(CommonPool){
    c.lobiAPI.getActivity(uid)
}