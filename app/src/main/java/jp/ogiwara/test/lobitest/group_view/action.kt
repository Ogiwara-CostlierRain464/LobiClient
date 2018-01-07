package jp.ogiwara.test.lobitest.group_view

import jp.ogiwara.cycle.Action
import jp.ogiwara.lobiapi.model.Chat
import jp.ogiwara.lobiapi.model.Group
import jp.ogiwara.lobirepository.extention.ReactiveChat
import jp.ogiwara.lobirepository.extention.ReactiveGroup

class OBSERVE(val e: ReactiveGroup,val callBack: (Group) -> Unit, val error: (Throwable) -> Unit): Action
object DESTROY: Action

sealed class Action2{
    object DESTROY: Action2()
}