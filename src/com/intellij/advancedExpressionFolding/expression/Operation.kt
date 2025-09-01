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

abstract class Operation : Expression {
    protected var character: String
    protected var operands: List<Expression>
    private var priority: Int

    constructor(element: PsiElement, textRange: TextRange, character: String, priority: Int, operands: List<Expression>) : super(element, textRange) {
        this.character = character
        this.priority = priority
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

    fun getOperands(): List<Expression> {
        return operands
    }

    fun getPriority(): Int {
        return priority
    }

    fun getCharacter(): String {
        return character
    }

    override fun supportsFoldRegions(document: Document, parent: Expression?): Boolean {
        val operandsSize = operands.stream().count().toInt()
        if (operands.isEmpty() || equalOrLessPriority(0)) {
            return false
        }
        var i = 1
        while (i < operandsSize) {
            if (operands.get(i - 1).getTextRange().getEndOffset() >= operands.get(i).getTextRange().getStartOffset()
                || equalOrLessPriority(i)) {
                return false
            }
            i++
        }
        return true
    }

    private fun equalOrLessPriority(index: Int): Boolean {
        return operands.get(index) is Operation &&
            (operands.get(index) as Operation).getPriority() < priority
    }

    override fun buildFoldRegions(element: PsiElement, document: Document, parent: Expression?): Array<FoldingDescriptor> {
        val group = FoldingGroup.newGroup(this.javaClass.getName())
        val descriptors: MutableList<FoldingDescriptor> = ArrayList()
        var offset = getTextRange().getStartOffset()
        if (operands.get(0).getTextRange().getStartOffset() > offset) {
            descriptors.add(FoldingDescriptor(element.getNode(),
                TextRange.create(offset, operands.get(0).getTextRange().getStartOffset()), group, ""))
        }
        offset = operands.get(0).getTextRange().getEndOffset()
        val operandsSize = operands.stream().count().toInt()
        var i = 1
        while (i < operandsSize) {
            val r = TextRange.create(changeOperandsStartOffset(offset),
                changeOperandsEndOffset(operands.get(i).getTextRange().getStartOffset()))
            val p = buildFolding(character)
            if (!document.getText(r).equals(p)) {
                descriptors.add(FoldingDescriptor(element.getNode(), r, group, p))
            }
            offset = operands.get(i).getTextRange().getEndOffset()
            i++
        }
        if (offset < getTextRange().getEndOffset()) {
            descriptors.add(FoldingDescriptor(element.getNode(),
                TextRange.create(offset, getTextRange().getEndOffset()), group, suffixText()))
        }
        val iterator = operands.iterator()
        while (iterator.hasNext()) {
            val operand = iterator.next()
            if (operand.supportsFoldRegions(document, this)) {
                Collections.addAll(descriptors, *operand.buildFoldRegions(operand.getElement(), document, this))
            }
        }
        return descriptors.toTypedArray()
    }

    protected fun changeOperandsEndOffset(startOffset: Int): Int {
        return startOffset
    }

    protected fun suffixText(): String {
        return ""
    }

    protected fun changeOperandsStartOffset(offset: Int): Int {
        return offset
    }

    protected fun buildFolding(character: String): String {
        return " " + character + " "
    }

    override fun equals(o: Any?): Boolean {
        if (this === o) return true
        if (o == null || this.javaClass != o.javaClass) return false
        val operation = o as Operation
        return priority == operation.getPriority()
            && character.equals(operation.getCharacter())
            && operands.equals(operation.getOperands())
    }

    override fun hashCode(): Int {
        var result = character.hashCode()
        result = 31 * result + priority
        result = 31 * result + operands.hashCode()
        return result
    }
}
