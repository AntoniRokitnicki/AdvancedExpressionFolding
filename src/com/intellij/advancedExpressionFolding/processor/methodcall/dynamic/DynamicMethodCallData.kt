package com.intellij.advancedExpressionFolding.processor.methodcall.dynamic

import com.intellij.advancedExpressionFolding.processor.methodcall.MethodName

data class DynamicMethodCallData(val map: Map<String, String>) {
    val method: MethodName
        get() = MethodName(map.getValue("method"))
    val newName: MethodName
        get() = MethodName(map.getValue("newName"))
}
