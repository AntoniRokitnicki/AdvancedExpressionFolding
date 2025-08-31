package com.intellij.advancedExpressionFolding.processor.expression

import com.intellij.advancedExpressionFolding.expression.operation.basic.TypeCast
import com.intellij.advancedExpressionFolding.processor.core.BaseExtension
import com.intellij.advancedExpressionFolding.processor.core.BuildExpressionExt
import com.intellij.openapi.editor.Document
import com.intellij.psi.PsiExpression
import com.intellij.psi.PsiTypeCastExpression

object PsiTypeCastExpressionExt : BaseExtension() {
    fun getTypeCastExpression(expression: PsiTypeCastExpression, document: Document): TypeCast? {
        val operand: PsiExpression? = expression.operand
        return if (operand != null) {
            TypeCast(expression, expression.textRange, BuildExpressionExt.getAnyExpression(operand, document))
        } else {
            null
        }
    }
}

