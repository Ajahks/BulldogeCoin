package com.bulldoge.blockChain

import com.bulldoge.common.ByteUtils
import java.security.KeyPair
import java.security.MessageDigest
import java.security.PublicKey
import java.util.Date
import javax.crypto.Cipher

class Transaction(
    val fromAddress: Address,
    val toAddress: Address,
    val amount: Double,
    val timestamp: Date
) {

    private var signature: ByteArray? = null
    private var hash: ByteArray
    private val digest = MessageDigest.getInstance("SHA-256")

    init {
        hash = calculateHash()
    }

    fun calculateHash(): ByteArray {
        return digest.digest(
            (fromAddress.identifier + toAddress.identifier + amount).toByteArray(Charsets.UTF_8) +
                    ByteUtils.longToBytes(timestamp.time)
        )
    }

    fun toByteArray(): ByteArray {
        return (fromAddress.identifier + toAddress.identifier + amount).toByteArray(Charsets.UTF_8) +
                ByteUtils.longToBytes(timestamp.time) + hash
    }

    fun signTransaction(keyPair: KeyPair) {
        verifyPublicKeyIsOwnedBySender(fromAddress.publicKey)

        val encryptCipher = Cipher.getInstance("RSA")
        encryptCipher.init(Cipher.ENCRYPT_MODE, keyPair.private)

        signature = encryptCipher.doFinal(hash)
    }

    private fun verifyPublicKeyIsOwnedBySender(publicKey: PublicKey) {
        if (publicKey != fromAddress.publicKey) {
            throw RuntimeException("KeyPair is not valid for signing this transaction.")
        }
    }
}
