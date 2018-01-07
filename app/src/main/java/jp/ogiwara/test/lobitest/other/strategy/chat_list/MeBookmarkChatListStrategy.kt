package jp.ogiwara.test.lobitest.other.strategy.chat_list

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import jp.ogiwara.lobiapi.model.Chat
import jp.ogiwara.lobirepository.extention.getBookmarks
import jp.ogiwara.lobirepository.model.ChatListTokenWrapper
import jp.ogiwara.test.lobitest.repo

class MeBookmarkChatListStrategy : ChatListStrategy{
    override fun execute(callBack: (ChatListTokenWrapper) -> Unit, token: String?, reload: Boolean): Disposable {
        return repo.me().observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    it.getBookmarks(repo,token).observeOn(AndroidSchedulers.mainThread())
                            .subscribe({
                                callBack(it)
                            },{})
                },{})
    }
}