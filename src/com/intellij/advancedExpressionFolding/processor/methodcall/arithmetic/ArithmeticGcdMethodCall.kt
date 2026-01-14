package com.intellij.advancedExpressionFolding.processor.methodcall.arithmetic

import com.intellij.advancedExpressionFolding.expression.math.advanced.Gcd
import com.intellij.advancedExpressionFolding.processor.methodcall.arithmetic.ConfiguredArithmeticMethodCall.ArgumentArity

class ArithmeticGcdMethodCall : ConfiguredArithmeticMethodCall(
    methodNames = listOf("gcd"),
    argumentArity = ArgumentArity.SINGLE_ARGUMENT,
    expressionBuilder = { element, context ->
        Gcd(
            element,
            element.textRange,
            context.getOperands()
        )
    }
)
