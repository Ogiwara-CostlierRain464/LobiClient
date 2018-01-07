package jp.ogiwara.test.lobitest.user_list

import jp.ogiwara.cycle.Action
import jp.ogiwara.lobiapi.model.User
import jp.ogiwara.lobirepository.extention.ReactiveUser
import jp.ogiwara.lobirepository.extention.ReactiveVal

class LOAD : Action
class RELOAD: Action
class LOADED(val list: List<ReactiveUser>): Action
object DESTROY: Action