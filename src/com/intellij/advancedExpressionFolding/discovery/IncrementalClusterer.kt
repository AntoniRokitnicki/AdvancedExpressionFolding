package com.intellij.advancedExpressionFolding.discovery

import org.apache.commons.math3.ml.clustering.Cluster
import org.apache.commons.math3.ml.clustering.Clusterable
import org.apache.commons.math3.ml.clustering.DBSCANClusterer
import java.util.concurrent.CopyOnWriteArrayList

class IncrementalClusterer(
    private val eps: Double = 0.3,
    private val minPoints: Int = 5,
    private val batchSize: Int = 1_000
) {

    private val allVectors = CopyOnWriteArrayList<PsiFeatureVector>()
    private val pendingVectors = CopyOnWriteArrayList<PsiFeatureVector>()
    private val clusterer = DBSCANClusterer<PsiClusterPoint>(eps, minPoints, CosineDistance())

    fun addVectors(vectors: Collection<PsiFeatureVector>): List<ClusterCandidate> {
        if (vectors.isEmpty()) {
            return emptyList()
        }
        pendingVectors.addAll(vectors)
        allVectors.addAll(vectors)
        return if (pendingVectors.size >= batchSize) {
            val snapshot = allVectors.toList()
            pendingVectors.clear()
            runClustering(snapshot)
        } else {
            emptyList()
        }
    }

    fun forceCluster(): List<ClusterCandidate> {
        val snapshot = allVectors.toList()
        if (snapshot.isEmpty()) {
            return emptyList()
        }
        pendingVectors.clear()
        return runClustering(snapshot)
    }

    private fun runClustering(vectors: List<PsiFeatureVector>): List<ClusterCandidate> {
        if (vectors.isEmpty()) {
            return emptyList()
        }
        val clusterables = vectors.map(::PsiClusterPoint)
        val clusters: List<Cluster<PsiClusterPoint>> = clusterer.cluster(clusterables)
        return clusters
            .filter { it.points.size >= minPoints }
            .mapNotNull(::toCandidate)
    }

    private fun toCandidate(cluster: Cluster<PsiClusterPoint>): ClusterCandidate? {
        val vectors = cluster.points.map(PsiClusterPoint::featureVector)
        if (vectors.isEmpty()) {
            return null
        }
        val centroid = VectorMath.average(vectors.map(PsiFeatureVector::vector))
        val distances = vectors.map { vector -> VectorMath.cosineDistance(vector.vector, centroid) }
        val maxRadius = distances.maxOrNull() ?: return null
        val density = if (maxRadius == 0.0) vectors.size.toDouble() else vectors.size / maxRadius
        val similarities = distances.map { 1.0 - it }
        val averageSimilarity = similarities.average()
        return ClusterCandidate(vectors, centroid, density, averageSimilarity)
    }

    private class PsiClusterPoint(val featureVector: PsiFeatureVector) : Clusterable {
        override fun getPoint(): DoubleArray = featureVector.vector
    }
}

data class ClusterCandidate(
    val vectors: List<PsiFeatureVector>,
    val centroid: DoubleArray,
    val density: Double,
    val averageSimilarity: Double
) {
    val size: Int
        get() = vectors.size

    fun confidenceScore(): Double {
        val normalizedDensity = (density / (density + 10.0)).coerceIn(0.0, 1.0)
        val normalizedSimilarity = averageSimilarity.coerceIn(0.0, 1.0)
        return ((normalizedDensity + normalizedSimilarity) / 2.0)
    }
}
