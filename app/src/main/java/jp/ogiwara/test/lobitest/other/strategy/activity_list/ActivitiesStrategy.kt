package jp.ogiwara.test.lobitest.other.strategy.activity_list

import io.reactivex.disposables.Disposable
import jp.ogiwara.lobiapi.model.Activities
import jp.ogiwara.lobiapi.model.Activity
import jp.ogiwara.lobirepository.model.ActivityListTokenWrapper
import java.io.Serializable

//streamをcloseしてもらう
interface ActivitiesStrategy : Serializable {
    fun execute(callBack: (ActivityListTokenWrapper) -> Unit,token: String?, reload: Boolean): Disposable
}