package com.intellij.advancedExpressionFolding.settings

interface IGlobalSettingsState {
    val globalOn: Boolean
    val memoryImprovement: Boolean
    val dynamic: Boolean
    val experimental: Boolean
}

data class GlobalSettingsState(
    override val globalOn: Boolean = true,
    override val memoryImprovement: Boolean = true,
    override val dynamic: Boolean = true,
    override val experimental: Boolean = false,
) : IGlobalSettingsState
