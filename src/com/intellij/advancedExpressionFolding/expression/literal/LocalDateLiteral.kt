package com.intellij.advancedExpressionFolding.expression.literal

import com.intellij.advancedExpressionFolding.expression.Expression
import com.intellij.advancedExpressionFolding.settings.AdvancedExpressionFoldingSettings
import com.intellij.lang.folding.FoldingDescriptor
import com.intellij.openapi.editor.Document
import com.intellij.openapi.editor.FoldingGroup
import com.intellij.openapi.util.TextRange
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiLiteralExpression
import java.util.ArrayList

class LocalDateLiteral(
    element: PsiElement,
    textRange: TextRange,
    year: PsiLiteralExpression,
    month: PsiLiteralExpression,
    day: PsiLiteralExpression
) : Expression(element, textRange) {
    companion object {
        const val DATE_SEPARATOR: String = "-"
        const val YEAR_POSTFIX: String = "Y"
        const val MONTH_POSTFIX: String = "M"
        const val DAY_POSTFIX: String = "D"
    }

    private var year: PsiLiteralExpression = year
    private var month: PsiLiteralExpression = month
    private var day: PsiLiteralExpression = day

    fun getYear(): PsiLiteralExpression {
        return year
    }

    fun getMonth(): PsiLiteralExpression {
        return month
    }

    fun getDay(): PsiLiteralExpression {
        return day
    }

    override fun supportsFoldRegions(document: Document, parent: Expression?): Boolean {
        return true
    }

    override fun buildFoldRegions(
        element: PsiElement,
        document: Document,
        parent: Expression?
    ): Array<FoldingDescriptor> {
        val group = FoldingGroup.newGroup(ListLiteral::class.java.getName())
        val descriptors = ArrayList<FoldingDescriptor>()
        descriptors.add(object : FoldingDescriptor(
            element.getNode(),
            TextRange.create(textRange.getStartOffset(), year.getTextRange().getStartOffset()),
            group
        ) {
            override fun getPlaceholderText(): String {
                return ""
            }
        })

        val usePostfix = AdvancedExpressionFoldingSettings.getInstance().getState().getLocalDateLiteralPostfixCollapse()

        val dateSep = DATE_SEPARATOR
        val yearPostfix = if (usePostfix) YEAR_POSTFIX else ""
        val monthPostfix = if (usePostfix) MONTH_POSTFIX else ""
        val dayPostfix = if (usePostfix) DAY_POSTFIX else ""

        descriptors.add(object : FoldingDescriptor(
            element.getNode(),
            TextRange.create(year.getTextRange().getEndOffset(), month.getTextRange().getStartOffset()),
            group
        ) {
            override fun getPlaceholderText(): String {
                return if (month.getTextLength() == 1) {
                    yearPostfix + dateSep + "0"
                } else {
                    yearPostfix + dateSep
                }
            }
        })

        descriptors.add(object : FoldingDescriptor(
            element.getNode(),
            TextRange.create(month.getTextRange().getEndOffset(), day.getTextRange().getStartOffset()),
            group
        ) {
            override fun getPlaceholderText(): String {
                return if (day.getTextLength() == 1) {
                    monthPostfix + dateSep + "0"
                } else {
                    monthPostfix + dateSep
                }
            }
        })

        descriptors.add(object : FoldingDescriptor(
            element.getNode(),
            TextRange.create(day.getTextRange().getEndOffset(), textRange.getEndOffset()),
            group
        ) {
            override fun getPlaceholderText(): String {
                return dayPostfix + ""
            }
        })
        return descriptors.toArray(arrayOfNulls<FoldingDescriptor>(0))
    }
}

