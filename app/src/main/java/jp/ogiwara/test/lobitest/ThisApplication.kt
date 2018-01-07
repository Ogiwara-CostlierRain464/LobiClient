package jp.ogiwara.test.lobitest

import android.support.multidex.MultiDex
import android.support.multidex.MultiDexApplication
import com.facebook.drawee.backends.pipeline.Fresco
import io.realm.Realm
import io.realm.RealmConfiguration
import jp.ogiwara.lobiapi.LobiAPI
import jp.ogiwara.lobirepository.LobiCache
import jp.ogiwara.lobirepository.LobiRepository
import jp.ogiwara.lobirepository.LobiStorage

val repo = LobiRepository(LobiAPI(), LobiCache(), LobiStorage())

object JavaBridge{
    val repository = repo
}

class ThisApplication : MultiDexApplication(){

    override fun onCreate() {
        super.onCreate()
        MultiDex.install(this)

        Realm.init(this)
        Fresco.initialize(this);

        val config = RealmConfiguration.Builder().deleteRealmIfMigrationNeeded().build()

        Realm.setDefaultConfiguration(config)
        repo.load()
    }

}