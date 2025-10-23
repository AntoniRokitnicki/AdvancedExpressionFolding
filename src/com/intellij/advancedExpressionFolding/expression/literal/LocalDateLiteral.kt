package com.intellij.advancedExpressionFolding.expression.literal

import com.intellij.advancedExpressionFolding.expression.Expression
import com.intellij.advancedExpressionFolding.settings.AdvancedExpressionFoldingSettings
import com.intellij.advancedExpressionFolding.settings.IDateOperationsState
import com.intellij.lang.folding.FoldingDescriptor
import com.intellij.openapi.editor.Document
import com.intellij.openapi.editor.FoldingGroup
import com.intellij.openapi.util.TextRange
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiLiteralExpression

class LocalDateLiteral(
    element: PsiElement,
    textRange: TextRange,
    private val year: PsiLiteralExpression,
    private val month: PsiLiteralExpression,
    private val day: PsiLiteralExpression,
    private val state: AdvancedExpressionFoldingSettings.State = AdvancedExpressionFoldingSettings.getInstance().state,
) : Expression(element, textRange), IDateOperationsState by state {

    override fun supportsFoldRegions(document: Document, parent: Expression?): Boolean = true

    override fun buildFoldRegions(
        element: PsiElement,
        document: Document,
        parent: Expression?
    ): Array<FoldingDescriptor> {
        val group = FoldingGroup.newGroup(ListLiteral::class.java.name)
        val descriptors = mutableListOf<FoldingDescriptor>()
        descriptors += object : FoldingDescriptor(
            element.node,
            TextRange.create(textRange.startOffset, year.textRange.startOffset),
            group
        ) {
            override fun getPlaceholderText(): String = ""
        }

        val usePostfix = localDateLiteralPostfixCollapse
        val dateSep = DATE_SEPARATOR
        val yearPostfix = if (usePostfix) YEAR_POSTFIX else ""
        val monthPostfix = if (usePostfix) MONTH_POSTFIX else ""
        val dayPostfix = if (usePostfix) DAY_POSTFIX else ""

        descriptors += object : FoldingDescriptor(
            element.node,
            TextRange.create(year.textRange.endOffset, month.textRange.startOffset),
            group
        ) {
            override fun getPlaceholderText(): String {
                return if (month.textLength == 1) {
                    yearPostfix + dateSep + "0"
                } else {
                    yearPostfix + dateSep
                }
            }
        }

        descriptors += object : FoldingDescriptor(
            element.node,
            TextRange.create(month.textRange.endOffset, day.textRange.startOffset),
            group
        ) {
            override fun getPlaceholderText(): String {
                return if (day.textLength == 1) {
                    monthPostfix + dateSep + "0"
                } else {
                    monthPostfix + dateSep
                }
            }
        }

        descriptors += object : FoldingDescriptor(
            element.node,
            TextRange.create(day.textRange.endOffset, textRange.endOffset),
            group
        ) {
            override fun getPlaceholderText(): String = dayPostfix
        }

        return descriptors.toTypedArray()
    }

    companion object {
        const val DATE_SEPARATOR: String = "-"
        const val YEAR_POSTFIX: String = "Y"
        const val MONTH_POSTFIX: String = "M"
        const val DAY_POSTFIX: String = "D"
    }
}
