package com.intellij.advancedExpressionFolding.expression.custom

import com.intellij.advancedExpressionFolding.expression.Expression
import com.intellij.openapi.util.TextRange
import com.intellij.psi.PsiElement

class LoggerBracketExpression(
    element: PsiElement, textRange: TextRange, text: String, child: Expression?
) : AbstractSingleChildExpression(element, textRange, text, child) {

    override fun groupName(): String  = LoggerBracketExpression::class.java.name

}
