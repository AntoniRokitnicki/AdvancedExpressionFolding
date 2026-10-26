package com.intellij.advancedExpressionFolding.processor.methodcall.collection

import com.intellij.advancedExpressionFolding.expression.Expression
import com.intellij.advancedExpressionFolding.expression.operation.collection.AddAssignForCollection
import com.intellij.advancedExpressionFolding.expression.operation.collection.PrependAssignForCollection
import com.intellij.advancedExpressionFolding.processor.methodcall.Context
import com.intellij.advancedExpressionFolding.processor.isInt
import com.intellij.advancedExpressionFolding.processor.util.Helper
import com.intellij.psi.PsiExpression
import com.intellij.psi.PsiLiteralExpression
import com.intellij.psi.PsiMethodCallExpression
import com.intellij.psi.util.TypeConversionUtil

class CollectionAddAllMethodCall : AbstractCollectionMethodCall() {
    override fun canExecute() = concatenationExpressionsCollapse

    override val methodNames by lazy { listOf("addAll") }
    
    override fun onSingleArgument(
        element: PsiMethodCallExpression,
        context: Context,
        argument: PsiExpression,
        argumentExpression: Expression
    ): Expression? =
        AddAssignForCollection(
            element,
            element.textRange,
            context.getOperands()
        )

    override fun onTwoArguments(
        element: PsiMethodCallExpression,
        context: Context,
        a1: PsiExpression,
        a2: PsiExpression,
        a1Expression: Expression,
        a2Expression: Expression
    ): Expression? {
        val parameters = context.method.parameterList.parameters
        if (parameters.size == 2 && parameters[0].type.isInt()) {
            val qualifierPsi = element.methodExpression.qualifierExpression ?: return null
            val qualifierExpression = context.qualifierExprNullable ?: return null
            if (!isZeroLiteral(a1)) {
                return null
            }

            if (!areTypesCompatible(qualifierPsi, a2)) {
                return null
            }

            if (Helper.equal(qualifierPsi, a2)) {
                return null
            }

            return PrependAssignForCollection(
                element,
                element.textRange,
                qualifierExpression,
                a2Expression,
                qualifierPsi.text,
            )
        }

        return AddAssignForCollection(
            element,
            element.textRange,
            listOf(a1Expression, a2Expression)
        )
    }

    private fun isZeroLiteral(expression: PsiExpression): Boolean {
        val literal = expression as? PsiLiteralExpression ?: return false
        val value = literal.value as? Number ?: return false
        return value.toInt() == 0
    }

    private fun areTypesCompatible(
        qualifier: PsiExpression,
        argument: PsiExpression,
    ): Boolean {
        val qualifierType = qualifier.type ?: return false
        val argumentType = argument.type ?: return false

        return TypeConversionUtil.isAssignable(qualifierType, argumentType) ||
            TypeConversionUtil.isAssignable(argumentType, qualifierType)
    }
}
