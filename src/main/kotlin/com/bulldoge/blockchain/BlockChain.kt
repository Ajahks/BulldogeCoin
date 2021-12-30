package com.bulldoge.blockchain

class BlockChain {
    private val chain: List<Block>

    init {
        val genesisBlock = Block("0", "0", Transaction("",""))
        chain = listOf(genesisBlock)
    }
}