package jp.ogiwara.lobirepository.model

import jp.ogiwara.lobiapi.model.Chat
import jp.ogiwara.lobirepository.extention.ReactiveChat


class ChatListTokenWrapper(override val list: List<ReactiveChat>,
                           override val token: String?) : ListTokenWrapper