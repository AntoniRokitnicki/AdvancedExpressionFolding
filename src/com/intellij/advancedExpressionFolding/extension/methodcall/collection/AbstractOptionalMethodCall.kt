package com.intellij.advancedExpressionFolding.extension.methodcall.collection

import com.intellij.advancedExpressionFolding.extension.methodcall.AbstractMethodCall

abstract class AbstractOptionalMethodCall : AbstractMethodCall() {
    override val classNames by lazy { listOf("java.util.Optional") }

    override fun permission() = optional
}