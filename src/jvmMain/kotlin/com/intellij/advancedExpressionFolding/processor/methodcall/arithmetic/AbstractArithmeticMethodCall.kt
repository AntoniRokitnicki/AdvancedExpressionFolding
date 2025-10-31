package com.intellij.advancedExpressionFolding.processor.methodcall.arithmetic

import com.intellij.advancedExpressionFolding.processor.methodcall.AbstractMethodCall
import com.intellij.advancedExpressionFolding.processor.methodcall.NeedsQualifier

abstract class AbstractArithmeticMethodCall : AbstractMethodCall(), NeedsQualifier {
    override val classNames by lazy { 
        listOf(
            "java.math.BigDecimal",
            "java.math.BigInteger"
        ) 
    }
}
