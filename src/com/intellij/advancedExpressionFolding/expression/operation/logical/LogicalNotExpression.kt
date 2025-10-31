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
    private val visualParentheses: Boolean,
) : Expression(element, textRange) {

    internal val operandExpression: Expression
        get() = operand

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
        descriptors += com.intellij.lang.folding.FoldingDescriptor(
            element.node,
            prefixRange,
            group,
            buildPrefixPlaceholder()
        )

        if (operand.supportsFoldRegions(document, this)) {
            descriptors += operand.buildFoldRegions(operand.element, document, this)
        }

        val suffixRange = TextRange.create(operand.textRange.endOffset, textRange.endOffset)
        if (!suffixRange.isEmpty) {
            descriptors += com.intellij.lang.folding.FoldingDescriptor(
                element.node,
                suffixRange,
                group,
                buildSuffixPlaceholder(document)
            )
        }

        return descriptors.toTypedArray()
    }

    private fun buildPrefixPlaceholder(): String {
        val keyword = if (uppercase) "NOT" else "not"
        return if (shouldWrapOperand()) {
            "$keyword ("
        } else {
            "$keyword "
        }
    }

    private fun buildSuffixPlaceholder(document: Document): String {
        if (!shouldWrapOperand()) {
            return ""
        }
        val suffixText = document.charsSequence.getOrNull(operand.textRange.endOffset)
        return if (suffixText == ')') "" else ")"
    }

    private fun shouldWrapOperand(): Boolean {
        if (!visualParentheses) {
            return false
        }
        return operand is LogicalBinaryExpression
    }
}

private fun CharSequence.getOrNull(index: Int): Char? {
    return if (index in 0 until length) this[index] else null
}
