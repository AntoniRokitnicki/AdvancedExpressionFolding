package com.intellij.advancedExpressionFolding.processor.expression

import com.intellij.advancedExpressionFolding.expression.operation.basic.TypeCast
import com.intellij.advancedExpressionFolding.processor.core.BuildExpressionExt
import com.intellij.openapi.editor.Document
import com.intellij.psi.PsiTypeCastExpression

object PsiTypeCastExpressionExt {

    fun getTypeCastExpression(expression: PsiTypeCastExpression, document: Document): TypeCast? {
        val operand = expression.operand ?: return null
        return TypeCast(expression, expression.textRange, BuildExpressionExt.getAnyExpression(operand, document))
    }
}
