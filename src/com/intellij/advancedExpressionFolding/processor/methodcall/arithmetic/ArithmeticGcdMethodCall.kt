package com.intellij.advancedExpressionFolding.processor.methodcall.arithmetic

import com.intellij.advancedExpressionFolding.expression.math.advanced.Gcd
import com.intellij.advancedExpressionFolding.processor.methodcall.Context
import com.intellij.advancedExpressionFolding.processor.methodcall.MethodCallBuilder

class ArithmeticGcdMethodCall : AbstractArithmeticMethodCall() {
    override fun configure(builder: MethodCallBuilder) {
        super.configure(builder)
        builder.methods("gcd")
        builder.onSingleArgument { element, context, _, _ ->
            Gcd(
                element,
                element.textRange,
                context.getOperands()
            )
        }
    }
}
