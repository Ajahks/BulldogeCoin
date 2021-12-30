package com.bulldoge.blockChain

class Transaction(
    val fromAddress: String,
    val toAddress: String,
    val amount: Double
) {
    fun toByteArray(): ByteArray {
        return (fromAddress + toAddress + amount).toByteArray(Charsets.UTF_8)
    }
}
