package jp.ogiwara.test.lobitest.bookmark

import android.databinding.ObservableArrayList
import android.databinding.ObservableBoolean
import jp.ogiwara.lobiapi.model.Chat


internal data class State(var chats: ObservableArrayList<Chat> = ObservableArrayList(),
                          var loading: ObservableBoolean = ObservableBoolean(false))