package com.intellij.advancedExpressionFolding.extension.methodcall.extracted

import com.intellij.advancedExpressionFolding.extension.methodcall.AbstractMethodCall
import com.intellij.advancedExpressionFolding.extension.methodcall.NeedsQualifier

abstract class AbstractArithmeticMethodCall : AbstractMethodCall(), NeedsQualifier {
    override val classNames by lazy { 
        listOf(
            "java.math.BigDecimal",
            "java.math.BigInteger"
        ) 
    }
}