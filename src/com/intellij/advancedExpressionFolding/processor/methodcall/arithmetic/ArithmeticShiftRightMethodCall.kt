package com.intellij.advancedExpressionFolding.processor.methodcall.arithmetic

import com.intellij.advancedExpressionFolding.expression.math.bitwise.ShiftRight
import com.intellij.advancedExpressionFolding.processor.methodcall.arithmetic.ConfiguredArithmeticMethodCall.ArgumentArity

class ArithmeticShiftRightMethodCall : ConfiguredArithmeticMethodCall(
    methodNames = listOf("shiftRight"),
    argumentArity = ArgumentArity.SINGLE_ARGUMENT,
    expressionBuilder = { element, context ->
        ShiftRight(
            element,
            element.textRange,
            context.getOperands()
        )
    }
)
