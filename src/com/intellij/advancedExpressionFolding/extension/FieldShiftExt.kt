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
    fun createExpression(element: PsiAssignmentExpression, document: Document): Expression? {
        if (!fieldShift) {
            return null
        }
        val l = element.lExpression as? PsiReferenceExpression ?: return null
        val r= element.rExpression ?: return null
        val r2 = BuildExpressionExt.getAnyExpression(r, document)

        val rName = if (r2 is INameable) {
            r2.name
        } else {
            r.asInstance<PsiReferenceExpression>()?.referenceNameElement?.text
        }
        val lName = l.referenceNameElement?.text

        if (lName == rName && lName != null) {
            if (r2 is IGetter) {
                return BuilderShiftExpression(r, r2.getterTextRange, "<<")
            }
            if (r2 is Variable) {
                return BuilderShiftExpression(r, r2.textRange, "<<")
            }
            r.asInstance<PsiReferenceExpression>()?.referenceNameElement?.let {
                return BuilderShiftExpression(it, it.textRange, "<<")
            }
        }

        return null
    }
}
