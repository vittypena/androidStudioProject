package es.ua.eps.calculadora

import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun testView(){
        assertNotNull(R.id.tvResult)
        assertNotNull(R.id.etSumando1)
        assertNotNull(R.id.etSumando2)
        assertNotNull(R.id.btCalcular)
    }

}