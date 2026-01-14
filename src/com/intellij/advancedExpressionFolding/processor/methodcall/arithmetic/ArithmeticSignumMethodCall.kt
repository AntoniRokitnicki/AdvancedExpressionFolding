package com.intellij.advancedExpressionFolding.processor.methodcall.arithmetic

import com.intellij.advancedExpressionFolding.expression.math.basic.Signum
import com.intellij.advancedExpressionFolding.processor.methodcall.arithmetic.ConfiguredArithmeticMethodCall.ArgumentArity

class ArithmeticSignumMethodCall : ConfiguredArithmeticMethodCall(
    methodNames = listOf("signum"),
    argumentArity = ArgumentArity.NO_ARGUMENTS,
    expressionBuilder = { element, context ->
        Signum(
            element,
            element.textRange,
            context.getOperands()
        )
    }
)
