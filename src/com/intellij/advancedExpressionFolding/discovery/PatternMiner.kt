package com.intellij.advancedExpressionFolding.discovery

import java.util.Locale
import java.util.UUID

private const val MIN_CLUSTER_SIZE = 10
private const val MIN_AVERAGE_SIMILARITY = 0.7

class PatternMiner {

    fun mine(cluster: ClusterCandidate): DiscoveredFoldingRule? {
        if (cluster.size < MIN_CLUSTER_SIZE) {
            return null
        }
        if (cluster.averageSimilarity < MIN_AVERAGE_SIMILARITY) {
            return null
        }
        val metadataList = cluster.vectors.map(PsiFeatureVector::metadata)
        val commonNodeTypes = metadataList
            .map { it.nodeTypes.toSet() }
            .reduceOrNull { acc, set -> acc.intersect(set) }
            ?.takeIf { it.isNotEmpty() }
            ?.joinToString(separator = " -> ")
            ?: "psi-subtree"

        val placeholder = inferPlaceholder(metadataList)
        val example = metadataList.maxByOrNull { it.sampleText.length }?.sampleText ?: ""
        val textHashes = metadataList.map(PsiVectorMetadata::textHash).distinct()
        val centroid = cluster.centroid.toList()
        val confidence = cluster.confidenceScore()

        return DiscoveredFoldingRule(
            id = UUID.randomUUID().toString(),
            pattern = commonNodeTypes,
            placeholderText = placeholder,
            confidence = confidence,
            exampleCode = example,
            count = cluster.size,
            centroid = centroid,
            silhouette = cluster.averageSimilarity,
            version = 1,
            textHashes = textHashes,
            density = cluster.density
        )
    }

    private fun inferPlaceholder(metadataList: List<PsiVectorMetadata>): String {
        val tokens = metadataList.flatMap(PsiVectorMetadata::methodNameTokens).map { it.lowercase(Locale.US) }
        if (tokens.isEmpty()) {
            return "…"
        }
        return when {
            tokens.any { it.contains("log") } -> "logger…"
            tokens.any { it.contains("error") || it.contains("warn") } -> "logger…"
            tokens.any { it.contains("builder") || it.contains("build") } -> "builder…"
            tokens.any { it.contains("map") || it.contains("filter") || it.contains("collect") } -> "stream…"
            tokens.any { it.contains("null") || it.contains("safe") } -> "null-safety…"
            tokens.any { it.contains("guard") } -> "guard…"
            else -> tokens.take(2).joinToString(separator = " ") { it }
        }
    }
}
