package com.intellij.advancedExpressionFolding.extension.methodcall.dynamic

import com.intellij.advancedExpressionFolding.extension.methodcall.MethodName

data class DynamicMethodCallData(val map: Map<String, String>) {
    val method: MethodName by map
    val newName: MethodName by map
}