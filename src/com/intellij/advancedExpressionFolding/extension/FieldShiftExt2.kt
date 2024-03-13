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
        element: PsiMethodCallExpression,
        document: Document,
        qualifier: PsiExpression?
    ): Expression? {
        fieldShift.takeIf {
            it && qualifier != null && element.argumentList.isEmpty
        } ?: return null

        val expressionList = element.parent.asInstance<PsiExpressionList>() ?: return null

        val parentMethod = expressionList.parent.asInstance<PsiMethodCallExpression>()?.resolveMethod() ?: return null
        val parameters = parentMethod.parameterList.parameters
        val index = expressionList.filterOutWhiteSpaceAndTokens().indexOf(element)
        val parameter = parameters.getOrNull(index) ?: return null

        val qualifierExpr = getAnyExpression(qualifier!!, document)

        val currentMethod = element.resolveMethod() ?: return null
        if (currentMethod.isGetter()) {
            val propertyName = currentMethod.guessPropertyName()
            if (propertyName == parameter.name) {
                if (parameters.size == 1) {
                    if (parentMethod.isSetterOrBuilder()) {
                        if (parentMethod.guessPropertyName() == propertyName) {
                            return FieldShiftMethod(element, element.textRange, listOf(qualifierExpr), "<<")
                        }
                    }
                }
            }
        }
        return null
    }


}

