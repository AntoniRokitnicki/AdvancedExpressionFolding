package com.intellij.advancedExpressionFolding.discovery

import com.intellij.util.messages.Topic

internal fun interface RuleDiscoveryListener {
    fun onRulesUpdated(report: RuleDiscoveryReport)

    companion object {
        val TOPIC: Topic<RuleDiscoveryListener> =
            Topic.create("advancedExpressionFolding.ruleDiscoveryReport", RuleDiscoveryListener::class.java)
    }
}
