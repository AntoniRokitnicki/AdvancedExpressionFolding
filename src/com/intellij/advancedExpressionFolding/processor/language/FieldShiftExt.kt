package com.intellij.advancedExpressionFolding.processor.language

import com.intellij.advancedExpressionFolding.expression.Expression
import com.intellij.advancedExpressionFolding.expression.operation.FieldShiftMethod
import com.intellij.advancedExpressionFolding.expression.operation.basic.Variable
import com.intellij.advancedExpressionFolding.expression.property.IGetter
import com.intellij.advancedExpressionFolding.expression.property.INameable
import com.intellij.advancedExpressionFolding.expression.semantic.BuilderShiftExpression
import com.intellij.advancedExpressionFolding.expression.semantic.kotlin.CheckNotNullExpression
import com.intellij.advancedExpressionFolding.processor.*
import com.intellij.advancedExpressionFolding.processor.core.BuildExpressionExt
import com.intellij.advancedExpressionFolding.processor.core.getAnyExpression
import com.intellij.advancedExpressionFolding.settings.State
import com.intellij.advancedExpressionFolding.settings.state.IExpressionCollapseState
import com.intellij.openapi.editor.Document
import com.intellij.psi.*

object FieldShiftExt : IExpressionCollapseState by State()() {
    private const val FIELD_SHIFT = "<<"

    @JvmStatic
    fun createExpression(element: PsiAssignmentExpression, document: Document?): Expression? {
        fieldShift.takeIf {
            it
        } ?: return null

        val right = element.rExpression?.let {
            it.asMethodCall()?.argumentList?.expressions?.singleOrNull() ?: it
        } ?: return null

        val leftText =
            element.lExpression.asRefExpr()?.referenceNameElement?.text ?: return null

        val rightExp = BuildExpressionExt.getAnyExpression(right, document).run {
            asInstance<CheckNotNullExpression>()?.run {
                argumentExpression
            } ?: this
        }

        val rightText = if (rightExp is INameable) {
            rightExp.name
        } else {
            right.asRefExpr()?.referenceNameElement?.text
        }

        if (leftText == rightText) {
            if (rightExp is IGetter) {
                return BuilderShiftExpression(right, rightExp.getterTextRange, FIELD_SHIFT)
            }
            if (rightExp is Variable) {
                return BuilderShiftExpression(right, rightExp.textRange, FIELD_SHIFT)
            }
            right.asRefExpr()?.referenceNameElement?.let {
                return BuilderShiftExpression(it, it.textRange, FIELD_SHIFT)
            }
        }
        return null
    }

    @JvmStatic
    fun createExpression(
        getterElement: PsiMethodCallExpression,
        document: Document,
        qualifier: PsiExpression?
    ): Expression? {
        fieldShift.takeIf {
            it && qualifier != null && getterElement.argumentList.isEmpty
        } ?: return null

        val expressionList = getterElement.parent.asExprList() ?: return null

        val parentMethod = expressionList.parent.asMethodCall()?.let { methodCall ->
            methodCall.resolveMethod()?.takeIf {
                it.isSetterOrBuilder()
            } ?: run {
                methodCall.parent.asExprList()?.parent.asMethodCall()
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
                        return FieldShiftMethod(
                            getterElement, getterElement.textRange, listOf(qualifierExpr),
                            FIELD_SHIFT
                        )
                    }
                }
            }
        }
        return null
    }

    private fun PsiElement?.asMethodCall(): PsiMethodCallExpression? = asInstance<PsiMethodCallExpression>()
    private fun PsiElement?.asRefExpr(): PsiReferenceExpression? = asInstance<PsiReferenceExpression>()
    private fun PsiElement?.asExprList(): PsiExpressionList? = asInstance<PsiExpressionList>()
}
