package com.intellij.advancedExpressionFolding.extension

import com.intellij.advancedExpressionFolding.expression.Expression
import com.intellij.advancedExpressionFolding.expression.IGetter
import com.intellij.advancedExpressionFolding.expression.INameable
import com.intellij.advancedExpressionFolding.expression.Variable
import com.intellij.advancedExpressionFolding.expression.custom.BuilderShiftExpression
import com.intellij.openapi.editor.Document
import com.intellij.psi.PsiAssignmentExpression
import com.intellij.psi.PsiReferenceExpression

object FieldShiftExt : BaseExtension() {

    @JvmStatic
    fun createExpression(element: PsiAssignmentExpression, document: Document?): Expression? {
        fieldShiftOld.takeIf {
            it || fieldShift
        } ?: return null

        val right = element.rExpression ?: return null

        val leftText =
            element.lExpression.asInstance<PsiReferenceExpression>()?.referenceNameElement?.text ?: return null

        val rightExp = BuildExpressionExt.getAnyExpression(right, document)
        val rightText = if (rightExp is INameable) {
            rightExp.name
        } else {
            right.asInstance<PsiReferenceExpression>()?.referenceNameElement?.text
        }

        if (leftText == rightText) {
            if (rightExp is IGetter) {
                return BuilderShiftExpression(right, rightExp.getterTextRange, "<<")
            }
            if (rightExp is Variable) {
                return BuilderShiftExpression(right, rightExp.textRange, "<<")
            }
            right.asInstance<PsiReferenceExpression>()?.referenceNameElement?.let {
                return BuilderShiftExpression(it, it.textRange, "<<")
            }
        }
        return null
    }

}
