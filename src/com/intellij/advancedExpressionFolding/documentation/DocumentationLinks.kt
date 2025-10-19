package com.intellij.advancedExpressionFolding.documentation

object DocumentationLinks {
    private const val BASE_URL = "https://github.com/AntoniRokitnicki/AdvancedExpressionFolding/wiki"

    private val anchors = mapOf(
        "getSetExpressionsCollapse" to "#getsetexpressionscollapse",
        "varExpressionsCollapse" to "#varexpressionscollapse",
        "compactControlFlowSyntaxCollapse" to "#compactcontrolflowsyntaxcollapse",
        "getExpressionsCollapse" to "#getexpressionscollapse",
        "concatenationExpressionsCollapse" to "#concatenationexpressionscollapse",
        "slicingExpressionsCollapse" to "#slicingexpressionscollapse",
        "comparingExpressionsCollapse" to "#comparingexpressionscollapse",
        "comparingLocalDatesCollapse" to "#comparinglocaldatescollapse",
        "localDateLiteralCollapse" to "#localdateliteralcollapse",
        "localDateLiteralPostfixCollapse" to "#localdateliteralpostfixcollapse",
        "castExpressionsCollapse" to "#castexpressionscollapse",
        "rangeExpressionsCollapse" to "#rangeexpressionscollapse",
        "checkExpressionsCollapse" to "#checkexpressionscollapse",
        "ifNullSafe" to "#ifnullsafe",
        "kotlinQuickReturn" to "#kotlinquickreturn",
        "assertsCollapse" to "#asserts",
        "optional" to "#optional",
        "streamSpread" to "#streamspread",
        "logFolding" to "#logfolding",
        "fieldShift" to "#fieldshift",
        "destructuring" to "#destructuring",
        "println" to "#println",
        "controlFlowSingleStatementCodeBlockCollapse" to "#controlflowsinglestatementcodeblockcollapse",
        "controlFlowMultiStatementCodeBlockCollapse" to "#controlflowmultistatementcodeblockcollapse",
        "semicolonsCollapse" to "#semicolonscollapse",
        "const" to "#const",
        "nullable" to "#nullable",
        "finalRemoval" to "#finalremoval",
        "finalEmoji" to "#finalemoji",
        "lombok" to "#lombok",
        "lombokDirtyOff" to "#lombokdirtyoff",
        "expressionFunc" to "#expressionfunc",
        "dynamic" to "#dynamic",
        "arithmeticExpressions" to "#arithmeticexpressions",
        "emojify" to "#emojify",
        "interfaceExtensionProperties" to "#interfaceextensionproperties",
        "patternMatchingInstanceof" to "#patternmatchinginstanceof",
        "summaryParentOverride" to "#summaryparentoverride",
        "constructorReferenceNotation" to "#constructorReferenceNotation",
        "methodDefaultParameters" to "#methodDefaultParameters",
        "overrideHide" to "#overrideHide",
        "suppressWarningsHide" to "#suppressWarningsHide",
        "pseudoAnnotations" to "#pseudoAnnotations",
        "experimental" to "#experimental"
    )

    fun urlFor(settingKey: String): String? = anchors[settingKey]?.let { BASE_URL + it }
}
