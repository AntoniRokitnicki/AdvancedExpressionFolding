package com.intellij.advancedExpressionFolding.settings.state

interface IControlFlowState {
    var compactControlFlowSyntaxCollapse: Boolean
    var controlFlowMultiStatementCodeBlockCollapse: Boolean
    var controlFlowSingleStatementCodeBlockCollapse: Boolean
    var assertsCollapse: Boolean
}

data class ControlFlowState(
    override var compactControlFlowSyntaxCollapse: Boolean = false,
    override var controlFlowMultiStatementCodeBlockCollapse: Boolean = false,
    override var controlFlowSingleStatementCodeBlockCollapse: Boolean = false,
    override var assertsCollapse: Boolean = true,
) : IControlFlowState
