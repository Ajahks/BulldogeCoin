package com.bulldoge.blockChain

import java.security.PublicKey

class Address(
    val publicKey: PublicKey,
    val identifier: String,
    val owner: String,
)