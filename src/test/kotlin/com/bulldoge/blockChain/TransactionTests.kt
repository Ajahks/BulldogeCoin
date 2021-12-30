package com.bulldoge.blockChain

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class TransactionTests {

    companion object {
        private const val testFrom = "testFrom"
        private const val testTo = "testTo"
        private const val testAmount = 100.00
        private const val combinedString = testFrom + testTo + testAmount
        private val testTransaction = Transaction(testFrom, testTo, testAmount)
    }

    @Test
    fun `Given valid transaction when toByteArray is called, verify that byteArray is of fromAddress, toAddress, amount in that order`() {
        val byteArray = testTransaction.toByteArray()

        Assertions.assertArrayEquals(byteArray, combinedString.toByteArray(Charsets.UTF_8))
    }
}