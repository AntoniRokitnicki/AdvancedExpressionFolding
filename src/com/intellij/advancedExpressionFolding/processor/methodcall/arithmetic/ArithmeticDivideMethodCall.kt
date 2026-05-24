package com.intellij.advancedExpressionFolding.processor.methodcall.arithmetic

import com.intellij.advancedExpressionFolding.expression.math.basic.Divide
import com.intellij.advancedExpressionFolding.processor.methodcall.Context
import com.intellij.advancedExpressionFolding.processor.methodcall.MethodCallBuilder

class ArithmeticDivideMethodCall : AbstractArithmeticMethodCall() {
    override fun configure(builder: MethodCallBuilder) {
        super.configure(builder)
        builder.methods("divide")
        builder.onSingleArgument { element, context, _, _ ->
            Divide(
                element,
                element.textRange,
                context.getOperands()
            )
        }
    }
}
