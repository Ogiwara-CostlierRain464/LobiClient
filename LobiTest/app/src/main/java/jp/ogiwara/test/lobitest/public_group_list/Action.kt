package jp.ogiwara.test.lobitest.public_group_list

import jp.ogiwara.cycle.Action
import jp.ogiwara.lobiapi.model.Group

internal class LOAD: Action
internal class LOADED(val list: List<Group>): Action