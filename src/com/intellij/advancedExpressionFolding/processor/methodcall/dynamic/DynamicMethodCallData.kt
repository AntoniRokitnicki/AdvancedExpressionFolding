package com.intellij.advancedExpressionFolding.processor.methodcall.dynamic

import com.intellij.advancedExpressionFolding.processor.methodcall.MethodName

data class DynamicMethodCallData(
    val method: MethodName = "",
    val newName: MethodName = "",
) {
    fun validatedOrNull(): DynamicMethodCallData? {
        val normalizedMethod = method.trim()
        val normalizedNewName = newName.trim()

        if (normalizedMethod.isEmpty() || normalizedNewName.isEmpty()) {
            return null
        }

        return copy(method = normalizedMethod, newName = normalizedNewName)
    }
}
