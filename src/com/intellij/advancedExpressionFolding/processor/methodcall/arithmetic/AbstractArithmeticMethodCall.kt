package com.intellij.advancedExpressionFolding.processor.methodcall.arithmetic

import com.intellij.advancedExpressionFolding.processor.methodcall.DslMethodCall
import com.intellij.advancedExpressionFolding.processor.methodcall.MethodCallBuilder
import com.intellij.advancedExpressionFolding.processor.methodcall.NeedsQualifier

abstract class AbstractArithmeticMethodCall : DslMethodCall(), NeedsQualifier {
    override fun configure(builder: MethodCallBuilder) {
        super.configure(builder)
        builder.classes(
            "java.math.BigDecimal",
            "java.math.BigInteger"
        )
    }
}
