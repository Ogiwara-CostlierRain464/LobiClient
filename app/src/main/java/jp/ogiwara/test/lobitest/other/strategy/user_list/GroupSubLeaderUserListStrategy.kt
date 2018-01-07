package jp.ogiwara.test.lobitest.other.strategy.user_list

import io.reactivex.android.schedulers.AndroidSchedulers
import jp.ogiwara.lobirepository.extention.getMembers
import jp.ogiwara.lobirepository.extention.getSubLeader
import jp.ogiwara.lobirepository.model.UserListTokenWrapper
import jp.ogiwara.test.lobitest.repo


class GroupSubLeaderUserListStrategy(val groupId: String): UserListStrategy {

    override fun execute(callBack: (UserListTokenWrapper) -> Unit, token: String?, reload: Boolean) {
        repo.environment.getGroup(repo,groupId,reload)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    it.getSubLeader(repo,reload).observeOn(AndroidSchedulers.mainThread())
                            .subscribe({
                                callBack(it)
                            },{})
                },{})
    }
}