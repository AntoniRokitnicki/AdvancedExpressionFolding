package com.intellij.advancedExpressionFolding.expression.literal;

import com.intellij.advancedExpressionFolding.expression.Expression;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiElement;

public class CharacterLiteral extends Expression {
    private Character character;

    public CharacterLiteral(PsiElement element, TextRange textRange, Character character) {
        super(element, textRange);
        this.character = character;
    }

    public Character getCharacter() {
        return character;
    }
}
