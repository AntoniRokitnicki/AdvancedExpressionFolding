package com.intellij.advancedExpressionFolding.processor.methodcall.arithmetic

import com.intellij.advancedExpressionFolding.expression.math.bitwise.ShiftLeft
import com.intellij.advancedExpressionFolding.processor.methodcall.Context
import com.intellij.advancedExpressionFolding.processor.methodcall.MethodCallBuilder

class ArithmeticShiftLeftMethodCall : AbstractArithmeticMethodCall() {
    override fun configure(builder: MethodCallBuilder) {
        super.configure(builder)
        builder.methods("shiftLeft")
        builder.onSingleArgument { element, context, _, _ ->
            ShiftLeft(
                element,
                element.textRange,
                context.getOperands()
            )
        }
    }
}
