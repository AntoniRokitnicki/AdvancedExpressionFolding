package com.intellij.advancedExpressionFolding.settings

import kotlin.collections.LinkedHashMap

/**
 * Lightweight snapshot of all user facing Advanced Expression Folding toggles.
 * The profile intentionally contains only plain Kotlin types so it can be shared
 * between the IntelliJ plugin and native tooling.
 */
data class FoldingProfile(
    val booleanToggles: Map<String, Boolean>,
    val stringToggles: Map<String, String?>,
) {
    fun prettyPrint(): String = buildString {
        appendLine("Advanced Expression Folding profile")
        appendLine("-------------------------------")
        booleanToggles.entries.sortedBy { it.key }.forEach { (name, value) ->
            appendLine("$name = $value")
        }
        if (stringToggles.isNotEmpty()) {
            appendLine()
            appendLine("Strings")
            appendLine("-------")
            stringToggles.entries.sortedBy { it.key }.forEach { (name, value) ->
                appendLine("$name = ${value ?: "<unset>"}")
            }
        }
    }

    fun toJson(): String = buildString {
        append("{")
        append("\"booleans\":{")
        append(
            booleanToggles.entries.sortedBy { it.key }
                .joinToString(separator = ",") { (name, value) ->
                    "\"$name\":$value"
                }
        )
        append("},\"strings\":{")
        append(
            stringToggles.entries.sortedBy { it.key }
                .joinToString(separator = ",") { (name, value) ->
                    val rendered = value?.replace("\\", "\\\\")?.replace("\"", "\\\"")
                    "\"$name\":${rendered?.let { "\"$it\"" } ?: "null"}"
                }
        )
        append("}}")
    }
}

fun IState.toFoldingProfile(): FoldingProfile {
    val booleans = LinkedHashMap<String, Boolean>()
    booleans["arithmeticExpressions"] = arithmeticExpressions
    booleans["assertsCollapse"] = assertsCollapse
    booleans["castExpressionsCollapse"] = castExpressionsCollapse
    booleans["checkExpressionsCollapse"] = checkExpressionsCollapse
    booleans["comparingExpressionsCollapse"] = comparingExpressionsCollapse
    booleans["comparingLocalDatesCollapse"] = comparingLocalDatesCollapse
    booleans["concatenationExpressionsCollapse"] = concatenationExpressionsCollapse
    booleans["constructorReferenceNotation"] = constructorReferenceNotation
    booleans["controlFlowMultiStatementCodeBlockCollapse"] = controlFlowMultiStatementCodeBlockCollapse
    booleans["controlFlowSingleStatementCodeBlockCollapse"] = controlFlowSingleStatementCodeBlockCollapse
    booleans["compactControlFlowSyntaxCollapse"] = compactControlFlowSyntaxCollapse
    booleans["const"] = const
    booleans["destructuring"] = destructuring
    booleans["dynamic"] = dynamic
    booleans["emojify"] = emojify
    booleans["experimental"] = experimental
    booleans["expressionFunc"] = expressionFunc
    booleans["fieldShift"] = fieldShift
    booleans["finalEmoji"] = finalEmoji
    booleans["finalRemoval"] = finalRemoval
    booleans["getExpressionsCollapse"] = getExpressionsCollapse
    booleans["getSetExpressionsCollapse"] = getSetExpressionsCollapse
    booleans["globalOn"] = globalOn
    booleans["ifNullSafe"] = ifNullSafe
    booleans["interfaceExtensionProperties"] = interfaceExtensionProperties
    booleans["kotlinQuickReturn"] = kotlinQuickReturn
    booleans["localDateLiteralCollapse"] = localDateLiteralCollapse
    booleans["localDateLiteralPostfixCollapse"] = localDateLiteralPostfixCollapse
    booleans["logFolding"] = logFolding
    booleans["logFoldingTextBlocks"] = logFoldingTextBlocks
    booleans["lombok"] = lombok
    booleans["lombokDirtyOff"] = lombokDirtyOff
    booleans["memoryImprovement"] = memoryImprovement
    booleans["methodDefaultParameters"] = methodDefaultParameters
    booleans["nullable"] = nullable
    booleans["optional"] = optional
    booleans["overrideHide"] = overrideHide
    booleans["patternMatchingInstanceof"] = patternMatchingInstanceof
    booleans["pseudoAnnotations"] = pseudoAnnotations
    booleans["println"] = println
    booleans["rangeExpressionsCollapse"] = rangeExpressionsCollapse
    booleans["semicolonsCollapse"] = semicolonsCollapse
    booleans["slicingExpressionsCollapse"] = slicingExpressionsCollapse
    booleans["streamSpread"] = streamSpread
    booleans["summaryParentOverride"] = summaryParentOverride
    booleans["suppressWarningsHide"] = suppressWarningsHide
    booleans["varExpressionsCollapse"] = varExpressionsCollapse

    val strings = LinkedHashMap<String, String?>()
    strings["lombokPatternOff"] = lombokPatternOff

    return FoldingProfile(booleanToggles = booleans, stringToggles = strings)
}
