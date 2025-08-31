package com.intellij.advancedExpressionFolding.expression

import com.intellij.lang.folding.FoldingDescriptor
import com.intellij.openapi.editor.Document
import com.intellij.openapi.editor.FoldingGroup
import com.intellij.openapi.util.TextRange
import com.intellij.psi.PsiElement

abstract class Operation(
    element: PsiElement,
    textRange: TextRange,
    protected var character: String,
    private var priority: Int,
    protected var operands: List<Expression>
) : Expression(element, textRange) {

    override fun isCollapsedByDefault(): Boolean {
        for (operand in operands) {
            if (!operand.isCollapsedByDefault()) {
                return false
            }
        }
        return super.isCollapsedByDefault()
    }

    fun getOperands(): List<Expression> = operands

    fun getPriority(): Int = priority

    fun getCharacter(): String = character

    override fun supportsFoldRegions(document: Document, parent: Expression?): Boolean {
        if (operands.isEmpty() || equalOrLessPriority(0)) {
            return false
        }
        for (i in 1 until operands.size) {
            if (operands[i - 1].textRange.endOffset >= operands[i].textRange.startOffset || equalOrLessPriority(i)) {
                return false
            }
        }
        return true // TODO no-format: ensure operands.supportFoldRegions
    }

    private fun equalOrLessPriority(index: Int): Boolean {
        return operands[index] is Operation && (operands[index] as Operation).getPriority() < priority
    }

    override fun buildFoldRegions(element: PsiElement, document: Document, parent: Expression?): Array<FoldingDescriptor> {
        val group = FoldingGroup.newGroup(javaClass.name)
        val descriptors = ArrayList<FoldingDescriptor>()
        var offset = textRange.startOffset
        if (operands[0].textRange.startOffset > offset) {
            descriptors.add(
                FoldingDescriptor(
                    element.node,
                    TextRange.create(offset, operands[0].textRange.startOffset),
                    group,
                    ""
                )
            )
        }
        offset = operands[0].textRange.endOffset
        for (i in 1 until operands.size) {
            val r = TextRange.create(changeOperandsStartOffset(offset), changeOperandsEndOffset(operands[i].textRange.startOffset))
            val p = buildFolding(character)
            if (document.getText(r) != p) {
                descriptors.add(FoldingDescriptor(element.node, r, group, p))
            }
            offset = operands[i].textRange.endOffset
        }
        if (offset < textRange.endOffset) {
            descriptors.add(
                FoldingDescriptor(
                    element.node,
                    TextRange.create(offset, textRange.endOffset),
                    group,
                    suffixText()
                )
            )
        }
        for (operand in operands) {
            if (operand.supportsFoldRegions(document, this)) {
                descriptors.addAll(operand.buildFoldRegions(operand.element, document, this))
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
        if (other == null || this::class != other::class) return false
        other as Operation
        return priority == other.priority && character == other.character && operands == other.operands
    }

    override fun hashCode(): Int {
        var result = character.hashCode()
        result = 31 * result + priority
        result = 31 * result + operands.hashCode()
        return result
    }
}

