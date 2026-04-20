package com.intellij.advancedExpressionFolding.processor.methodcall.arithmetic

import com.intellij.advancedExpressionFolding.expression.math.basic.Subtract
import com.intellij.advancedExpressionFolding.processor.methodcall.arithmetic.ConfiguredArithmeticMethodCall.ArgumentArity

class ArithmeticSubtractMethodCall : ConfiguredArithmeticMethodCall(
    methodNames = listOf("subtract"),
    argumentArity = ArgumentArity.SINGLE_ARGUMENT,
    expressionBuilder = { element, context ->
        Subtract(
            element,
            element.textRange,
            context.getOperands()
        )
    }
)
