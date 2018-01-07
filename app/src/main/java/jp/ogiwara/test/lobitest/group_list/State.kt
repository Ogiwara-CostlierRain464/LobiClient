package jp.ogiwara.test.lobitest.group_list

import android.databinding.ObservableArrayList
import android.databinding.ObservableBoolean
import jp.ogiwara.cycle.State
import jp.ogiwara.lobiapi.model.Group
import jp.ogiwara.lobirepository.extention.ReactiveVal
import jp.ogiwara.test.lobitest.other.strategy.group_list.GroupsStrategy

data class State(var list: ObservableArrayList<ReactiveVal<Group>> = ObservableArrayList(),
                 val loading: ObservableBoolean = ObservableBoolean(false),
                 var strategy: GroupsStrategy,
                 var token: String? = null): State