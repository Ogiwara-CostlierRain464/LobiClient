package jp.ogiwara.lobiapi

import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject
import org.junit.Test

//Repo test
class Test3 {

    val fond = Observable.create<Int> {
        it.onNext(0)
        it.onNext(1)
        it.onNext(2)
    }

    val subject = PublishSubject.create<Int>()

    @Test
    fun test(){
       val sub1 = subject
               .observeOn(Schedulers.computation())
               .subscribe {
                   println("Sub1: $it On: ${Thread.currentThread().name}")
               }

        val sub2 = subject
                .observeOn(Schedulers.computation())
                .subscribe {
                    println("Sub2: $it On: ${Thread.currentThread().name}")
                }

        fond
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.computation())
                .subscribe{
                    println("Subject Sending...")
                    subject.onNext(it)
                    println("Subject Sent $it On: ${Thread.currentThread().name}")
                }

        Thread.sleep(10L)
    }

    fun getGroup() = subject

}