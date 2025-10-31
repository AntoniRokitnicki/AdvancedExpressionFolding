package com.intellij.advancedExpressionFolding.expression.operation.collection

import com.intellij.advancedExpressionFolding.expression.Expression
import com.intellij.lang.folding.FoldingDescriptor
import com.intellij.openapi.editor.Document
import com.intellij.openapi.editor.FoldingGroup
import com.intellij.openapi.util.TextRange
import com.intellij.psi.PsiElement

open class Range(
    element: PsiElement,
    textRange: TextRange,
    val operand: Expression,
    val start: Expression,
    private val startInclusive: Boolean,
    val end: Expression,
    private val endInclusive: Boolean
) : Expression(element, textRange) {

    var separator: String = RANGE_IN_SEPARATOR

    fun isStartInclusive(): Boolean = startInclusive

    fun isEndInclusive(): Boolean = endInclusive

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Range

        return startInclusive == other.startInclusive &&
            endInclusive == other.endInclusive &&
            operand == other.operand &&
            start == other.start &&
            end == other.end &&
            separator == other.separator
    }

    override fun hashCode(): Int {
        var result = operand.hashCode()
        result = 31 * result + start.hashCode()
        result = 31 * result + end.hashCode()
        result = 31 * result + separator.hashCode()
        result = 31 * result + if (startInclusive) 1 else 0
        result = 31 * result + if (endInclusive) 1 else 0
        return result
    }

    override fun supportsFoldRegions(document: Document, parent: Expression?): Boolean {
        val startRange = start.textRange
        val endRange = end.textRange
        val endOffset = endRange.endOffset
        val withinBounds = startRange.startOffset < endRange.startOffset &&
            (endOffset < textRange.endOffset || SUPPORTED_OVERLAPPED_SYMBOLS.contains(
                document.getText(TextRange.create(endOffset, endOffset + 1))
            ))
        return withinBounds
    }

    override fun buildFoldRegions(
        element: PsiElement,
        document: Document,
        parent: Expression?
    ): Array<FoldingDescriptor> {
        val group = FoldingGroup.newGroup(javaClass.name)
        val prefix = buildString {
            append(' ')
            append(separator)
            append(' ')
            append(if (isStartInclusive()) '[' else '(')
        }
        val suffix = if (isEndInclusive()) "]" else ")"
        val descriptors = mutableListOf<FoldingDescriptor>()
        descriptors += FoldingDescriptor(
            element.node,
            TextRange.create(operand.textRange.endOffset, start.textRange.startOffset),
            group,
            prefix
        )
        descriptors += FoldingDescriptor(
            element.node,
            TextRange.create(start.textRange.endOffset, end.textRange.startOffset),
            group,
            RANGE_COMMA_DELIMITER
        )
        val closingRange = if (textRange.endOffset > end.textRange.endOffset) {
            TextRange.create(end.textRange.endOffset, textRange.endOffset)
        } else {
            TextRange.create(end.textRange.endOffset, end.textRange.endOffset + 1)
        }
        val closingPlaceholder = if (textRange.endOffset > end.textRange.endOffset) {
            suffix
        } else {
            suffix + document.getText(TextRange.create(end.textRange.endOffset, end.textRange.endOffset + 1))
        }
        descriptors += FoldingDescriptor(element.node, closingRange, group, closingPlaceholder)
        if (start.supportsFoldRegions(document, this)) {
            descriptors += start.buildFoldRegions(start.element, document, this).toList()
        }
        if (end.supportsFoldRegions(document, this)) {
            descriptors += end.buildFoldRegions(end.element, document, this).toList()
        }
        return descriptors.toTypedArray()
    }

    companion object {
        const val RANGE_COMMA_DELIMITER: String = ", "
        const val RANGE_IN_SEPARATOR: String = "in"
        private val SUPPORTED_OVERLAPPED_SYMBOLS: Set<String> = setOf(" ", "&", "|", "(", ")")
    }
}
