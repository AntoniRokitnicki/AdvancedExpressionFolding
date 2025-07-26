package com.intellij.advancedExpressionFolding.settings

interface ILessImportantState {
    val localDateLiteralCollapse: Boolean
    val localDateLiteralPostfixCollapse: Boolean
    val controlFlowSingleStatementCodeBlockCollapse: Boolean
    val controlFlowMultiStatementCodeBlockCollapse: Boolean
    val semicolonsCollapse: Boolean
    val finalRemoval: Boolean
    val finalEmoji: Boolean
    val lombokDirtyOff: Boolean
    val destructuring: Boolean

    val expressionFunc: Boolean
    val dynamic: Boolean
    val arithmeticExpressions: Boolean
    val emojify: Boolean
    val interfaceExtensionProperties: Boolean
    val patternMatchingInstanceof: Boolean
    val summaryParentOverride: Boolean
    val constructorReferenceNotation: Boolean
    val methodDefaultParameters: Boolean
    val lombokPatternOff: String?
    val overrideHide: Boolean
    val suppressWarningsHide: Boolean
    val pseudoAnnotations: Boolean
    // NEW OPTION VAL

    var experimental: Boolean
}
