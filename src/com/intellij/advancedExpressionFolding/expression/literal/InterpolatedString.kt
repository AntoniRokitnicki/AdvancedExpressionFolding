package com.intellij.advancedExpressionFolding.expression.literal

import com.intellij.advancedExpressionFolding.expression.Expression
import com.intellij.advancedExpressionFolding.expression.operation.basic.Variable
import com.intellij.lang.folding.FoldingDescriptor
import com.intellij.openapi.editor.Document
import com.intellij.openapi.editor.FoldingGroup
import com.intellij.openapi.util.TextRange
import com.intellij.psi.PsiElement
import java.util.ArrayList
import java.util.Collections
import java.util.HashSet
import java.util.Set

class InterpolatedString(
    element: PsiElement,
    textRange: TextRange,
    private val operands: List<Expression>
) : Expression(element, textRange) {

    override fun supportsFoldRegions(document: Document, parent: Expression?) = true

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
        val first = operands[0]
        val last = operands[operands.size - 1]
        val group = overflowGroup ?: FoldingGroup.newGroup(
            InterpolatedString::class.java.name +
                if (isHighlighted()) Expression.HIGHLIGHTED_GROUP_POSTFIX else ""
        )
        val descriptors = ArrayList<FoldingDescriptor>()
        val buf = arrayOf("")
        if (first !is CharSequenceLiteral) {
            val startOffset = first.textRange.startOffset
            if (startOffset > 0) {
                val overflowLeftRange = TextRange.create(startOffset - 1, startOffset)
                val overflowLeftText = document.getText(overflowLeftRange)
                if (OVERFLOW_CHARACTERS.contains(overflowLeftText)) {
                    val overflowText = overflowLeftPlaceholder ?: overflowLeftText
                    if (first is Variable) {
                        descriptors.add(
                            FoldingDescriptor(
                                element.node,
                                overflowLeftRange,
                                group,
                                overflowText + "\"$"
                            )
                        )
                    } else {
                        descriptors.add(
                            FoldingDescriptor(
                                element.node,
                                overflowLeftRange,
                                group,
                                overflowText + "\"\${",
                            )
                        )
                        buf[0] = "}"
                    }
                } else {
                    val p = if (first is Variable) {
                        "\"$" + first.element.text // TODO no-format: not sure
                    } else {
                        "\"\${" + first.element.text + "}" // TODO no-format: not sure
                    }
                    descriptors.add(
                        FoldingDescriptor(
                            element.node,
                            TextRange.create(startOffset, first.textRange.endOffset),
                            group,
                            p
                        )
                    )
                }
            }
        } else if (first is CharacterLiteral) {
            descriptors.add(
                FoldingDescriptor(
                    element.node,
                    TextRange.create(first.textRange.startOffset, first.textRange.startOffset + 1),
                    group,
                    "\""
                )
            )
        }
        for (i in 0 until operands.size - 1) {
            val s = if (operands[i] is CharSequenceLiteral) {
                operands[i].textRange.endOffset - 1
            } else {
                operands[i].textRange.endOffset
            }
            val e = if (operands[i + 1] is CharSequenceLiteral) {
                operands[i + 1].textRange.startOffset + 1
            } else {
                operands[i + 1].textRange.startOffset
            }
            val sI = StringBuilder().append(buf[0])
            if (operands[i + 1] !is CharSequenceLiteral) {
                sI.append('$')
            }
            if (operands[i + 1] !is Variable && operands[i + 1] !is CharSequenceLiteral) {
                sI.append('{')
                buf[0] = "}"
            } else {
                buf[0] = ""
            }
            descriptors.add(
                FoldingDescriptor(
                    element.node,
                    TextRange.create(s, e),
                    group,
                    sI.toString()
                )
            )
        }
        if (last !is CharSequenceLiteral && document.textLength > last.textRange.endOffset + 1) {
            val overflowRightRange = TextRange.create(last.textRange.endOffset, last.textRange.endOffset + 1)
            val beforeLast = operands[operands.size - 2]
            val s = if (beforeLast is CharSequenceLiteral) {
                beforeLast.textRange.endOffset - 1
            } else {
                beforeLast.textRange.endOffset
            }
            val e = last.textRange.startOffset
            val overflowRightText = document.getText(overflowRightRange)
            if (OVERFLOW_CHARACTERS.contains(overflowRightText)) {
                val overflowText = overflowRightPlaceholder ?: overflowRightText
                if (last is Variable) {
                    descriptors.add(
                        FoldingDescriptor(
                            element.node,
                            TextRange.create(s, e),
                            group,
                            "$"
                        )
                    )
                    descriptors.add(
                        FoldingDescriptor(
                            element.node,
                            overflowRightRange,
                            group,
                            "\"" + overflowText
                        )
                    )
                } else {
                    descriptors.add(
                        FoldingDescriptor(
                            element.node,
                            TextRange.create(s, e),
                            group,
                            "\${"
                        )
                    )
                    descriptors.add(
                        FoldingDescriptor(
                            element.node,
                            overflowRightRange,
                            group,
                            "}\"" + overflowText
                        )
                    )
                }
            } else {
                descriptors.add(
                    FoldingDescriptor(
                        element.node,
                        TextRange.create(last.textRange.startOffset, last.textRange.endOffset),
                        group,
                        last.element.text + buf[0] + "\"" /* TODO no-format: not sure */
                    )
                )
            }
        } else if (last is CharacterLiteral) {
            descriptors.add(
                FoldingDescriptor(
                    element.node,
                    TextRange.create(last.textRange.endOffset - 1, last.textRange.endOffset),
                    group,
                    "\""
                )
            )
        }
        for (operand in operands) {
            if (operand.supportsFoldRegions(document, this)) {
                Collections.addAll(
                    descriptors,
                    *operand.buildFoldRegions(operand.element, document, this)
                )
            }
        }
        return descriptors.toTypedArray()
    }

    override fun isRightOverflow(): Boolean {
        return operands[operands.size - 1] !is CharSequenceLiteral
    }

    override fun isHighlighted(): Boolean {
        return operands.stream().filter { it is CharSequenceLiteral }.count().toInt() == operands.size &&
            operands[0] is StringLiteral &&
            operands[operands.size - 1] is StringLiteral
    }

    override fun isLeftOverflow(): Boolean {
        return operands[0] !is CharSequenceLiteral
    }

    companion object {
        val OVERFLOW_CHARACTERS: MutableSet<String> = object : HashSet<String>() {
            init {
                add(".")
                add(";")
                add(",")
                add(")")
                add("(")
                add(" ")
            }
        }
    }
}

