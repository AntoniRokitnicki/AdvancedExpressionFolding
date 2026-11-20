package com.intellij.advancedExpressionFolding.expression.semantic.kotlin

import com.intellij.advancedExpressionFolding.expression.Expression
import com.intellij.advancedExpressionFolding.expression.controlflow.CompactControlFlowExpression
import com.intellij.advancedExpressionFolding.settings.State
import com.intellij.advancedExpressionFolding.settings.state.IControlFlowState
import com.intellij.lang.folding.FoldingDescriptor
import com.intellij.openapi.editor.Document
import com.intellij.openapi.editor.FoldingGroup
import com.intellij.openapi.util.TextRange
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiExpression
import com.intellij.psi.PsiForeachStatement
import com.intellij.psi.PsiMethodCallExpression
import com.intellij.psi.PsiParameter

/// Header of a destructured map loop: `for (Map.Entry<K, V> e : map.entrySet())` → `for ((key, value) in map)`.
/// Keeps the compact-control-flow parentheses folding, since both come from the same for-each statement.
class MapEntryForEachExpression(
    private val loop: PsiForeachStatement,
    private val entry: PsiParameter,
    private val map: PsiExpression,
    private val entrySetCall: PsiMethodCallExpression,
) : Expression(loop, TextRange.create(loop.lParenth.textRange.startOffset, loop.rParenth!!.textRange.endOffset)),
    IControlFlowState by State()() {

    override fun supportsFoldRegions(document: Document, parent: Expression?): Boolean = true

    override fun buildFoldRegions(
        element: PsiElement,
        document: Document,
        parent: Expression?
    ): Array<FoldingDescriptor> {
        val group = FoldingGroup.newGroup(MapEntryForEachExpression::class.java.name)
        val descriptors = mutableListOf(
            FoldingDescriptor(
                element.node,
                TextRange.create(entry.textRange.startOffset, map.textRange.startOffset),
                group,
                "(key, value) in "
            ),
            FoldingDescriptor(
                element.node,
                TextRange.create(map.textRange.endOffset, entrySetCall.textRange.endOffset),
                group,
                ""
            ),
        )
        if (compactControlFlowSyntaxCollapse && CompactControlFlowExpression.supportsFoldRegions(document, textRange)) {
            CompactControlFlowExpression.buildFoldRegions(
                element,
                FoldingGroup.newGroup(CompactControlFlowExpression::class.java.name + HIGHLIGHTED_GROUP_POSTFIX),
                descriptors,
                textRange
            )
        }
        return descriptors.toTypedArray()
    }
}
