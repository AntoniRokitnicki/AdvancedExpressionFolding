package com.intellij.advancedExpressionFolding.extension.methodcall.date

import com.intellij.advancedExpressionFolding.extension.methodcall.AbstractMethodCall

abstract class AbstractDateMethodCall : AbstractMethodCall() {

    override val classNames: List<String> by lazy { SHARED_CLASS_NAMES }

    companion object {
        val SHARED_CLASS_NAMES = listOf("java.time.LocalDate")
    }
}