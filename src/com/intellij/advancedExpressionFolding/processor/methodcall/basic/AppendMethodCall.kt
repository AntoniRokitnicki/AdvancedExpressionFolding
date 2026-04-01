package com.intellij.advancedExpressionFolding.processor.methodcall.basic

import com.intellij.advancedExpressionFolding.expression.literal.StringLiteral
import com.intellij.advancedExpressionFolding.expression.operation.basic.Append
import com.intellij.advancedExpressionFolding.processor.methodcall.Context
import com.intellij.advancedExpressionFolding.processor.methodcall.NeedsQualifier
import com.intellij.advancedExpressionFolding.processor.methodcall.DslMethodCall
import com.intellij.advancedExpressionFolding.processor.methodcall.MethodCallBuilder
import com.intellij.psi.PsiStatement

class AppendMethodCall : DslMethodCall(), NeedsQualifier {
    override fun configure(builder: MethodCallBuilder) {
        super.configure(builder)
        builder.enableWhen { concatenationExpressionsCollapse }
        builder.methods("append")
        builder.classes(
            "java.lang.StringBuilder",
            "java.lang.AbstractStringBuilder"
        )
        builder.onSingleArgument { element, context, _, argumentExpression ->
            val qualifier = context.qualifierExpr
            when {
                qualifier is Append -> {
                    val operands = qualifier.operands.toMutableList()
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
                        element,
                        element.textRange,
                        listOf(argumentExpression),
                        element.parent is PsiStatement
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
}
