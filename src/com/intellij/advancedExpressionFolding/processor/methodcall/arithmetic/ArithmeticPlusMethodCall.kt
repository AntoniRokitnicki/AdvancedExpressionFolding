package com.intellij.advancedExpressionFolding.processor.methodcall.arithmetic

import com.intellij.advancedExpressionFolding.processor.methodcall.arithmetic.ConfiguredArithmeticMethodCall.ArgumentArity

class ArithmeticPlusMethodCall : ConfiguredArithmeticMethodCall(
    methodNames = listOf("plus"),
    argumentArity = ArgumentArity.NO_ARGUMENTS,
    expressionBuilder = { _, context ->
        context.qualifierExprNullable
    }
)
