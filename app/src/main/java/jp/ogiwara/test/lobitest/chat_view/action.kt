package jp.ogiwara.test.lobitest.chat_view

import jp.ogiwara.cycle.Action
import jp.ogiwara.lobiapi.model.Chat
import jp.ogiwara.lobirepository.extention.ReactiveChat

class SET_UP_VIEW(val callBack: () -> Unit): Action
data class LOADED(val chat: ReactiveChat,
                  val callBack: (Chat) -> Unit,
                  val onError: (Throwable) -> Unit): Action
data class GOO(val chat: ReactiveChat): Action
data class BOO(val chat: ReactiveChat): Action
data class BOOKMARK(val chat: ReactiveChat): Action
object DESTROY: Action