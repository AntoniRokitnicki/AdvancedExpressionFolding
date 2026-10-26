package com.intellij.advancedExpressionFolding.processor.methodcall.arithmetic

import com.intellij.advancedExpressionFolding.expression.math.bitwise.ShiftLeft
import com.intellij.advancedExpressionFolding.processor.methodcall.arithmetic.ConfiguredArithmeticMethodCall.ArgumentArity

class ArithmeticShiftLeftMethodCall : ConfiguredArithmeticMethodCall(
    methodNames = listOf("shiftLeft"),
    argumentArity = ArgumentArity.SINGLE_ARGUMENT,
    expressionBuilder = { element, context ->
        ShiftLeft(
            element,
            element.textRange,
            context.getOperands()
        )
    }
)
