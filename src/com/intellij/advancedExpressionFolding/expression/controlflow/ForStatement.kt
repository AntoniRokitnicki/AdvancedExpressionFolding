package com.intellij.advancedExpressionFolding.expression.controlflow

import com.intellij.advancedExpressionFolding.expression.Expression
import com.intellij.advancedExpressionFolding.expression.operation.collection.Range
import com.intellij.advancedExpressionFolding.settings.AdvancedExpressionFoldingSettings
import com.intellij.advancedExpressionFolding.settings.IControlFlowState
import com.intellij.lang.folding.FoldingDescriptor
import com.intellij.openapi.editor.Document
import com.intellij.openapi.editor.FoldingGroup
import com.intellij.openapi.util.TextRange
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiForStatement

class ForStatement(
    private val statement: PsiForStatement,
    textRange: TextRange,
    operand: Expression,
    startRange: Expression,
    startInclusive: Boolean,
    endRange: Expression,
    endInclusive: Boolean,
) : Range(statement, textRange, operand, startRange, startInclusive, endRange, endInclusive),
    IControlFlowState by AdvancedExpressionFoldingSettings.State()() {

    init {
        separator = FOR_SEPARATOR
    }

    override fun buildFoldRegions(
        element: PsiElement,
        document: Document,
        parent: Expression?
    ): Array<FoldingDescriptor> {
        val descriptors = mutableListOf(*super.buildFoldRegions(element, document, parent))
        if (compactControlFlowSyntaxCollapse && statement.lParenth != null && statement.rParenth != null) {
            val parenthesesRange = TextRange.create(
                statement.lParenth!!.textRange.startOffset,
                statement.rParenth!!.textRange.endOffset
            )
            if (CompactControlFlowExpression.supportsFoldRegions(document, parenthesesRange)) {
                val group = descriptors.firstOrNull()?.group ?: FoldingGroup.newGroup(CompactControlFlowExpression::class.java.name)
                CompactControlFlowExpression.buildFoldRegions(element, group, descriptors, parenthesesRange)
            }
        }
        return descriptors.toTypedArray()
    }

    companion object {
        const val FOR_SEPARATOR: String = ":"
    }
}
