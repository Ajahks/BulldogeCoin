import com.bulldoge.blockChain.Block
import com.bulldoge.blockChain.Transaction
import com.bulldoge.common.ByteUtils
import java.time.Instant
import java.util.Date

fun main(args: Array<String>) {
    println("Hello World!")

    val myDate = Date(10002L)
    println("Today's date: $myDate" )
    println("Today's date in long: ${myDate.time}")
    println("Today's date in bytes: ${ByteUtils.longToBytes(myDate.time)}")

    val myBlock = Block(
        previousHash = "test",
        hash = "hash",
        timestamp = myDate,
        transactions = listOf(
            Transaction("yes", "ne", 100.00)
        ),
        nonce = 0
    )

    println("My block: $myBlock")
    println("My block's hash: ${myBlock.calculateHash()}")
    // Try adding program arguments via Run/Debug configuration.
    // Learn more about running applications: https://www.jetbrains.com/help/idea/running-applications.html.
    println("Program arguments: ${args.joinToString()}")
}