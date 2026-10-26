package com.intellij.advancedExpressionFolding.processor.methodcall.arithmetic

import com.intellij.advancedExpressionFolding.expression.math.basic.Divide
import com.intellij.advancedExpressionFolding.processor.methodcall.arithmetic.ConfiguredArithmeticMethodCall.ArgumentArity

class ArithmeticDivideMethodCall : ConfiguredArithmeticMethodCall(
    methodNames = listOf("divide"),
    argumentArity = ArgumentArity.SINGLE_ARGUMENT,
    expressionBuilder = { element, context ->
        Divide(
            element,
            element.textRange,
            context.getOperands()
        )
    }
)
