package jp.ogiwara.lobiapi

import org.junit.Test

/**
 * Example local unit test, which will execute on the development machine (host).

 * @see [Testing documentation](http://d.android.com/tools/testing)
 */
class ExampleUnitTest {
    @Test
    fun userAgentTest(){
        val api = LobiAPI()
        api.login("mail","pass")

        println(api.getMe().name)
    }
}