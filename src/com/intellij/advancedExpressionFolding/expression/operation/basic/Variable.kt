package com.intellij.advancedExpressionFolding.expression.operation.basic

import com.intellij.advancedExpressionFolding.expression.Expression
import com.intellij.advancedExpressionFolding.expression.math.ArithmeticExpression
import com.intellij.advancedExpressionFolding.expression.property.INameable
import com.intellij.lang.folding.FoldingDescriptor
import com.intellij.openapi.editor.Document
import com.intellij.openapi.editor.FoldingGroup
import com.intellij.openapi.util.TextRange
import com.intellij.psi.PsiElement
import org.jetbrains.annotations.Nullable
import java.util.ArrayList

class Variable(
    element: PsiElement,
    textRange: TextRange,
    variableTextRange: TextRange?,
    name: String,
    copy: Boolean
) : Expression(element, textRange), ArithmeticExpression, INameable {
    private var name: String = name
    private var copy: Boolean = copy
    private var variableTextRange: TextRange? = variableTextRange

    fun getName(): String {
        return name
    }

    override fun equals(o: Any?): Boolean {
        if (this === o) return true
        if (o == null || javaClass != o.javaClass) return false
        val variable = o as Variable
        return name == variable.name
    }

    override fun hashCode(): Int {
        return name.hashCode()
    }

    override fun supportsFoldRegions(document: Document, parent: Expression?): Boolean {
        return isHighlighted()
    }

    fun isCopy(): Boolean {
        return copy
    }

    override fun buildFoldRegions(element: PsiElement, document: Document, parent: Expression?): Array<FoldingDescriptor> {
        val descriptors = ArrayList<FoldingDescriptor>()
        if (variableTextRange != null) {
            val group = FoldingGroup.newGroup(
                Variable::class.java.getName() + Expression.HIGHLIGHTED_GROUP_POSTFIX
            )
            if (getTextRange().getStartOffset() < variableTextRange!!.getStartOffset()) {
                descriptors.add(
                    FoldingDescriptor(
                        element.getNode(),
                        TextRange.create(getTextRange().getStartOffset(), variableTextRange!!.getStartOffset()),
                        group,
                        ""
                    )
                )
            }
            if (variableTextRange!!.getEndOffset() < getTextRange().getEndOffset()) {
                descriptors.add(
                    FoldingDescriptor(
                        element.getNode(),
                        TextRange.create(variableTextRange!!.getEndOffset(), getTextRange().getEndOffset()),
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
            (variableTextRange!!.getStartOffset() > getTextRange().getStartOffset() ||
                variableTextRange!!.getEndOffset() < getTextRange().getEndOffset())
    }

    fun getVariableTextRange(): TextRange? {
        return variableTextRange
    }
}
