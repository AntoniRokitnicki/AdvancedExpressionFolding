package com.intellij.advancedExpressionFolding.extension.methodcall.extracted

import com.intellij.advancedExpressionFolding.extension.methodcall.AbstractMethodCall
import com.intellij.advancedExpressionFolding.extension.methodcall.NeedsQualifier

abstract class AbstractOptionalMethodCall : AbstractMethodCall(), NeedsQualifier {
    override fun permission() = optional
    
    override val classNames by lazy { listOf("java.util.Optional") }
}