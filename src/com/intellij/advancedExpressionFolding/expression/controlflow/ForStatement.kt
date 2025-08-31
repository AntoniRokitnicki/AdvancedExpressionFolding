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
import java.util.ArrayList

class ForStatement(
    private val statement: PsiForStatement,
    textRange: TextRange,
    operand: Expression,
    startRange: Expression,
    startInclusive: Boolean,
    endRange: Expression,
    endInclusive: Boolean
) : Range(statement, textRange, operand, startRange, startInclusive, endRange, endInclusive) {
    init {
        this.separator = FOR_SEPARATOR
    }

    override fun buildFoldRegions(
        element: PsiElement,
        document: Document,
        parent: Expression?
    ): Array<FoldingDescriptor> {
        // TODO: Refactor this mess
        val descriptors = ArrayList<FoldingDescriptor>(
            listOf(*super.buildFoldRegions(element, document, parent))
        )
        if (
            AdvancedExpressionFoldingSettings.getInstance().state.compactControlFlowSyntaxCollapse &&
            statement.lParenth != null &&
            statement.rParenth != null
        ) {
            // TODO: Refactor this mess
            val textRange = TextRange.create(
                statement.lParenth!!.textRange.startOffset,
                statement.rParenth!!.textRange.endOffset
            )
            if (CompactControlFlowExpression.supportsFoldRegions(document, textRange)) {
                CompactControlFlowExpression.buildFoldRegions(
                    element,
                    if (descriptors.isNotEmpty()) descriptors[0].group
                    else FoldingGroup.newGroup(CompactControlFlowExpression::class.java.name),
                    descriptors,
                    textRange
                )
            }
        }
        return descriptors.toTypedArray()
    }

    companion object {
        const val FOR_SEPARATOR = ":"
    }
}

