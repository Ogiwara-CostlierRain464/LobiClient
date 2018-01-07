package jp.ogiwara.lobiapi.exception

import android.content.Context
import android.content.res.Resources
import jp.ogiwara.lobiapi.R
import java.io.IOException


/**
 * ログインできない理由の基準
 */
class LoginException(val reason: String): IOException() {

    companion object{
        val INVALID_MAIL_ADDRESS = LoginException("Invalid mail or password") //TODO 多言語
    }

    override val message: String?
        get() = reason
}