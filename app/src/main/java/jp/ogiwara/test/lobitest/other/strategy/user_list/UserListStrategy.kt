package jp.ogiwara.test.lobitest.other.strategy.user_list

import jp.ogiwara.lobiapi.model.User
import jp.ogiwara.lobirepository.extention.ReactiveVal
import jp.ogiwara.lobirepository.model.UserListTokenWrapper
import java.io.Serializable


interface UserListStrategy : Serializable{

    fun execute(callBack: (UserListTokenWrapper) -> Unit,token: String?, reload: Boolean)

}