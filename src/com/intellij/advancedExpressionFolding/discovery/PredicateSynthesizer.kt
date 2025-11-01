package com.intellij.advancedExpressionFolding.discovery

import kotlin.math.abs
import kotlin.math.ln
import kotlin.math.max
import kotlin.math.min
import kotlin.math.sqrt

internal data class PredicateSynthesis(
    val predicate: RulePredicate,
    val description: String,
    val psiChecks: List<String>,
    val snippet: String
)

internal class PredicateSynthesizer(
    private val means: DoubleArray,
    private val std: DoubleArray
) {

    fun synthesize(
        clusterIndices: List<Int>,
        train: List<StandardizedOccurrence>,
        negatives: List<StandardizedOccurrence>
    ): PredicateSynthesis? {
        if (clusterIndices.isEmpty()) {
            return null
        }

        val allowedFeatures = FeatureDictionary.allowedPredicateIndices()
        val membership = BooleanArray(train.size)
        clusterIndices.forEach { membership[it] = true }

        val mutualInformation = allowedFeatures.associateWith { index ->
            computeMutualInformation(index, train, membership)
        }

        val ranked = mutualInformation.entries
            .sortedByDescending { it.value }
            .map { it.key }

        val predicateClauses = mutableListOf<PredicateClause>()

        for (featureIndex in ranked.take(MAX_FEATURES)) {
            val clause = buildClause(featureIndex, predicateClauses, clusterIndices, train, negatives) ?: continue
            predicateClauses += clause
            val coverage = coverage(clusterIndices, predicateClauses, train)
            val falsePositiveRate = falsePositiveRate(predicateClauses, negatives)
            if (coverage >= REQUIRED_COVERAGE && falsePositiveRate <= MAX_FALSE_POSITIVE) {
                break
            }
        }

        if (predicateClauses.isEmpty()) {
            return null
        }

        val coverage = coverage(clusterIndices, predicateClauses, train)
        val falsePositiveRate = falsePositiveRate(predicateClauses, negatives)
        if (coverage < REQUIRED_COVERAGE || falsePositiveRate > MAX_FALSE_POSITIVE) {
            return null
        }

        val predicate = RulePredicate(predicateClauses)
        val descriptors = predicateClauses.map { FeatureDictionary.descriptor(it.featureIndex) }
        val description = descriptors.joinToString(", ") { it.description }
        val psiChecks = predicateClauses.map { clause ->
            val descriptor = FeatureDictionary.descriptor(clause.featureIndex)
            descriptor.psiCheck(clause)
        }
        val snippet = buildSnippet(predicateClauses)

        return PredicateSynthesis(predicate, description, psiChecks, snippet)
    }

    private fun buildSnippet(clauses: List<PredicateClause>): String {
        val body = clauses.joinToString(separator = " &&\n        ") { clause ->
            val descriptor = FeatureDictionary.descriptor(clause.featureIndex)
            descriptor.predicateLine(clause)
        }
        return "fun matches(element: PsiElement): Boolean =\n        $body"
    }

    private fun buildClause(
        featureIndex: Int,
        existing: List<PredicateClause>,
        clusterIndices: List<Int>,
        train: List<StandardizedOccurrence>,
        negatives: List<StandardizedOccurrence>
    ): PredicateClause? {
        val clusterValues = clusterIndices.map { train[it].standardized[featureIndex] }.sorted()
        if (clusterValues.isEmpty()) {
            return null
        }
        val lower = percentile(clusterValues, 0.1)
        val upper = percentile(clusterValues, 0.9)
        val candidates = listOf(
            ComparisonCandidate(ComparisonOperator.GREATER_OR_EQUAL, lower),
            ComparisonCandidate(ComparisonOperator.LESS_OR_EQUAL, upper)
        )

        var bestClause: PredicateClause? = null
        var bestScore = Double.POSITIVE_INFINITY

        for (candidate in candidates) {
            val clause = predicateClause(featureIndex, candidate)
            val combined = existing + clause
            val coverage = coverage(clusterIndices, combined, train)
            val falsePositive = falsePositiveRate(combined, negatives)
            if (coverage < REQUIRED_COVERAGE || falsePositive > MAX_FALSE_POSITIVE) {
                continue
            }
            val score = falsePositive + (1.0 - coverage)
            if (score < bestScore) {
                bestScore = score
                bestClause = clause
            }
        }

        return bestClause
    }

    private fun predicateClause(featureIndex: Int, candidate: ComparisonCandidate): PredicateClause {
        val originalThreshold = candidate.threshold * std[featureIndex] + means[featureIndex]
        return PredicateClause(featureIndex, candidate.operator, candidate.threshold, originalThreshold)
    }

    private fun coverage(
        clusterIndices: List<Int>,
        clauses: List<PredicateClause>,
        data: List<StandardizedOccurrence>
    ): Double {
        if (clusterIndices.isEmpty()) {
            return 0.0
        }
        val predicate = RulePredicate(clauses)
        val matches = clusterIndices.count { index -> predicate.matches(data[index].standardized) }
        return matches.toDouble() / clusterIndices.size.toDouble()
    }

    private fun falsePositiveRate(clauses: List<PredicateClause>, negatives: List<StandardizedOccurrence>): Double {
        if (negatives.isEmpty()) {
            return 0.0
        }
        val predicate = RulePredicate(clauses)
        val matches = negatives.count { occurrence -> predicate.matches(occurrence.standardized) }
        return matches.toDouble() / negatives.size.toDouble()
    }

    private fun computeMutualInformation(
        featureIndex: Int,
        data: List<StandardizedOccurrence>,
        membership: BooleanArray
    ): Double {
        val values = DoubleArray(data.size) { idx -> data[idx].standardized[featureIndex] }
        val minValue = values.minOrNull() ?: return 0.0
        val maxValue = values.maxOrNull() ?: return 0.0
        if (abs(maxValue - minValue) < 1e-6) {
            return 0.0
        }
        val binCount = 6
        val step = (maxValue - minValue) / binCount
        val counts = Array(binCount) { DoubleArray(2) }
        for (i in values.indices) {
            var bin = ((values[i] - minValue) / step).toInt()
            if (bin >= binCount) {
                bin = binCount - 1
            }
            val label = if (membership[i]) 1 else 0
            counts[bin][label] += 1.0
        }
        val total = values.size.toDouble()
        val labelCounts = DoubleArray(2)
        counts.forEach { pair ->
            labelCounts[0] += pair[0]
            labelCounts[1] += pair[1]
        }
        var result = 0.0
        for (bin in 0 until binCount) {
            val binTotal = counts[bin][0] + counts[bin][1]
            if (binTotal == 0.0) continue
            for (label in 0..1) {
                val joint = counts[bin][label]
                if (joint == 0.0) continue
                val jointProb = joint / total
                val binProb = binTotal / total
                val labelProb = labelCounts[label] / total
                result += jointProb * ln(jointProb / (binProb * labelProb))
            }
        }
        return result
    }

    private fun percentile(values: List<Double>, percentile: Double): Double {
        if (values.isEmpty()) {
            return 0.0
        }
        if (values.size == 1) {
            return values[0]
        }
        val clamped = percentile.coerceIn(0.0, 1.0)
        val position = clamped * (values.size - 1)
        val index = position.toInt()
        val fraction = position - index
        if (index + 1 >= values.size) {
            return values.last()
        }
        return values[index] * (1 - fraction) + values[index + 1] * fraction
    }

    private data class ComparisonCandidate(
        val operator: ComparisonOperator,
        val threshold: Double
    )

    companion object {
        private const val REQUIRED_COVERAGE = 0.9
        private const val MAX_FALSE_POSITIVE = 0.1
        private const val MAX_FEATURES = 3
    }
}
