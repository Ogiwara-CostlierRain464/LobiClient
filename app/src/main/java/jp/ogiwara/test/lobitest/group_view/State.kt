package jp.ogiwara.test.lobitest.group_view

import android.databinding.ObservableBoolean
import android.databinding.ObservableLong
import jp.ogiwara.cycle.State


data class State(var lastChatAt: Long = 0): State