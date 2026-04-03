package com.intellij.advancedExpressionFolding.processor.methodcall.arithmetic

import com.intellij.advancedExpressionFolding.expression.math.bitwise.Xor
import com.intellij.advancedExpressionFolding.processor.methodcall.arithmetic.ConfiguredArithmeticMethodCall.ArgumentArity

class ArithmeticXorMethodCall : ConfiguredArithmeticMethodCall(
    methodNames = listOf("xor"),
    argumentArity = ArgumentArity.SINGLE_ARGUMENT,
    expressionBuilder = { element, context ->
        Xor(
            element,
            element.textRange,
            context.getOperands()
        )
    }
)
