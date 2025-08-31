package com.intellij.advancedExpressionFolding.processor.reference

import com.intellij.advancedExpressionFolding.expression.Expression
import com.intellij.advancedExpressionFolding.expression.literal.NumberLiteral
import com.intellij.advancedExpressionFolding.expression.operation.basic.Variable
import com.intellij.advancedExpressionFolding.processor.expression.AssignmentExpressionExt
import com.intellij.advancedExpressionFolding.processor.language.ExperimentalExt
import com.intellij.advancedExpressionFolding.processor.util.Consts
import com.intellij.advancedExpressionFolding.processor.util.Helper
import com.intellij.psi.*
import java.util.*

object ReferenceExpressionExt {
    fun getReferenceExpression(element: PsiReferenceExpression, copy: Boolean): Expression? {
        var found: Optional<PsiElement> = Optional.empty()
        for (c in element.children) {
            if (c is PsiIdentifier) {
                found = Optional.of(c)
                break
            }
        }
        val identifier = found
        if (identifier.isPresent) {
            val constant = Consts.SUPPORTED_CONSTANTS[identifier.get().text]
            if (constant != null) {
                if (Helper.isSupportedClass(element) && constant is Number) {
                    return NumberLiteral(element, element.textRange, element.textRange, constant, true)
                } else if (Helper.isSupportedClass(element) && constant is String) {
                    return Variable(element, element.textRange, null, constant, copy)
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

    fun getReferenceExpression(element: PsiReferenceExpression): Expression? {
        return getReferenceExpression(element, false)
    }
}

