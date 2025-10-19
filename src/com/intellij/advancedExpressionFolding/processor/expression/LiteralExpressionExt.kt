package com.intellij.advancedExpressionFolding.processor.expression

import com.intellij.advancedExpressionFolding.expression.Expression
import com.intellij.advancedExpressionFolding.expression.literal.CharacterLiteral
import com.intellij.advancedExpressionFolding.expression.literal.NumberLiteral
import com.intellij.advancedExpressionFolding.expression.literal.StringLiteral
import com.intellij.advancedExpressionFolding.processor.util.Consts
import com.intellij.advancedExpressionFolding.settings.AdvancedExpressionFoldingSettings
import com.intellij.psi.PsiLiteralExpression

object LiteralExpressionExt {

    fun getLiteralExpression(element: PsiLiteralExpression): Expression? {
        val type = element.type ?: return null
        if (!Consts.SUPPORTED_PRIMITIVE_TYPES.contains(type.canonicalText)) {
            return null
        }
        val value = element.value
        return when (value) {
            is Number -> NumberLiteral(element, element.textRange, null, value, false)
            is String -> if (!element.isTextBlock || AdvancedExpressionFoldingSettings.getInstance().state.logFoldingTextBlocks) {
                StringLiteral(element, element.textRange, value)
            } else {
                null
            }
            is Char -> CharacterLiteral(element, element.textRange, value)
            else -> null
        }
    }
}
