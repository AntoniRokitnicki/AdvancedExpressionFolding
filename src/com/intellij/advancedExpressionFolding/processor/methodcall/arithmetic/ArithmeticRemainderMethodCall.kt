package com.intellij.advancedExpressionFolding.processor.methodcall.arithmetic

import com.intellij.advancedExpressionFolding.expression.math.bitwise.Remainder
import com.intellij.advancedExpressionFolding.processor.methodcall.arithmetic.ConfiguredArithmeticMethodCall.ArgumentArity

class ArithmeticRemainderMethodCall : ConfiguredArithmeticMethodCall(
    methodNames = listOf("remainder", "mod"),
    argumentArity = ArgumentArity.SINGLE_ARGUMENT,
    expressionBuilder = { element, context ->
        Remainder(
            element,
            element.textRange,
            context.getOperands()
        )
    }
)
