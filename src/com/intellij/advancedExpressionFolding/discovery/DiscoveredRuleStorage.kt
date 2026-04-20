package com.intellij.advancedExpressionFolding.discovery

import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import com.intellij.openapi.application.PathManager
import java.io.IOException
import java.nio.file.Files
import java.nio.file.Path
import java.util.concurrent.locks.ReentrantReadWriteLock
import kotlin.io.path.exists
import kotlin.io.path.reader
import kotlin.io.path.writer
import kotlin.concurrent.read
import kotlin.concurrent.write

class DiscoveredRuleStorage {

    private val lock = ReentrantReadWriteLock()
    private val mapper = jacksonObjectMapper().enable(SerializationFeature.INDENT_OUTPUT)
    private val storagePath: Path = PathManager.getConfigDir()
        .resolve("advanced-expression-folding")
        .resolve("discovered-folding-rules.json")

    private var state: DiscoveredRuleFile = load()

    fun getRules(): List<DiscoveredFoldingRule> = lock.read { state.rules }

    fun upsert(rule: DiscoveredFoldingRule): DiscoveredFoldingRule = lock.write {
        val existingIndex = state.rules.indexOfFirst { it.pattern == rule.pattern }
        val updatedRules = state.rules.toMutableList()
        val result = if (existingIndex >= 0) {
            val existing = updatedRules[existingIndex]
            val merged = existing.merge(rule)
            updatedRules[existingIndex] = merged
            merged
        } else {
            updatedRules += rule
            rule
        }
        state = state.copy(rules = updatedRules)
        persist()
        result
    }

    fun replaceAll(rules: List<DiscoveredFoldingRule>) = lock.write {
        state = state.copy(rules = rules)
        persist()
    }

    private fun load(): DiscoveredRuleFile = lock.write {
        try {
            if (!storagePath.parent.exists()) {
                Files.createDirectories(storagePath.parent)
            }
            if (!storagePath.exists()) {
                state = DiscoveredRuleFile()
                persist()
                return@write state
            }
            storagePath.reader().use { reader ->
                state = mapper.readValue(reader)
            }
        } catch (ignored: IOException) {
            state = DiscoveredRuleFile()
        }
        state
    }

    private fun persist() {
        if (!storagePath.parent.exists()) {
            Files.createDirectories(storagePath.parent)
        }
        storagePath.writer().use { writer ->
            mapper.writeValue(writer, state)
        }
    }

    private fun DiscoveredFoldingRule.merge(other: DiscoveredFoldingRule): DiscoveredFoldingRule {
        val mergedCount = count + other.count
        val mergedConfidence = (confidence + other.confidence) / 2.0
        val mergedHashes = (textHashes + other.textHashes).distinct()
        val mergedCentroid = VectorMath.average(listOf(centroidArray(), other.centroidArray())).toList()
        val mergedDensity = (density + other.density) / 2.0
        val mergedSilhouette = (silhouette + other.silhouette) / 2.0
        return copy(
            count = mergedCount,
            confidence = mergedConfidence,
            textHashes = mergedHashes,
            centroid = mergedCentroid,
            density = mergedDensity,
            silhouette = mergedSilhouette,
            drift = if (mergedHashes.size > textHashes.size) drift + 1 else drift
        )
    }
}

data class DiscoveredRuleFile(
    val version: Int = 1,
    val rules: List<DiscoveredFoldingRule> = emptyList()
)
