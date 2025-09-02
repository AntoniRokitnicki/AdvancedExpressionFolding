package com.intellij.advancedExpressionFolding.expression.controlflow

import com.intellij.advancedExpressionFolding.expression.Expression
import com.intellij.lang.folding.FoldingDescriptor
import com.intellij.openapi.editor.Document
import com.intellij.openapi.editor.FoldingGroup
import com.intellij.openapi.util.TextRange
import com.intellij.psi.PsiElement

class SemicolonExpression : Expression {
    constructor(element: PsiElement, textRange: TextRange) : super(element, textRange)

    override fun supportsFoldRegions(document: Document, parent: Expression?): Boolean {
        return true
    }

    override fun buildFoldRegions(
        element: PsiElement,
        document: Document,
        parent: Expression?
    ): Array<FoldingDescriptor> {
        return arrayOf(
            FoldingDescriptor(
                element.getNode(),
                getTextRange(),
                FoldingGroup.newGroup(SemicolonExpression::class.java.getName()),
                ""
            )
        )
    }
}

