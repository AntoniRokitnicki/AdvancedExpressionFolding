package com.intellij.advancedExpressionFolding.extension.methodcall.extracted

import com.intellij.advancedExpressionFolding.extension.methodcall.AbstractMethodCall
import com.intellij.advancedExpressionFolding.extension.methodcall.NeedsQualifier

abstract class AbstractCollectionMethodCall : AbstractMethodCall(), NeedsQualifier {
    override val classNames by lazy { 
        listOf(
            "java.util.List", 
            "java.util.ArrayList", 
            "java.util.Set", 
            "java.util.HashSet", 
            "java.util.Map", 
            "java.util.HashMap", 
            "java.util.Collection",
            "java.util.Collections",
        ) 
    }
}
