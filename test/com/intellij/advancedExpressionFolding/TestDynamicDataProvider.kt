package com.intellij.advancedExpressionFolding

import com.intellij.advancedExpressionFolding.extension.methodcall.dynamic.DynamicMethodCall
import com.intellij.advancedExpressionFolding.extension.methodcall.dynamic.IDynamicDataProvider

open class TestDynamicDataProvider : IDynamicDataProvider {
    override fun parse(): List<DynamicMethodCall> = emptyList()
}