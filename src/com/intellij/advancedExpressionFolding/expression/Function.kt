package com.intellij.advancedExpressionFolding.expression

import com.intellij.lang.folding.FoldingDescriptor
import com.intellij.openapi.editor.Document
import com.intellij.openapi.editor.FoldingGroup
import com.intellij.openapi.util.TextRange
import com.intellij.psi.PsiElement

abstract class Function : Expression {
    protected var operands: List<Expression> = java.util.ArrayList()
    private var name: String = ""

    constructor(
        element: PsiElement,
        textRange: TextRange,
        name: String,
        operands: List<Expression>
    ) : super(element, textRange) {
        this.name = name
        this.operands = operands
    }

    override fun isCollapsedByDefault(): Boolean {
        for (operand in operands) {
            if (!operand.isCollapsedByDefault()) {
                return false
            }
        }
        return super.isCollapsedByDefault()
    }

    override fun equals(o: Any?): Boolean {
        if (this === o) return true
        if (o == null || this.javaClass != o.javaClass) return false
        val function = o as Function
        return name.equals(function.name) && operands.equals(function.operands)
    }

    override fun hashCode(): Int {
        var result = name.hashCode()
        result = 31 * result + operands.hashCode()
        return result
    }

    override fun supportsFoldRegions(document: Document, parent: Expression?): Boolean {
        return operands.size > 0 && getTextRange().getStartOffset() < operands[0].getTextRange().getStartOffset() && getTextRange()
            .getEndOffset() > operands[operands.size - 1].getTextRange().getEndOffset()
    }

    override fun buildFoldRegions(
        element: PsiElement,
        document: Document,
        parent: Expression?
    ): Array<FoldingDescriptor> {
        val group = FoldingGroup.newGroup(this.javaClass.getName())
        val descriptors: MutableList<FoldingDescriptor> = java.util.ArrayList()
        var offset = getTextRange().getStartOffset()
        descriptors.add(
            FoldingDescriptor(
                element.getNode(),
                TextRange.create(offset, operands[0].getTextRange().getStartOffset()),
                group,
                name + "("
            )
        )
        offset = operands[0].getTextRange().getEndOffset()
        for (i in 1 until operands.size) {
            val r = TextRange.create(offset, operands[i].getTextRange().getStartOffset())
            val p = ", "
            if (!document.getText(r).equals(p)) {
                descriptors.add(FoldingDescriptor(element.getNode(), r, group, p))
            }
            offset = operands[i].getTextRange().getEndOffset()
        }
        descriptors.add(
            FoldingDescriptor(
                element.getNode(),
                TextRange.create(offset, getTextRange().getEndOffset()),
                group,
                ")"
            )
        )
        for (operand in operands) {
            if (operand.supportsFoldRegions(document, this)) {
                java.util.Collections.addAll(
                    descriptors,
                    *operand.buildFoldRegions(operand.getElement(), document, this)
                )
            }
        }
        return descriptors.toTypedArray()
    }

    fun getOperands(): List<Expression> {
        return operands
    }
}

