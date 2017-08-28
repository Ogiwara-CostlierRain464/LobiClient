package jp.ogiwara.lobirepository.extention

import jp.ogiwara.lobiapi.model.Chat
import jp.ogiwara.lobiapi.model.Group
import jp.ogiwara.lobirepository.LobiRepository
import kotlinx.coroutines.experimental.CommonPool
import kotlinx.coroutines.experimental.async

fun Chat.like(client: LobiRepository) = async(CommonPool){
    client.lobiAPI.like(group.uid,id)
}

fun Chat.unlike(client: LobiRepository) = async(CommonPool){
    client.lobiAPI.unlike(group.uid,id)
}

fun Chat.boo(client: LobiRepository) = async(CommonPool){
    client.lobiAPI.boo(group.uid,id)
}

fun Chat.unboo(client: LobiRepository) = async(CommonPool){
    client.lobiAPI.unboo(group.uid,id)
}

fun Chat.bookmark(c: LobiRepository) = async(CommonPool){
    c.lobiAPI.bookmark(id)
}

fun Chat.unBookmark(c: LobiRepository) = async(CommonPool){
    c.lobiAPI.unBookmark(id)
}

