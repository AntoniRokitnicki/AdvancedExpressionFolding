package com.intellij.advancedExpressionFolding

import com.intellij.advancedExpressionFolding.settings.IConfig
import com.intellij.advancedExpressionFolding.settings.IState
import com.intellij.driver.client.Remote

@Remote("com.intellij.advancedExpressionFolding.settings.AdvancedExpressionFoldingSettings", plugin = "com.github.advanced-java-folding2")
interface SettingsStub {
    fun enableEverything()
    fun getState(): ISettingsState
}

@Remote($$"com.intellij.advancedExpressionFolding.settings.AdvancedExpressionFoldingSettings$State", plugin = "com.github.advanced-java-folding2")
interface ISettingsState : IState, IConfig

@Remote("com.intellij.advancedExpressionFolding.action.UpdateFoldedTextColorsAction", plugin = "com.github.advanced-java-folding2")
interface ColorActionStub {
    fun changeFoldingColors()
}

@Remote("com.intellij.advancedExpressionFolding.integration.IntegrationTestApi", plugin = "com.github.advanced-java-folding2")
interface FoldingIntegrationStub {
    fun toggleGlobalFolding(state: Boolean)
    fun countAdvancedFoldRegions(): Int
}
