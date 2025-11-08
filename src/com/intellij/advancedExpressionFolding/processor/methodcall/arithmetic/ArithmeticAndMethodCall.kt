package com.intellij.advancedExpressionFolding.processor.methodcall.arithmetic

import com.intellij.advancedExpressionFolding.expression.math.bitwise.And
import com.intellij.advancedExpressionFolding.processor.methodcall.arithmetic.ConfiguredArithmeticMethodCall.ArgumentArity

class ArithmeticAndMethodCall : ConfiguredArithmeticMethodCall(
    methodNames = listOf("and"),
    argumentArity = ArgumentArity.SINGLE_ARGUMENT,
    expressionBuilder = { element, context ->
        And(
            element,
            element.textRange,
            context.getOperands()
        )
    }
)
