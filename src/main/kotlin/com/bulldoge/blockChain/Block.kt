package com.bulldoge.blockChain

import com.bulldoge.common.ByteUtils
import java.security.MessageDigest
import java.util.Date


class Block(
    var previousHash: ByteArray,
    var hash: ByteArray,
    var timestamp: Date,
    var transactions: List<Transaction>,
    var nonce: Long,
) {
    private val digest = MessageDigest.getInstance("SHA-256")

    fun calculateHash(): ByteArray {
        var transactionsByteArray = ByteArray(0)
        transactions.forEach { transactionsByteArray += it.toByteArray() }

        return digest.digest(
            previousHash +
                    ByteUtils.longToBytes(timestamp.time + nonce) +
                    transactionsByteArray
        )
    }
}