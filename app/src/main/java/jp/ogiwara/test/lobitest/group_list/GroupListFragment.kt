package jp.ogiwara.test.lobitest.group_list

import android.databinding.DataBindingUtil
import android.databinding.ObservableArrayList
import android.databinding.ObservableBoolean
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.GridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import jp.ogiwara.cycle.BasicStore
import jp.ogiwara.lobiapi.model.Group
import jp.ogiwara.test.lobitest.R
import jp.ogiwara.test.lobitest.databinding.FragmentGroupListBinding
import jp.ogiwara.test.lobitest.other.strategy.group_list.GroupsStrategy

class GroupListFragment : Fragment(), SwipeRefreshLayout.OnRefreshListener{

    companion object{
        fun newInstance(strategy: GroupsStrategy): GroupListFragment{
            val f = GroupListFragment()
            val args = Bundle()
            args.putSerializable(ARG.STRATEGY,strategy)
            f.arguments = args
            return  f
        }
    }

    object ARG{
        const val STRATEGY = "strategy"
    }

    lateinit private var store: BasicStore<State>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if(savedInstanceState == null){
            store = BasicStore(State(strategy = arguments.getSerializable(ARG.STRATEGY) as GroupsStrategy), dispatcher,context)
        }else{
            val strategy = savedInstanceState.getSerializable(ARG.STRATEGY) as GroupsStrategy
            store = BasicStore(State(strategy = strategy), dispatcher, context)
        }
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding: FragmentGroupListBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_group_list,container,false)
        binding.store = store
        binding.handler = this

        binding.swipeRefresh.setColorSchemeResources(R.color.lobi)

        binding.list.setOnReachLastListener {
            //store.dispatch(LOAD())
        }

        store.dispatch(LOAD())

        return binding.root
    }

    override fun onStop() {
        super.onStop()

        store.dispatch(DESTROY)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putSerializable(ARG.STRATEGY,store.state.strategy)
    }

    override fun onRefresh() {
        store.dispatch(RELOAD())
    }
}