package com.intellij.advancedExpressionFolding.settings

interface ILogFoldingState {
    val logFolding: Boolean
    val logFoldingTextBlocks: Boolean
}

data class LogFoldingState(
    override val logFolding: Boolean = true,
    override val logFoldingTextBlocks: Boolean = true,
) : ILogFoldingState
