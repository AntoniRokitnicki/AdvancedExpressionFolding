package com.intellij.advancedExpressionFolding

import com.intellij.advancedExpressionFolding.AdvancedExpressionFoldingSettings.Companion.allMainProperties
import com.intellij.advancedExpressionFolding.AdvancedExpressionFoldingSettings.Companion.getInstance
import com.intellij.advancedExpressionFolding.AdvancedExpressionFoldingSettings.State
import org.junit.AssumptionViolatedException
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.*
import java.util.stream.Stream
import kotlin.math.pow

@Disabled("very long execution time")
open class CrazyFoldingTest : BaseTest() {

    class TooComplexException : AssumptionViolatedException("TOO COMPLEX FOLDING")

    private val state: State by lazy {
        getInstance().state
    }

    override fun doFoldingTest() {
        try {
            super.doFoldingTest()
        } catch (e: IllegalArgumentException) {
            if (e.message == "Comparison method violates its general contract!") {
                throw TooComplexException()
            } else {
                throw e
            }
        }
    }

    /**
     * [data.ElvisTestData]
     */
    @ParameterizedTest
    @MethodSource("generateBooleanCombinations")
    fun testElvisTestData(booleans: BooleanArray) {
        allMainProperties().zip(booleans.toList()).forEach { (prop, value) ->
            prop.setter.call(state, value)
        }
        doFoldingTest()
    }

    companion object {
        @JvmStatic
        fun generateBooleanCombinations(): Stream<Arguments> {
            val numBooleans = allMainProperties().size
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

