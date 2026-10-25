package com.intellij.advancedExpressionFolding.processor.methodcall.arithmetic

import com.intellij.advancedExpressionFolding.expression.math.trig.Atan2
import com.intellij.advancedExpressionFolding.processor.methodcall.Context
import com.intellij.advancedExpressionFolding.processor.methodcall.MethodCallBuilder

class ArithmeticAtan2MethodCall : AbstractArithmeticMethodCall() {
    override fun configure(builder: MethodCallBuilder) {
        super.configure(builder)
        builder.methods("atan2")
        builder.onTwoArguments { element, context, _, _, _, _ ->
            Atan2(
                element,
                element.textRange,
                context.getOperands()
            )
        }
    }
}
