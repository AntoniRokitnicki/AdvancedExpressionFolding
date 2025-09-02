package com.intellij.advancedExpressionFolding.processor.reference

import com.intellij.advancedExpressionFolding.expression.Expression
import com.intellij.advancedExpressionFolding.expression.literal.NumberLiteral
import com.intellij.advancedExpressionFolding.expression.operation.basic.Variable
import com.intellij.advancedExpressionFolding.processor.expression.AssignmentExpressionExt
import com.intellij.advancedExpressionFolding.processor.language.ExperimentalExt
import com.intellij.advancedExpressionFolding.processor.util.Consts
import com.intellij.advancedExpressionFolding.processor.util.Helper
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiIdentifier
import com.intellij.psi.PsiMethodReferenceExpression
import com.intellij.psi.PsiReferenceExpression
import org.jetbrains.annotations.Nullable
import java.util.Optional

object ReferenceExpressionExt {
    @JvmStatic
    @Nullable
    fun getReferenceExpression(element: PsiReferenceExpression, copy: Boolean): Expression? {
        var found: Optional<PsiElement> = Optional.empty()
        for (c in element.getChildren()) {
            if (c is PsiIdentifier) {
                found = Optional.of(c)
                break
            }
        }
        val identifier = found
        if (identifier.isPresent) {
            val constant = Consts.SUPPORTED_CONSTANTS.get(identifier.get().getText())
            if (constant != null) {
                if (Helper.isSupportedClass(element) && constant is Number) {
                    return NumberLiteral(element, element.getTextRange(), element.getTextRange(), constant, true)
                } else if (Helper.isSupportedClass(element) && constant is String) {
                    return Variable(element, element.getTextRange(), null, constant as String, copy)
                }
            } else {
                val variable = AssignmentExpressionExt.getVariableExpression(element, copy)
                if (variable != null) return variable
                if (element is PsiMethodReferenceExpression) {
                    val methodReference = MethodReferenceExt.createExpression(element)
                    if (methodReference != null) {
                        return methodReference
                    }
                }
                val expression = ExperimentalExt.createExpression(element)
                if (expression != null) {
                    return expression
                }
            }
        }
        return null
    }

    @JvmStatic
    @Nullable
    fun getReferenceExpression(element: PsiReferenceExpression): Expression? {
        return getReferenceExpression(element, false)
    }
}
