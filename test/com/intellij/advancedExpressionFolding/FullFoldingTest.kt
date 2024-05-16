package com.intellij.advancedExpressionFolding

import com.intellij.advancedExpressionFolding.AdvancedExpressionFoldingSettings.Companion.getInstance
import java.io.File
import java.nio.file.Files

class FullFoldingTest : FoldingTest() {

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
    }

    override fun testDestructuringAssignmentListWithoutValTestData() {
        // ignored, already tested in testDestructuringAssignmentListTestData
    }

    override fun testAppendSetterInterpolatedStringTestData() {
        //FIXME: java.lang.IllegalArgumentException: Comparison method violates its general contract!
        //super.testAppendSetterInterpolatedStringTestData()
    }

    override fun testLombokTestData() {
        super.testLombokTestData()
    }

    override fun testNullableAnnotationTestData() {
        //FIXME: java.lang.IllegalArgumentException: Comparison method violates its general contract!
        //super.testNullableAnnotationTestData()
    }
}
