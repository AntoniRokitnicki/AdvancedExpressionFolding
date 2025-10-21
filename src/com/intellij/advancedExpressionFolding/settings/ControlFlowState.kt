package com.intellij.advancedExpressionFolding.settings

interface IControlFlowState {
    val compactControlFlowSyntaxCollapse: Boolean
    val controlFlowMultiStatementCodeBlockCollapse: Boolean
    val controlFlowSingleStatementCodeBlockCollapse: Boolean
    val assertsCollapse: Boolean
}

data class ControlFlowState(
    override val compactControlFlowSyntaxCollapse: Boolean = false,
    override val controlFlowMultiStatementCodeBlockCollapse: Boolean = false,
    override val controlFlowSingleStatementCodeBlockCollapse: Boolean = false,
    override val assertsCollapse: Boolean = true,
) : IControlFlowState
