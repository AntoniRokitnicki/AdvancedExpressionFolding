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
    numberTextRange: TextRange?,
    number: Number,
    convert: Boolean
) : Expression(element, textRange), ArithmeticExpression {
    private var number: Number = number
    private var convert: Boolean = convert
    private var numberTextRange: TextRange? = numberTextRange

    fun getNumber(): Number {
        return number
    }

    override fun equals(o: Any?): Boolean {
        if (this === o) return true
        if (o == null || javaClass != o.javaClass) return false
        val numberLiteral = o as NumberLiteral
        return number.toString() == numberLiteral.number.toString()
    }

    override fun hashCode(): Int {
        return number.toString().hashCode()
    }

    override fun supportsFoldRegions(document: Document, parent: Expression?): Boolean {
        return isHighlighted()
    }

    override fun buildFoldRegions(
        element: PsiElement,
        document: Document,
        parent: Expression?
    ): Array<FoldingDescriptor> {
        val descriptors = ArrayList<FoldingDescriptor>()
        if (numberTextRange != null) {
            val group = FoldingGroup.newGroup(NumberLiteral::class.java.getName() + HIGHLIGHTED_GROUP_POSTFIX)
            if (getTextRange().getStartOffset() < numberTextRange!!.getStartOffset()) {
                descriptors.add(
                    FoldingDescriptor(
                        element.getNode(),
                        TextRange.create(getTextRange().getStartOffset(), numberTextRange!!.getStartOffset()),
                        group,
                        ""
                    )
                )
            }
            if (convert) {
                descriptors.add(
                    FoldingDescriptor(
                        element.getNode(),
                        numberTextRange!!,
                        group,
                        if (number is Float) {
                            java.lang.Float.toString(number.toFloat()) + "f"
                        } else if (number is Double) {
                            java.lang.Double.toString(number.toDouble())
                        } else {
                            number.toString()
                        }
                    )
                )
            }
            if (numberTextRange!!.getEndOffset() < getTextRange().getEndOffset()) {
                descriptors.add(
                    FoldingDescriptor(
                        element.getNode(),
                        TextRange.create(numberTextRange!!.getEndOffset(), getTextRange().getEndOffset()),
                        group,
                        ""
                    )
                )
            }
        }
        return descriptors.toArray(arrayOfNulls<FoldingDescriptor>(0))
    }

    override fun isHighlighted(): Boolean {
        return numberTextRange != null && numberTextRange!!.getStartOffset() >= getTextRange().getStartOffset() && numberTextRange!!.getEndOffset() <= getTextRange().getEndOffset()
    }

    fun getNumberTextRange(): TextRange? {
        return numberTextRange
    }
}

