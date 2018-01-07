package jp.ogiwara.lobirepository.model

/**
 * Decorator Pattern[GoF]
 *
 * struct{
 *  - Model List
 *  - Token
 * }
 */
interface ListTokenWrapper {
    val list: List<*>
    val token: String?
}