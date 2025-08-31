package com.intellij.advancedExpressionFolding.expression.operation.collection

import com.intellij.advancedExpressionFolding.expression.Expression
import com.intellij.lang.folding.FoldingDescriptor
import com.intellij.openapi.editor.Document
import com.intellij.openapi.editor.FoldingGroup
import com.intellij.openapi.util.TextRange
import com.intellij.psi.PsiElement
import java.util.ArrayList
import java.util.Collections

open class Range(
    element: PsiElement,
    textRange: TextRange,
    private val operand: Expression,
    private val startRange: Expression,
    private val startInclusive: Boolean,
    private val endRange: Expression,
    private val endInclusive: Boolean
) : Expression(element, textRange) {

    protected var separator: String = RANGE_IN_SEPARATOR

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || javaClass != other.javaClass) return false
        val that = other as Range
        return startInclusive == that.startInclusive &&
            endInclusive == that.endInclusive &&
            operand == that.operand &&
            startRange == that.startRange &&
            endRange == that.endRange &&
            separator == that.separator
    }

    override fun hashCode(): Int {
        var result = operand.hashCode()
        result = 31 * result + startRange.hashCode()
        result = 31 * result + endRange.hashCode()
        result = 31 * result + separator.hashCode()
        result = 31 * result + if (startInclusive) 1 else 0
        result = 31 * result + if (endInclusive) 1 else 0
        return result
    }

    fun isStartInclusive(): Boolean = startInclusive
    fun isEndInclusive(): Boolean = endInclusive
    fun getStart(): Expression = startRange
    fun getOperand(): Expression = operand
    fun getEnd(): Expression = endRange

    override fun supportsFoldRegions(document: Document, parent: Expression?): Boolean {
        val endText = document.getText(
            TextRange.create(endRange.textRange.endOffset, endRange.textRange.endOffset + 1)
        )
        return startRange.textRange.startOffset < endRange.textRange.startOffset &&
            (endRange.textRange.endOffset < textRange.endOffset || SUPPORTED_OVERLAPPED_SYMBOLS.contains(endText))
    }

    override fun buildFoldRegions(
        element: PsiElement,
        document: Document,
        parent: Expression?
    ): Array<FoldingDescriptor> {
        val group = FoldingGroup.newGroup(javaClass.name)
        val p1 = StringBuilder(separator.length + 3)
            .append(' ')
            .append(separator)
            .append(' ')
            .append(if (isStartInclusive()) '[' else '(')
            .toString()
        val p2 = if (isEndInclusive()) "]" else ")"
        val descriptors = ArrayList<FoldingDescriptor>()
        descriptors.add(
            FoldingDescriptor(
                element.node,
                TextRange.create(operand.textRange.endOffset, startRange.textRange.startOffset),
                group,
                p1
            )
        )
        descriptors.add(
            FoldingDescriptor(
                element.node,
                TextRange.create(startRange.textRange.endOffset, endRange.textRange.startOffset),
                group,
                RANGE_COMMA_DELIMITER
            )
        )
        descriptors.add(
            if (textRange.endOffset > endRange.textRange.endOffset) {
                FoldingDescriptor(
                    element.node,
                    TextRange.create(endRange.textRange.endOffset, textRange.endOffset),
                    group,
                    p2
                )
            } else {
                FoldingDescriptor(
                    element.node,
                    TextRange.create(endRange.textRange.endOffset, endRange.textRange.endOffset + 1),
                    group,
                    p2 + document.getText(
                        TextRange.create(endRange.textRange.endOffset, endRange.textRange.endOffset + 1)
                    )
                )
            }
        )
        if (startRange.supportsFoldRegions(document, this)) {
            Collections.addAll(
                descriptors,
                *startRange.buildFoldRegions(startRange.element, document, this)
            )
        }
        if (endRange.supportsFoldRegions(document, this)) {
            Collections.addAll(
                descriptors,
                *endRange.buildFoldRegions(endRange.element, document, this)
            )
        }
        return descriptors.toTypedArray()
    }

    companion object {
        const val RANGE_COMMA_DELIMITER = ", "
        const val RANGE_IN_SEPARATOR = "in"
        private val SUPPORTED_OVERLAPPED_SYMBOLS = setOf(" ", "&", "|", "(", ")")
    }
}
