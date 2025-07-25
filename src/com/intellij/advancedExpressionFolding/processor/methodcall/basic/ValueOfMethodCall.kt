package com.intellij.advancedExpressionFolding.processor.methodcall.basic

import com.intellij.advancedExpressionFolding.expression.Expression
import com.intellij.advancedExpressionFolding.expression.operation.basic.Variable
import com.intellij.advancedExpressionFolding.processor.methodcall.AbstractMethodCall
import com.intellij.advancedExpressionFolding.processor.methodcall.Context
import com.intellij.advancedExpressionFolding.processor.methodcall.NeedsQualifier
import com.intellij.advancedExpressionFolding.processor.reference.NewExpressionExt
import com.intellij.advancedExpressionFolding.processor.reference.ReferenceExpressionExt.getReferenceExpression
import com.intellij.psi.PsiExpression
import com.intellij.psi.PsiLiteralExpression
import com.intellij.psi.PsiMethodCallExpression
import com.intellij.psi.PsiReferenceExpression

class ValueOfMethodCall : AbstractMethodCall(), NeedsQualifier {
    override val methodNames by lazy { listOf("valueOf") }
    
    override val classNames by lazy { listOf(
        "java.lang.String",
        "java.lang.Integer",
        "java.lang.Long",
        "java.lang.Float",
        "java.lang.Double",
        "java.lang.Boolean",
        "java.lang.Character",
        "java.lang.Character",
        "java.math.BigDecimal",
        "java.math.BigInteger",
    ) }
    
    override fun onSingleArgument(
        element: PsiMethodCallExpression,
        context: Context,
        argument: PsiExpression,
        argumentExpression: Expression
    ): Expression? {
        return when (argument) {
            is PsiLiteralExpression -> NewExpressionExt.getConstructorExpression(
                element, 
                argument, 
                context.className
            )
            is PsiReferenceExpression -> {
                val refExpr = getReferenceExpression(argument)
                if (refExpr is Variable) {
                    Variable(
                        element,
                        element.textRange,
                        refExpr.textRange,
                        refExpr.name,
                        true
                    )
                } else null
            }
            else -> null
        }
    }
}
