package com.intellij.advancedExpressionFolding.processor.expression

import com.intellij.advancedExpressionFolding.expression.Expression
import com.intellij.advancedExpressionFolding.expression.literal.NumberLiteral
import com.intellij.advancedExpressionFolding.expression.operation.collection.ArrayGet
import com.intellij.advancedExpressionFolding.processor.core.BaseExtension
import com.intellij.advancedExpressionFolding.processor.core.BuildExpressionExt
import com.intellij.advancedExpressionFolding.processor.util.PsiTreeUtilEx
import com.intellij.advancedExpressionFolding.settings.AdvancedExpressionFoldingSettings
import com.intellij.openapi.editor.Document
import com.intellij.psi.PsiArrayAccessExpression
import com.intellij.psi.PsiAssignmentExpression
import com.intellij.psi.PsiBinaryExpression
import com.intellij.psi.impl.source.tree.java.PsiAssignmentExpressionImpl

object PsiArrayAccessExpressionExt : BaseExtension() {

    fun getArrayAccessExpression(element: PsiArrayAccessExpression, document: Document): Expression? {
        val index = element.indexExpression
        val settings = AdvancedExpressionFoldingSettings.getInstance()
        val isLeftSideAssignment = element.parent is PsiAssignmentExpression &&
            (element.parent as PsiAssignmentExpressionImpl).lExpression == element
        if (!isLeftSideAssignment && index != null && settings.state.getExpressionsCollapse) {
            val arrayExpression = BuildExpressionExt.getAnyExpression(element.arrayExpression, document)
            if (index is PsiBinaryExpression) {
                val position: NumberLiteral? = PsiTreeUtilEx.getSlicePosition(element, arrayExpression, index, document)
                if (position != null && position.number == -1) {
                    return ArrayGet(element, element.textRange, arrayExpression)
                }
            }
        }
        return null
    }
}
