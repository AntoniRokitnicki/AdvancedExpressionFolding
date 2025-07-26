package com.intellij.advancedExpressionFolding.settings

open class StateDelegate(private val state: AdvancedExpressionFoldingSettings.State = AdvancedExpressionFoldingSettings.Companion.getInstance().state) : IState by state
