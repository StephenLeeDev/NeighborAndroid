package com.stephenleedev.neighborandroid

import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @Test
    fun add_commas_to_int_and_return_by_string() {
        assertEquals(123456789.addCommas, "123,456,789")
    }

}