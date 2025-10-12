package com.intellij.advancedExpressionFolding.processor.reference

import com.intellij.advancedExpressionFolding.expression.Expression
import com.intellij.advancedExpressionFolding.expression.literal.NumberLiteral
import com.intellij.advancedExpressionFolding.expression.operation.basic.Variable
import com.intellij.advancedExpressionFolding.processor.expression.AssignmentExpressionExt
import com.intellij.advancedExpressionFolding.processor.language.ExperimentalExt
import com.intellij.advancedExpressionFolding.processor.util.Consts
import com.intellij.advancedExpressionFolding.processor.util.Helper
import com.intellij.psi.PsiIdentifier
import com.intellij.psi.PsiMethodReferenceExpression
import com.intellij.psi.PsiReferenceExpression

object ReferenceExpressionExt {

    fun getReferenceExpression(element: PsiReferenceExpression, copy: Boolean = false): Expression? {
        val identifier = element.children.firstOrNull { it is PsiIdentifier } as? PsiIdentifier ?: return null
        val constant = Consts.SUPPORTED_CONSTANTS[identifier.text]
        if (constant != null) {
            if (Helper.isSupportedClass(element) && constant is Number) {
                return NumberLiteral(element, element.textRange, element.textRange, constant, true)
            }
            if (Helper.isSupportedClass(element) && constant is String) {
                return Variable(element, element.textRange, null, constant, copy)
            }
        } else {
            AssignmentExpressionExt.getVariableExpression(element, copy)?.let { return it }
            if (element is PsiMethodReferenceExpression) {
                MethodReferenceExt.createExpression(element)?.let { return it }
            }
            ExperimentalExt.createExpression(element)?.let { return it }
        }
        return null
    }
}
