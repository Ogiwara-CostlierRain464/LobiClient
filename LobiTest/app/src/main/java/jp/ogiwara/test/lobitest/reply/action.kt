package jp.ogiwara.test.lobitest.reply

import jp.ogiwara.cycle.Action
import jp.ogiwara.lobiapi.model.Chat

internal data class LOADED(val chat: Chat): Action
internal data class GOO(val chat: Chat): Action
internal data class BOO(val chat: Chat): Action
internal data class BOOKMARK(val chat: Chat): Action