package com.intellij.advancedExpressionFolding

import com.intellij.advancedExpressionFolding.AdvancedExpressionFoldingSettings.Companion.getInstance
import java.io.File
import java.nio.file.Files
import kotlin.reflect.KMutableProperty0

class FullFoldingTest : FoldingTest() {

    override fun doFoldingTest(vararg turnOnProperties: KMutableProperty0<Boolean>) {
        try {
            super.doFoldingTest(*turnOnProperties)
        } catch (e: TooComplexException) {
            println(e)
        }
    }

    override fun getTestFileName(testName: String): String {
        val baseFile = super.getTestFileName(testName)
        val file = getAllTestFileName(baseFile)
        if (!file.exists()) {
            Files.copy(File(baseFile).toPath(), file.toPath())
        }
        return file.canonicalPath
    }

    override fun setUp() {
        super.setUp()
        getInstance().enableAll()
    }

    override fun testDestructuringAssignmentArrayWithoutValTestData() {
        // ignored, already tested in testDestructuringAssignmentArrayTestData
        super.testDestructuringAssignmentArrayWithoutValTestData()
    }

    override fun testDestructuringAssignmentListWithoutValTestData() {
        // ignored, already tested in testDestructuringAssignmentListTestData
    }
}
