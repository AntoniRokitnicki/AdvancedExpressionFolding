package com.intellij.advancedExpressionFolding.processor.expression

import com.intellij.advancedExpressionFolding.expression.operation.basic.TypeCast
import com.intellij.advancedExpressionFolding.processor.core.BaseExtension
import com.intellij.advancedExpressionFolding.processor.core.BuildExpressionExt
import com.intellij.openapi.editor.Document
import com.intellij.psi.PsiExpression
import com.intellij.psi.PsiTypeCastExpression
import org.jetbrains.annotations.NotNull
import org.jetbrains.annotations.Nullable

object PsiTypeCastExpressionExt : BaseExtension() {
    @JvmStatic
    @Nullable
    fun getTypeCastExpression(@NotNull expression: PsiTypeCastExpression, @NotNull document: Document): TypeCast? {
        val operand: PsiExpression? = expression.getOperand()
        return if (operand != null)
            TypeCast(expression, expression.getTextRange(), BuildExpressionExt.getAnyExpression(operand, document))
        else
            null
    }
}
