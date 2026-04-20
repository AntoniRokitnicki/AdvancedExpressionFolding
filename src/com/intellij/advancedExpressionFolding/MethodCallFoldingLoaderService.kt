package com.intellij.advancedExpressionFolding

import com.intellij.advancedExpressionFolding.processor.methodcall.MethodCallFactory
import com.intellij.openapi.components.Service
import com.intellij.openapi.components.service

@Service
internal class MethodCallFoldingLoaderService {

    @PublishedApi
    internal val factory by lazy(LazyThreadSafetyMode.PUBLICATION) {
        MethodCallFactory.initialize()
    }

    companion object {
        @PublishedApi
        internal fun get() = service<MethodCallFoldingLoaderService>()
        @JvmStatic
        @PublishedApi
        internal fun factory() = get().factory
    }
}

