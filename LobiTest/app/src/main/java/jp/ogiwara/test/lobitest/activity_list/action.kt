package jp.ogiwara.test.lobitest.activity_list

import jp.ogiwara.cycle.Action
import jp.ogiwara.lobiapi.model.Activity

class LOAD : Action
data class LOADED(val list: Activity): Action