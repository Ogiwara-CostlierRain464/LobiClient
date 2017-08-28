package jp.ogiwara.test.lobitest

import android.support.multidex.MultiDex
import android.support.multidex.MultiDexApplication
import io.realm.Realm
import io.realm.RealmConfiguration
import jp.ogiwara.lobiapi.LobiAPI
import jp.ogiwara.lobirepository.LobiCache
import jp.ogiwara.lobirepository.LobiRepository
import jp.ogiwara.lobirepository.LobiStorage

val repo = LobiRepository(LobiAPI(), LobiCache(), LobiStorage())

class ThisApplication : MultiDexApplication(){

    override fun onCreate() {
        super.onCreate()
        MultiDex.install(this)

        Realm.init(this)

        val config = RealmConfiguration.Builder().deleteRealmIfMigrationNeeded().build()

        Realm.setDefaultConfiguration(config)
        repo.load()
    }

}