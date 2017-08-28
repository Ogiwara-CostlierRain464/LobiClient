package jp.ogiwara.test.lobitest.activity_list

import android.databinding.DataBindingUtil
import android.databinding.ObservableArrayList
import android.databinding.ObservableBoolean
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import jp.ogiwara.cycle.BasicStore
import jp.ogiwara.lobiapi.model.Activity
import jp.ogiwara.test.lobitest.R
import jp.ogiwara.test.lobitest.databinding.FragmentActivityBinding


class ActivityFragment constructor() : Fragment(), SwipeRefreshLayout.OnRefreshListener{

    lateinit private var store: BasicStore<State>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if(savedInstanceState == null){
            store = BasicStore(State(), reducer,context)
        }else{
            val loading = ObservableBoolean(savedInstanceState.getBoolean("loading"))
            val list = ObservableArrayList<Activity>()
            list.addAll(savedInstanceState.getParcelableArrayList("list"))
            store = BasicStore(State(loading,list),reducer,context)
        }
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding: FragmentActivityBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_activity,container,true)
        val root = binding.root
        binding.store = store
        binding.handler = this

        return root
    }

    override fun onStart() {
        super.onStart()

        store.dispatch(LOAD())
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)

        outState?.putBoolean("loading",store.state.loading.get())
        outState?.putParcelableArrayList("list",store.state.list)
    }

    override fun onRefresh() {
        store.dispatch(LOAD())
    }

}