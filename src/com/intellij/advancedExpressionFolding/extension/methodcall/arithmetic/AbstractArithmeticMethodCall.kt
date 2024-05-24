package com.intellij.advancedExpressionFolding.extension.methodcall.arithmetic

import com.intellij.advancedExpressionFolding.extension.methodcall.AbstractMethodCall

abstract class AbstractArithmeticMethodCall : AbstractMethodCall() {

    override val classNames by lazy { listOf("java.math.BigDecimal", "java.math.BigInteger") }

}