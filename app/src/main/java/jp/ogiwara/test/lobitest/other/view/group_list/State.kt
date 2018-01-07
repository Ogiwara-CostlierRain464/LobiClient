package jp.ogiwara.test.lobitest.other.view.group_list

import android.databinding.ObservableArrayList
import android.databinding.ObservableBoolean
import jp.ogiwara.lobiapi.model.Group

data class State(var groups: ObservableArrayList<Group> = ObservableArrayList(),
                 var loading: ObservableBoolean = ObservableBoolean(false))