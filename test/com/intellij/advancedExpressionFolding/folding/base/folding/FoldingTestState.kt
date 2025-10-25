package com.intellij.advancedExpressionFolding.folding.base.folding

import com.intellij.advancedExpressionFolding.folding.BaseTest
import com.intellij.advancedExpressionFolding.folding.util.TestDynamicDataProvider
import com.intellij.advancedExpressionFolding.processor.methodcall.dynamic.IDynamicDataProvider
import com.intellij.advancedExpressionFolding.settings.AdvancedExpressionFoldingSettings.State
import kotlin.reflect.KMutableProperty0

internal fun foldingState(): State = State()()

interface FoldingTestSection {
    val testCase: FoldingFeatureTestCase
}

abstract class FoldingFeatureTestCase : BaseTest(), FoldingTestSection {
    final override val testCase: FoldingFeatureTestCase
        get() = this

    fun runFoldingTest(
        vararg turnOnProperties: KMutableProperty0<Boolean>,
        dynamic: IDynamicDataProvider = TestDynamicDataProvider(),
    ) {
        doFoldingTest(*turnOnProperties, dynamic = dynamic)
    }

    fun runReadOnlyFoldingTest(
        vararg turnOnProperties: KMutableProperty0<Boolean>,
        dynamic: IDynamicDataProvider = TestDynamicDataProvider(),
    ) {
        doReadOnlyFoldingTest(*turnOnProperties, dynamic = dynamic)
    }
}
