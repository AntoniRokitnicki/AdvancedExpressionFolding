package com.intellij.advancedExpressionFolding.discovery

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
data class DiscoveredFoldingRule(
    val id: String,
    val pattern: String,
    val placeholderText: String,
    val confidence: Double,
    val exampleCode: String,
    val count: Int,
    val centroid: List<Double>,
    val silhouette: Double,
    val version: Int,
    val textHashes: List<Int>,
    val density: Double,
    val enabled: Boolean = true,
    val drift: Int = 0,
    val promoted: Boolean = false
) {
    fun centroidArray(): DoubleArray = centroid.toDoubleArray()
}
