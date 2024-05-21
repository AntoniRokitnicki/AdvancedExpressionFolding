package com.intellij.advancedExpressionFolding

import com.intellij.advancedExpressionFolding.AdvancedExpressionFoldingSettings.Companion.allMainProperties
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream
import kotlin.math.pow

class BooleanCombinationsTest {

    @ParameterizedTest
    @MethodSource("generateBooleanCombinations")
    fun testAllBooleanCombinations(booleans: BooleanArray) {
        println(booleans)
    }

    companion object {
        @JvmStatic
        fun generateBooleanCombinations(): Stream<Arguments> {
            val numBooleans = allMainProperties().size
            println("numBooleans = $numBooleans")
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
