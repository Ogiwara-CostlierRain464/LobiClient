package jp.ogiwara.test.lobitest.bookmark

import jp.ogiwara.cycle.Action
import jp.ogiwara.lobiapi.model.Chat

internal class LOAD: Action
internal data class LOADED(val list: List<Chat>): Action