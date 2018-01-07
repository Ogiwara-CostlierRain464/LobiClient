package jp.ogiwara.test.lobitest.other.strategy.user_list

import io.reactivex.android.schedulers.AndroidSchedulers
import jp.ogiwara.lobirepository.extention.getGooUser
import jp.ogiwara.lobirepository.model.UserListTokenWrapper
import jp.ogiwara.test.lobitest.repo

class ChatGooUserListStrategy(val chatId: String,val groupId: String) : UserListStrategy{
    override fun execute(callBack: (UserListTokenWrapper) -> Unit, token: String?, reload: Boolean) {
        repo.environment.getChat(repo,chatId,groupId).observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    it.getGooUser(repo,token).observeOn(AndroidSchedulers.mainThread())
                            .subscribe({
                                callBack(it)
                            },{})
                },{})
    }
}