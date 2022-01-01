package com.bulldoge.blockChain

class Transaction(
    val fromAddress: Address,
    val toAddress: Address,
    val amount: Double,
) {
    fun toByteArray(): ByteArray {
        return (fromAddress.identifier + toAddress.identifier + amount).toByteArray(Charsets.UTF_8)
    }
}
