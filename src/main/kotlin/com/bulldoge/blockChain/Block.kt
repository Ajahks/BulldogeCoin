package com.bulldoge.blockChain

import com.bulldoge.common.ByteUtils
import java.security.MessageDigest
import java.util.Date


class Block(
    var previousHash: String,
    var hash: String,
    var timestamp: Date,
    var transactions: List<Transaction>,
    var nonce: Long,
) {
    private val digest = MessageDigest.getInstance("SHA-256")

    fun calculateHash(): String {
        var transactionsByteArray = ByteArray(0)
        transactions.forEach { transactionsByteArray += it.toByteArray() }
        val hash = digest.digest(
            previousHash.toByteArray(Charsets.UTF_8) +
                    ByteUtils.longToBytes(timestamp.time + nonce) +
                    transactionsByteArray
        )

        return ByteUtils.byteArrayToHexString(hash)
    }
}