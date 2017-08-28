package jp.ogiwara.test.lobitest.chat_view

import jp.ogiwara.cycle.Action
import jp.ogiwara.lobiapi.model.Chat

data class LOADED(val chat: Chat): Action
