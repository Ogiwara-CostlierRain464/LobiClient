package jp.ogiwara.test.lobitest.chat_list

import android.databinding.ObservableArrayList
import android.databinding.ObservableBoolean
import jp.ogiwara.cycle.State
import jp.ogiwara.lobiapi.model.Chat
import jp.ogiwara.lobirepository.extention.ReactiveChat
import jp.ogiwara.test.lobitest.other.strategy.chat_list.ChatListStrategy

data class State(val loading: ObservableBoolean = ObservableBoolean(false),
                 val list: ObservableArrayList<ReactiveChat> = ObservableArrayList(),
                 var strategy: ChatListStrategy,
                 var token: String? = null): State
