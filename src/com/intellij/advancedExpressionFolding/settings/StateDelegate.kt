package com.intellij.advancedExpressionFolding.settings

open class StateDelegate() : IState by AdvancedExpressionFoldingSettings.State()()
