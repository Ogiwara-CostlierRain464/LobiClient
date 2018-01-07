package jp.ogiwara.test.lobitest.activity_list

import android.databinding.ObservableArrayList
import android.databinding.ObservableBoolean
import jp.ogiwara.cycle.State
import jp.ogiwara.lobiapi.model.Activity
import jp.ogiwara.test.lobitest.other.strategy.activity_list.ActivitiesStrategy


data class State(val loading: ObservableBoolean = ObservableBoolean(false),
                 val list: ObservableArrayList<Activity> = ObservableArrayList(),
                 var strategy: ActivitiesStrategy,
                 var token: String? = null): State