package com.intellij.advancedExpressionFolding.extension.methodcall.stream

import com.intellij.advancedExpressionFolding.extension.methodcall.AbstractMethodCall
import com.intellij.advancedExpressionFolding.extension.methodcall.NeedsQualifier

abstract class AbstractStreamMethodCall : AbstractMethodCall(), NeedsQualifier {
    override fun permission() = streamSpread
    
    override val classNames by lazy { listOf("java.util.stream.Stream") }
}