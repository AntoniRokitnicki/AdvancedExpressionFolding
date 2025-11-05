package com.intellij.advancedExpressionFolding.settings.state

interface IKotlinLanguageState {
    var const: Boolean
    var ifNullSafe: Boolean
    var kotlinQuickReturn: Boolean
    var nullable: Boolean
    var println: Boolean
    var destructuring: Boolean
    var expressionFunc: Boolean
    var methodDefaultParameters: Boolean
    var patternMatchingInstanceof: Boolean
    var constructorReferenceNotation: Boolean
    var arithmeticExpressions: Boolean
}

data class KotlinLanguageState(
    override var const: Boolean = true,
    override var ifNullSafe: Boolean = true,
    override var kotlinQuickReturn: Boolean = true,
    override var nullable: Boolean = false,
    override var println: Boolean = true,
    override var destructuring: Boolean = false,
    override var expressionFunc: Boolean = true,
    override var methodDefaultParameters: Boolean = true,
    override var patternMatchingInstanceof: Boolean = true,
    override var constructorReferenceNotation: Boolean = true,
    override var arithmeticExpressions: Boolean = false,
) : IKotlinLanguageState
