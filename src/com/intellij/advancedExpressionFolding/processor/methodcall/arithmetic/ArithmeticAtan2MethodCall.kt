package com.intellij.advancedExpressionFolding.processor.methodcall.arithmetic

import com.intellij.advancedExpressionFolding.expression.math.trig.Atan2
import com.intellij.advancedExpressionFolding.processor.methodcall.arithmetic.ConfiguredArithmeticMethodCall.ArgumentArity

class ArithmeticAtan2MethodCall : ConfiguredArithmeticMethodCall(
    methodNames = listOf("atan2"),
    argumentArity = ArgumentArity.TWO_ARGUMENTS,
    expressionBuilder = { element, context ->
        Atan2(
            element,
            element.textRange,
            context.getOperands()
        )
    }
)
