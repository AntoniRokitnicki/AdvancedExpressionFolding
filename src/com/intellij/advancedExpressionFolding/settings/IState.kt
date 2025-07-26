package com.intellij.advancedExpressionFolding.settings

interface IState : ILessImportantState {
    val concatenationExpressionsCollapse: Boolean
    val slicingExpressionsCollapse: Boolean
    val comparingExpressionsCollapse: Boolean
    val comparingLocalDatesCollapse: Boolean
    val getExpressionsCollapse: Boolean
    val rangeExpressionsCollapse: Boolean
    val checkExpressionsCollapse: Boolean
    val castExpressionsCollapse: Boolean
    val varExpressionsCollapse: Boolean
    val getSetExpressionsCollapse: Boolean
    val compactControlFlowSyntaxCollapse: Boolean
    val assertsCollapse: Boolean

    val optional: Boolean
    val streamSpread: Boolean
    val lombok: Boolean
    val fieldShift: Boolean
    val kotlinQuickReturn: Boolean
    val ifNullSafe: Boolean
    val logFolding: Boolean
    val println: Boolean
    val const: Boolean
    val nullable: Boolean

}
