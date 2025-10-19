package com.intellij.advancedExpressionFolding.processor.methodcall.basic

import com.intellij.advancedExpressionFolding.expression.Expression
import com.intellij.advancedExpressionFolding.processor.language.kotlin.PrintlnExt
import com.intellij.advancedExpressionFolding.processor.methodcall.AbstractMethodCall
import com.intellij.advancedExpressionFolding.processor.methodcall.Context
import com.intellij.advancedExpressionFolding.processor.methodcall.NeedsQualifier
import com.intellij.psi.PsiExpression
import com.intellij.psi.PsiMethodCallExpression

class PrintlnMethodCall : AbstractMethodCall(), NeedsQualifier {
    override val methodNames by lazy { methodNames("println") }
    
    override val classNames by lazy { listOf("java.io.PrintStream") }
    
    override fun onSingleArgument(
        element: PsiMethodCallExpression,
        context: Context,
        argument: PsiExpression,
        argumentExpression: Expression
    ): Expression? = PrintlnExt.createExpression(element, context.qualifierExpr, argumentExpression)
}
