package jp.ogiwara.lobirepository.extention

import jp.ogiwara.lobiapi.model.Chat
import jp.ogiwara.lobiapi.model.Group
import jp.ogiwara.lobirepository.LobiRepository
import kotlinx.coroutines.experimental.CommonPool
import kotlinx.coroutines.experimental.async

fun Group.makeThread(client: LobiRepository, message: String, shout: Boolean = false) = async<Chat>(CommonPool){
    client.lobiAPI.chat(this@makeThread.uid,message,shout)
}

fun Group.searchChat(client: LobiRepository,query: String) = async(CommonPool){
    client.lobiAPI.searchGroupChats(query = query,groupId = uid)
}
