package com.intellij.advancedExpressionFolding.discovery

import org.apache.commons.math3.ml.clustering.DBSCANClusterer
import kotlin.math.sqrt

internal data class ClusterOutput(
    val rules: List<RuleCandidate>,
    val noiseContribution: Double
)

internal class RuleClusterer(
    private val language: String,
    private val family: String,
    private val occurrences: List<StandardizedOccurrence>,
    private val means: DoubleArray,
    private val std: DoubleArray,
    private val epsCandidates: DoubleArray = doubleArrayOf(0.8, 1.0, 1.2, 1.5),
    private val minPtsCandidates: IntArray = intArrayOf(8, 12, 16)
) {

    fun cluster(): ClusterOutput {
        if (occurrences.isEmpty()) {
            return ClusterOutput(emptyList(), 0.0)
        }

        val train = occurrences.filter { it.base.split == DatasetSplit.TRAIN }
        val holdout = occurrences.filter { it.base.split == DatasetSplit.TEST }
        if (train.size < minPtsCandidates.minOrNull() ?: 5) {
            return ClusterOutput(emptyList(), 0.0)
        }

        val trainData = train.map { it.standardized }.toTypedArray()
        val selector = ParameterSelector(trainData, epsCandidates, minPtsCandidates)
        val parameters = selector.select() ?: return ClusterOutput(emptyList(), 0.0)

        val clusterer = DBSCANClusterer<IndexedPoint>(parameters.eps, parameters.minPts)
        val trainPoints = train.mapIndexed { index, occurrence -> IndexedPoint(index, occurrence.standardized) }
        val clusters = clusterer.cluster(trainPoints)
        if (clusters.isEmpty()) {
            val noiseContribution = occurrences.size.toDouble()
            return ClusterOutput(emptyList(), noiseContribution)
        }
        val labels = IntArray(trainPoints.size) { -1 }
        clusters.forEachIndexed { clusterId, cluster ->
            cluster.points.forEach { point ->
                labels[point.index] = clusterId
            }
        }
        val noiseRate = labels.count { it == -1 }.toDouble() / trainPoints.size.toDouble()
        val synthesizer = PredicateSynthesizer(means, std)
        val rules = mutableListOf<RuleCandidate>()
        var clusterIndex = 0

        clusters.forEachIndexed { label, cluster ->
            val indices = cluster.points.map { it.index }
            val negatives = train.filterIndexed { idx, _ -> labels[idx] != label }
            val synthesis = synthesizer.synthesize(indices, train, negatives) ?: return@forEachIndexed
            val centroid = centroid(indices, train)
            val metrics = evaluateHoldout(synthesis.predicate, centroid, parameters.eps, holdout)
            val support = indices.size
            if (support < 50 || metrics.precision < 0.8) {
                return@forEachIndexed
            }
            val examples = indices.take(MAX_EXAMPLES).mapNotNull { index ->
                val base = train[index].base
                val range = base.metadata.lineRange
                val snippet = base.metadata.snippet
                val formattedRange = "${range.first}-${range.last}"
                RuleExample(base.metadata.fileHash, formattedRange, snippet)
            }
            val ruleId = "${family}_${language}_${clusterIndex++}"
            val candidate = RuleCandidate(
                id = ruleId,
                language = language,
                foldType = family,
                predicateDescription = synthesis.description,
                psiChecks = synthesis.psiChecks,
                predicateSnippet = synthesis.snippet,
                metrics = metrics.copy(support = support, noiseRate = noiseRate),
                examples = examples
            )
            rules += candidate
        }

        return ClusterOutput(rules, noiseRate * occurrences.size)
    }

    private fun centroid(indices: List<Int>, data: List<StandardizedOccurrence>): DoubleArray {
        val centroid = DoubleArray(FeatureDictionary.FEATURE_COUNT)
        indices.forEach { index ->
            val vector = data[index].standardized
            for (i in vector.indices) {
                centroid[i] += vector[i]
            }
        }
        for (i in centroid.indices) {
            centroid[i] /= indices.size.toDouble()
        }
        return centroid
    }

    private fun evaluateHoldout(
        predicate: RulePredicate,
        centroid: DoubleArray,
        eps: Double,
        holdout: List<StandardizedOccurrence>
    ): RuleMetrics {
        var predicted = 0
        var truePositives = 0
        var actualPositives = 0
        for (occurrence in holdout) {
            val actual = distance(occurrence.standardized, centroid) <= eps
            if (actual) {
                actualPositives++
            }
            val match = predicate.matches(occurrence.standardized)
            if (match) {
                predicted++
            }
            if (match && actual) {
                truePositives++
            }
        }
        val precision = if (predicted == 0) 1.0 else truePositives.toDouble() / predicted.toDouble()
        val recall = if (actualPositives == 0) 0.0 else truePositives.toDouble() / actualPositives.toDouble()
        return RuleMetrics(0, precision, recall, 0.0)
    }

    private fun distance(a: DoubleArray, b: DoubleArray): Double {
        var sum = 0.0
        for (i in a.indices) {
            val diff = a[i] - b[i]
            sum += diff * diff
        }
        return sqrt(sum)
    }

    companion object {
        private const val MAX_EXAMPLES = 5
    }
}
