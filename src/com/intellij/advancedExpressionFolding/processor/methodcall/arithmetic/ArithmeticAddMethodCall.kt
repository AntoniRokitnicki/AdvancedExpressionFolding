package com.intellij.advancedExpressionFolding.processor.methodcall.arithmetic

import com.intellij.advancedExpressionFolding.expression.math.basic.Add
import com.intellij.advancedExpressionFolding.processor.methodcall.arithmetic.ConfiguredArithmeticMethodCall.ArgumentArity

class ArithmeticAddMethodCall : ConfiguredArithmeticMethodCall(
    methodNames = listOf("add"),
    argumentArity = ArgumentArity.SINGLE_ARGUMENT,
    expressionBuilder = { element, context ->
        Add(
            element,
            element.textRange,
            context.getOperands()
        )
    }
)
