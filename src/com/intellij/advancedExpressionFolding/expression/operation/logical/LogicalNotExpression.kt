package com.intellij.advancedExpressionFolding.expression.operation.logical

import com.intellij.advancedExpressionFolding.expression.Expression
import com.intellij.openapi.editor.Document
import com.intellij.openapi.editor.FoldingGroup
import com.intellij.openapi.util.TextRange
import com.intellij.psi.PsiElement

class LogicalNotExpression(
    element: PsiElement,
    textRange: TextRange,
    private val operand: Expression,
    private val uppercase: Boolean,
    @Suppress("UNUSED_PARAMETER") private val visualParentheses: Boolean,
) : Expression(element, textRange) {

    override fun supportsFoldRegions(document: Document, parent: Expression?): Boolean {
        return operand.textRange.startOffset > textRange.startOffset
    }

    override fun buildFoldRegions(
        element: PsiElement,
        document: Document,
        parent: Expression?
    ): Array<com.intellij.lang.folding.FoldingDescriptor> {
        val group = FoldingGroup.newGroup(javaClass.name)
        val descriptors = mutableListOf<com.intellij.lang.folding.FoldingDescriptor>()
        val prefixRange = TextRange.create(textRange.startOffset, operand.textRange.startOffset)
        descriptors += com.intellij.lang.folding.FoldingDescriptor(element.node, prefixRange, group, buildPlaceholder())

        if (operand.supportsFoldRegions(document, this)) {
            descriptors += operand.buildFoldRegions(operand.element, document, this)
        }

        val suffixRange = TextRange.create(operand.textRange.endOffset, textRange.endOffset)
        if (!suffixRange.isEmpty) {
            descriptors += com.intellij.lang.folding.FoldingDescriptor(element.node, suffixRange, group, "")
        }

        return descriptors.toTypedArray()
    }

    private fun buildPlaceholder(): String {
        val keyword = if (uppercase) "NOT" else "not"
        return "$keyword "
    }
}
