package com.bulldoge.blockChain

import com.bulldoge.common.ByteUtils
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import io.mockk.mockk
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import java.security.KeyPair
import java.security.PublicKey
import java.util.Date

@ExtendWith(MockKExtension::class)
class TransactionTests {

    @MockK
    private lateinit var address1: Address

    @MockK
    private lateinit var address2: Address

    private lateinit var combinedString: String
    private lateinit var testTransaction: Transaction

    companion object {
        private const val TEST_AMOUNT = 100.00

        private val timestamp = Date(1)
    }

    @BeforeEach
    fun setup() {
        every { address1.identifier } returns "address1"
        every { address2.identifier } returns "address2"

        combinedString = address1.identifier + address2.identifier + TEST_AMOUNT
        testTransaction = Transaction(address1, address2, TEST_AMOUNT, timestamp)
    }

    @Test
    fun `Given valid transaction when toByteArray is called, verify that byteArray is of fromAddress, toAddress, amount in that order`() {
        val byteArray = testTransaction.toByteArray()
        val hash = testTransaction.calculateHash()
        val expectedBytes = combinedString.toByteArray(Charsets.UTF_8) + ByteUtils.longToBytes(timestamp.time) + hash

        Assertions.assertArrayEquals(byteArray, expectedBytes)
    }

    @Test
    fun `Given signing with an invalid publicKey in the key pair, verify that exception is thrown`() {
        val mockKeyPair = mockk<KeyPair>()
        val mockPublicKey = mockk<PublicKey>()
        val mockOtherPublicKey = mockk<PublicKey>()

        every { mockKeyPair.public } returns mockPublicKey
        every { testTransaction.fromAddress.publicKey } returns mockOtherPublicKey

        Assertions.assertThrows(RuntimeException::class.java) {
            testTransaction.signTransaction(mockKeyPair)
        }
    }
}