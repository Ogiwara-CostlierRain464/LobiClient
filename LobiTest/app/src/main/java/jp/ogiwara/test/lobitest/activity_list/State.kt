package jp.ogiwara.test.lobitest.activity_list

import android.databinding.ObservableArrayList
import android.databinding.ObservableBoolean
import jp.ogiwara.lobiapi.model.Activity


data class State(val loading: ObservableBoolean = ObservableBoolean(false),
                 val list: ObservableArrayList<Activity> = ObservableArrayList())