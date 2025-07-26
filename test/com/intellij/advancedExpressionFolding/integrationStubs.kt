package com.intellij.advancedExpressionFolding

import com.intellij.advancedExpressionFolding.settings.IState
import com.intellij.driver.client.Remote

@Remote("com.intellij.advancedExpressionFolding.settings.AdvancedExpressionFoldingSettings", plugin = "com.github.advanced-java-folding2")
interface SettingsStub{
    fun disableAll()
    fun enableEverything()
    fun getState(): ISettingsState
}

@Remote("com.intellij.advancedExpressionFolding.settings.AdvancedExpressionFoldingSettings\$State", plugin = "com.github.advanced-java-folding2")
interface ISettingsState : IState {

}
