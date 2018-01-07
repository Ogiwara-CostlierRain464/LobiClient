package jp.ogiwara.test.lobitest.other.strategy.user_list

import io.reactivex.android.schedulers.AndroidSchedulers
import jp.ogiwara.lobiapi.model.User
import jp.ogiwara.lobirepository.extention.ReactiveVal
import jp.ogiwara.lobirepository.model.UserListTokenWrapper
import jp.ogiwara.test.lobitest.repo

class SearchUserListStrategy(val query: String) : UserListStrategy{
    override fun execute(callBack: (UserListTokenWrapper) -> Unit, token: String?, reload: Boolean) {
        repo.environment.searchUsers(repo,query,reload).observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    callBack(it)
                },{})
    }
}