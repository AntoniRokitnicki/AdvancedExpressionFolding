package com.intellij.advancedExpressionFolding.processor.expression

import com.intellij.advancedExpressionFolding.expression.Expression
import com.intellij.advancedExpressionFolding.expression.literal.CharacterLiteral
import com.intellij.advancedExpressionFolding.expression.literal.NumberLiteral
import com.intellij.advancedExpressionFolding.expression.literal.StringLiteral
import com.intellij.advancedExpressionFolding.processor.util.Consts
import com.intellij.psi.PsiLiteralExpression
import org.jetbrains.annotations.NotNull
import org.jetbrains.annotations.Nullable

object LiteralExpressionExt {
    @JvmStatic
    @Nullable
    fun getLiteralExpression(@NotNull element: PsiLiteralExpression): Expression? {
        if (element.getType() != null) {
            if (Consts.SUPPORTED_PRIMITIVE_TYPES.contains(element.getType()!!.getCanonicalText())) {
                val value = element.getValue()
                if (value is Number) {
                    return NumberLiteral(element, element.getTextRange(), null, value, false)
                } else if (value is String) {
                    return StringLiteral(element, element.getTextRange(), value)
                } else if (value is Character) {
                    return CharacterLiteral(element, element.getTextRange(), value)
                }
            }
        }
        return null
    }
}
