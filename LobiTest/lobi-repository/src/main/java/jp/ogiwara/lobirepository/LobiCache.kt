package jp.ogiwara.lobirepository

import jp.ogiwara.lobiapi.model.Me
import org.cache2k.Cache2kBuilder
import java.util.concurrent.TimeUnit


class LobiCache {

    val meCache = Cache2kBuilder
            .of(String::class.java,Me::class.java)
            .expireAfterWrite(10,TimeUnit.MINUTES)
            .build()

    var me: Me?
        get() = meCache.peek("me")
        set(value) = meCache.put("me",value)

}