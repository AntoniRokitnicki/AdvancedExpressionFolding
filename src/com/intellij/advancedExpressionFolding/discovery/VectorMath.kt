package com.intellij.advancedExpressionFolding.discovery

import kotlin.math.abs
import kotlin.math.max
import kotlin.math.sqrt

internal object VectorMath {
    fun normalize(source: DoubleArray): DoubleArray {
        val length = sqrt(source.sumOf { it * it })
        if (length == 0.0) {
            return source.copyOf()
        }
        return DoubleArray(source.size) { index -> source[index] / length }
    }

    fun cosineSimilarity(a: DoubleArray, b: DoubleArray): Double {
        val size = minOf(a.size, b.size)
        var dot = 0.0
        var lengthA = 0.0
        var lengthB = 0.0
        for (index in 0 until size) {
            val av = a[index]
            val bv = b[index]
            dot += av * bv
            lengthA += av * av
            lengthB += bv * bv
        }
        if (lengthA == 0.0 || lengthB == 0.0) {
            return 0.0
        }
        val similarity = dot / (sqrt(lengthA) * sqrt(lengthB))
        return similarity.coerceIn(-1.0, 1.0)
    }

    fun average(vectors: List<DoubleArray>): DoubleArray {
        if (vectors.isEmpty()) {
            return DoubleArray(0)
        }
        val size = vectors.first().size
        val accumulator = DoubleArray(size)
        vectors.forEach { vector ->
            for (index in 0 until size) {
                accumulator[index] += vector[index]
            }
        }
        return normalize(accumulator)
    }

    fun cosineDistance(a: DoubleArray, b: DoubleArray): Double {
        return 1.0 - cosineSimilarity(a, b)
    }

    fun magnitude(vector: DoubleArray): Double {
        var sum = 0.0
        vector.forEach { value ->
            sum += value * value
        }
        return sqrt(max(sum, 0.0))
    }
}
