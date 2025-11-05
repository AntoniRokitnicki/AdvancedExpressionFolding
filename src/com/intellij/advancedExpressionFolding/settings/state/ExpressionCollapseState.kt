package com.intellij.advancedExpressionFolding.settings.state

interface IExpressionCollapseState {
    var castExpressionsCollapse: Boolean
    var checkExpressionsCollapse: Boolean
    var comparingExpressionsCollapse: Boolean
    var concatenationExpressionsCollapse: Boolean
    var getExpressionsCollapse: Boolean
    var getSetExpressionsCollapse: Boolean
    var rangeExpressionsCollapse: Boolean
    var slicingExpressionsCollapse: Boolean
    var varExpressionsCollapse: Boolean
    var fieldShift: Boolean
}

data class ExpressionCollapseState(
    override var castExpressionsCollapse: Boolean = true,
    override var checkExpressionsCollapse: Boolean = true,
    override var comparingExpressionsCollapse: Boolean = true,
    override var concatenationExpressionsCollapse: Boolean = true,
    override var getExpressionsCollapse: Boolean = true,
    override var getSetExpressionsCollapse: Boolean = true,
    override var rangeExpressionsCollapse: Boolean = true,
    override var slicingExpressionsCollapse: Boolean = true,
    override var varExpressionsCollapse: Boolean = true,
    override var fieldShift: Boolean = true,
) : IExpressionCollapseState
