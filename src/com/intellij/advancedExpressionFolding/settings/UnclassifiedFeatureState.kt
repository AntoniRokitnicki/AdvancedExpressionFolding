package com.intellij.advancedExpressionFolding.settings

interface IUnclassifiedFeatureState {
    val semicolonsCollapse: Boolean
    val logicalOperatorsWords: Boolean
    val logicalOperatorsUppercase: Boolean
    val logicalOperatorsParentheses: Boolean
}

data class UnclassifiedFeatureState(
    override val semicolonsCollapse: Boolean = false,
    override val logicalOperatorsWords: Boolean = false,
    override val logicalOperatorsUppercase: Boolean = true,
    override val logicalOperatorsParentheses: Boolean = true,
) : IUnclassifiedFeatureState
