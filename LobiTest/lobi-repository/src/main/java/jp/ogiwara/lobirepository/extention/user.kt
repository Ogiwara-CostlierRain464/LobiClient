package jp.ogiwara.lobirepository.extention

import jp.ogiwara.lobiapi.model.Group
import jp.ogiwara.lobiapi.model.Me
import jp.ogiwara.lobiapi.model.User
import jp.ogiwara.lobirepository.LobiRepository
import kotlinx.coroutines.experimental.CommonPool
import kotlinx.coroutines.experimental.Deferred
import kotlinx.coroutines.experimental.async

fun User.follower(client: LobiRepository) = async(CommonPool){
    if(this@follower is Me){
        client.lobiAPI.getFollowers()
    }else{
        client.lobiAPI.getFollowers(uid)
    }
}

fun User.following(client: LobiRepository) = async(CommonPool){
    if(this@following is Me){
        client.lobiAPI.getContacts()
    }else{
        client.lobiAPI.getContacts(uid)
    }
}

fun User.publicGroupAll(client: LobiRepository): Deferred<List<Group>> = async(CommonPool){
    if(this@publicGroupAll is Me){
        client.lobiAPI.getPublicGroup(0,20).first().items
    }else{
        client.lobiAPI.getPublicGroupAll(uid).public_groups
    }
}
