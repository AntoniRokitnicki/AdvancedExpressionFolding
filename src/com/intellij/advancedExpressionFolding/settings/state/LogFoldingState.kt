package com.intellij.advancedExpressionFolding.settings.state

interface ILogFoldingState {
    var logFolding: Boolean
    var logFoldingTextBlocks: Boolean
}

data class LogFoldingState(
    override var logFolding: Boolean = true,
    override var logFoldingTextBlocks: Boolean = true,
) : ILogFoldingState
