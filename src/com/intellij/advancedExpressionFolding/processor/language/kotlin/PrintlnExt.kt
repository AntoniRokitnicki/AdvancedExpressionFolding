package com.intellij.advancedExpressionFolding.processor.language.kotlin

import com.intellij.advancedExpressionFolding.expression.Expression
import com.intellij.advancedExpressionFolding.expression.semantic.kotlin.PrintlnExpression
import com.intellij.advancedExpressionFolding.processor.end
import com.intellij.advancedExpressionFolding.processor.equalsIgnoreSpaces
import com.intellij.advancedExpressionFolding.processor.isWhitespace
import com.intellij.advancedExpressionFolding.processor.start
import com.intellij.advancedExpressionFolding.processor.toTextRange
import com.intellij.advancedExpressionFolding.settings.State
import com.intellij.advancedExpressionFolding.settings.state.IKotlinLanguageState
import com.intellij.psi.PsiMethodCallExpression

object PrintlnExt : IKotlinLanguageState by State()() {

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
        val start = element.start()
        val end = endSibling.end()
        return PrintlnExpression(element, (start to end).toTextRange(), argumentExpression)
    }

}
