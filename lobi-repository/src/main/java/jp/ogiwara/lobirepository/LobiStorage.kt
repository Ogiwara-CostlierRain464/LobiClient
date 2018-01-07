package jp.ogiwara.lobirepository

import io.realm.Realm
import jp.ogiwara.lobirepository.model.UserData

class LobiStorage {

    var token: String?
        get() {
            return Realm.getDefaultInstance().where(UserData::class.java).findFirst()?.token
        }
        set(value) {
            Realm.getDefaultInstance().use { realm ->
                realm.executeTransaction {

                    val obj = UserData()
                    obj.id = 0
                    obj.token = value
                    realm.copyToRealmOrUpdate(obj)
                }
            }
        }

}