package jp.ogiwara.test.lobitest.other.strategy.activity_list

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import jp.ogiwara.lobiapi.model.Activities
import jp.ogiwara.lobiapi.model.Activity
import jp.ogiwara.lobirepository.extention.getInfoActivities
import jp.ogiwara.lobirepository.model.ActivityListTokenWrapper
import jp.ogiwara.test.lobitest.repo

class InfoActivitiesStrategy: ActivitiesStrategy{
    override fun execute(callBack: (ActivityListTokenWrapper) -> Unit, token: String?, reload: Boolean): Disposable {
       return repo.me(reload).observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    it.getInfoActivities(repo,token).observeOn(AndroidSchedulers.mainThread())
                            .doAfterSuccess {  }
                            .subscribe({
                                callBack(it)
                            },{})
                },{})
    }
}