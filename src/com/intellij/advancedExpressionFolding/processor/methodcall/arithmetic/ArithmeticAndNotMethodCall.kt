package com.intellij.advancedExpressionFolding.processor.methodcall.arithmetic

import com.intellij.advancedExpressionFolding.expression.math.basic.Not
import com.intellij.advancedExpressionFolding.expression.math.bitwise.And
import com.intellij.advancedExpressionFolding.processor.methodcall.Context
import com.intellij.advancedExpressionFolding.processor.methodcall.MethodCallBuilder

class ArithmeticAndNotMethodCall : AbstractArithmeticMethodCall() {
    override fun configure(builder: MethodCallBuilder) {
        super.configure(builder)
        builder.methods("andNot")
        builder.onSingleArgument { element, context, _, argumentExpression ->
            val qualifier = context.qualifierExpr
            val notExpr = Not(
                element,
                argumentExpression.textRange,
                listOf(argumentExpression)
            )
            And(
                element,
                element.textRange,
                listOf(qualifier, notExpr)
            )
        }
    }
}
