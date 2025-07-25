package com.intellij.advancedExpressionFolding.processor.methodcall.stream

import com.intellij.advancedExpressionFolding.processor.methodcall.AbstractMethodCall
import com.intellij.advancedExpressionFolding.processor.methodcall.NeedsQualifier

abstract class AbstractStreamMethodCall : AbstractMethodCall(), NeedsQualifier {
    override fun permission() = streamSpread
    
    override val classNames by lazy { listOf("java.util.stream.Stream") }
}
