package com.intellij.advancedExpressionFolding.discovery

import java.util.Locale
import kotlin.math.roundToInt

internal data class FeatureDescriptor(
    val index: Int,
    val key: String,
    val description: String,
    val allowPredicate: Boolean = true,
    val psiCheck: (PredicateClause) -> String,
    val predicateLine: (PredicateClause) -> String
)

internal object FeatureDictionary {
    const val FEATURE_COUNT: Int = 18

    private val descriptors: List<FeatureDescriptor> = listOf(
        FeatureDescriptor(
            index = 0,
            key = "languageId",
            description = "Language hash",
            allowPredicate = false,
            psiCheck = ::unsupported,
            predicateLine = ::unsupportedLine
        ),
        FeatureDescriptor(
            index = 1,
            key = "extensionId",
            description = "File extension hash",
            allowPredicate = false,
            psiCheck = ::unsupported,
            predicateLine = ::unsupportedLine
        ),
        FeatureDescriptor(
            index = 2,
            key = "psiKindId",
            description = "PSI node kind hash",
            allowPredicate = false,
            psiCheck = ::unsupported,
            predicateLine = ::unsupportedLine
        ),
        FeatureDescriptor(
            index = 3,
            key = "foldTypeId",
            description = "Candidate family hash",
            allowPredicate = false,
            psiCheck = ::unsupported,
            predicateLine = ::unsupportedLine
        ),
        FeatureDescriptor(
            index = 4,
            key = "depth",
            description = "Depth in PSI tree",
            psiCheck = { clause -> comparison("depth", clause) },
            predicateLine = { clause -> "depth(element) ${symbol(clause)} ${formatInt(clause.originalThreshold)}" }
        ),
        FeatureDescriptor(
            index = 5,
            key = "tokenSpan",
            description = "Token span length",
            psiCheck = { clause -> comparison("token span", clause) },
            predicateLine = { clause -> "tokenSpan(element) ${symbol(clause)} ${formatInt(clause.originalThreshold)}" }
        ),
        FeatureDescriptor(
            index = 6,
            key = "lineSpan",
            description = "Line span length",
            psiCheck = { clause -> comparison("line span", clause) },
            predicateLine = { clause -> "lineSpan(element) ${symbol(clause)} ${formatInt(clause.originalThreshold)}" }
        ),
        FeatureDescriptor(
            index = 7,
            key = "siblingCount",
            description = "Sibling count",
            psiCheck = { clause -> comparison("sibling count", clause) },
            predicateLine = { clause -> "siblingCount(element) ${symbol(clause)} ${formatInt(clause.originalThreshold)}" }
        ),
        FeatureDescriptor(
            index = 8,
            key = "childCount",
            description = "Child count",
            psiCheck = { clause -> comparison("child count", clause) },
            predicateLine = { clause -> "childCount(element) ${symbol(clause)} ${formatInt(clause.originalThreshold)}" }
        ),
        FeatureDescriptor(
            index = 9,
            key = "chainLength",
            description = "Chain length",
            psiCheck = { clause -> comparison("chain length", clause) },
            predicateLine = { clause -> "chainLength(element) ${symbol(clause)} ${formatInt(clause.originalThreshold)}" }
        ),
        FeatureDescriptor(
            index = 10,
            key = "loggerCall",
            description = "Contains logger call",
            psiCheck = { clause ->
                if (clause.operator == ComparisonOperator.GREATER_OR_EQUAL) {
                    "logger call present"
                } else {
                    "logger call absent"
                }
            },
            predicateLine = { clause ->
                val expected = clause.operator == ComparisonOperator.GREATER_OR_EQUAL
                "hasLoggerCall(element) == ${expected}"
            }
        ),
        FeatureDescriptor(
            index = 11,
            key = "nullCheck",
            description = "Contains null-check",
            psiCheck = { clause ->
                if (clause.operator == ComparisonOperator.GREATER_OR_EQUAL) {
                    "includes null check"
                } else {
                    "no null check"
                }
            },
            predicateLine = { clause ->
                val expected = clause.operator == ComparisonOperator.GREATER_OR_EQUAL
                "hasNullCheck(element) == ${expected}"
            }
        ),
        FeatureDescriptor(
            index = 12,
            key = "commentDensity",
            description = "Comment density",
            psiCheck = { clause -> comparison("comment density", clause, 2) },
            predicateLine = { clause -> "commentDensity(element) ${symbol(clause)} ${formatDouble(clause.originalThreshold, 2)}" }
        ),
        FeatureDescriptor(
            index = 13,
            key = "indentLevel",
            description = "Indent level",
            psiCheck = { clause -> comparison("indent level", clause) },
            predicateLine = { clause -> "indentLevel(element) ${symbol(clause)} ${formatInt(clause.originalThreshold)}" }
        ),
        FeatureDescriptor(
            index = 14,
            key = "nestingIf",
            description = "Ancestor if-count",
            psiCheck = { clause -> comparison("if nesting", clause) },
            predicateLine = { clause -> "nestingHistogram(element)[0] ${symbol(clause)} ${formatInt(clause.originalThreshold)}" }
        ),
        FeatureDescriptor(
            index = 15,
            key = "nestingLoop",
            description = "Ancestor loop-count",
            psiCheck = { clause -> comparison("loop nesting", clause) },
            predicateLine = { clause -> "nestingHistogram(element)[1] ${symbol(clause)} ${formatInt(clause.originalThreshold)}" }
        ),
        FeatureDescriptor(
            index = 16,
            key = "nestingTry",
            description = "Ancestor try-count",
            psiCheck = { clause -> comparison("try nesting", clause) },
            predicateLine = { clause -> "nestingHistogram(element)[2] ${symbol(clause)} ${formatInt(clause.originalThreshold)}" }
        ),
        FeatureDescriptor(
            index = 17,
            key = "nestingLambda",
            description = "Ancestor lambda-count",
            psiCheck = { clause -> comparison("lambda nesting", clause) },
            predicateLine = { clause -> "nestingHistogram(element)[3] ${symbol(clause)} ${formatInt(clause.originalThreshold)}" }
        )
    )

    fun descriptor(index: Int): FeatureDescriptor = descriptors[index]

    fun allowedPredicateIndices(): List<Int> = descriptors.filter { it.allowPredicate }.map { it.index }

    private fun unsupported(@Suppress("UNUSED_PARAMETER") clause: PredicateClause): String =
        "not supported"

    private fun unsupportedLine(@Suppress("UNUSED_PARAMETER") clause: PredicateClause): String =
        "// not supported"

    private fun symbol(clause: PredicateClause): String = when (clause.operator) {
        ComparisonOperator.GREATER_OR_EQUAL -> ">="
        ComparisonOperator.LESS_OR_EQUAL -> "<="
    }

    private fun comparison(label: String, clause: PredicateClause, decimals: Int = 0): String {
        val formatted = if (decimals == 0) {
            formatInt(clause.originalThreshold)
        } else {
            formatDouble(clause.originalThreshold, decimals)
        }
        val operator = when (clause.operator) {
            ComparisonOperator.GREATER_OR_EQUAL -> "≥"
            ComparisonOperator.LESS_OR_EQUAL -> "≤"
        }
        return "$label $operator $formatted"
    }

    private fun formatInt(value: Double): Int = value.roundToInt()

    private fun formatDouble(value: Double, decimals: Int): String =
        String.format(Locale.US, "%.${decimals}f", value)
}
