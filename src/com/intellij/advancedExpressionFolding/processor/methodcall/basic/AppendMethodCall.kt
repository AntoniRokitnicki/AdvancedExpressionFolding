package com.intellij.advancedExpressionFolding.processor.methodcall.basic

import com.intellij.advancedExpressionFolding.expression.Expression
import com.intellij.advancedExpressionFolding.expression.literal.StringLiteral
import com.intellij.advancedExpressionFolding.expression.operation.basic.Append
import com.intellij.advancedExpressionFolding.processor.methodcall.AbstractMethodCall
import com.intellij.advancedExpressionFolding.processor.methodcall.Context
import com.intellij.advancedExpressionFolding.processor.methodcall.NeedsQualifier
import com.intellij.psi.PsiExpression
import com.intellij.psi.PsiMethodCallExpression
import com.intellij.psi.PsiStatement
import java.util.*

class AppendMethodCall : AbstractMethodCall(), NeedsQualifier {
    override fun canExecute() = concatenationExpressionsCollapse

    override val methodNames by lazy { methodNames("append") }
    
    override val classNames by lazy { listOf(
        "java.lang.StringBuilder", 
        "java.lang.AbstractStringBuilder"
    ) }
    
    override fun onSingleArgument(
        element: PsiMethodCallExpression,
        context: Context,
        argument: PsiExpression,
        argumentExpression: Expression
    ): Expression? {
        val qualifier = context.qualifierExpr
        return when {
            qualifier is Append -> {
                val operands = ArrayList(qualifier.operands)
                operands.add(argumentExpression)
                Append(
                    element,
                    element.textRange,
                    operands,
                    element.parent is PsiStatement
                )
            }
            qualifier is StringLiteral && qualifier.string.isEmpty() -> {
                Append(
                    element, element.textRange,
                    Collections.singletonList(argumentExpression), element.parent is PsiStatement
                )
            }
            else -> {
                Append(
                    element,
                    element.textRange,
                    context.getOperands(),
                    element.parent is PsiStatement
                )
            }
        }
    }
}
