package com.intellij.advancedExpressionFolding

import com.intellij.advancedExpressionFolding.settings.AdvancedExpressionFoldingSettings.Companion.getInstance
import java.io.File
import java.nio.file.Files
import kotlin.reflect.KMutableProperty0

class FullFoldingTest : FoldingTest() {

    override val skippedGeneratedTests: Set<String> = super.skippedGeneratedTests + setOf(
        "destructuringAssignmentArrayWithoutValTestData",
        "destructuringAssignmentListWithoutValTestData",
    )

    @Suppress("DEPRECATION")
    override fun assignState(turnOnProperties: Array<out KMutableProperty0<Boolean>>) = getInstance().enableAll(state::emojify, state::finalEmoji, state::arithmeticExpressions)

    override fun getTestFileName(testName: String): String {
        val baseFile = super.getTestFileName(testName)
        val file = getAllTestFileName(baseFile)
        if (!file.exists()) {
            Files.copy(File(baseFile).toPath(), file.toPath())
        }
        return file.canonicalPath
    }

    protected override fun registerGeneratedTests(builder: FoldingTestBuilder) {
        super.registerGeneratedTests(builder)
        builder.skip(
            "destructuringAssignmentArrayWithoutValTestData",
            "destructuringAssignmentListWithoutValTestData",
        )
    }

}
