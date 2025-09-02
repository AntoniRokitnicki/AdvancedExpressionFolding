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
    operand: Expression,
    startRange: Expression,
    startInclusive: Boolean,
    endRange: Expression,
    endInclusive: Boolean
) : Expression(element, textRange) {
    private var operand: Expression = operand
    private var startRange: Expression = startRange
    private var endRange: Expression = endRange
    protected var separator: String = RANGE_IN_SEPARATOR
    private var startInclusive: Boolean = startInclusive
    private var endInclusive: Boolean = endInclusive

    override fun equals(o: Any?): Boolean {
        if (this === o) return true
        if (o == null || this.javaClass != o.javaClass) return false
        val that = o as Range
        if (startInclusive != that.startInclusive) return false
        if (endInclusive != that.endInclusive) return false
        if (!operand.equals(that.operand)) return false
        if (!startRange.equals(that.startRange)) return false
        if (!endRange.equals(that.endRange)) return false
        return separator.equals(that.separator)
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

    fun isStartInclusive(): Boolean {
        return startInclusive
    }

    fun isEndInclusive(): Boolean {
        return endInclusive
    }

    fun getStart(): Expression {
        return startRange
    }

    fun getOperand(): Expression {
        return operand
    }

    fun getEnd(): Expression {
        return endRange
    }

    override fun supportsFoldRegions(document: Document, parent: Expression?): Boolean {
        return getStart().getTextRange().getStartOffset() < getEnd().getTextRange().getStartOffset() &&
            (getEnd().getTextRange().getEndOffset() < getTextRange().getEndOffset() ||
                SUPPORTED_OVERLAPPED_SYMBOLS.contains(
                    document.getText(
                        TextRange.create(
                            getEnd().getTextRange().getEndOffset(),
                            getEnd().getTextRange().getEndOffset() + 1
                        )
                    )
                ))
    }

    override fun buildFoldRegions(element: PsiElement, document: Document, parent: Expression?): Array<FoldingDescriptor> {
        val group = FoldingGroup.newGroup(this.javaClass.getName())
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
                element.getNode(),
                TextRange.create(
                    getOperand().getTextRange().getEndOffset(),
                    getStart().getTextRange().getStartOffset()
                ),
                group,
                p1
            )
        )
        descriptors.add(
            FoldingDescriptor(
                element.getNode(),
                TextRange.create(
                    getStart().getTextRange().getEndOffset(),
                    getEnd().getTextRange().getStartOffset()
                ),
                group,
                RANGE_COMMA_DELIMITER
            )
        )
        descriptors.add(
            if (getTextRange().getEndOffset() > getEnd().getTextRange().getEndOffset()) {
                FoldingDescriptor(
                    element.getNode(),
                    TextRange.create(getEnd().getTextRange().getEndOffset(), getTextRange().getEndOffset()),
                    group,
                    p2
                )
            } else {
                FoldingDescriptor(
                    element.getNode(),
                    TextRange.create(
                        getEnd().getTextRange().getEndOffset(),
                        getEnd().getTextRange().getEndOffset() + 1
                    ),
                    group,
                    p2 + document.getText(
                        TextRange.create(
                            getEnd().getTextRange().getEndOffset(),
                            getEnd().getTextRange().getEndOffset() + 1
                        )
                    )
                )
            }
        )
        if (startRange.supportsFoldRegions(document, this)) {
            Collections.addAll(
                descriptors,
                *startRange.buildFoldRegions(startRange.getElement(), document, this)
            )
        }
        if (endRange.supportsFoldRegions(document, this)) {
            Collections.addAll(
                descriptors,
                *endRange.buildFoldRegions(endRange.getElement(), document, this)
            )
        }
        return descriptors.toArray(EMPTY_ARRAY)
    }

    companion object {
        const val RANGE_COMMA_DELIMITER: String = ", "
        const val RANGE_IN_SEPARATOR: String = "in"
        private val SUPPORTED_OVERLAPPED_SYMBOLS: Set<String> = hashSetOf(
            " ",
            "&",
            "|",
            "(",
            ")"
        )
    }
}
