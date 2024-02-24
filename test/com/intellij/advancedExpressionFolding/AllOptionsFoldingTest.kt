package com.intellij.advancedExpressionFolding

import com.intellij.advancedExpressionFolding.AdvancedExpressionFoldingSettings.Companion.getInstance
import java.io.File
import java.nio.file.Files

class AllOptionsFoldingTest : FoldingTest() {

    override fun getTestFileName(testName: String): String {
        val baseFile = super.getTestFileName(testName)
        val allName = baseFile.replace(".java", "-all.java")
        val file = File(allName)
        if (!file.exists()) {
            Files.copy(File(baseFile).toPath(), file.toPath())
        }
        return allName
    }

    override fun setUp() {
        super.setUp()
        getInstance().enableAll()
    }
}
