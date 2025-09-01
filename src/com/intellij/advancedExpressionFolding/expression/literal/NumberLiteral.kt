package com.intellij.advancedExpressionFolding.expression.literal

import com.intellij.advancedExpressionFolding.expression.Expression
import com.intellij.advancedExpressionFolding.expression.math.ArithmeticExpression
import com.intellij.lang.folding.FoldingDescriptor
import com.intellij.openapi.editor.Document
import com.intellij.openapi.editor.FoldingGroup
import com.intellij.openapi.util.TextRange
import com.intellij.psi.PsiElement
import org.jetbrains.annotations.NotNull
import org.jetbrains.annotations.Nullable

import java.util.ArrayList

class NumberLiteral : Expression, ArithmeticExpression {
    private var number: Number
    private val convert: Boolean
    private var numberTextRange: TextRange?

    constructor(element: PsiElement, textRange: TextRange, numberTextRange: TextRange?, number: Number, convert: Boolean) : super(element, textRange) {
        this.numberTextRange = numberTextRange
        this.number = number
        this.convert = convert
    }

    fun getNumber(): Number {
        return number
    }

    override fun equals(o: Any?): Boolean {
        if (this === o) return true
        if (o == null || this.javaClass != o.javaClass) return false

        val numberLiteral = o as NumberLiteral

        return number.toString().equals(numberLiteral.number.toString())
    }

    override fun hashCode(): Int {
        return number.toString().hashCode()
    }

    override fun supportsFoldRegions(document: Document, parent: Expression?): Boolean {
        return isHighlighted()
    }

    override fun buildFoldRegions(element: PsiElement, document: Document, parent: Expression?): Array<FoldingDescriptor> {
        val descriptors = ArrayList<FoldingDescriptor>()
        if (numberTextRange != null) {
            val group = FoldingGroup.newGroup(NumberLiteral::class.java.getName() + HIGHLIGHTED_GROUP_POSTFIX)
            if (textRange.getStartOffset() < numberTextRange!!.getStartOffset()) {
                descriptors.add(
                    FoldingDescriptor(
                        element.getNode(),
                        TextRange.create(textRange.getStartOffset(), numberTextRange!!.getStartOffset()),
                        group,
                        ""
                    )
                )
            }
            if (convert) {
                descriptors.add(
                    FoldingDescriptor(
                        element.getNode(),
                        numberTextRange,
                        group,
                        if (number is Float)
                            java.lang.Float.toString(number.toFloat()) + "f"
                        else if (number is Double)
                            java.lang.Double.toString(number.toDouble())
                        else
                            number.toString()
                    )
                )
            }
            if (numberTextRange!!.getEndOffset() < textRange.getEndOffset()) {
                descriptors.add(
                    FoldingDescriptor(
                        element.getNode(),
                        TextRange.create(numberTextRange!!.getEndOffset(), textRange.getEndOffset()),
                        group,
                        ""
                    )
                )
            }
        }
        return descriptors.toArray(EMPTY_ARRAY)
    }

    override fun isHighlighted(): Boolean {
        return numberTextRange != null && numberTextRange!!.getStartOffset() >= textRange.getStartOffset() && numberTextRange!!.getEndOffset() <= textRange.getEndOffset()
    }

    fun getNumberTextRange(): TextRange? {
        return numberTextRange
    }
}
