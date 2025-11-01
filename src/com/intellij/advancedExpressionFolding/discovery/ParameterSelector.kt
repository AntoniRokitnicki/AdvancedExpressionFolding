package com.intellij.advancedExpressionFolding.discovery

import org.apache.commons.math3.ml.clustering.DBSCANClusterer
import org.apache.commons.math3.ml.clustering.Cluster
import kotlin.math.min
import kotlin.math.sqrt

internal data class DbscanParameters(
    val eps: Double,
    val minPts: Int,
    val noiseRate: Double,
    val coreRatio: Double
)

internal class ParameterSelector(
    private val data: Array<DoubleArray>,
    private val epsCandidates: DoubleArray,
    private val minPtsCandidates: IntArray
) {

    fun select(): DbscanParameters? {
        if (data.isEmpty()) {
            return null
        }
        val validationPoints = createValidationPoints()
        val globalMedian = medianDistance(validationPoints)
        var best: DbscanParameters? = null
        var bestScore = Double.POSITIVE_INFINITY

        for (eps in epsCandidates) {
            for (minPts in minPtsCandidates) {
                val clusterer = DBSCANClusterer<IndexedPoint>(eps, minPts)
                val clusters = clusterer.cluster(validationPoints)
                if (clusters.isEmpty()) {
                    continue
                }
                val labels = IntArray(validationPoints.size) { -1 }
                clusters.forEachIndexed { clusterId, cluster ->
                    cluster.points.forEach { point ->
                        labels[point.index] = clusterId
                    }
                }
                val noiseCount = labels.count { it == -1 }
                val noiseRate = noiseCount.toDouble() / validationPoints.size.toDouble()
                val coreRatio = 1.0 - noiseRate
                if (coreRatio < 0.6 || noiseRate > 0.4) {
                    continue
                }
                val intraMedian = intraClusterMedian(clusters)
                val ratio = if (globalMedian == 0.0) Double.POSITIVE_INFINITY else intraMedian / globalMedian
                if (ratio < bestScore) {
                    bestScore = ratio
                    best = DbscanParameters(eps, minPts, noiseRate, coreRatio)
                }
            }
        }

        return best
    }

    private fun createValidationPoints(): List<IndexedPoint> {
        val size = min(data.size, MAX_VALIDATION)
        return (0 until size).map { index -> IndexedPoint(index, data[index]) }
    }

    private fun medianDistance(points: List<IndexedPoint>): Double {
        val dimension = points.first().point.size
        val centroid = DoubleArray(dimension)
        points.forEach { point ->
            val vector = point.point
            for (i in vector.indices) {
                centroid[i] += vector[i]
            }
        }
        for (i in centroid.indices) {
            centroid[i] /= points.size.toDouble()
        }
        val distances = points.map { euclidean(it.point, centroid) }.sorted()
        return distances[distances.size / 2]
    }

    private fun intraClusterMedian(clusters: List<Cluster<IndexedPoint>>): Double {
        if (clusters.isEmpty()) {
            return Double.POSITIVE_INFINITY
        }
        val values = mutableListOf<Double>()
        clusters.forEach { cluster ->
            val clusterPoints = cluster.points
            if (clusterPoints.isEmpty()) {
                return@forEach
            }
            val centroid = DoubleArray(clusterPoints.first().point.size)
            clusterPoints.forEach { point ->
                val vector = point.point
                for (i in vector.indices) {
                    centroid[i] += vector[i]
                }
            }
            for (i in centroid.indices) {
                centroid[i] /= clusterPoints.size.toDouble()
            }
            clusterPoints.forEach { point ->
                values += euclidean(point.point, centroid)
            }
        }
        if (values.isEmpty()) {
            return Double.POSITIVE_INFINITY
        }
        val sorted = values.sorted()
        return sorted[sorted.size / 2]
    }

    private fun euclidean(a: DoubleArray, b: DoubleArray): Double {
        var sum = 0.0
        for (i in a.indices) {
            val diff = a[i] - b[i]
            sum += diff * diff
        }
        return sqrt(sum)
    }

    companion object {
        private const val MAX_VALIDATION = 800
    }
}
