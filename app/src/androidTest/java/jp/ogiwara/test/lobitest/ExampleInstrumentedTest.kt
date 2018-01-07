package jp.ogiwara.test.lobitest

import android.content.Context
import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4

import org.junit.Test
import org.junit.runner.RunWith
import javax.annotation.ParametersAreNonnullByDefault

import io.realm.OrderedCollectionChangeSet
import io.realm.OrderedRealmCollectionChangeListener
import io.realm.Realm
import io.realm.RealmList
import io.realm.RealmObject
import io.realm.RealmResults
import io.realm.annotations.PrimaryKey

import org.junit.Assert.*

/**
 * Instrumentation test, which will execute on an Android device.
 *
 * @see [Testing documentation](http://d.android.com/tools/testing)
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    //@Test
    @Throws(Exception::class)
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getTargetContext()


        val dog = Dog()
        dog.name = "Rex"
        dog.age = 1

        dog.addChangeListener<Dog>{ e, objectChangeSet ->

        }

        Realm.init(appContext)

        val realm = Realm.getDefaultInstance()

        val puppies = realm.where(Dog::class.java).lessThan("age", 2).findAll()

        realm.beginTransaction()
        val managedDog = realm.copyToRealm(dog)
        val person = realm.createObject(Person::class.java)
        person.dogs.add(managedDog)
        realm.commitTransaction()

        puppies.addChangeListener { dogs, orderedCollectionChangeSet ->
            println(orderedCollectionChangeSet!!.insertions)
        }
    }

    //why don't you make reactive repo?
    @Test
    fun reactiveRepo(){
        //val appContext = InstrumentationRegistry.getTargetContext()
        println("w")
    }
}
