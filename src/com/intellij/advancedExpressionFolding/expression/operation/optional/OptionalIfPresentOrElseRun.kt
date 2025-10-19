package com.intellij.advancedExpressionFolding.expression.operation.optional

import com.intellij.advancedExpressionFolding.expression.Expression
import com.intellij.openapi.util.TextRange
import com.intellij.psi.PsiElement

class OptionalIfPresentOrElseRun(
    element: PsiElement,
    textRange: TextRange,
    qualifier: Expression,
) : OptionalIfPresentLet(element, textRange, qualifier) {

    override fun suffixText(): String = super.suffixText() + " ?: run { … }"
}

