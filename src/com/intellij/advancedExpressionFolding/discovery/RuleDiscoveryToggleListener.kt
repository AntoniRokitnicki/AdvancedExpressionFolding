package com.intellij.advancedExpressionFolding.discovery

import com.intellij.util.messages.Topic

fun interface RuleDiscoveryToggleListener {
    fun onRuleDiscoveryToggle(enabled: Boolean)

    companion object {
        val TOPIC: Topic<RuleDiscoveryToggleListener> =
            Topic.create("advancedExpressionFolding.ruleDiscoveryToggle", RuleDiscoveryToggleListener::class.java)
    }
}
