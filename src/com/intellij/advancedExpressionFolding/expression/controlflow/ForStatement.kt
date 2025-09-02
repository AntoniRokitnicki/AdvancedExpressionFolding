package com.intellij.advancedExpressionFolding.expression.controlflow

import com.intellij.advancedExpressionFolding.expression.Expression
import com.intellij.advancedExpressionFolding.expression.operation.collection.Range
import com.intellij.advancedExpressionFolding.settings.AdvancedExpressionFoldingSettings
import com.intellij.lang.folding.FoldingDescriptor
import com.intellij.openapi.editor.Document
import com.intellij.openapi.editor.FoldingGroup
import com.intellij.openapi.util.TextRange
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiForStatement

class ForStatement : Range {
    companion object {
        const val FOR_SEPARATOR: String = ":"
    }

    private var element: PsiForStatement? = null

    constructor(
        element: PsiForStatement,
        textRange: TextRange,
        operand: Expression,
        startRange: Expression,
        startInclusive: Boolean,
        endRange: Expression,
        endInclusive: Boolean
    ) : super(element, textRange, operand, startRange, startInclusive, endRange, endInclusive) {
        this.element = element
        this.separator = FOR_SEPARATOR
    }

    override fun buildFoldRegions(
        element: PsiElement,
        document: Document,
        parent: Expression?
    ): Array<FoldingDescriptor> {
        val descriptors: MutableList<FoldingDescriptor> = java.util.ArrayList(java.util.Arrays.asList(*super.buildFoldRegions(element, document, parent)))
        if (AdvancedExpressionFoldingSettings.getInstance().getState().getCompactControlFlowSyntaxCollapse()
            && this.element!!.getLParenth() != null && this.element!!.getRParenth() != null
        ) {
            val textRange = TextRange.create(this.element!!.getLParenth()!!.getTextRange().getStartOffset(), this.element!!.getRParenth()!!.getTextRange().getEndOffset())
            if (CompactControlFlowExpression.supportsFoldRegions(document, textRange)) {
                CompactControlFlowExpression.buildFoldRegions(
                    element,
                    if (descriptors.size > 0) descriptors.get(0).getGroup() else FoldingGroup.newGroup(CompactControlFlowExpression::class.java.getName()),
                    descriptors,
                    textRange
                )
            }
        }
        return descriptors.toTypedArray()
    }
}

