package com.intellij.advancedExpressionFolding.processor.methodcall.optional

import com.intellij.advancedExpressionFolding.processor.methodcall.AbstractMethodCall
import com.intellij.advancedExpressionFolding.processor.methodcall.NeedsQualifier

abstract class AbstractOptionalMethodCall : AbstractMethodCall(), NeedsQualifier {
    override fun permission() = optional
    
    override val classNames by lazy { listOf("java.util.Optional") }
}
