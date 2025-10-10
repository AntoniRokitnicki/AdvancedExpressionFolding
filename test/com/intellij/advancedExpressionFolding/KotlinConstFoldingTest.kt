package com.intellij.advancedExpressionFolding

import org.junit.jupiter.api.Test
import java.io.File

class KotlinConstFoldingTest : FoldingTest() {
    override fun getTestFileName(testName: String): String {
        val baseFile = File(super.getTestFileName(testName))
        val parent = baseFile.parentFile
        return File(parent, "kotlin/${baseFile.nameWithoutExtension}.kt").canonicalPath
    }

    /**
     * [data.kotlin.ConstModifierOrderTestData]
     */
    @Test
    fun constModifierOrderTestData() {
        doFoldingTest(state::const)
    }
}
