package com.intellij.advancedExpressionFolding.settings

interface IGlobalSettingsState : IConfig {
    override var globalOn: Boolean
    override var memoryImprovement: Boolean
    val dynamic: Boolean
    val experimental: Boolean
}

data class GlobalSettingsState(
    override var globalOn: Boolean = true,
    override var memoryImprovement: Boolean = true,
    override val dynamic: Boolean = true,
    override val experimental: Boolean = false,
) : IGlobalSettingsState
