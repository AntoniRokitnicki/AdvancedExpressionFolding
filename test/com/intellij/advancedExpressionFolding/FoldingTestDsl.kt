package com.intellij.advancedExpressionFolding

import com.intellij.advancedExpressionFolding.processor.methodcall.dynamic.IDynamicDataProvider
import kotlin.reflect.KMutableProperty0

data class FoldingTestCase(
    val testName: String,
    val toggles: List<KMutableProperty0<Boolean>>,
    val readOnly: Boolean,
    val dynamicProviderFactory: () -> IDynamicDataProvider,
) {
    override fun toString(): String = testName
}

class FoldingTestBuilder {
    private val cases = mutableListOf<FoldingTestCase>()

    fun folding(
        testName: String,
        vararg toggles: KMutableProperty0<Boolean>,
        dynamic: () -> IDynamicDataProvider = { TestDynamicDataProvider() },
    ) {
        cases += FoldingTestCase(
            testName = testName,
            toggles = toggles.toList(),
            readOnly = false,
            dynamicProviderFactory = dynamic,
        )
    }

    fun readOnly(
        testName: String,
        vararg toggles: KMutableProperty0<Boolean>,
    ) {
        cases += FoldingTestCase(
            testName = testName,
            toggles = toggles.toList(),
            readOnly = true,
            dynamicProviderFactory = { TestDynamicDataProvider() },
        )
    }

    fun skip(vararg testNames: String) {
        if (testNames.isEmpty()) return
        val names = testNames.toSet()
        cases.removeAll { it.testName in names }
    }

    internal fun build(): List<FoldingTestCase> = cases.toList()
}

fun buildFoldingTestCases(block: FoldingTestBuilder.() -> Unit): List<FoldingTestCase> =
    FoldingTestBuilder().apply(block).build()
