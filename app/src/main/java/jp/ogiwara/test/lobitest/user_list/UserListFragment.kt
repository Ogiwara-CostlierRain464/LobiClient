package jp.ogiwara.test.lobitest.user_list

import android.content.Context
import android.databinding.DataBindingUtil
import android.databinding.ObservableBoolean
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.TextView
import jp.ogiwara.cycle.BasicStore
import jp.ogiwara.test.lobitest.R
import jp.ogiwara.test.lobitest.databinding.FragmentUserListBinding
import jp.ogiwara.test.lobitest.other.strategy.user_list.UserListStrategy
import jp.ogiwara.test.lobitest.user_icon.UserIconView
import org.jetbrains.anko.find

class UserListFragment : Fragment(), SwipeRefreshLayout.OnRefreshListener{

    companion object{

        fun newInstance(strategy: UserListStrategy): UserListFragment{
            val f = UserListFragment()
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
            store = BasicStore(State(strategy = arguments.getSerializable(ARG.STRATEGY) as UserListStrategy), dispatcher, context)
        }else{
            val strategy = savedInstanceState.getSerializable(ARG.STRATEGY) as UserListStrategy
            store = BasicStore(State(strategy = strategy), dispatcher,context)
        }
    }


    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding: FragmentUserListBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_user_list,container,false)
        binding.store = store
        binding.handler = this

        binding.recyclerView.setOnReachLastListener {
            //store.dispatch(LOAD())
        }

        store.dispatch(LOAD())

        return binding.root
    }

    override fun onRefresh() {
        store.dispatch(RELOAD())
    }

    override fun onStop() {
        super.onStop()

        store.dispatch(DESTROY)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        outState.putSerializable(ARG.STRATEGY,store.state.strategy)
    }
}

