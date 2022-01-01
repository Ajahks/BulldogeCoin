package com.bulldoge.blockChain

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import java.security.KeyFactory
import java.security.PublicKey
import java.security.spec.X509EncodedKeySpec

class TransactionTests {

    @Mock
    private lateinit var address1: Address

    @Mock
    private lateinit var address2: Address

    private lateinit var combinedString: String
    private lateinit var testTransaction: Transaction

    companion object {
        private const val testAmount = 100.00
    }

    @BeforeEach
    fun setup() {
        MockitoAnnotations.openMocks(this)

        Mockito.`when`(address1.identifier).thenReturn("address1")
        Mockito.`when`(address2.identifier).thenReturn("address2")

        combinedString = address1.identifier + address2.identifier + testAmount
        testTransaction = Transaction(address1, address2, testAmount)
    }

    @Test
    fun `Given valid transaction when toByteArray is called, verify that byteArray is of fromAddress, toAddress, amount in that order`() {
        val byteArray = testTransaction.toByteArray()

        Assertions.assertArrayEquals(byteArray, combinedString.toByteArray(Charsets.UTF_8))
    }
}