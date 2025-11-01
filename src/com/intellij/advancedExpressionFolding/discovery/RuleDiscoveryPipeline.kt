package com.intellij.advancedExpressionFolding.discovery

import com.intellij.openapi.progress.ProgressIndicator
import com.intellij.openapi.progress.ProgressManager
import com.intellij.openapi.project.Project
import java.time.Instant

internal class RuleDiscoveryPipeline(private val project: Project) {

    fun execute(indicator: ProgressIndicator): RuleDiscoveryReport {
        indicator.isIndeterminate = false
        val scanner = RuleDiscoveryScanner()
        val occurrences = scanner.collect(project, indicator)
        if (occurrences.isEmpty()) {
            val emptyStats = PipelineStats(0, 0, 0, 0.0)
            return RuleDiscoveryReport(Instant.now(), emptyList(), emptyStats)
        }

        val rules = mutableListOf<RuleCandidate>()
        val byLanguage = occurrences.groupBy { it.language }
        var totalNoise = 0.0
        var familyCount = 0

        val standardizer = FeatureStandardizer()

        byLanguage.forEach { (language, languageOccurrences) ->
            ProgressManager.checkCanceled()
            indicator.text = "Analyzing $language"
            assignSplits(languageOccurrences)
            val standardized = standardizer.standardize(languageOccurrences)
            val byFamily = standardized.occurrences.groupBy { it.base.foldType }
            familyCount += byFamily.size
            byFamily.forEach { (family, group) ->
                ProgressManager.checkCanceled()
                val clusterer = RuleClusterer(language, family, group, standardized.means, standardized.std)
                val clusterOutput = clusterer.cluster()
                rules += clusterOutput.rules
                totalNoise += clusterOutput.noiseContribution
            }
        }

        val totalOccurrences = occurrences.size
        val overallNoiseRate = if (totalOccurrences == 0) 0.0 else totalNoise / totalOccurrences.toDouble()

        return RuleDiscoveryReport(
            generatedAt = Instant.now(),
            rules = rules.sortedByDescending { it.metrics.support },
            stats = PipelineStats(
                totalOccurrences = totalOccurrences,
                languagesProcessed = byLanguage.size,
                familyCount = familyCount,
                overallNoiseRate = overallNoiseRate
            )
        )
    }

    private fun assignSplits(languageOccurrences: List<CandidateOccurrence>) {
        val byFamily = languageOccurrences.groupBy { it.foldType }
        byFamily.values.forEach { familyOccurrences ->
            if (familyOccurrences.size < 5) {
                familyOccurrences.forEach { it.split = DatasetSplit.TRAIN }
                return@forEach
            }
            val sorted = familyOccurrences.sortedBy { it.timestamp }
            val thresholdIndex = (sorted.size * 0.8).toInt().coerceAtMost(sorted.lastIndex)
            val threshold = sorted[thresholdIndex].timestamp
            familyOccurrences.forEach { occurrence ->
                occurrence.split = if (occurrence.timestamp <= threshold) {
                    DatasetSplit.TRAIN
                } else {
                    DatasetSplit.TEST
                }
            }
        }
    }
}
