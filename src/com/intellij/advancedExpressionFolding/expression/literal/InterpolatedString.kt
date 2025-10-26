package com.intellij.advancedExpressionFolding.expression.literal

import com.intellij.advancedExpressionFolding.expression.Expression
import com.intellij.advancedExpressionFolding.expression.operation.basic.Variable
import com.intellij.lang.folding.FoldingDescriptor
import com.intellij.openapi.editor.Document
import com.intellij.openapi.editor.FoldingGroup
import com.intellij.openapi.util.TextRange
import com.intellij.psi.PsiElement

class InterpolatedString(
    element: PsiElement,
    textRange: TextRange,
    private val operands: List<Expression>
) : Expression(element, textRange) {

    override fun supportsFoldRegions(document: Document, parent: Expression?): Boolean = true

    override fun buildFoldRegions(
        element: PsiElement,
        document: Document,
        parent: Expression?
    ): Array<FoldingDescriptor> {
        return buildFoldRegions(element, document, parent, null, null, null)
    }

    override fun buildFoldRegions(
        element: PsiElement,
        document: Document,
        parent: Expression?,
        overflowGroup: FoldingGroup?,
        overflowLeftPlaceholder: String?,
        overflowRightPlaceholder: String?
    ): Array<FoldingDescriptor> {
        val first = operands.first()
        val last = operands.last()
        val firstIsQuoteCharacter = first is CharacterLiteral && first.character == '"'
        val lastIsQuoteCharacter = last is CharacterLiteral && last.character == '"'
        val group = overflowGroup ?: FoldingGroup.newGroup(
            InterpolatedString::class.java.name + if (isHighlighted()) Expression.HIGHLIGHTED_GROUP_POSTFIX else ""
        )
        val descriptors = mutableListOf<FoldingDescriptor>()
        var suffix = ""
        if (firstIsQuoteCharacter) {
            descriptors += FoldingDescriptor(
                element.node,
                TextRange.create(first.textRange.startOffset, first.textRange.endOffset),
                group,
                "\\\""
            )
        } else if (first !is CharSequenceLiteral) {
            val startOffset = first.textRange.startOffset
            if (startOffset > 0) {
                val overflowLeftRange = TextRange.create(startOffset - 1, startOffset)
                val overflowLeftText = document.getText(overflowLeftRange)
                if (OVERFLOW_CHARACTERS.contains(overflowLeftText)) {
                    val overflowText = overflowLeftPlaceholder ?: overflowLeftText
                    if (first is Variable) {
                        descriptors += FoldingDescriptor(
                            element.node,
                            overflowLeftRange,
                            group,
                            overflowText + "\"${'$'}"
                        )
                    } else {
                        descriptors += FoldingDescriptor(
                            element.node,
                            overflowLeftRange,
                            group,
                            overflowText + "\"${'$'}{"
                        )
                        suffix = "}"
                    }
                } else {
                    val placeholder = if (first is Variable) {
                        "\"${'$'}" + first.element.text
                    } else {
                        "\"${'$'}{" + first.element.text + "}"
                    }
                    descriptors += FoldingDescriptor(
                        element.node,
                        TextRange.create(startOffset, first.textRange.endOffset),
                        group,
                        placeholder
                    )
                }
            }
        }
        for (i in 0 until operands.size - 1) {
            val start = if (operands[i] is CharSequenceLiteral) {
                operands[i].textRange.endOffset - 1
            } else {
                operands[i].textRange.endOffset
            }
            val end = if (operands[i + 1] is CharSequenceLiteral) {
                operands[i + 1].textRange.startOffset + 1
            } else {
                operands[i + 1].textRange.startOffset
            }
            val next = operands[i + 1]
            val nextIsQuoteCharacter = next is CharacterLiteral && next.character == '"'
            val placeholder = StringBuilder().append(suffix)
            if (next !is CharSequenceLiteral && !nextIsQuoteCharacter) {
                placeholder.append('$')
            }
            if (
                next !is Variable &&
                next !is CharSequenceLiteral &&
                !nextIsQuoteCharacter
            ) {
                placeholder.append('{')
                suffix = "}"
            } else {
                suffix = ""
            }
            descriptors += FoldingDescriptor(
                element.node,
                TextRange.create(start, end),
                group,
                placeholder.toString()
            )
        }
        if (lastIsQuoteCharacter) {
            descriptors += FoldingDescriptor(
                element.node,
                TextRange.create(last.textRange.startOffset, last.textRange.endOffset),
                group,
                "\\\""
            )
        } else if (last !is CharSequenceLiteral && document.textLength > last.textRange.endOffset + 1) {
            val overflowRightRange = TextRange.create(last.textRange.endOffset, last.textRange.endOffset + 1)
            val beforeLast = operands[operands.size - 2]
            val start = if (beforeLast is CharSequenceLiteral) {
                beforeLast.textRange.endOffset - 1
            } else {
                beforeLast.textRange.endOffset
            }
            val end = last.textRange.startOffset
            val overflowRightText = document.getText(overflowRightRange)
            if (OVERFLOW_CHARACTERS.contains(overflowRightText)) {
                val overflowText = overflowRightPlaceholder ?: overflowRightText
                if (last is Variable) {
                    descriptors += FoldingDescriptor(
                        element.node,
                        TextRange.create(start, end),
                        group,
                        "$"
                    )
                    descriptors += FoldingDescriptor(
                        element.node,
                        overflowRightRange,
                        group,
                        "\"" + overflowText
                    )
                } else {
                    descriptors += FoldingDescriptor(
                        element.node,
                        TextRange.create(start, end),
                        group,
                        "${'$'}{"
                    )
                    descriptors += FoldingDescriptor(
                        element.node,
                        overflowRightRange,
                        group,
                        "}\"" + overflowText
                    )
                }
            } else {
                descriptors += FoldingDescriptor(
                    element.node,
                    TextRange.create(last.textRange.startOffset, last.textRange.endOffset),
                    group,
                    last.element.text + suffix + "\""
                )
            }
        }
        for (operand in operands) {
            if (operand.supportsFoldRegions(document, this)) {
                descriptors += operand.buildFoldRegions(operand.element, document, this).toList()
            }
        }
        return descriptors.toTypedArray()
    }

    override fun isRightOverflow(): Boolean = operands.last() !is CharSequenceLiteral

    override fun isLeftOverflow(): Boolean = operands.first() !is CharSequenceLiteral

    override fun isHighlighted(): Boolean {
        val literalCount = operands.count { it is CharSequenceLiteral }
        return literalCount == operands.size && operands.first() is StringLiteral && operands.last() is StringLiteral
    }

    companion object {
        val OVERFLOW_CHARACTERS: Set<String> = setOf(".", ";", ",", ")", "(", " ")
    }
}
