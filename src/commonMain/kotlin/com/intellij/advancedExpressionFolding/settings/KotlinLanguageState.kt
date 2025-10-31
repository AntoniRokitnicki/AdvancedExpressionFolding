package com.intellij.advancedExpressionFolding.settings

interface IKotlinLanguageState {
    val const: Boolean
    val ifNullSafe: Boolean
    val kotlinQuickReturn: Boolean
    val nullable: Boolean
    val println: Boolean
    val destructuring: Boolean
    val expressionFunc: Boolean
    val methodDefaultParameters: Boolean
    val patternMatchingInstanceof: Boolean
    val constructorReferenceNotation: Boolean
    val arithmeticExpressions: Boolean
}

data class KotlinLanguageState(
    override val const: Boolean = true,
    override val ifNullSafe: Boolean = true,
    override val kotlinQuickReturn: Boolean = true,
    override val nullable: Boolean = false,
    override val println: Boolean = true,
    override val destructuring: Boolean = false,
    override val expressionFunc: Boolean = true,
    override val methodDefaultParameters: Boolean = true,
    override val patternMatchingInstanceof: Boolean = true,
    override val constructorReferenceNotation: Boolean = true,
    override val arithmeticExpressions: Boolean = false,
) : IKotlinLanguageState
