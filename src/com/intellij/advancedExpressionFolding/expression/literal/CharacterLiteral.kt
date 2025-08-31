package com.intellij.advancedExpressionFolding.expression.literal

import com.intellij.advancedExpressionFolding.expression.Expression
import com.intellij.openapi.util.TextRange
import com.intellij.psi.PsiElement

class CharacterLiteral(
    element: PsiElement,
    textRange: TextRange,
    private val character: Char?
) : Expression(element, textRange) {
    fun getCharacter(): Char? = character
}
