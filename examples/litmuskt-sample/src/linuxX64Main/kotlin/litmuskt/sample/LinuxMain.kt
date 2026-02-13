package litmuskt.sample

import org.jetbrains.litmuskt.PthreadRunner
import org.jetbrains.litmuskt.barriers.KNativeSpinBarrier
import org.jetbrains.litmuskt.generateTable

fun main() {
    val runner = PthreadRunner()
    val result = runMessagePassing(runner) { threadCount -> KNativeSpinBarrier(threadCount) }
    println("Message Passing (Native)")
    println(result.generateTable())
}
