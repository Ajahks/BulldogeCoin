package com.bulldoge.common

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class ByteUtilsTests {

    @Test
    fun `when long is encoded, verify that the same long can be decoded`() {
        val myLong = 1234L
        val encodedLong = ByteUtils.longToBytes(myLong)
        println(encodedLong.toString())
        val decodedLong = ByteUtils.bytesToLong(encodedLong)

        Assertions.assertEquals(myLong, decodedLong)
    }
}