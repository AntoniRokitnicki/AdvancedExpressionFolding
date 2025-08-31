package com.intellij.advancedExpressionFolding.expression.literal

import com.intellij.advancedExpressionFolding.expression.Expression
import com.intellij.advancedExpressionFolding.expression.math.ArithmeticExpression
import com.intellij.lang.folding.FoldingDescriptor
import com.intellij.openapi.editor.Document
import com.intellij.openapi.editor.FoldingGroup
import com.intellij.openapi.util.TextRange
import com.intellij.psi.PsiElement
import java.util.ArrayList

class NumberLiteral(
    element: PsiElement,
    textRange: TextRange,
    val numberTextRange: TextRange?,
    val number: Number,
    private val convert: Boolean
) : Expression(element, textRange), ArithmeticExpression {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || javaClass != other.javaClass) return false

        other as NumberLiteral

        return number.toString() == other.number.toString()
    }

    override fun hashCode(): Int = number.toString().hashCode()

    override fun supportsFoldRegions(document: Document, parent: Expression?): Boolean {
        return isHighlighted()
    }

    override fun buildFoldRegions(element: PsiElement, document: Document, parent: Expression?): Array<FoldingDescriptor> {
        val descriptors = ArrayList<FoldingDescriptor>()
        if (numberTextRange != null) {
            val group = FoldingGroup.newGroup(NumberLiteral::class.java.name + HIGHLIGHTED_GROUP_POSTFIX)
            if (textRange.startOffset < numberTextRange.startOffset) {
                descriptors.add(
                    FoldingDescriptor(
                        element.node,
                        TextRange.create(textRange.startOffset, numberTextRange.startOffset),
                        group,
                        ""
                    )
                )
            }
            if (convert) {
                descriptors.add(
                    FoldingDescriptor(
                        element.node,
                        numberTextRange,
                        group,
                        if (number is Float) {
                            "${number.toFloat()}f"
                        } else if (number is Double) {
                            number.toDouble().toString()
                        } else {
                            number.toString()
                        }
                    )
                )
            }
            if (numberTextRange.endOffset < textRange.endOffset) {
                descriptors.add(
                    FoldingDescriptor(
                        element.node,
                        TextRange.create(numberTextRange.endOffset, textRange.endOffset),
                        group,
                        ""
                    )
                )
            }
        }
        return descriptors.toTypedArray()
    }

    override fun isHighlighted(): Boolean {
        return numberTextRange != null &&
            numberTextRange.startOffset >= textRange.startOffset &&
            numberTextRange.endOffset <= textRange.endOffset
    }
}

