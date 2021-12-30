package com.bulldoge.blockChain

import org.junit.Assert
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import java.util.Date

class BlockTests {

    @Test
    fun `Given two transactions with the same fields when calculateHash is called, verify that the hash is the same`() {
        val block1 = Block(
            previousHash = "prevHash",
            hash = "hash",
            timestamp = Date(1),
            transactions = listOf(Transaction("test1", "test2", 1.0)),
            nonce = 1
        )

        val block2 = Block(
            previousHash = "prevHash",
            hash = "hash",
            timestamp = Date(1),
            transactions = listOf(Transaction("test1", "test2", 1.0)),
            nonce = 1
        )

        Assertions.assertEquals(block1.calculateHash(), block2.calculateHash())
    }

    @Test
    fun `Given two transactions with different hash when calculateHash is called, verify that the hash is still the same`() {
        val block1 = Block(
            previousHash = "prevHash",
            hash = "hash1",
            timestamp = Date(1),
            transactions = listOf(Transaction("test1", "test2", 1.0)),
            nonce = 1
        )

        val block2 = Block(
            previousHash = "prevHash",
            hash = "hash2",
            timestamp = Date(1),
            transactions = listOf(Transaction("test1", "test2", 1.0)),
            nonce = 1
        )

        Assertions.assertEquals(block1.calculateHash(), block2.calculateHash())
    }

    @Test
    fun `Given two transactions with different previous hash when calculateHash is called, verify that the hash is different`() {
        val block1 = Block(
            previousHash = "prevHash1",
            hash = "hash",
            timestamp = Date(1),
            transactions = listOf(Transaction("test1", "test2", 1.0)),
            nonce = 1
        )

        val block2 = Block(
            previousHash = "prevHash2",
            hash = "hash",
            timestamp = Date(1),
            transactions = listOf(Transaction("test1", "test2", 1.0)),
            nonce = 1
        )

        Assertions.assertNotEquals(block1.calculateHash(), block2.calculateHash())
    }

    @Test
    fun `Given two transactions with different timestamp when calculateHash is called, verify that the hash is different`() {
        val block1 = Block(
            previousHash = "prevHash",
            hash = "hash",
            timestamp = Date(1),
            transactions = listOf(Transaction("test1", "test2", 1.0)),
            nonce = 1
        )

        val block2 = Block(
            previousHash = "prevHash",
            hash = "hash",
            timestamp = Date(2),
            transactions = listOf(Transaction("test1", "test2", 1.0)),
            nonce = 1
        )

        Assertions.assertNotEquals(block1.calculateHash(), block2.calculateHash())
    }

    @Test
    fun `Given two transactions with different transactions when calculateHash is called, verify that the hash is different`() {
        val block1 = Block(
            previousHash = "prevHash",
            hash = "hash",
            timestamp = Date(1),
            transactions = listOf(Transaction("test1", "test2", 1.0)),
            nonce = 1
        )

        val block2 = Block(
            previousHash = "prevHash",
            hash = "hash",
            timestamp = Date(1),
            transactions = listOf(
                Transaction("test1", "test2", 1.0),
                Transaction("test2", "test1", 1.0)
            ),
            nonce = 1
        )

        Assertions.assertNotEquals(block1.calculateHash(), block2.calculateHash())
    }

    @Test
    fun `Given two transactions with different nonce value when calculateHash is called, verify that the hash is different`() {
        val block1 = Block(
            previousHash = "prevHash",
            hash = "hash",
            timestamp = Date(1),
            transactions = listOf(Transaction("test1", "test2", 1.0)),
            nonce = 1
        )

        val block2 = Block(
            previousHash = "prevHash",
            hash = "hash",
            timestamp = Date(1),
            transactions = listOf(Transaction("test1", "test2", 1.0)),
            nonce = 2
        )

        Assertions.assertNotEquals(block1.calculateHash(), block2.calculateHash())
    }
}