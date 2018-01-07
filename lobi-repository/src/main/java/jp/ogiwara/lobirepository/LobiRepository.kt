package jp.ogiwara.lobirepository

import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.BehaviorSubject
import jp.ogiwara.lobiapi.LobiAPI
import jp.ogiwara.lobiapi.exception.LoginException
import jp.ogiwara.lobiapi.model.*
import jp.ogiwara.lobirepository.model.Environment

/**
 * DI用のコンストラクタ
 *
 *
 * TODO Optimizer
 * TODO キャッシュは参照の局所性を確認してから
 *
 * NOTE Chat#groupはnullであってはならない
 *
 * NOTE 三大ReactiveObject: Group, User, Chat
 *
 * NOTE Chat.groupというように、三大オブジェクトはネストしない!
 */
class LobiRepository(val lobiAPI: LobiAPI,val lobiCache: LobiCache,val lobiStorage: LobiStorage){

    //region login

    fun login(mail: String,pass: String):Completable =
        Completable.create {
            try {
                lobiAPI.login(mail, pass)
            }catch (e: LoginException){
                it.onError(e)
            }

            it.onComplete()
        }.subscribeOn(Schedulers.io())

    fun twitterLogin(token: String,secret: String): Completable =
        Completable.create {
            try{
                lobiAPI.twitterLogin(token,secret)
            }catch (t: Throwable) {
                it.onError(t)
            }
            it.onComplete()
        }

    fun isLogined() = lobiAPI.token != null

    //endregion

    //region me or other

    fun me(reload: Boolean = false): Observable<Me>{

        fun remote(){
            Observable.fromCallable {
                lobiAPI.getMe()
            }.subscribeOn(Schedulers.io()).subscribe {
                lobiCache.me!!.onNext(it)
            }
        }

        if(lobiCache.me == null){
            lobiCache.me = BehaviorSubject.create<Me>().toSerialized()
            remote()
        }else if (reload){
            remote()
        }

        return lobiCache.me!!
    }

    val environment = Environment()

    //endregion

    //region data save or load

    fun save(){
        lobiStorage.token = lobiAPI.token
    }

    //Realmからtoken等をセットする
    fun load(){
        lobiAPI.token = lobiStorage.token
    }

    //endregion
}