package com.intellij.advancedExpressionFolding.settings.state

import com.intellij.advancedExpressionFolding.settings.IConfig

interface IGlobalSettingsState : IConfig {
    override var globalOn: Boolean
    override var memoryImprovement: Boolean
    var dynamic: Boolean
    var experimental: Boolean
}

data class GlobalSettingsState(
    override var globalOn: Boolean = true,
    override var memoryImprovement: Boolean = true,
    override var dynamic: Boolean = true,
    override var experimental: Boolean = false,
) : IGlobalSettingsState
