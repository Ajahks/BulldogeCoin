package com.bulldoge.blockchain

import java.nio.charset.StandardCharsets

class Transaction(
    val sourceAddress: String,
    val destinationAddress: String
) {
    fun toByteArray(): ByteArray {
        return (sourceAddress + destinationAddress).toByteArray(StandardCharsets.UTF_8)
    }
}
