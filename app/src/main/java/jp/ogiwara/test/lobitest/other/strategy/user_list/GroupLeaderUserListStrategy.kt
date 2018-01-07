package jp.ogiwara.test.lobitest.other.strategy.user_list

import io.reactivex.android.schedulers.AndroidSchedulers
import jp.ogiwara.lobirepository.extention.getLeader
import jp.ogiwara.lobirepository.model.UserListTokenWrapper
import jp.ogiwara.test.lobitest.repo

class GroupLeaderUserListStrategy(val groupId: String) : UserListStrategy{
    override fun execute(callBack: (UserListTokenWrapper) -> Unit, token: String?, reload: Boolean) {
        repo.environment.getGroup(repo,groupId).observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    it.getLeader(repo).observeOn(AndroidSchedulers.mainThread())
                            .subscribe({
                                callBack(it)
                            },{})
                },{})
    }
}