package jp.ogiwara.lobirepository.model

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import io.realm.annotations.Required


open class UserData: RealmObject(){
    @PrimaryKey
    var id: Int = 0

    var token: String? = null
}