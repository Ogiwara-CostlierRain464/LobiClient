package jp.ogiwara.test.lobitest.user_list

import android.databinding.ObservableArrayList
import android.databinding.ObservableBoolean
import jp.ogiwara.cycle.State
import jp.ogiwara.lobiapi.model.User
import jp.ogiwara.lobirepository.extention.ReactiveVal
import jp.ogiwara.test.lobitest.other.strategy.user_list.UserListStrategy

class State(var list: ObservableArrayList<ReactiveVal<User>> = ObservableArrayList(),
            var loading: ObservableBoolean = ObservableBoolean(false),
            var strategy: UserListStrategy,
            var token: String? = null): State