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
    protected val operands: List<Expression>
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
        if (other == null || this::class != other::class) return false
        other as Function
        return name == other.name && operands == other.operands
    }

    override fun hashCode(): Int {
        var result = name.hashCode()
        result = 31 * result + operands.hashCode()
        return result
    }

    override fun supportsFoldRegions(document: Document, parent: Expression?): Boolean {
        return operands.isNotEmpty() &&
            textRange.startOffset < operands[0].textRange.startOffset &&
            textRange.endOffset > operands[operands.size - 1].textRange.endOffset
    }

    override fun buildFoldRegions(element: PsiElement, document: Document, parent: Expression?): Array<FoldingDescriptor> {
        val group = FoldingGroup.newGroup(javaClass.name)
        val descriptors = ArrayList<FoldingDescriptor>()
        var offset = textRange.startOffset
        descriptors.add(
            FoldingDescriptor(
                element.node,
                TextRange.create(offset, operands[0].textRange.startOffset),
                group,
                "$name("
            )
        )
        offset = operands[0].textRange.endOffset
        for (i in 1 until operands.size) {
            val r = TextRange.create(offset, operands[i].textRange.startOffset)
            val p = ", "
            if (document.getText(r) != p) {
                descriptors.add(FoldingDescriptor(element.node, r, group, p))
            }
            offset = operands[i].textRange.endOffset
        }
        descriptors.add(
            FoldingDescriptor(
                element.node,
                TextRange.create(offset, textRange.endOffset),
                group,
                ")"
            )
        )
        for (operand in operands) {
            if (operand.supportsFoldRegions(document, this)) {
                descriptors.addAll(operand.buildFoldRegions(operand.element, document, this))
            }
        }
        return descriptors.toTypedArray()
    }

    fun getOperands(): List<Expression> = operands
}

