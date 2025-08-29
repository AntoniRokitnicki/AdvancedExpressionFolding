package com.intellij.advancedExpressionFolding

import com.intellij.advancedExpressionFolding.settings.AdvancedExpressionFoldingSettings.Companion.getInstance
import com.intellij.advancedExpressionFolding.settings.AdvancedExpressionFoldingSettings.State
import org.junit.jupiter.api.Test
import java.io.File
import java.nio.file.Files
import kotlin.reflect.KMutableProperty0

class FullFoldingTest : FoldingTest() {

    @Suppress("DEPRECATION")
    override fun assignState(turnOnProperties: Array<out KMutableProperty0<Boolean>>) =
        getInstance().enableAll(State::emojify, State::finalEmoji, State::arithmeticExpressions)

    override fun getTestFileName(testName: String): String {
        val baseFile = super.getTestFileName(testName)
        val file = getAllTestFileName(baseFile)
        if (!file.exists()) {
            Files.copy(File(baseFile).toPath(), file.toPath())
        }
        return file.canonicalPath
    }

    override fun destructuringAssignmentArrayWithoutValTestData() {
        // ignored, already tested in destructuringAssignmentArrayTestData
    }

    override fun destructuringAssignmentListWithoutValTestData() {
        // ignored, already tested in destructuringAssignmentListTestData
    }

    @Test
    override fun lombokTestData() {
        super.lombokTestData()
    }

}
