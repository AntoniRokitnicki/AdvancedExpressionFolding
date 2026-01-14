package com.intellij.advancedExpressionFolding.processor.methodcall.arithmetic

import com.intellij.advancedExpressionFolding.expression.math.basic.Not
import com.intellij.advancedExpressionFolding.processor.methodcall.arithmetic.ConfiguredArithmeticMethodCall.ArgumentArity

class ArithmeticNotMethodCall : ConfiguredArithmeticMethodCall(
    methodNames = listOf("not"),
    argumentArity = ArgumentArity.NO_ARGUMENTS,
    expressionBuilder = { element, context ->
        Not(
            element,
            element.textRange,
            context.getOperands()
        )
    }
)
