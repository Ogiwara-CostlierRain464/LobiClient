package jp.ogiwara.test.lobitest.private_group_list

import jp.ogiwara.cycle.Action
import jp.ogiwara.lobiapi.model.Groups

internal class LOAD: Action
internal class LOADED(val list: List<Groups>): Action