package com.bulldoge.common

import java.math.BigInteger
import java.nio.ByteBuffer

class ByteUtils {
    companion object {
        private val longBuffer: ByteBuffer  = ByteBuffer.allocate(Long.SIZE_BYTES)

        fun longToBytes(x: Long): ByteArray {
            longBuffer.putLong(0,x)
            return longBuffer.array()
        }

        fun bytesToLong(bytes: ByteArray): Long {
            longBuffer.put(bytes, 0, bytes.size)
            longBuffer.flip()
            return longBuffer.getLong()
        }

        // source: https://www.geeksforgeeks.org/sha-256-hash-in-java/
        fun byteArrayToHexString(byteArray: ByteArray): String {
            // Convert byte array into signum representation
            val number = BigInteger(1, byteArray)

            // Convert message digest into hex value
            val hexString: StringBuilder = StringBuilder(number.toString(16))

            // Pad with leading zeros
            while (hexString.length < 32) {
                hexString.insert(0, '0')
            }

            return hexString.toString()
        }

    }
}