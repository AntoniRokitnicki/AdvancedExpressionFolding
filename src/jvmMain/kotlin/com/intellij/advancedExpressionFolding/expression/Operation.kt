package com.intellij.advancedExpressionFolding.expression

import com.intellij.lang.folding.FoldingDescriptor
import com.intellij.openapi.editor.Document
import com.intellij.openapi.editor.FoldingGroup
import com.intellij.openapi.util.TextRange
import com.intellij.psi.PsiElement

abstract class Operation(
    element: PsiElement,
    textRange: TextRange,
    protected var characterValue: String,
    private var priority: Int,
    operands: List<Expression>
) : Expression(element, textRange) {

    private val operandsBacking: List<Expression> = operands

    open val operands: List<Expression>
        get() = operandsBacking

    override fun isCollapsedByDefault(): Boolean {
        for (operand in operands) {
            if (!operand.isCollapsedByDefault()) {
                return false
            }
        }
        return super.isCollapsedByDefault()
    }

    fun getPriority(): Int = priority

    fun getCharacter(): String = characterValue

    override fun supportsFoldRegions(document: Document, parent: Expression?): Boolean {
        if (operands.isEmpty() || equalOrLessPriority(0)) {
            return false
        }
        for (i in 1 until operands.size) {
            val previous = operands[i - 1]
            val current = operands[i]
            if (previous.textRange.endOffset >= current.textRange.startOffset || equalOrLessPriority(i)) {
                return false
            }
        }
        return true
    }

    private fun equalOrLessPriority(index: Int): Boolean {
        val operand = operands[index]
        return operand is Operation && operand.getPriority() < priority
    }

    override fun buildFoldRegions(
        element: PsiElement,
        document: Document,
        parent: Expression?
    ): Array<FoldingDescriptor> {
        val group = FoldingGroup.newGroup(javaClass.name)
        val descriptors = mutableListOf<FoldingDescriptor>()
        var offset = textRange.startOffset
        if (operands[0].textRange.startOffset > offset) {
            descriptors += FoldingDescriptor(
                element.node,
                TextRange.create(offset, operands[0].textRange.startOffset),
                group,
                ""
            )
        }
        offset = operands[0].textRange.endOffset
        for (i in 1 until operands.size) {
            val range = TextRange.create(
                changeOperandsStartOffset(offset),
                changeOperandsEndOffset(operands[i].textRange.startOffset)
            )
            val placeholder = buildFolding(characterValue)
            if (document.getText(range) != placeholder) {
                descriptors += FoldingDescriptor(element.node, range, group, placeholder)
            }
            offset = operands[i].textRange.endOffset
        }
        if (offset < textRange.endOffset) {
            descriptors += FoldingDescriptor(
                element.node,
                TextRange.create(offset, textRange.endOffset),
                group,
                suffixText()
            )
        }
        for (operand in operands) {
            if (operand.supportsFoldRegions(document, this)) {
                descriptors += operand.buildFoldRegions(operand.element, document, this).toList()
            }
        }
        return descriptors.toTypedArray()
    }

    protected open fun changeOperandsEndOffset(startOffset: Int): Int = startOffset

    protected open fun suffixText(): String = ""

    protected open fun changeOperandsStartOffset(offset: Int): Int = offset

    protected open fun buildFolding(character: String): String = " $character "

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Operation) return false

        return priority == other.priority &&
            characterValue == other.characterValue &&
            operands == other.operands
    }

    override fun hashCode(): Int {
        var result = characterValue.hashCode()
        result = 31 * result + priority
        result = 31 * result + operands.hashCode()
        return result
    }
}
