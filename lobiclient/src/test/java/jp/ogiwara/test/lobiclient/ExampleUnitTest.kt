package jp.ogiwara.test.lobiclient

import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).

 * @see [Testing documentation](http://d.android.com/tools/testing)
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        val repo = LobiRepository()
        repo.login("yushiogiwara@icloud.com","Maiko7039")
    }
}