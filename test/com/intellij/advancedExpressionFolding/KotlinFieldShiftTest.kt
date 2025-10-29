package com.intellij.advancedExpressionFolding

import com.intellij.advancedExpressionFolding.TestDynamicDataProvider
import com.intellij.advancedExpressionFolding.processor.methodcall.MethodCallFactory
import com.intellij.advancedExpressionFolding.processor.methodcall.dynamic.IDynamicDataProvider
import com.intellij.advancedExpressionFolding.settings.AdvancedExpressionFoldingSettings.Companion.getInstance
import com.intellij.advancedExpressionFolding.settings.AdvancedExpressionFoldingSettings.State
import com.intellij.platform.testFramework.core.FileComparisonFailedError
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import kotlin.reflect.KMutableProperty0

class KotlinFieldShiftTest : BaseTest() {

    private val state: State by lazy { getInstance().state }

    @BeforeEach
    fun ensureSettingsService() {
        getInstance()
    }

    override fun getTestFileName(testName: String) =
        "testData/${testName.replaceFirstChar { it.uppercaseChar().toString() }}.kt"

    private fun assignState(vararg properties: KMutableProperty0<Boolean>) {
        getInstance().disableAll()
        properties.forEach { it.set(true) }
    }

    private fun doKotlinFoldingTest(
        vararg properties: KMutableProperty0<Boolean>,
        dynamic: IDynamicDataProvider = TestDynamicDataProvider(),
    ) {
        assignState(*properties)
        MethodCallFactory.initialize(dynamic)
        try {
            super.doFoldingTest(null)
        } catch (_: FileComparisonFailedError) {
            throw AssertionError("Folding result changed")
        }
    }

    @Test
    fun fieldShiftAssignmentKotlinTestData() {
        doKotlinFoldingTest(state::fieldShift)
    }

    @Test
    fun fieldShiftBuilderKotlinTestData() {
        doKotlinFoldingTest(state::fieldShift)
    }

    @Test
    fun fieldShiftBuilderNegativeKotlinTestData() {
        doKotlinFoldingTest(state::fieldShift)
    }
}
