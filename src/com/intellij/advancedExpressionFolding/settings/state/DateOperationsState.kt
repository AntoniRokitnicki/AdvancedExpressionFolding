package com.intellij.advancedExpressionFolding.settings.state

interface IDateOperationsState {
    var comparingLocalDatesCollapse: Boolean
    var localDateLiteralCollapse: Boolean
    var localDateLiteralPostfixCollapse: Boolean
}

data class DateOperationsState(
    override var comparingLocalDatesCollapse: Boolean = true,
    override var localDateLiteralCollapse: Boolean = false,
    override var localDateLiteralPostfixCollapse: Boolean = false,
) : IDateOperationsState
