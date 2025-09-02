package com.intellij.advancedExpressionFolding.expression.literal

import com.intellij.advancedExpressionFolding.expression.Expression
import com.intellij.openapi.util.TextRange
import com.intellij.psi.PsiElement
import java.lang.Character

class CharacterLiteral(element: PsiElement, textRange: TextRange, character: Character?) :
    Expression(element, textRange) {
    private var character: Character? = character
    fun getCharacter(): Character? {
        return character
    }
}

