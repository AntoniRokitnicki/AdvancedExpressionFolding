package com.intellij.advancedExpressionFolding.processor.methodcall.arithmetic

import com.intellij.advancedExpressionFolding.expression.math.basic.Negate
import com.intellij.advancedExpressionFolding.processor.methodcall.arithmetic.ConfiguredArithmeticMethodCall.ArgumentArity

class ArithmeticNegateMethodCall : ConfiguredArithmeticMethodCall(
    methodNames = listOf("negate"),
    argumentArity = ArgumentArity.NO_ARGUMENTS,
    expressionBuilder = { element, context ->
        Negate(
            element,
            element.textRange,
            context.getOperands()
        )
    }
)
