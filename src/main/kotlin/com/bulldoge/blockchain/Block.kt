package com.bulldoge.blockchain

import java.nio.charset.StandardCharsets
import java.security.MessageDigest

class Block(
    var hash: String,
    var prevHash: String,
    var transaction: Transaction,
    var nonce: Long = 0
) {
    private val digest: MessageDigest = MessageDigest.getInstance("SHA-256")

    fun generateHash(): String {
        val encodedHash = digest.digest(prevHash.toByteArray(StandardCharsets.UTF_8) + transaction.toByteArray() + nonce.toByte())
        return encodedHash.toHex()
    }

    fun ByteArray.toHex(): String = joinToString(separator = "") { eachByte -> "%02x".format(eachByte) }
}