package com.intellij.advancedExpressionFolding.expression

import com.intellij.lang.folding.FoldingDescriptor
import com.intellij.openapi.editor.Document
import com.intellij.openapi.editor.FoldingGroup
import com.intellij.openapi.util.TextRange
import com.intellij.psi.PsiElement

abstract class Operation(
    element: PsiElement,
    textRange: TextRange,
    character: String,
    priority: Int,
    operands: List<Expression>
) : Expression(element, textRange) {
    protected var character: String = character
    protected var operands: List<Expression> = operands
    private var priority: Int = priority

    override fun isCollapsedByDefault(): Boolean {
        for (operand in operands) {
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
        if (operands.isEmpty() || equalOrLessPriority(0)) {
            return false
        }
        for (i in 1 until operands.size) {
            if (operands[i - 1].getTextRange().getEndOffset() >= operands[i].getTextRange().getStartOffset()
                || equalOrLessPriority(i)
            ) {
                return false
            }
        }
        return true // TODO no-format: ensure operands.supportFoldRegions
    }

    private fun equalOrLessPriority(index: Int): Boolean {
        return operands[index] is Operation && (operands[index] as Operation).getPriority() < priority
    }

    override fun buildFoldRegions(element: PsiElement, document: Document, parent: Expression?): Array<FoldingDescriptor> {
        val group = FoldingGroup.newGroup(this.javaClass.getName())
        val descriptors: MutableList<FoldingDescriptor> = ArrayList()
        var offset = getTextRange().getStartOffset()
        if (operands[0].getTextRange().getStartOffset() > offset) {
            descriptors.add(
                FoldingDescriptor(
                    element.getNode(),
                    TextRange.create(offset, operands[0].getTextRange().getStartOffset()),
                    group,
                    ""
                )
            )
        }
        offset = operands[0].getTextRange().getEndOffset()
        for (i in 1 until operands.size) {
            val r = TextRange.create(
                changeOperandsStartOffset(offset),
                changeOperandsEndOffset(operands[i].getTextRange().getStartOffset())
            )
            val p = buildFolding(character)
            if (!document.getText(r).equals(p)) {
                descriptors.add(FoldingDescriptor(element.getNode(), r, group, p))
            }
            offset = operands[i].getTextRange().getEndOffset()
        }
        if (offset < getTextRange().getEndOffset()) {
            descriptors.add(
                FoldingDescriptor(
                    element.getNode(),
                    TextRange.create(offset, getTextRange().getEndOffset()),
                    group,
                    suffixText()
                )
            )
        }
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

    protected open fun changeOperandsEndOffset(startOffset: Int): Int {
        return startOffset
    }

    protected open fun suffixText(): String {
        return ""
    }

    protected open fun changeOperandsStartOffset(offset: Int): Int {
        return offset
    }

    protected open fun buildFolding(character: String): String {
        return " " + character + " "
    }

    override fun equals(o: Any?): Boolean {
        if (this === o) return true
        if (o == null || this.javaClass != o.javaClass) return false

        val operation = o as Operation

        return priority == operation.priority &&
            character.equals(operation.character) &&
            operands.equals(operation.operands)
    }

    override fun hashCode(): Int {
        var result = character.hashCode()
        result = 31 * result + priority
        result = 31 * result + operands.hashCode()
        return result
    }
}

