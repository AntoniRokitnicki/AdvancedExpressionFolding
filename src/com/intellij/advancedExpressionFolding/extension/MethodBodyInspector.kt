package com.intellij.advancedExpressionFolding.extension

import com.intellij.psi.*

object MethodBodyInspector {
    fun PsiMethod.isDirtyGetter(): Boolean {
        val field = this.propertyField ?: return false
        return body
            ?.statements
            ?.singleOrNull()
            .asInstance<PsiReturnStatement>()
            ?.returnValue
            .asInstance<PsiReferenceExpression>()
            ?.resolve() != field
    }

    fun PsiMethod.isDirtySetter(): Boolean {
        val field: PsiField = this.propertyField ?: return false
        val assignment = body
            ?.statements
            ?.singleOrNull()
            .asInstance<PsiExpressionStatement>()
            ?.expression
            .asInstance<PsiAssignmentExpression>() ?: return true

        // check `this.*data* = data;` point to correct field
        assignment.lExpression
            .asInstance<PsiReferenceExpression>()
            ?.takeIf {
                it.qualifier.isInstance<PsiThisExpression>()
            }?.takeIf { thisField ->
                thisField.resolve() == field
            } ?: return true

        // check `this.data = *data*;` is same as method param
        val firstParam = parameterList.parameters.singleOrNull() ?: return false
        assignment.rExpression.asInstance<PsiReferenceExpression>()?.takeIf {
            it.resolve() == firstParam
        } ?: return true

        return false
    }
}