package jp.ogiwara.test.lobitest.other.strategy.group_list

import android.content.Context
import io.reactivex.disposables.Disposable
import jp.ogiwara.cycle.Action
import jp.ogiwara.cycle.BasicStore
import jp.ogiwara.cycle.State
import jp.ogiwara.lobiapi.model.Chat
import jp.ogiwara.lobiapi.model.Group
import jp.ogiwara.lobirepository.extention.ReactiveVal
import jp.ogiwara.lobirepository.model.GroupListTokenWrapper
import java.io.Serializable


interface GroupsStrategy : Serializable {
    fun execute(callBack: (GroupListTokenWrapper) -> Unit,token: String?,reload: Boolean): Disposable
}