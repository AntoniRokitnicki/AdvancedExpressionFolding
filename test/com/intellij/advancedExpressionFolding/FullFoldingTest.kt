package com.intellij.advancedExpressionFolding

import com.intellij.advancedExpressionFolding.settings.AdvancedExpressionFoldingSettings.Companion.getInstance
import io.kotest.core.spec.style.FunSpec
import java.io.File
import java.nio.file.Files
import kotlin.reflect.KMutableProperty0

class FullFoldingTest : FunSpec({
    registerStandardFoldingTests(
        caseFactory = ::FullFoldingTestCase,
        excludedTests = setOf(
            "destructuringAssignmentArrayWithoutValTestData",
            "destructuringAssignmentListWithoutValTestData",
        ),
    )
})

private class FullFoldingTestCase : FoldingTestCase() {

    @Suppress("DEPRECATION")
    override fun assignState(vararg turnOnProperties: KMutableProperty0<Boolean>) {
        getInstance().enableAll(state::emojify, state::finalEmoji, state::arithmeticExpressions)
    }

    override fun getTestFileName(testName: String): String {
        val baseFile = super.getTestFileName(testName)
        val file = BaseTest.getAllTestFileName(baseFile)
        if (!file.exists()) {
            Files.copy(File(baseFile).toPath(), file.toPath())
        }
        return file.canonicalPath
    }
}
