package jp.ogiwara.test.lobitest.chat_list

import android.databinding.DataBindingUtil
import android.databinding.ObservableBoolean
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.GridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import jp.ogiwara.cycle.BasicStore
import jp.ogiwara.test.lobitest.R
import jp.ogiwara.test.lobitest.databinding.FragmentChatListBinding
import jp.ogiwara.test.lobitest.other.strategy.chat_list.ChatListStrategy

class ChatListFragment: Fragment(), SwipeRefreshLayout.OnRefreshListener{

    companion object{
        fun newInstance(strategy: ChatListStrategy): ChatListFragment{
            val f = ChatListFragment()
            val args = Bundle()
            args.putSerializable(ARG.STRATEGY,strategy)
            f.arguments = args
            return f
        }
    }

    object ARG{
        const val STRATEGY = "strategy"
    }

    lateinit var store: BasicStore<State>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if(savedInstanceState == null){
            store = BasicStore(State(strategy = arguments.getSerializable(ARG.STRATEGY) as ChatListStrategy),dispatcher, context)
        }else{
            store = BasicStore(State(strategy = arguments.getSerializable(ARG.STRATEGY) as ChatListStrategy),dispatcher, context)
        }
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding: FragmentChatListBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_chat_list,container,false)
        binding.store = store
        binding.handler = this

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