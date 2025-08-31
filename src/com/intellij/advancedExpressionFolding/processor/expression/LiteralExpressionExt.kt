package com.intellij.advancedExpressionFolding.processor.expression

import com.intellij.advancedExpressionFolding.expression.Expression
import com.intellij.advancedExpressionFolding.expression.literal.CharacterLiteral
import com.intellij.advancedExpressionFolding.expression.literal.NumberLiteral
import com.intellij.advancedExpressionFolding.expression.literal.StringLiteral
import com.intellij.advancedExpressionFolding.processor.util.Consts
import com.intellij.psi.PsiLiteralExpression

object LiteralExpressionExt {
    fun getLiteralExpression(element: PsiLiteralExpression): Expression? {
        val type = element.type
        if (type != null) {
            if (Consts.SUPPORTED_PRIMITIVE_TYPES.contains(type.canonicalText)) {
                val value = element.value
                if (value is Number) {
                    return NumberLiteral(element, element.textRange, null, value, false)
                } else if (value is String) {
                    return StringLiteral(element, element.textRange, value)
                } else if (value is Char) {
                    return CharacterLiteral(element, element.textRange, value)
                }
            }
        }
        return null
    }
}
