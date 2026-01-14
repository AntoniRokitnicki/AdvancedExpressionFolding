package com.intellij.advancedExpressionFolding.processor.methodcall.arithmetic

import com.intellij.advancedExpressionFolding.expression.math.basic.Multiply
import com.intellij.advancedExpressionFolding.processor.methodcall.arithmetic.ConfiguredArithmeticMethodCall.ArgumentArity

class ArithmeticMultiplyMethodCall : ConfiguredArithmeticMethodCall(
    methodNames = listOf("multiply"),
    argumentArity = ArgumentArity.SINGLE_ARGUMENT,
    expressionBuilder = { element, context ->
        Multiply(
            element,
            element.textRange,
            context.getOperands()
        )
    }
)
