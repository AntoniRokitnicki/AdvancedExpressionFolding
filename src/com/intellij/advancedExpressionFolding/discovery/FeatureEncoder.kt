package com.intellij.advancedExpressionFolding.discovery

import kotlin.math.abs

internal class FeatureEncoder(private val dimension: Int = 64) {

    fun encode(categorical: Map<String, Int>, numeric: Map<String, Double>): DoubleArray {
        val buffer = DoubleArray(dimension)
        categorical.forEach { (key, count) ->
            accumulate(buffer, "cat:$key", count.toDouble())
        }
        numeric.forEach { (key, value) ->
            accumulate(buffer, "num:$key", value)
        }
        return VectorMath.normalize(buffer)
    }

    fun encodeTokens(tokens: Collection<String>): DoubleArray {
        val buffer = DoubleArray(dimension)
        tokens.forEach { token ->
            accumulate(buffer, "tok:$token", 1.0)
        }
        return VectorMath.normalize(buffer)
    }

    private fun accumulate(buffer: DoubleArray, key: String, value: Double) {
        if (buffer.isEmpty()) {
            return
        }
        val index = abs(key.hashCode()) % buffer.size
        buffer[index] += value
    }
}
