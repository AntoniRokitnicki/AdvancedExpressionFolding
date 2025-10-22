package com.intellij.advancedExpressionFolding.performance

import com.intellij.advancedExpressionFolding.FoldingTestCase
import com.intellij.advancedExpressionFolding.registerStandardFoldingTests
import com.intellij.platform.testFramework.core.FileComparisonFailedError
import com.intellij.testFramework.PlatformTestUtil
import io.kotest.core.spec.style.FunSpec
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.PrintStream

class PerformanceTest : FunSpec({
    val enabled = System.getenv("performance-mode") == "1"
    if (enabled) {
        registerStandardFoldingTests(::PerformanceFoldingTestCase)

        afterSpec {
            if (methodNameToExecutionTimeMap.isEmpty()) return@afterSpec
            val sum = methodNameToExecutionTimeMap.values.sum()
            println("sum = $sum")
            val results = buildString {
                methodNameToExecutionTimeMap.toSortedMap().forEach { (name, time) ->
                    append("        \"$name\" to $time\n")
                }
            }
            val content = """
package com.intellij.advancedExpressionFolding.performance

object PerformanceResult {

    @Suppress("unused")
    val result = mapOf(
$results    )
}
""".trimIndent()
            File("test/com/intellij/advancedExpressionFolding/performance/performanceResult.kt").writeText(content)
        }
    }
})

private class PerformanceFoldingTestCase : FoldingTestCase() {

    private inline fun captureSystemOut(action: () -> Unit): String {
        val originalOut = System.out
        val byteArrayOutputStream = ByteArrayOutputStream()
        System.setOut(PrintStream(byteArrayOutputStream))
        try {
            action()
        } finally {
            System.setOut(originalOut)
        }
        return byteArrayOutputStream.toString()
    }

    override fun testWrapper(fileName: String, testName: String, action: () -> Unit) {
        val output = captureSystemOut {
            PlatformTestUtil.newBenchmark(testName) {
                try {
                    action.invoke()
                } catch (_: FileComparisonFailedError) {
                    // ignore
                }
            }.warmupIterations(100).attempts(1_000).start()
        }
        val executionTime = output.substringAfter("attempt.median.ms").trim().substringBefore("\n").toLong()
        println("executionTime = $executionTime")
        methodNameToExecutionTimeMap[testName] = executionTime
    }
}

val methodNameToExecutionTimeMap = mutableMapOf<String, Long>()
