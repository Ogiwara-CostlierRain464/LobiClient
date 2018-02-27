package jp.ogiwara.lobirepository

import io.reactivex.subjects.Subject
import io.realm.Realm
import jp.ogiwara.lobiapi.model.Chat
import jp.ogiwara.lobiapi.model.Group
import jp.ogiwara.lobiapi.model.Me
import jp.ogiwara.lobiapi.model.User
import jp.ogiwara.lobirepository.util.CHAT_POOL_MAX_SIZE
import jp.ogiwara.lobirepository.util.GROUP_POOL_MAX_SIZE
import jp.ogiwara.lobirepository.util.SizeFixedMap
import jp.ogiwara.lobirepository.util.USER_POOL_MAX_SIZE

/**
 * リアクティブデータ型のためのパイプラインを格納
 * 実装は @see
 *
 * 主な機能
 * - ReactiveXによるデータストリーム: 成功
 * - オンメモリキャッシュ: 成功*
 * - ストレージキャッシュ: 成功
 * - 一定期間で破棄: TODO?
 * - バックグラウンドでのデータ自動更新: TODO
 *
 */
class LobiCache(){

    var me: Subject<Me>? = null

    val users = SizeFixedMap<String, Subject<User>>(USER_POOL_MAX_SIZE)

    val groups = SizeFixedMap<String, Subject<Group>>(GROUP_POOL_MAX_SIZE)

    val chats = SizeFixedMap<String,Subject<Chat>>(CHAT_POOL_MAX_SIZE)
}