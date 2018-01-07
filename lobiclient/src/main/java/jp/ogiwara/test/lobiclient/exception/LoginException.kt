package jp.ogiwara.test.lobiclient.exception

import java.io.IOException

/**
 * ログインできない理由の基準
 */
class LoginException(val reason: String): IOException() {

    companion object{
        val INVALID_MAIL_ADDRESS = LoginException("Invalid mail address") //TODO 多言語
    }

    override val message: String?
        get() = reason
}