package jp.ogiwara.test.lobitest.bookmark

import android.databinding.DataBindingUtil
import android.databinding.ObservableArrayList
import android.databinding.ObservableBoolean
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import jp.ogiwara.cycle.BasicStore
import jp.ogiwara.lobiapi.model.Chat
import jp.ogiwara.test.lobitest.R
import jp.ogiwara.test.lobitest.databinding.FragmentBookmarkBinding

class BookMarkFragment constructor(): Fragment(),SwipeRefreshLayout.OnRefreshListener{

    companion object{
        fun newInstance() = BookMarkFragment()
    }

    lateinit private var store: BasicStore<State>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if(savedInstanceState == null){
            store = BasicStore(State(), reducer,context)
        }else{
            val loading = ObservableBoolean(savedInstanceState.getBoolean("loading"))
            val list = ObservableArrayList<Chat>()
            list.addAll(savedInstanceState.getParcelableArrayList<Chat>("list"))
            store = BasicStore(State(list,loading), reducer,context)
        }
    }

    override fun onStart() {
        super.onStart()
        store.dispatch(LOAD())
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding: FragmentBookmarkBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_bookmark,container,false)
        val root = binding.root
        binding.store = store
        binding.handler = this

        return root
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)

        outState?.putBoolean("loading",store.state.loading.get())
        outState?.putParcelableArrayList("list",store.state.chats)
    }

    override fun onRefresh() {
        store.dispatch(LOAD())
    }
}