package es.ua.eps.pruebasdebugger

import junit.framework.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class LenguajeTest {
    var lenguaje : Lenguaje? = null

    @Before
    fun setUp(){
        lenguaje = Lenguaje("C","Lenguaje procedural")
    }

    @Test
    fun test_nombre(){
        assertEquals("C", lenguaje?.typo)
    }

    @Test
    fun test_to_String(){
        assertEquals("C", lenguaje?.toString())
    }
}