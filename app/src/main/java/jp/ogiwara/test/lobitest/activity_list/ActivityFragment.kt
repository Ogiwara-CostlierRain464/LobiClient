package jp.ogiwara.test.lobitest.activity_list

import android.databinding.DataBindingUtil
import android.databinding.ObservableArrayList
import android.databinding.ObservableBoolean
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import jp.ogiwara.cycle.BasicStore
import jp.ogiwara.lobiapi.model.Activity
import jp.ogiwara.test.lobitest.R
import jp.ogiwara.test.lobitest.databinding.FragmentActivityBinding
import jp.ogiwara.test.lobitest.other.strategy.activity_list.ActivitiesStrategy
import jp.ogiwara.test.lobitest.other.util.math.RecyclerViewBottomListener


class ActivityFragment : Fragment(), SwipeRefreshLayout.OnRefreshListener{

    companion object{
        fun newInstance(strategy: ActivitiesStrategy): ActivityFragment{
            val f = ActivityFragment()
            val args = Bundle()
            args.putSerializable(ARG.STRATEGY,strategy)
            f.arguments = args
            return f
        }
    }

    object ARG{
        const val STRATEGY = "strategy"
    }

    lateinit private var store: BasicStore<State>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if(savedInstanceState == null){
            store = BasicStore(State(strategy = arguments.getSerializable(ARG.STRATEGY) as ActivitiesStrategy), dispatcher ,context)
        }else{
            val str = savedInstanceState.getSerializable(ARG.STRATEGY) as ActivitiesStrategy
            store = BasicStore(State(strategy = str), dispatcher ,context)
        }
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding: FragmentActivityBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_activity,container,false)

        binding.list.setOnReachLastListener {
            //store.dispatch(LOAD)
        }

        binding.store = store
        binding.handler = this

        store.dispatch(LOAD)

        return binding.root
    }

    override fun onStop() {
        super.onStop()

        store.dispatch(DESTROY)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        outState.putSerializable(ARG.STRATEGY, store.state.strategy)
    }

    override fun onRefresh() {
        store.dispatch(RELOAD())
    }
}