package com.bulldoge.blockChain

import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import java.util.Date

@ExtendWith(MockKExtension::class)
class BlockTests {

    @MockK
    private lateinit var mockTransaction1: Transaction

    @MockK
    private lateinit var mockTransaction2: Transaction

    @BeforeEach
    fun setup() {
        every { mockTransaction1.toByteArray() } returns "mockTransaction1".toByteArray()
        every { mockTransaction2.toByteArray() } returns "mockTransaction2".toByteArray()
    }

    @Test
    fun `Given two transactions with the same fields when calculateHash is called, verify that the hash is the same`() {
        val block1 = Block(
            previousHash = "prevHash".toByteArray(),
            hash = "hash".toByteArray(),
            timestamp = Date(1),
            transactions = listOf(mockTransaction1),
            nonce = 1
        )

        val block2 = Block(
            previousHash = "prevHash".toByteArray(),
            hash = "hash".toByteArray(),
            timestamp = Date(1),
            transactions = listOf(mockTransaction1),
            nonce = 1
        )

        Assertions.assertArrayEquals(block1.calculateHash(), block2.calculateHash())
    }

    @Test
    fun `Given two transactions with different hash when calculateHash is called, verify that the hash is still the same`() {
        val block1 = Block(
            previousHash = "prevHash".toByteArray(),
            hash = "hash1".toByteArray(),
            timestamp = Date(1),
            transactions = listOf(mockTransaction1),
            nonce = 1
        )

        val block2 = Block(
            previousHash = "prevHash".toByteArray(),
            hash = "hash2".toByteArray(),
            timestamp = Date(1),
            transactions = listOf(mockTransaction1),
            nonce = 1
        )

        Assertions.assertArrayEquals(block1.calculateHash(), block2.calculateHash())
    }

    @Test
    fun `Given two transactions with different previous hash when calculateHash is called, verify that the hash is different`() {
        val block1 = Block(
            previousHash = "prevHash1".toByteArray(),
            hash = "hash".toByteArray(),
            timestamp = Date(1),
            transactions = listOf(mockTransaction1),
            nonce = 1
        )

        val block2 = Block(
            previousHash = "prevHash2".toByteArray(),
            hash = "hash".toByteArray(),
            timestamp = Date(1),
            transactions = listOf(mockTransaction1),
            nonce = 1
        )

        Assertions.assertFalse(block1.calculateHash().contentEquals(block2.calculateHash()))
    }

    @Test
    fun `Given two transactions with different timestamp when calculateHash is called, verify that the hash is different`() {
        val block1 = Block(
            previousHash = "prevHash".toByteArray(),
            hash = "hash".toByteArray(),
            timestamp = Date(1),
            transactions = listOf(mockTransaction1),
            nonce = 1
        )

        val block2 = Block(
            previousHash = "prevHash".toByteArray(),
            hash = "hash".toByteArray(),
            timestamp = Date(2),
            transactions = listOf(mockTransaction1),
            nonce = 1
        )

        Assertions.assertFalse(block1.calculateHash().contentEquals(block2.calculateHash()))
    }

    @Test
    fun `Given two transactions with different transactions when calculateHash is called, verify that the hash is different`() {
        val block1 = Block(
            previousHash = "prevHash".toByteArray(),
            hash = "hash".toByteArray(),
            timestamp = Date(1),
            transactions = listOf(mockTransaction1),
            nonce = 1
        )

        val block2 = Block(
            previousHash = "prevHash".toByteArray(),
            hash = "hash".toByteArray(),
            timestamp = Date(1),
            transactions = listOf(mockTransaction2),
            nonce = 1
        )

        Assertions.assertFalse(block1.calculateHash().contentEquals(block2.calculateHash()))
    }

    @Test
    fun `Given two transactions with different nonce value when calculateHash is called, verify that the hash is different`() {
        val block1 = Block(
            previousHash = "prevHash".toByteArray(),
            hash = "hash".toByteArray(),
            timestamp = Date(1),
            transactions = listOf(mockTransaction1),
            nonce = 1
        )

        val block2 = Block(
            previousHash = "prevHash".toByteArray(),
            hash = "hash".toByteArray(),
            timestamp = Date(1),
            transactions = listOf(mockTransaction1),
            nonce = 2
        )

        Assertions.assertFalse(block1.calculateHash().contentEquals(block2.calculateHash()))
    }
}