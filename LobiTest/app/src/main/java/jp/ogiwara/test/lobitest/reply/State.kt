package jp.ogiwara.test.lobitest.reply

import android.databinding.ObservableBoolean
import android.databinding.ObservableField
import android.databinding.ObservableInt


data class State(val userName: ObservableField<String> = ObservableField(),
                 val message: ObservableField<String> = ObservableField(),
                 val gooIng: ObservableBoolean = ObservableBoolean(false),
                 val gooCount: ObservableInt = ObservableInt(0),
                 val booIng: ObservableBoolean = ObservableBoolean(false),
                 val booCount: ObservableInt = ObservableInt(0),
                 val bookmarkIng: ObservableBoolean = ObservableBoolean(false),
                 val bookmarkCount: ObservableInt = ObservableInt(0))