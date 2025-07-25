package com.intellij.advancedExpressionFolding.processor.methodcall.math

import com.intellij.advancedExpressionFolding.processor.methodcall.AbstractMethodCall
import com.intellij.advancedExpressionFolding.processor.methodcall.NeedsQualifier

abstract class AbstractMathMethodCall : AbstractMethodCall(), NeedsQualifier {
    override fun permission() = arithmeticExpressions
    
    override val classNames by lazy { 
        listOf("java.lang.Math") 
    }
}
