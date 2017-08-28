package jp.ogiwara.test.lobitest.reply

import android.content.Context
import android.support.v7.app.AlertDialog
import jp.ogiwara.cycle.Action
import jp.ogiwara.cycle.BasicStore
import jp.ogiwara.cycle.Reducer
import jp.ogiwara.lobirepository.extention.*
import jp.ogiwara.test.lobitest.R
import jp.ogiwara.test.lobitest.repo
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.launch
import org.jetbrains.anko.alert
import org.jetbrains.anko.noButton
import org.jetbrains.anko.toast
import org.jetbrains.anko.yesButton

internal val reducer = object: Reducer<State>{
    override fun reduce(store: BasicStore<State>, action: Action, context: Context) {
        when(action){
            is LOADED -> {
                store.state.userName.set(action.chat.user.name)
                store.state.message.set(action.chat.message)
                store.state.gooIng.set(action.chat.liked == 1)
                store.state.gooCount.set(action.chat.likes_count)
                store.state.booIng.set(action.chat.booed == 1)
                store.state.booCount.set(action.chat.boos_count)
                store.state.bookmarkIng.set(action.chat.is_me_bookmarked == 1)
                store.state.bookmarkCount.set(action.chat.bookmarks_count)
            }
            is GOO -> {
                val state = store.state
                launch(UI){
                    if(state.gooIng.get()){
                        if(action.chat.unlike(repo).await().isSuccess()){
                            state.gooIng.set(false)
                            state.gooCount.set(state.gooCount.get() - 1)
                        }else{
                            showCannotInterfere(context)
                        }
                    }else{
                        if(action.chat.like(repo).await().isSuccess()){
                            state.gooIng.set(true)
                            state.gooCount.set(state.gooCount.get() + 1)
                        }else{
                            showCannotInterfere(context)
                        }
                    }
                }
            }
            is BOO -> {
                val state = store.state
                launch(UI){
                    if(state.booIng.get()){
                        if(action.chat.unboo(repo).await().isSuccess()){
                            state.booIng.set(false)
                            state.booCount.set(state.booCount.get() - 1)
                        }else{
                            showCannotInterfere(context)
                        }
                    }else{
                        checkBoo(context,action,state)
                    }
                }
            }
            is BOOKMARK -> {
                val state = store.state
                launch(UI){
                    if(state.bookmarkIng.get()){
                        if(action.chat.unBookmark(repo).await().isSuccess()){
                            state.bookmarkIng.set(false)
                            state.bookmarkCount.set(state.bookmarkCount.get() - 1)
                        }else{
                            showCannotInterfere(context)
                        }
                    }else{
                        if(action.chat.bookmark(repo).await().isSuccess()){
                            state.bookmarkIng.set(true)
                            state.bookmarkCount.set(state.bookmarkCount.get() + 1)
                        }else{
                            showCannotInterfere(context)
                        }
                    }
                }
            }
        }
    }

    private fun showCannotInterfere(c: Context){
        c.alert(R.string.cannot_interfere).show()
    }

    private fun checkBoo(c: Context,action: BOO,state: State){
        /*c.alert(messageResource = R.string.boo_check){
            yesButton {
                launch(UI){
                    if(action.chat.boo(repo).await().isSuccess()){
                        state.booIng.set(true)
                        state.booCount.set(state.booCount.get() + 1)
                    }else{
                        showCannotInterfere(c)
                    }
                }
            }
            noButton {}
        }.show()*/

        AlertDialog.Builder(c,R.style.AlertDialog)
                .setTitle(R.string.boo_check)
                .setCancelable(false)
                .setPositiveButton("OK",{ d,i ->
                    launch(UI){
                        if(action.chat.boo(repo).await().isSuccess()){
                            state.booIng.set(true)
                            state.booCount.set(state.booCount.get() + 1)
                        }else{
                            showCannotInterfere(c)
                        }
                    }
                })
                .setNegativeButton("NO",{ d,i ->})
                .show()
    }
}
