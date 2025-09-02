package com.intellij.advancedExpressionFolding.processor.expression

import com.intellij.advancedExpressionFolding.expression.Expression
import com.intellij.advancedExpressionFolding.expression.literal.NumberLiteral
import com.intellij.advancedExpressionFolding.expression.operation.collection.ArrayGet
import com.intellij.advancedExpressionFolding.processor.core.BaseExtension
import com.intellij.advancedExpressionFolding.processor.core.BuildExpressionExt
import com.intellij.advancedExpressionFolding.processor.util.Helper
import com.intellij.advancedExpressionFolding.settings.AdvancedExpressionFoldingSettings
import com.intellij.openapi.editor.Document
import com.intellij.psi.PsiArrayAccessExpression
import com.intellij.psi.PsiAssignmentExpression
import com.intellij.psi.PsiBinaryExpression
import com.intellij.psi.PsiExpression
import com.intellij.psi.impl.source.tree.java.PsiAssignmentExpressionImpl
import org.jetbrains.annotations.NotNull
import org.jetbrains.annotations.Nullable

object PsiArrayAccessExpressionExt : BaseExtension() {
    @JvmStatic
    @Nullable
    fun getArrayAccessExpression(@NotNull element: PsiArrayAccessExpression, @NotNull document: Document): Expression? {
        val index = element.getIndexExpression()
        val settings = AdvancedExpressionFoldingSettings.getInstance()
        if (!(element.getParent() is PsiAssignmentExpression && (element.getParent() as PsiAssignmentExpressionImpl).getLExpression() == element) && index != null && settings.getState().getGetExpressionsCollapse()) {
            val arrayExpression = BuildExpressionExt.getAnyExpression(element.getArrayExpression(), document)
            if (index is PsiBinaryExpression) {
                val position: NumberLiteral? = Helper.getSlicePosition(element, arrayExpression, index, document)
                if (position != null && position.getNumber() == -1) {
                    return ArrayGet(element, element.getTextRange(), arrayExpression)
                }
            }
        }
        return null
    }
}
