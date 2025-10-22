package com.intellij.advancedExpressionFolding.settings

interface IExpressionCollapseState {
    val castExpressionsCollapse: Boolean
    val checkExpressionsCollapse: Boolean
    val comparingExpressionsCollapse: Boolean
    val concatenationExpressionsCollapse: Boolean
    val getExpressionsCollapse: Boolean
    val getSetExpressionsCollapse: Boolean
    val rangeExpressionsCollapse: Boolean
    val slicingExpressionsCollapse: Boolean
    val varExpressionsCollapse: Boolean
    val fieldShift: Boolean
}

data class ExpressionCollapseState(
    override val castExpressionsCollapse: Boolean = true,
    override val checkExpressionsCollapse: Boolean = true,
    override val comparingExpressionsCollapse: Boolean = true,
    override val concatenationExpressionsCollapse: Boolean = true,
    override val getExpressionsCollapse: Boolean = true,
    override val getSetExpressionsCollapse: Boolean = true,
    override val rangeExpressionsCollapse: Boolean = true,
    override val slicingExpressionsCollapse: Boolean = true,
    override val varExpressionsCollapse: Boolean = true,
    override val fieldShift: Boolean = true,
) : IExpressionCollapseState
