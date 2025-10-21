package com.intellij.advancedExpressionFolding.settings

interface IDateOperationsState {
    val comparingLocalDatesCollapse: Boolean
    val localDateLiteralCollapse: Boolean
    val localDateLiteralPostfixCollapse: Boolean
}

data class DateOperationsState(
    override val comparingLocalDatesCollapse: Boolean = true,
    override val localDateLiteralCollapse: Boolean = false,
    override val localDateLiteralPostfixCollapse: Boolean = false,
) : IDateOperationsState
