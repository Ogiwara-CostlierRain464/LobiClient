package jp.ogiwara.test.lobitest.other.strategy.group_list

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import jp.ogiwara.lobirepository.model.GroupListTokenWrapper
import jp.ogiwara.test.lobitest.repo

class SearchGroupsStrategy(val query: String): GroupsStrategy{
    override fun execute(callBack: (GroupListTokenWrapper) -> Unit, token: String?, reload: Boolean): Disposable{
        return repo.environment.searchGroups(repo,query)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    callBack(it)
                },{ println(it)})
    }
}