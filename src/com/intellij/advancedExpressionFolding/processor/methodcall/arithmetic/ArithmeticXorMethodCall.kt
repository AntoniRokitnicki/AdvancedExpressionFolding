package com.intellij.advancedExpressionFolding.processor.methodcall.arithmetic

import com.intellij.advancedExpressionFolding.expression.math.bitwise.Xor
import com.intellij.advancedExpressionFolding.processor.methodcall.Context
import com.intellij.advancedExpressionFolding.processor.methodcall.MethodCallBuilder

class ArithmeticXorMethodCall : AbstractArithmeticMethodCall() {
    override fun configure(builder: MethodCallBuilder) {
        super.configure(builder)
        builder.methods("xor")
        builder.onSingleArgument { element, context, _, _ ->
            Xor(
                element,
                element.textRange,
                context.getOperands()
            )
        }
    }
}
