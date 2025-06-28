package com.intellij.advancedExpressionFolding.extension.methodcall.math

import com.intellij.advancedExpressionFolding.extension.methodcall.AbstractMethodCall
import com.intellij.advancedExpressionFolding.extension.methodcall.NeedsQualifier

abstract class AbstractMathMethodCall : AbstractMethodCall(), NeedsQualifier {
    override fun permission() = arithmeticExpressions
    
    override val classNames by lazy { 
        listOf("java.lang.Math") 
    }
}
