package com.intellij.advancedExpressionFolding.processor.methodcall.basic

import com.intellij.advancedExpressionFolding.expression.literal.StringLiteral
import com.intellij.advancedExpressionFolding.expression.operation.basic.Append
import com.intellij.advancedExpressionFolding.processor.methodcall.Context
import com.intellij.advancedExpressionFolding.processor.methodcall.dsl.QualifiedDslMethodCall
import com.intellij.psi.PsiMethodCallExpression
import com.intellij.psi.PsiStatement
import java.util.*

class AppendMethodCall : QualifiedDslMethodCall({
    canExecute { concatenationExpressionsCollapse }

    method("append")
    classNames(
        "java.lang.StringBuilder",
        "java.lang.AbstractStringBuilder",
    )

    onSingleArgument { element, context, _, argumentExpression ->
        val qualifier = context.qualifierExpr
        when {
            qualifier is Append -> {
                val operands = ArrayList(qualifier.operands)
                operands.add(argumentExpression)
                Append(
                    element,
                    element.textRange,
                    operands,
                    element.parent is PsiStatement,
                )
            }
            qualifier is StringLiteral && qualifier.string.isEmpty() -> {
                Append(
                    element,
                    element.textRange,
                    Collections.singletonList(argumentExpression),
                    element.parent is PsiStatement,
                )
            }
            else -> {
                Append(
                    element,
                    element.textRange,
                    context.getOperands(),
                    element.parent is PsiStatement,
                )
            }
        }
    }
})
