package com.bulldoge.common

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

    }
}