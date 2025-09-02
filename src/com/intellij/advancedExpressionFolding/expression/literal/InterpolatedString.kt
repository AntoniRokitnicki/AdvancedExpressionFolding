package com.intellij.advancedExpressionFolding.expression.literal

import com.intellij.advancedExpressionFolding.expression.Expression
import com.intellij.advancedExpressionFolding.expression.operation.basic.Variable
import com.intellij.lang.folding.FoldingDescriptor
import com.intellij.openapi.editor.Document
import com.intellij.openapi.editor.FoldingGroup
import com.intellij.openapi.util.TextRange
import com.intellij.psi.PsiElement
import java.util.ArrayList
import java.util.HashSet


class InterpolatedString(
    element: PsiElement,
    textRange: TextRange,
    operands: List<Expression>
) : Expression(element, textRange) {
    private var operands: List<Expression> = operands

    fun getOperands(): List<Expression> {
        return operands
    }

    override fun supportsFoldRegions(document: Document, parent: Expression?): Boolean {
        return true
    }

    companion object {
        val OVERFLOW_CHARACTERS: HashSet<String> = object : HashSet<String>() {
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

    override fun buildFoldRegions(
        element: PsiElement,
        document: Document,
        parent: Expression?
    ): Array<FoldingDescriptor> {
        return buildFoldRegions(element, document, parent, null, null, null)
    }

    fun buildFoldRegions(
        element: PsiElement,
        document: Document,
        parent: Expression?,
        overflowGroup: FoldingGroup?,
        overflowLeftPlaceholder: String?,
        overflowRightPlaceholder: String?
    ): Array<FoldingDescriptor> {
        val first = operands.get(0)
        val last = operands.get(operands.size - 1)
        val group = if (overflowGroup != null) {
            overflowGroup
        } else {
            FoldingGroup.newGroup(
                InterpolatedString::class.java.getName() + if (isHighlighted()) Expression.HIGHLIGHTED_GROUP_POSTFIX else ""
            )
        }
        val descriptors = ArrayList<FoldingDescriptor>()
        val buf = arrayOf("")
        if (first !is CharSequenceLiteral) {
            val startOffset = first.getTextRange().getStartOffset()
            if (startOffset > 0) {
                val overflowLeftRange = TextRange.create(startOffset - 1, startOffset)
                val overflowLeftText = document.getText(overflowLeftRange)
                if (OVERFLOW_CHARACTERS.contains(overflowLeftText)) {
                    val overflowText = overflowLeftPlaceholder ?: overflowLeftText
                    if (first is Variable) {
                        descriptors.add(
                            FoldingDescriptor(
                                element.getNode(),
                                overflowLeftRange,
                                group,
                                overflowText + "\"$"
                            )
                        )
                    } else {
                        descriptors.add(
                            FoldingDescriptor(
                                element.getNode(),
                                overflowLeftRange,
                                group,
                                overflowText + "\"${"
                            )
                        )
                        buf[0] = "}"
                    }
                } else {
                    val p: String
                    if (first is Variable) {
                        p = "\"$" + first.getElement().getText()
                    } else {
                        p = "\"${" + first.getElement().getText() + "}"
                    }
                    descriptors.add(
                        FoldingDescriptor(
                            element.getNode(),
                            TextRange.create(startOffset, first.getTextRange().getEndOffset()),
                            group,
                            p
                        )
                    )
                }
            }
        } else if (first is CharacterLiteral) {
            descriptors.add(
                FoldingDescriptor(
                    element.getNode(),
                    TextRange.create(
                        first.getTextRange().getStartOffset(),
                        first.getTextRange().getStartOffset() + 1
                    ),
                    group,
                    "\""
                )
            )
        }
        for (i in 0 until operands.size - 1) {
            val s = if (operands.get(i) is CharSequenceLiteral) {
                operands.get(i).getTextRange().getEndOffset() - 1
            } else {
                operands.get(i).getTextRange().getEndOffset()
            }
            val e = if (operands.get(i + 1) is CharSequenceLiteral) {
                operands.get(i + 1).getTextRange().getStartOffset() + 1
            } else {
                operands.get(i + 1).getTextRange().getStartOffset()
            }
            val sI = StringBuilder().append(buf[0])
            if (operands.get(i + 1) !is CharSequenceLiteral) {
                sI.append('$')
            }
            if (operands.get(i + 1) !is Variable && operands.get(i + 1) !is CharSequenceLiteral) {
                sI.append('{')
                buf[0] = "}"
            } else {
                buf[0] = ""
            }
            descriptors.add(
                FoldingDescriptor(
                    element.getNode(),
                    TextRange.create(s, e),
                    group,
                    sI.toString()
                )
            )
        }
        if (last !is CharSequenceLiteral && document.getTextLength() > last.getTextRange().getEndOffset() + 1) {
            val overflowRightRange = TextRange.create(
                last.getTextRange().getEndOffset(),
                last.getTextRange().getEndOffset() + 1
            )
            val beforeLast = operands.get(operands.size - 2)
            val s = if (beforeLast is CharSequenceLiteral) {
                beforeLast.getTextRange().getEndOffset() - 1
            } else {
                beforeLast.getTextRange().getEndOffset()
            }
            val e = last.getTextRange().getStartOffset()
            val overflowRightText = document.getText(overflowRightRange)
            if (OVERFLOW_CHARACTERS.contains(overflowRightText)) {
                val overflowText = overflowRightPlaceholder ?: overflowRightText
                if (last is Variable) {
                    descriptors.add(
                        FoldingDescriptor(
                            element.getNode(),
                            TextRange.create(s, e),
                            group,
                            "\$"
                        )
                    )
                    descriptors.add(
                        FoldingDescriptor(
                            element.getNode(),
                            overflowRightRange,
                            group,
                            "\"" + overflowText
                        )
                    )
                } else {
                    descriptors.add(
                        FoldingDescriptor(
                            element.getNode(),
                            TextRange.create(s, e),
                            group,
                            "${"
                        )
                    )
                    descriptors.add(
                        FoldingDescriptor(
                            element.getNode(),
                            overflowRightRange,
                            group,
                            "}\"" + overflowText
                        )
                    )
                }
            } else {
                descriptors.add(
                    FoldingDescriptor(
                        element.getNode(),
                        TextRange.create(
                            last.getTextRange().getStartOffset(),
                            last.getTextRange().getEndOffset()
                        ),
                        group,
                        last.getElement().getText() + buf[0] + "\""
                    )
                )
            }
        } else if (last is CharacterLiteral) {
            descriptors.add(
                FoldingDescriptor(
                    element.getNode(),
                    TextRange.create(
                        last.getTextRange().getEndOffset() - 1,
                        last.getTextRange().getEndOffset()
                    ),
                    group,
                    "\""
                )
            )
        }
        for (operand in operands) {
            if (operand.supportsFoldRegions(document, this)) {
                for (d in operand.buildFoldRegions(operand.getElement(), document, this)) {
                    descriptors.add(d)
                }
            }
        }
        return descriptors.toArray(arrayOfNulls<FoldingDescriptor>(0))
    }

    override fun isRightOverflow(): Boolean {
        return operands.get(operands.size - 1) !is CharSequenceLiteral
    }

    override fun isHighlighted(): Boolean {
        return operands.stream().filter { o -> o is CharSequenceLiteral }.count().toInt() == operands.size &&
            operands.get(0) is StringLiteral &&
            operands.get(operands.size - 1) is StringLiteral
    }

    override fun isLeftOverflow(): Boolean {
        return operands.get(0) !is CharSequenceLiteral
    }
}

