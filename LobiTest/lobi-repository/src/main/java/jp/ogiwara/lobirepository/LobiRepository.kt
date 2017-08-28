package jp.ogiwara.lobirepository

import jp.ogiwara.lobiapi.LobiAPI
import jp.ogiwara.lobiapi.model.*
import kotlinx.coroutines.experimental.*

/**
 * DI用のコンストラクタ
 *
 * 目的
 *
 * Serverの変更を即時適用は厳しい
 *
 * In-clientならまだしも…
 *
 * TODO キャッシュは参照の局所性を確認してから
 */
class LobiRepository(val lobiAPI: LobiAPI,private val lobiCache: LobiCache,private val lobiStorage: LobiStorage){
    /**
     * Tree-Map
     *
     * LOBI
     * - me
     * - other(find)
     * -- group_tab
     * -- chat
     * -- user
     *
     * User info?
     * - name
     * - description
     * - following
     * - follower
     * - (private-group_tab)
     * - public-group_tab
     * - (bookmark)
     * - activity
     * - (notification)
     *
     * 操作されるオブジェクトを明確に!
     */

    val me: Deferred<Me>
        get() = async(CommonPool){
            var me = lobiCache.me
            if(me == null){
                me = lobiAPI.getMe()
                lobiCache.me = me
            }
            me as Me
        }

    suspend fun login(mail: String,pass: String) = run(CommonPool) {
        lobiAPI.login(mail, pass)
    }

    fun twitterLogin(token: String,secret: String) = launch(CommonPool){
        lobiAPI.twitterLogin(token,secret)
    }

    fun save(){
        lobiStorage.token = lobiAPI.token
    }

    //Realmからtoken等をセットする
    fun load(){
        lobiAPI.token = lobiStorage.token
    }

    fun isLogined() = lobiAPI.token != null
}