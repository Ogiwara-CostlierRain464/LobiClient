package jp.ogiwara.test.lobitest.chat_view

import android.arch.lifecycle.LifecycleObserver
import android.content.Context
import android.support.v7.app.AlertDialog
import android.util.Log
import android.widget.Toast
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import jp.ogiwara.cycle.Action
import jp.ogiwara.cycle.BasicStore
import jp.ogiwara.cycle.Dispatcher
import jp.ogiwara.lobiapi.model.Chat
import jp.ogiwara.lobirepository.extention.*
import jp.ogiwara.test.lobitest.E
import jp.ogiwara.test.lobitest.R
import jp.ogiwara.test.lobitest.repo

//グー、ぶーを押したときに、とりあえず反映
internal val dispatcher =  object: Dispatcher<State>{

    var chat: Chat? = null

    val disposers = mutableListOf<Disposable>()

    override fun dispatch(store: BasicStore<State>, action: Action, context: Context) {
        when(action){
            is SET_UP_VIEW -> {
                action.callBack()
            }
            is LOADED -> {
                action.chat.observeOn(AndroidSchedulers.mainThread())
                        .subscribe({
                            if(chat == null){
                                chat = makeChat(it.group.uid,it.id)
                            }

                            action.callBack(it)

                            store.state.replyCount.set(it.replies?.count ?: 0)
                            store.state.userName.set(it.user.name)
                            store.state.message.set(it.message)
                            store.state.gooIng.set(it.liked == 1)
                            store.state.gooCount.set(it.likes_count)
                            store.state.booIng.set(it.booed == 1)
                            store.state.booCount.set(it.boos_count)
                            store.state.bookmarkIng.set(it.is_me_bookmarked == 1)
                            store.state.bookmarkCount.set(it.bookmarks_count)
                        },action.onError).apply { disposers.add(this) }
            }
            is GOO -> {
                val state = store.state
                if(state.gooIng.get()){
                                chat?.postUnlike(repo)?.observeOn(AndroidSchedulers.mainThread())
                                        ?.subscribe({
                                            state.gooIng.set(false)
                                        },{ showCannotInterfere(context) })

                }else{
                            chat?.postLike(repo)?.observeOn(AndroidSchedulers.mainThread())
                                        ?.subscribe({
                                            state.gooIng.set(true)
                                        },{ showCannotInterfere(context) })

                }
            }
            is BOO -> {
                val state = store.state
                if(state.booIng.get()){
                                chat?.postUnBoo(repo)?.observeOn(AndroidSchedulers.mainThread())
                                        ?.subscribe({
                                            state.booIng.set(false)
                                        },{ showCannotInterfere(context) })
                }else{
                    checkBoo(context,action,state)
                }
            }
            is BOOKMARK -> {
                val state = store.state
                if(state.bookmarkIng.get()){
                                chat?.postUnBookmark(repo)?.observeOn(AndroidSchedulers.mainThread())
                                        ?.subscribe({
                                            state.bookmarkIng.set(false)
                                        },{ showCannotInterfere(context) })
                }else{
                                chat?.postBookmark(repo)
                                        ?.observeOn(AndroidSchedulers.mainThread())
                                        ?.subscribe({
                                            state.bookmarkIng.set(true)
                                        },{ showCannotInterfere(context) })
                }
            }
            is DESTROY -> {
                disposers.forEach {
                    it.dispose()
                }
            }
        }
    }

    fun showCannotInterfere(c: Context){
        Toast.makeText(c,R.string.cannot_interfere,Toast.LENGTH_SHORT).show()
    }

    fun checkBoo(c: Context,action: BOO,state: State){
        AlertDialog.Builder(c/*,R.style.AlertDialog*/)
                .setTitle(R.string.boo_check)
                .setPositiveButton(R.string.yes,{ _,_ ->
                                chat?.postBoo(repo)?.observeOn(AndroidSchedulers.mainThread())
                                        ?.subscribe({
                                            state.gooIng.set(true)
                                        },{ showCannotInterfere(c) })
                })
                .setNegativeButton(R.string.no,{ _,_ ->})
                .show()
    }
}