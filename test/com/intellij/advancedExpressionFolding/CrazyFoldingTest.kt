package com.intellij.advancedExpressionFolding

import com.intellij.advancedExpressionFolding.AdvancedExpressionFoldingSettings.Companion.allProperties
import com.intellij.advancedExpressionFolding.AdvancedExpressionFoldingSettings.Companion.getInstance
import com.intellij.advancedExpressionFolding.AdvancedExpressionFoldingSettings.State
import org.junit.AssumptionViolatedException
import org.junit.jupiter.api.condition.EnabledIfEnvironmentVariable
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.*
import java.util.stream.Stream
import kotlin.math.pow

@EnabledIfEnvironmentVariable(named = "dev-mode", matches = "1")
class CrazyFoldingTest : BaseTest() {

    class TooComplexException : AssumptionViolatedException("TOO COMPLEX FOLDING")

    private val state: State by lazy {
        getInstance().state
    }

    private var counter = 0

    override fun doFoldingTest() {
        counter += 1
        try {
            super.doFoldingTest()
        } catch (e: com.intellij.rt.execution.junit.FileComparisonFailure) {
            println("counter = $counter")
        } catch (e: IllegalArgumentException) {
            if (e.message == "Comparison method violates its general contract!") {
                throw TooComplexException()
            } else {
                throw e
            }
        } finally {
            GitUtils.commitAllChanges()
        }
    }

    /**
     * [data.ElvisTestData]
     */
    @ParameterizedTest
    @MethodSource("permutations")
    fun testElvisTestData(booleans: BooleanArray) {
        allProperties().zip(booleans.toList()).forEach { (prop, value) ->
            prop.setter.call(state, value)
        }

        doFoldingTest()
    }

    companion object {
        @JvmStatic
        fun permutations(): Stream<Arguments> {
            val numBooleans = allProperties().size
            return Stream.iterate(BooleanArray(numBooleans)) { prev ->
                val next = prev.clone()
                for (i in 0 until numBooleans) {
                    next[i] = !next[i]
                    if (next[i]) {
                        break
                    }
                }
                next
            }.limit(2.0.pow(numBooleans.toDouble()).toLong())
                .map {
                    Arguments.of(it)
                }
        }
    }

}

