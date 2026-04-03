package com.intellij.advancedExpressionFolding.processor.methodcall.arithmetic

import com.intellij.advancedExpressionFolding.expression.math.bitwise.Or
import com.intellij.advancedExpressionFolding.processor.methodcall.arithmetic.ConfiguredArithmeticMethodCall.ArgumentArity

class ArithmeticOrMethodCall : ConfiguredArithmeticMethodCall(
    methodNames = listOf("or"),
    argumentArity = ArgumentArity.SINGLE_ARGUMENT,
    expressionBuilder = { element, context ->
        Or(
            element,
            element.textRange,
            context.getOperands()
        )
    }
)
