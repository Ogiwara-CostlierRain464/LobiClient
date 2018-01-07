package jp.ogiwara.test.lobitest.group_list

import jp.ogiwara.cycle.Action
import jp.ogiwara.lobiapi.model.Group
import jp.ogiwara.lobirepository.extention.ReactiveGroup
import jp.ogiwara.lobirepository.extention.ReactiveVal

class LOAD : Action
class RELOAD: Action
class LOADED(val list: List<ReactiveGroup>): Action
object DESTROY: Action