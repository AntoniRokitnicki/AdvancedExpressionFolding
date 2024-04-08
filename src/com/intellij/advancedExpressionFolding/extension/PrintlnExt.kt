package com.intellij.advancedExpressionFolding.extension

import com.intellij.advancedExpressionFolding.expression.Expression
import com.intellij.advancedExpressionFolding.expression.custom.PrintlnExpression
import com.intellij.openapi.util.TextRange
import com.intellij.psi.PsiMethodCallExpression

object PrintlnExt : BaseExtension() {

    @JvmStatic
    fun createExpression(
        element: PsiMethodCallExpression,
        qualifierExpression: Expression,
        argumentExpression: Expression
    ): Expression? {
        if (!println) {
            return null
        }

        val dotElement = qualifierExpression.element.takeIf {
            it.text.equalsIgnoreSpaces("System.out")
        }?.nextSibling ?: return null
        val endSibling = dotElement.nextSibling?.nextSibling?.takeIf {
            it.isWhitespace()
        } ?: dotElement
        val start = element.textRange.startOffset
        val end = endSibling.textRange.endOffset
        return PrintlnExpression(element, TextRange(start, end), argumentExpression)
    }

}