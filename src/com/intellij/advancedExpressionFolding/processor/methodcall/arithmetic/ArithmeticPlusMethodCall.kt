package com.intellij.advancedExpressionFolding.processor.methodcall.arithmetic

import com.intellij.advancedExpressionFolding.processor.methodcall.Context
import com.intellij.advancedExpressionFolding.processor.methodcall.MethodCallBuilder

class ArithmeticPlusMethodCall : AbstractArithmeticMethodCall() {
    override fun configure(builder: MethodCallBuilder) {
        super.configure(builder)
        builder.methods("plus")
        builder.onNoArguments { _, context ->
            context.qualifierExprNullable
        }
    }
}
