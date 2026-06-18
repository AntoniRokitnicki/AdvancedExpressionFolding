package litmuskt.sample

import org.jetbrains.litmuskt.JvmThreadRunner
import org.jetbrains.litmuskt.barriers.JvmSpinBarrier
import org.jetbrains.litmuskt.generateTable

fun main() {
    val runner = JvmThreadRunner()
    val result = runMessagePassing(runner) { threadCount -> JvmSpinBarrier(threadCount) }
    println("Message Passing (JVM)")
    println(result.generateTable())
}
