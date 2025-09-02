package com.intellij.advancedExpressionFolding.expression.literal

import com.intellij.advancedExpressionFolding.expression.Expression
import com.intellij.lang.folding.FoldingDescriptor
import com.intellij.openapi.editor.Document
import com.intellij.openapi.editor.FoldingGroup
import com.intellij.openapi.util.TextRange
import com.intellij.psi.PsiElement

class InterpolatedString(
    element: PsiElement,
    textRange: TextRange,
    operands: List<Expression>
) : Expression(element, textRange) {
    private var operands: List<Expression> = operands

    fun getOperands(): List<Expression> {
        return operands
    }

    override fun supportsFoldRegions(document: Document, parent: Expression?): Boolean {
        return true
    }

    override fun buildFoldRegions(
        element: PsiElement,
        document: Document,
        parent: Expression?
    ): Array<FoldingDescriptor> {
        return buildFoldRegions(element, document, parent, null, null, null)
    }

    override fun buildFoldRegions(
        element: PsiElement,
        document: Document,
        parent: Expression?,
        overflowGroup: FoldingGroup?,
        overflowLeftPlaceholder: String?,
        overflowRightPlaceholder: String?
    ): Array<FoldingDescriptor> {
        return Expression.EMPTY_ARRAY
    }

    override fun isRightOverflow(): Boolean {
        return false
    }

    override fun isHighlighted(): Boolean {
        return false
    }

    override fun isLeftOverflow(): Boolean {
        return false
    }
}
