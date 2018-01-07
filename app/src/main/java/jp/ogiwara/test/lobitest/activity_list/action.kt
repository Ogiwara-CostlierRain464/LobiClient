package jp.ogiwara.test.lobitest.activity_list

import jp.ogiwara.cycle.Action
import jp.ogiwara.lobiapi.model.Activity

object LOAD : Action //initial load, additional load...
class RELOAD: Action //refresh token and load...
class LOADED(val list: List<Activity>): Action //set token and list
object DESTROY: Action