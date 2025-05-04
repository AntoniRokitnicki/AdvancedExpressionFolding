package com.intellij.advancedExpressionFolding

import com.intellij.advancedExpressionFolding.extension.methodcall.MethodCallFactory
import com.intellij.openapi.components.Service
import com.intellij.openapi.components.service

@Service
class MethodCallFoldingLoaderService {

    val factory by lazy(LazyThreadSafetyMode.PUBLICATION) {
        MethodCallFactory.initialize()
    }

    companion object {
        fun get() = service<MethodCallFoldingLoaderService>()
        @JvmStatic
        fun factory() = get().factory
    }
}

