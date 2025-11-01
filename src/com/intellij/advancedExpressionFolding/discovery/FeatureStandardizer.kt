package com.intellij.advancedExpressionFolding.discovery

import org.jetbrains.kotlinx.multik.api.d1array
import org.jetbrains.kotlinx.multik.api.mk
import org.jetbrains.kotlinx.multik.ndarray.data.get
import kotlin.math.sqrt

internal data class StandardizationResult(
    val occurrences: List<StandardizedOccurrence>,
    val means: DoubleArray,
    val std: DoubleArray
)

internal class FeatureStandardizer {

    fun standardize(occurrences: List<CandidateOccurrence>): StandardizationResult {
        if (occurrences.isEmpty()) {
            val zeros = DoubleArray(FeatureDictionary.FEATURE_COUNT)
            val ones = DoubleArray(FeatureDictionary.FEATURE_COUNT) { 1.0 }
            return StandardizationResult(emptyList(), zeros, ones)
        }

        val featureCount = FeatureDictionary.FEATURE_COUNT
        val rows = occurrences.size
        val means = DoubleArray(featureCount)
        val std = DoubleArray(featureCount)

        for (col in 0 until featureCount) {
            var sum = 0.0
            occurrences.forEach { sum += it.features[col] }
            val mean = sum / rows.toDouble()
            means[col] = mean

            var sumSquares = 0.0
            occurrences.forEach { occurrence ->
                val diff = occurrence.features[col] - mean
                sumSquares += diff * diff
            }
            val variance = sumSquares / rows.toDouble()
            val stdValue = sqrt(variance)
            std[col] = if (stdValue == 0.0 || stdValue.isNaN()) 1.0 else stdValue
        }

        val standardized = occurrences.map { occurrence ->
            val normalizedVector = mk.d1array(featureCount) { featureIndex ->
                val centered = (occurrence.features[featureIndex] - means[featureIndex]) / std[featureIndex]
                centered.coerceIn(-4.0, 4.0)
            }
            val normalizedArray = DoubleArray(featureCount) { index -> normalizedVector[index] }
            StandardizedOccurrence(occurrence, normalizedArray)
        }

        return StandardizationResult(standardized, means, std)
    }
}
