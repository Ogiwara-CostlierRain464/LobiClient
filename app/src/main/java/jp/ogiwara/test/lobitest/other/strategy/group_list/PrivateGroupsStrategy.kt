package jp.ogiwara.test.lobitest.other.strategy.group_list

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import jp.ogiwara.lobirepository.extention.getPrivateGroups
import jp.ogiwara.lobirepository.model.GroupListTokenWrapper
import jp.ogiwara.test.lobitest.repo

class PrivateGroupsStrategy : GroupsStrategy {

    override fun execute(callBack: (GroupListTokenWrapper) -> Unit, token: String?, reload: Boolean): Disposable{
        return repo.me().observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    it.getPrivateGroups(repo,reload).observeOn(AndroidSchedulers.mainThread())
                            .subscribe({
                                callBack(it)
                            },{})
                },{})
    }
}
