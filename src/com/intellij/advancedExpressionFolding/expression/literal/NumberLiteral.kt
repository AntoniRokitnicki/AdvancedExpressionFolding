@file:Suppress("DEPRECATION")
package com.intellij.advancedExpressionFolding.expression.literal

import com.intellij.advancedExpressionFolding.expression.Expression
import com.intellij.advancedExpressionFolding.expression.math.ArithmeticExpression
import com.intellij.lang.folding.FoldingDescriptor
import com.intellij.openapi.editor.Document
import com.intellij.openapi.editor.FoldingGroup
import com.intellij.openapi.util.TextRange
import com.intellij.psi.PsiElement

class NumberLiteral(
    element: PsiElement,
    textRange: TextRange,
    val numberTextRange: TextRange?,
    val number: Number,
    private val convert: Boolean
) : Expression(element, textRange), ArithmeticExpression {

    override fun supportsFoldRegions(document: Document, parent: Expression?): Boolean = isHighlighted()

    override fun buildFoldRegions(
        element: PsiElement,
        document: Document,
        parent: Expression?
    ): Array<FoldingDescriptor> {
        val descriptors = mutableListOf<FoldingDescriptor>()
        numberTextRange?.let { highlightedRange ->
            val group = FoldingGroup.newGroup(NumberLiteral::class.java.name + HIGHLIGHTED_GROUP_POSTFIX)
            if (textRange.startOffset < highlightedRange.startOffset) {
                descriptors += FoldingDescriptor(
                    element.node,
                    TextRange.create(textRange.startOffset, highlightedRange.startOffset),
                    group,
                    ""
                )
            }
            if (convert) {
                val placeholder = when (number) {
                    is Float -> number.toFloat().toString() + "f"
                    is Double -> number.toDouble().toString()
                    else -> number.toString()
                }
                descriptors += FoldingDescriptor(element.node, highlightedRange, group, placeholder)
            }
            if (highlightedRange.endOffset < textRange.endOffset) {
                descriptors += FoldingDescriptor(
                    element.node,
                    TextRange.create(highlightedRange.endOffset, textRange.endOffset),
                    group,
                    ""
                )
            }
        }
        return descriptors.toTypedArray()
    }

    override fun isHighlighted(): Boolean {
        val highlightedRange = numberTextRange ?: return false
        return highlightedRange.startOffset >= textRange.startOffset &&
            highlightedRange.endOffset <= textRange.endOffset
    }
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as NumberLiteral

        return number.toString() == other.number.toString()
    }

    override fun hashCode(): Int = number.toString().hashCode()
}
