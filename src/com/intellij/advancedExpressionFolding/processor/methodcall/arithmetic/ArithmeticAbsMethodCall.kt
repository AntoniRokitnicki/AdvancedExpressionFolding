package com.intellij.advancedExpressionFolding.processor.methodcall.arithmetic

import com.intellij.advancedExpressionFolding.expression.math.basic.Abs
import com.intellij.advancedExpressionFolding.processor.methodcall.arithmetic.ConfiguredArithmeticMethodCall.ArgumentArity

class ArithmeticAbsMethodCall : ConfiguredArithmeticMethodCall(
    methodNames = listOf("abs"),
    argumentArity = ArgumentArity.NO_ARGUMENTS,
    expressionBuilder = { element, context ->
        Abs(
            element,
            element.textRange,
            context.getOperands()
        )
    }
)
