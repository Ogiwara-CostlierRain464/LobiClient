package jp.ogiwara.test.lobitest.private_group_list

import android.databinding.DataBindingUtil
import android.databinding.ObservableArrayList
import android.databinding.ObservableBoolean
import android.os.Bundle
import android.support.v4.widget.SwipeRefreshLayout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import jp.ogiwara.cycle.BasicStore
import jp.ogiwara.lobiapi.model.Group
import jp.ogiwara.test.lobitest.R
import jp.ogiwara.test.lobitest.databinding.FragmentGroupListBaseBinding
import jp.ogiwara.test.lobitest.other.view.group_list.GroupListBaseFragment
import jp.ogiwara.test.lobitest.other.view.group_list.State

class PrivateGroupListFragment constructor(): GroupListBaseFragment(),SwipeRefreshLayout.OnRefreshListener{

    companion object{
        fun newInstance() = PrivateGroupListFragment()
    }

    lateinit private var store: BasicStore<State>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if(savedInstanceState == null){
            store = BasicStore(State(), reducer ,context)
        }else{
            val loading = ObservableBoolean(savedInstanceState.getBoolean("loading"))
            val list = ObservableArrayList<Group>()
            list.addAll(savedInstanceState.getParcelableArrayList<Group>("list"))
            store = BasicStore(State(list,loading), reducer,context)
        }
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding: FragmentGroupListBaseBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_group_list_base,container,false)
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
        outState?.putParcelableArrayList("list",store.state.groups)
    }

    override fun onRefresh() {
        store.dispatch(LOAD())
    }
}
