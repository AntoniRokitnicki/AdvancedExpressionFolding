package com.intellij.advancedExpressionFolding.expression.property

import com.intellij.advancedExpressionFolding.expression.Expression
import com.intellij.openapi.editor.Document
import com.intellij.openapi.util.TextRange
import com.intellij.psi.PsiElement

class GetterRecord(
    element: PsiElement,
    textRange: TextRange,
    override val getterTextRange: TextRange,
    override val `object`: Expression?,
    override val name: String
) : Expression(element, textRange), IGetter {
    override fun supportsFoldRegions(
        document: Document,
        parent: Expression?
    ): Boolean {
        return true
    }

    override fun buildFoldRegions(element: PsiElement, document: Document, parent: Expression?) =
        buildGetterFoldRegions(element, document)
}
