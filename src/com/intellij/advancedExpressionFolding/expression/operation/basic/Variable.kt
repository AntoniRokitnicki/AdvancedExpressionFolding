package com.intellij.advancedExpressionFolding.expression.operation.basic

import com.intellij.advancedExpressionFolding.expression.Expression
import com.intellij.advancedExpressionFolding.expression.math.ArithmeticExpression
import com.intellij.advancedExpressionFolding.expression.property.INameable
import com.intellij.lang.folding.FoldingDescriptor
import com.intellij.openapi.editor.Document
import com.intellij.openapi.editor.FoldingGroup
import com.intellij.openapi.util.TextRange
import com.intellij.psi.PsiElement
import java.util.ArrayList

class Variable(
    element: PsiElement,
    textRange: TextRange,
    private val variableTextRange: TextRange?,
    private val name: String,
    private val copy: Boolean
) : Expression(element, textRange), ArithmeticExpression, INameable {
    override fun getName(): String = name

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || javaClass != other.javaClass) return false
        val variable = other as Variable
        return name == variable.name
    }

    override fun hashCode(): Int = name.hashCode()

    override fun supportsFoldRegions(document: Document, parent: Expression?): Boolean {
        return isHighlighted
    }

    fun isCopy(): Boolean = copy

    override fun buildFoldRegions(
        element: PsiElement,
        document: Document,
        parent: Expression?
    ): Array<FoldingDescriptor> {
        val descriptors = ArrayList<FoldingDescriptor>()
        if (variableTextRange != null) {
            val group = FoldingGroup.newGroup(
                Variable::class.java.name + Expression.HIGHLIGHTED_GROUP_POSTFIX
            )
            if (textRange.startOffset < variableTextRange.startOffset) {
                descriptors.add(
                    FoldingDescriptor(
                        element.node,
                        TextRange.create(textRange.startOffset, variableTextRange.startOffset),
                        group,
                        ""
                    )
                )
            }
            if (variableTextRange.endOffset < textRange.endOffset) {
                descriptors.add(
                    FoldingDescriptor(
                        element.node,
                        TextRange.create(variableTextRange.endOffset, textRange.endOffset),
                        group,
                        ""
                    )
                )
            }
        }
        return descriptors.toTypedArray()
    }

    override fun isCollapsedByDefault(): Boolean {
        return super.isCollapsedByDefault()
    }

    override fun isHighlighted(): Boolean {
        return variableTextRange != null &&
            (variableTextRange.startOffset > textRange.startOffset ||
                variableTextRange.endOffset < textRange.endOffset)
    }

    fun getVariableTextRange(): TextRange? = variableTextRange
}
