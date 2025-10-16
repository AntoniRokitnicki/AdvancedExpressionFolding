package com.intellij.advancedExpressionFolding

import com.intellij.driver.client.Remote

@Remote("com.intellij.advancedExpressionFolding.view.FindUsageCustomViewTestHook", plugin = "com.github.advanced-java-folding2")
interface FindUsageCustomViewTestBridge {
    fun reset()
    fun dumpUsageData(): List<String>
    fun isSearchFinished(): Boolean
}

@Remote("com.intellij.advancedExpressionFolding.action.FindMethodsWithDefaultParametersActionTestHook", plugin = "com.github.advanced-java-folding2")
interface FindMethodsWithDefaultParametersActionTestBridge {
    fun reset()
    fun setCancelAfter(processedCount: Int?)
    fun setDelayPerFileMillis(delay: Long)
    fun getProcessedEvents(): List<String>
    fun getFinishedEvent(): String?
}

@Remote("com.intellij.advancedExpressionFolding.action.FindMethodsWithDefaultParametersActionExecutor", plugin = "com.github.advanced-java-folding2")
interface FindMethodsWithDefaultParametersActionExecutorBridge {
    fun runOnCurrentProject()
}
