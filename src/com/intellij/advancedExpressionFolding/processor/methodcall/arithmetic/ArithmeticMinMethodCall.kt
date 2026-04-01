package com.intellij.advancedExpressionFolding.processor.methodcall.arithmetic

import com.intellij.advancedExpressionFolding.expression.math.basic.Min
import com.intellij.advancedExpressionFolding.processor.methodcall.Context
import com.intellij.advancedExpressionFolding.processor.methodcall.MethodCallBuilder

class ArithmeticMinMethodCall : AbstractArithmeticMethodCall() {
    override fun configure(builder: MethodCallBuilder) {
        super.configure(builder)
        builder.methods("min")
        builder.onSingleArgument { element, context, _, _ ->
            if (context.className == "java.util.stream.Stream") {
                null
            } else {
                Min(
                    element,
                    element.textRange,
                    context.getOperands()
                )
            }
        }
    }
}
