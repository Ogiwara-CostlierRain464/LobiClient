package jp.ogiwara.lobirepository.model

import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import io.reactivex.subjects.BehaviorSubject
import io.realm.Realm
import io.realm.RealmModel
import io.realm.RealmObject

//streamとrealm内包すればよい
//onNext,s1ubscribeの実装?
// Duck typing
class RealmWrappedBehaviorSubject<T: RealmModel>{

    val realm = Realm.getDefaultInstance()

    //val stream = BehaviorSubject.create<T>().toSerialized()

    val realmField: RealmObject = TODO()

    fun onNext(t: T){
        //stream.onNext(t)
        writeToRealm(t)
    }

    fun subscribe(func: (T)->Unit){
        realmField.addChangeListener<T>{ e, objectChangeSet ->
            func(e)
        }
        //!
        realmField.removeAllChangeListeners()
    }

    private fun writeToRealm(t: T){
        realm.executeTransaction {
            realm.copyToRealmOrUpdate(t)
        }
    }
}

/**
 * in-out
 * connect realm to Rx Stream.
 * Repository自体を作り変える？
 * とりあえず簡単なモデルキャッシュだけ
 */
