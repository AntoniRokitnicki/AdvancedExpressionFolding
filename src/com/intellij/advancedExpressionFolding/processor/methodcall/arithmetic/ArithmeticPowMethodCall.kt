package com.intellij.advancedExpressionFolding.processor.methodcall.arithmetic

import com.intellij.advancedExpressionFolding.expression.math.advanced.Pow
import com.intellij.advancedExpressionFolding.processor.methodcall.arithmetic.ConfiguredArithmeticMethodCall.ArgumentArity

class ArithmeticPowMethodCall : ConfiguredArithmeticMethodCall(
    methodNames = listOf("pow"),
    argumentArity = ArgumentArity.SINGLE_ARGUMENT,
    expressionBuilder = { element, context ->
        Pow(
            element,
            element.textRange,
            context.getOperands()
        )
    }
)
