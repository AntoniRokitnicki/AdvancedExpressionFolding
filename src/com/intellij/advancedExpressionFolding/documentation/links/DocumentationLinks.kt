package com.intellij.advancedExpressionFolding.documentation.links

object DocumentationLinks {
  private const val BASE_URL = "https://github.com/AntoniRokitnicki/AdvancedExpressionFolding/wiki/docs/features/"

  private val available = setOf(
    "arithmeticExpressions",
    "assertsCollapse",
    "castExpressionsCollapse",
    "checkExpressionsCollapse",
    "compactControlFlowSyntaxCollapse",
    "comparingExpressionsCollapse",
    "comparingLocalDatesCollapse",
    "concatenationExpressionsCollapse",
    "const",
    "constructorReferenceNotation",
    "controlFlowMultiStatementCodeBlockCollapse",
    "controlFlowSingleStatementCodeBlockCollapse",
    "destructuring",
    "dynamic",
    "emojify",
    "expressionFunc",
    "experimental",
    "fieldShift",
    "finalEmoji",
    "finalRemoval",
    "getExpressionsCollapse",
    "getSetExpressionsCollapse",
    "globalOn",
    "ifNullSafe",
    "interfaceExtensionProperties",
    "kotlinQuickReturn",
    "localDateLiteralCollapse",
    "localDateLiteralPostfixCollapse",
    "logFolding",
    "logFoldingTextBlocks",
    "lombok",
    "lombokDirtyOff",
    "lombokPatternOff",
    "memoryImprovement",
    "methodDefaultParameters",
    "nullable",
    "optional",
    "overrideHide",
    "patternMatchingInstanceof",
    "println",
    "pseudoAnnotations",
    "rangeExpressionsCollapse",
    "semicolonsCollapse",
    "slicingExpressionsCollapse",
    "streamSpread",
    "summaryParentOverride",
    "suppressWarningsHide",
    "varExpressionsCollapse"
  )

  fun urlFor(settingKey: String): String? =
    if (available.contains(settingKey)) "$BASE_URL$settingKey" else null
}
