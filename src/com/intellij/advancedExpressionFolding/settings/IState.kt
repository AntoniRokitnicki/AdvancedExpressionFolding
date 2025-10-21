package com.intellij.advancedExpressionFolding.settings

interface IState {
    val concatenationExpressionsCollapse: Boolean
    val slicingExpressionsCollapse: Boolean
    val comparingExpressionsCollapse: Boolean
    val comparingLocalDatesCollapse: Boolean
    val localDateLiteralCollapse: Boolean
    val localDateLiteralPostfixCollapse: Boolean
    val getExpressionsCollapse: Boolean
    val rangeExpressionsCollapse: Boolean
    val checkExpressionsCollapse: Boolean
    val castExpressionsCollapse: Boolean
    val varExpressionsCollapse: Boolean
    val getSetExpressionsCollapse: Boolean
    val controlFlowSingleStatementCodeBlockCollapse: Boolean
    val compactControlFlowSyntaxCollapse: Boolean
    val controlFlowMultiStatementCodeBlockCollapse: Boolean
    val semicolonsCollapse: Boolean
    val assertsCollapse: Boolean

    val optional: Boolean
    val streamSpread: Boolean
    val lombok: Boolean
    val fieldShift: Boolean
    val kotlinQuickReturn: Boolean
    val ifNullSafe: Boolean

    val logFolding: Boolean
    val logFoldingTextBlocks: Boolean

    val destructuring: Boolean
    val println: Boolean
    val const: Boolean
    val nullable: Boolean
    val finalRemoval: Boolean
    val finalEmoji: Boolean
    val lombokDirtyOff: Boolean

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
