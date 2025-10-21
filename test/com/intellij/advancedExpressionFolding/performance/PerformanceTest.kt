package com.intellij.advancedExpressionFolding.performance

import com.intellij.advancedExpressionFolding.folding.base.FoldingTest
import com.intellij.platform.testFramework.core.FileComparisonFailedError
import com.intellij.testFramework.PlatformTestUtil
import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.MethodOrderer
import org.junit.jupiter.api.TestMethodOrder
import org.junit.jupiter.api.condition.EnabledIfEnvironmentVariable
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.PrintStream

@TestMethodOrder(MethodOrderer.DisplayName::class)
@EnabledIfEnvironmentVariable(named = "performance-mode", matches = "1")
class PerformanceTest : FoldingTest() {

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
        methodNameToExecutionTimeMap[testNameRule.methodName] = executionTime
    }

    companion object {
        @JvmStatic
        @AfterAll
        fun after() {
            val sum = methodNameToExecutionTimeMap.values.sum()
            println("sum = $sum")
            val results = buildString {
                methodNameToExecutionTimeMap.toSortedMap().forEach { (name, time) ->
                    append("        ::$name to $time")
                    append("\n")
                }
            }
            val content = """
package com.intellij.advancedExpressionFolding.performance

import com.intellij.advancedExpressionFolding.folding.base.FoldingTest
import org.junit.jupiter.api.condition.EnabledIfEnvironmentVariable
import kotlin.reflect.KFunction0

@EnabledIfEnvironmentVariable(named = "run-mode", matches = "never")
object PerformanceResult : FoldingTest() {

    @Suppress("unused")
    val result = performance { // sum=$sum
$results
    }

    class PerformanceDSL {
        infix fun (KFunction0<Unit>).to(@Suppress("UNUSED_PARAMETER") expectedValue: Int) {
            // ignore
        }
    }

    private fun performance(block: PerformanceDSL.() -> Unit) {
        val dsl = PerformanceDSL()
        dsl.block()
    }

}
""".trimIndent()
            File("test/com/intellij/advancedExpressionFolding/performance/performanceResult.kt").writeText(content)
        }
    }

}

val methodNameToExecutionTimeMap = mutableMapOf<String, Long>()
