package com.intellij.advancedExpressionFolding.expression

import com.intellij.lang.folding.FoldingDescriptor
import com.intellij.openapi.editor.Document
import com.intellij.openapi.editor.FoldingGroup
import com.intellij.openapi.util.TextRange
import com.intellij.psi.PsiElement
import org.jetbrains.annotations.NotNull
import org.jetbrains.annotations.Nullable
import java.util.ArrayList
import java.util.Collections
import java.util.List

abstract class Function : Expression {
    protected var operands: List<Expression>
    private var name: String

    constructor(element: PsiElement, textRange: TextRange, name: String, operands: List<Expression>) : super(element, textRange) {
        this.name = name
        this.operands = operands
    }

    override fun isCollapsedByDefault(): Boolean {
        val iterator = operands.iterator()
        while (iterator.hasNext()) {
            val operand = iterator.next()
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
        return name.equals(function.getName()) && operands.equals(function.getOperands())
    }

    override fun hashCode(): Int {
        var result = name.hashCode()
        result = 31 * result + operands.hashCode()
        return result
    }

    override fun supportsFoldRegions(document: Document, parent: Expression?): Boolean {
        val operandsSize = operands.stream().count().toInt()
        return operandsSize > 0 &&
            getTextRange().getStartOffset() < operands.get(0).getTextRange().getStartOffset() &&
            getTextRange().getEndOffset() > operands.get(operandsSize - 1).getTextRange().getEndOffset()
    }

    override fun buildFoldRegions(element: PsiElement, document: Document, parent: Expression?): Array<FoldingDescriptor> {
        val group = FoldingGroup.newGroup(this.javaClass.getName())
        val descriptors: MutableList<FoldingDescriptor> = ArrayList()
        var offset = getTextRange().getStartOffset()
        descriptors.add(FoldingDescriptor(element.getNode(),
            TextRange.create(offset, operands.get(0).getTextRange().getStartOffset()), group, name + "("))

        offset = operands.get(0).getTextRange().getEndOffset()
        var i = 1
        val operandsSize = operands.stream().count().toInt()
        while (i < operandsSize) {
            val r = TextRange.create(offset, operands.get(i).getTextRange().getStartOffset())
            val p = ", "
            if (!document.getText(r).equals(p)) {
                descriptors.add(FoldingDescriptor(element.getNode(), r, group, p))
            }
            offset = operands.get(i).getTextRange().getEndOffset()
            i++
        }
        descriptors.add(FoldingDescriptor(element.getNode(),
            TextRange.create(offset, getTextRange().getEndOffset()), group, ")"))
        val operandsIterator = operands.iterator()
        while (operandsIterator.hasNext()) {
            val operand = operandsIterator.next()
            if (operand.supportsFoldRegions(document, this)) {
                Collections.addAll(descriptors, *operand.buildFoldRegions(operand.getElement(), document, this))
            }
        }
        return descriptors.toTypedArray()
    }

    fun getOperands(): List<Expression> {
        return operands
    }

    fun getName(): String {
        return name
    }
}
