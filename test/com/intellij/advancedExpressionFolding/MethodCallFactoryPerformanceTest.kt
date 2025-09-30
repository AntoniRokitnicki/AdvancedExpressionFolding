package com.intellij.advancedExpressionFolding

import com.intellij.advancedExpressionFolding.processor.methodcall.MethodCallFactory
import com.intellij.advancedExpressionFolding.processor.methodcall.dynamic.DynamicMethodCall
import com.intellij.advancedExpressionFolding.processor.methodcall.dynamic.DynamicMethodCallData
import com.intellij.advancedExpressionFolding.processor.methodcall.dynamic.IDynamicDataProvider
import com.intellij.testFramework.PlatformTestUtil
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.condition.EnabledIfEnvironmentVariable

@EnabledIfEnvironmentVariable(named = "performance-mode", matches = "1")
class MethodCallFactoryPerformanceTest : BaseTest() {

    companion object {
        private const val WARMUP_ITERATIONS = 2
        private const val ATTEMPT_COUNT = 2
        private const val LARGE_DYNAMIC_DATA_SIZE = 1000
    }

    private inline fun captureSystemOut(action: () -> Unit): String {
        val originalOut = System.out
        val byteArrayOutputStream = java.io.ByteArrayOutputStream()
        System.setOut(java.io.PrintStream(byteArrayOutputStream))
        try {
            action()
        } finally {
            System.setOut(originalOut)
        }
        return byteArrayOutputStream.toString()
    }

    private fun benchmark(name: String, action: () -> Unit) {
        val output = captureSystemOut {
            PlatformTestUtil.newBenchmark(name) {
                action()
            }.warmupIterations(WARMUP_ITERATIONS).attempts(ATTEMPT_COUNT).start()
        }
        val executionTime = output.substringAfter("attempt.median.ms").trim().substringBefore("\n").toLong()
        println("executionTime = $executionTime")
        methodNameToExecutionTimeMap[name] = executionTime
    }

    @Test
    fun initializeWithLargeDynamicData() {
        val provider = LargeDynamicDataProvider(LARGE_DYNAMIC_DATA_SIZE)
        benchmark("initializeWithLargeDynamicData") {
            MethodCallFactory.initialize(provider)
        }
    }

    @Test
    fun refreshMethodCallMappingsWithLargeDynamicData() {
        val provider = LargeDynamicDataProvider(LARGE_DYNAMIC_DATA_SIZE)
        MethodCallFactory.initialize(provider)
        benchmark("refreshMethodCallMappingsWithLargeDynamicData") {
            MethodCallFactory.refreshMethodCallMappings()
        }
    }

    private class LargeDynamicDataProvider(private val size: Int) : IDynamicDataProvider {
        private var invocation = 0
        override fun parse(): List<DynamicMethodCall> {
            invocation++
            return (1..size).map { index ->
                val method = $$"m${invocation}_$index"
                DynamicMethodCall(DynamicMethodCallData(mapOf("method" to method, "newName" to $$"n$index")))
            }
        }
    }
}

