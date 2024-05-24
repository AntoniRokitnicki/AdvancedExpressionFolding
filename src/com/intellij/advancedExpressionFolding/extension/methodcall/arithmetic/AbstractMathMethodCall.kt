package com.intellij.advancedExpressionFolding.extension.methodcall.arithmetic

import com.intellij.advancedExpressionFolding.extension.methodcall.AbstractMethodCall

abstract class AbstractMathMethodCall : AbstractMethodCall() {

    override val classNames by lazy { listOf("java.lang.Math") }

}