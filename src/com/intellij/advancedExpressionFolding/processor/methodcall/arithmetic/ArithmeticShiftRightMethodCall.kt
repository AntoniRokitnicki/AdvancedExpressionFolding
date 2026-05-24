package com.intellij.advancedExpressionFolding.processor.methodcall.arithmetic

import com.intellij.advancedExpressionFolding.expression.math.bitwise.ShiftRight
import com.intellij.advancedExpressionFolding.processor.methodcall.Context
import com.intellij.advancedExpressionFolding.processor.methodcall.MethodCallBuilder

class ArithmeticShiftRightMethodCall : AbstractArithmeticMethodCall() {
    override fun configure(builder: MethodCallBuilder) {
        super.configure(builder)
        builder.methods("shiftRight")
        builder.onSingleArgument { element, context, _, _ ->
            ShiftRight(
                element,
                element.textRange,
                context.getOperands()
            )
        }
    }
}
