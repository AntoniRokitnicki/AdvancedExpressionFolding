package com.intellij.advancedExpressionFolding.telemetry

import com.intellij.lang.folding.FoldingDescriptor
import com.intellij.openapi.util.Key
import com.intellij.psi.PsiElement

object FoldingTelemetry {
    private const val RULE_KEY = "AdvancedExpressionFolding.RuleId"

    @JvmField
    val RULE_ID_KEY: Key<String> = Key.create(RULE_KEY)

    const val UNKNOWN_RULE_ID: String = "Unknown"

    @JvmStatic
    fun tagElement(element: PsiElement, ruleId: String) {
        element.putUserData(RULE_ID_KEY, ruleId)
    }

    fun FoldingDescriptor.telemetryRuleId(): String =
        element?.getUserData(RULE_ID_KEY) ?: UNKNOWN_RULE_ID
}
