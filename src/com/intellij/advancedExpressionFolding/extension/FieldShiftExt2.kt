package com.intellij.advancedExpressionFolding.extension

import com.intellij.advancedExpressionFolding.expression.Expression
import com.intellij.advancedExpressionFolding.expression.optional.FieldShiftMethod
import com.intellij.openapi.editor.Document
import com.intellij.psi.PsiExpression
import com.intellij.psi.PsiExpressionList
import com.intellij.psi.PsiMethodCallExpression

object FieldShiftExt2 : BaseExtension() {

    @JvmStatic
    fun createExpression(
        getterElement: PsiMethodCallExpression,
        document: Document,
        qualifier: PsiExpression?
    ): Expression? {
        fieldShift.takeIf {
            it && qualifier != null && getterElement.argumentList.isEmpty
        } ?: return null

        val expressionList = getterElement.parent.asInstance<PsiExpressionList>() ?: return null

        val parentMethod = expressionList.parent.asInstance<PsiMethodCallExpression>()?.let { methodCall ->
            methodCall.resolveMethod()?.takeIf {
                it.isSetterOrBuilder()
            } ?: run {
                methodCall.parent.asInstance<PsiExpressionList>()?.parent.asInstance<PsiMethodCallExpression>()
                    ?.resolveMethod()?.takeIf {
                        it.isSetterOrBuilder()
                    }
            }
        } ?: return null

        val parameters = parentMethod.parameterList.parameters
        val index = expressionList.filterOutWhiteSpaceAndTokens().indexOf(getterElement)
        val parameter = parameters.getOrNull(index) ?: return null

        val currentMethod = getterElement.resolveMethod() ?: return null
        if (currentMethod.isGetter()) {
            val propertyName = currentMethod.guessPropertyName()
            if (propertyName == parameter.name) {
                if (parameters.size == 1) {
                    if (parentMethod.guessPropertyName() == propertyName) {
                        val qualifierExpr = getAnyExpression(qualifier!!, document)
                        return FieldShiftMethod(getterElement, getterElement.textRange, listOf(qualifierExpr), "<<")
                    }
                }
            }
        }
        return null
    }


}

