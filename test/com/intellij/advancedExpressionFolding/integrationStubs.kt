package com.intellij.advancedExpressionFolding

import com.intellij.advancedExpressionFolding.integration.FoldingSnapshot
import com.intellij.advancedExpressionFolding.settings.IConfig
import com.intellij.advancedExpressionFolding.settings.IState
import com.intellij.driver.client.Remote

@Remote("com.intellij.advancedExpressionFolding.settings.AdvancedExpressionFoldingSettings", plugin = "com.github.advanced-java-folding2")
interface SettingsStub {
    fun enableEverything()
    fun getState(): ISettingsState
}

@Remote("com.intellij.advancedExpressionFolding.settings.AdvancedExpressionFoldingSettings\$State", plugin = "com.github.advanced-java-folding2")
interface ISettingsState : IState, IConfig

@Remote("com.intellij.advancedExpressionFolding.action.UpdateFoldedTextColorsAction", plugin = "com.github.advanced-java-folding2")
interface ColorActionStub {
    fun changeFoldingColors()
}

@Remote("com.intellij.advancedExpressionFolding.FoldingService", plugin = "com.github.advanced-java-folding2")
interface FoldingServiceStub {
    fun clearAllKeys()
}

@Remote("com.intellij.advancedExpressionFolding.integration.FoldingIntegrationTestBridge", plugin = "com.github.advanced-java-folding2")
interface FoldingTestBridgeStub {
    fun snapshot(filePaths: List<String>): List<FoldingSnapshot>
    fun updateFoldRegions(filePaths: List<String>)
    fun fold(relativePath: String, collapse: Boolean)
}

@Remote("com.intellij.advancedExpressionFolding.integration.IntegrationTestApi", plugin = "com.github.advanced-java-folding2")
interface FoldingIntegrationStub {
    fun toggleGlobalFolding(state: Boolean)
    fun countAdvancedFoldRegions(): Int
}

@Remote("com.intellij.usages.UsageViewManager", plugin = "com.intellij")
interface UsageViewManagerStub {
    fun getSelectedUsageView(): UsageViewStub?
}

@Remote("com.intellij.usages.UsageView", plugin = "com.intellij")
interface UsageViewStub {
    fun getUsagesCount(): Int
}
