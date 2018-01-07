package jp.ogiwara.lobiapi

import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.experimental.runners.Enclosed
import org.junit.runner.RunWith
import java.io.IOException

@RunWith(Enclosed::class)
class Test2 {
    class caseXX{
        @Before
        fun setUp(){
            println("setUp")
        }
        @Test
        fun testCase(){
            println("testCase")
        }
    }
}