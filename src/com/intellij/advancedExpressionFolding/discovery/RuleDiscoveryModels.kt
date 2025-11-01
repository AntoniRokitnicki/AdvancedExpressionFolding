package com.intellij.advancedExpressionFolding.discovery

import com.intellij.openapi.util.TextRange
import java.time.Instant

internal enum class DatasetSplit {
    TRAIN,
    TEST
}

internal data class CandidateMetadata(
    val fileHash: String,
    val psiKind: String,
    val lineRange: IntRange,
    val snippet: String,
    val fileExtension: String?,
    val indentLevel: Int,
    val textRange: TextRange
)

internal data class CandidateOccurrence(
    val id: String,
    val language: String,
    val foldType: String,
    val features: DoubleArray,
    val timestamp: Long,
    val metadata: CandidateMetadata,
    var split: DatasetSplit = DatasetSplit.TRAIN
)

internal data class StandardizedOccurrence(
    val base: CandidateOccurrence,
    val standardized: DoubleArray
)

internal data class RuleMetrics(
    val support: Int,
    val precision: Double,
    val recall: Double,
    val noiseRate: Double
)

internal data class RuleExample(
    val fileHash: String,
    val range: String,
    val code: String
)

internal data class RuleCandidate(
    val id: String,
    val language: String,
    val foldType: String,
    val predicateDescription: String,
    val psiChecks: List<String>,
    val predicateSnippet: String,
    val metrics: RuleMetrics,
    val examples: List<RuleExample>
)

internal data class PipelineStats(
    val totalOccurrences: Int,
    val languagesProcessed: Int,
    val familyCount: Int,
    val overallNoiseRate: Double
)

internal data class RuleDiscoveryReport(
    val generatedAt: Instant,
    val rules: List<RuleCandidate>,
    val stats: PipelineStats
)

internal enum class ComparisonOperator {
    GREATER_OR_EQUAL,
    LESS_OR_EQUAL
}

internal data class PredicateClause(
    val featureIndex: Int,
    val operator: ComparisonOperator,
    val threshold: Double,
    val originalThreshold: Double
)

internal data class RulePredicate(
    val clauses: List<PredicateClause>
) {
    fun matches(features: DoubleArray): Boolean = clauses.all { clause ->
        val value = features[clause.featureIndex]
        when (clause.operator) {
            ComparisonOperator.GREATER_OR_EQUAL -> value >= clause.threshold
            ComparisonOperator.LESS_OR_EQUAL -> value <= clause.threshold
        }
    }
}
