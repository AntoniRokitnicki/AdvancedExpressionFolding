package com.intellij.advancedExpressionFolding.expression.operation.basic

import com.intellij.advancedExpressionFolding.expression.Expression
import com.intellij.advancedExpressionFolding.expression.math.ArithmeticExpression
import com.intellij.advancedExpressionFolding.expression.property.INameable
import com.intellij.lang.folding.FoldingDescriptor
import com.intellij.openapi.editor.Document
import com.intellij.openapi.editor.FoldingGroup
import com.intellij.openapi.util.TextRange
import com.intellij.psi.PsiElement

class Variable(
    element: PsiElement,
    textRange: TextRange,
    private val variableTextRange: TextRange?,
    override val name: String,
    private val copy: Boolean
) : Expression(element, textRange), ArithmeticExpression, INameable {

    override fun supportsFoldRegions(document: Document, parent: Expression?): Boolean = isHighlighted()

    override fun buildFoldRegions(
        element: PsiElement,
        document: Document,
        parent: Expression?
    ): Array<FoldingDescriptor> {
        val descriptors = mutableListOf<FoldingDescriptor>()
        val highlightedRange = variableTextRange
        if (highlightedRange != null) {
            val group = FoldingGroup.newGroup(Variable::class.java.name + Expression.HIGHLIGHTED_GROUP_POSTFIX)
            if (textRange.startOffset < highlightedRange.startOffset) {
                descriptors += FoldingDescriptor(
                    element.node,
                    TextRange.create(textRange.startOffset, highlightedRange.startOffset),
                    group,
                    ""
                )
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
        val highlightedRange = variableTextRange ?: return false
        return highlightedRange.startOffset > textRange.startOffset ||
            highlightedRange.endOffset < textRange.endOffset
    }

    fun getVariableTextRange(): TextRange? = variableTextRange

    fun isCopy(): Boolean = copy

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Variable

        return name == other.name
    }

    override fun hashCode(): Int = name.hashCode()
}
