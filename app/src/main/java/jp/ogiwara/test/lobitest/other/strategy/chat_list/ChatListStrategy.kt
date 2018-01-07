package jp.ogiwara.test.lobitest.other.strategy.chat_list

import io.reactivex.disposables.Disposable
import jp.ogiwara.lobiapi.model.Chat
import jp.ogiwara.lobirepository.model.ChatListTokenWrapper
import java.io.Serializable

interface ChatListStrategy: Serializable {
    fun execute(callBack: (ChatListTokenWrapper) -> Unit,token: String?, reload: Boolean): Disposable
}