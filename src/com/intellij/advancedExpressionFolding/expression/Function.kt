package com.intellij.advancedExpressionFolding.expression

import com.intellij.lang.folding.FoldingDescriptor
import com.intellij.openapi.editor.Document
import com.intellij.openapi.editor.FoldingGroup
import com.intellij.openapi.util.TextRange
import com.intellij.psi.PsiElement

abstract class Function(
    element: PsiElement,
    textRange: TextRange,
    private val name: String,
    open val operands: List<Expression>
) : Expression(element, textRange) {

    override fun isCollapsedByDefault(): Boolean {
        for (operand in operands) {
            if (!operand.isCollapsedByDefault()) {
                return false
            }
        }
        return super.isCollapsedByDefault()
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Function

        if (name != other.name) return false
        if (operands != other.operands) return false

        return true
    }

    override fun hashCode(): Int {
        var result = name.hashCode()
        result = 31 * result + operands.hashCode()
        return result
    }

    override fun supportsFoldRegions(document: Document, parent: Expression?): Boolean {
        return operands.isNotEmpty() &&
            textRange.startOffset < operands.first().textRange.startOffset &&
            textRange.endOffset > operands.last().textRange.endOffset
    }

    override fun buildFoldRegions(
        element: PsiElement,
        document: Document,
        parent: Expression?
    ): Array<FoldingDescriptor> {
        val group = FoldingGroup.newGroup(javaClass.name)
        val descriptors = mutableListOf<FoldingDescriptor>()
        var offset = textRange.startOffset
        descriptors += FoldingDescriptor(
            element.node,
            TextRange.create(offset, operands.first().textRange.startOffset),
            group,
            "$name("
        )
        offset = operands.first().textRange.endOffset
        for (i in 1 until operands.size) {
            val range = TextRange.create(offset, operands[i].textRange.startOffset)
            val placeholder = ", "
            if (document.getText(range) != placeholder) {
                descriptors += FoldingDescriptor(element.node, range, group, placeholder)
            }
            offset = operands[i].textRange.endOffset
        }
        descriptors += FoldingDescriptor(
            element.node,
            TextRange.create(offset, textRange.endOffset),
            group,
            ")"
        )
        for (operand in operands) {
            if (operand.supportsFoldRegions(document, this)) {
                descriptors += operand.buildFoldRegions(operand.element, document, this).toList()
            }
        }
        return descriptors.toTypedArray()
    }
}
