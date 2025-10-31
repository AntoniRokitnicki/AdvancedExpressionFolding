package com.intellij.advancedExpressionFolding.chaos

import com.intellij.advancedExpressionFolding.AdvancedExpressionFoldingBuilder
import com.intellij.advancedExpressionFolding.folding.BaseTest
import com.intellij.advancedExpressionFolding.folding.util.FoldingDataStorage
import com.intellij.advancedExpressionFolding.settings.AdvancedExpressionFoldingSettings
import com.intellij.advancedExpressionFolding.store
import com.intellij.openapi.application.ReadAction
import org.junit.jupiter.api.Assertions.assertDoesNotThrow
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.condition.EnabledIfEnvironmentVariable
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths
import kotlin.random.Random
import kotlin.streams.toList

@EnabledIfEnvironmentVariable(named = "chaos", matches = "1")
class ChaosFoldingTest : BaseTest() {

    @Test
    fun randomConfigurationsDoNotCrash() {
        val builder = AdvancedExpressionFoldingBuilder()
        val random = Random(0)
        val files = collectJavaFiles()
        check(files.isNotEmpty()) { "No test data files found for chaos testing." }

        val settings = AdvancedExpressionFoldingSettings.getInstance()
        val originalState = settings.state.copy()
        val originalStore = store

        try {
            repeat(iterationCount(files.size)) { iteration ->
                val previousStore = store
                store = FoldingDataStorage()
                try {
                    val file = files[random.nextInt(files.size)]
                    val text = readJavaFile(file)
                    fixture.configureByText(file.fileName.toString(), text)
                    val psiFile = fixture.file
                    val document = fixture.getDocument(psiFile)

                    assertDoesNotThrow(
                        {
                            ReadAction.run<RuntimeException> {
                                builder.buildFoldRegions(psiFile, document, random.nextBoolean())
                            }
                        },
                        "Chaos iteration $iteration failed for $file",
                    )
                } finally {
                    store = previousStore
                }
            }
        } finally {
            store = originalStore
            settings.loadState(originalState)
        }
    }

    private fun collectJavaFiles(): List<Path> =
        Files.walk(Paths.get(getTestDataPath())).use { stream ->
            stream
                .filter { Files.isRegularFile(it) && it.toString().endsWith(".java") && !it.fileName.toString().endsWith("-folded.java") }
                .toList()
        }

    private fun iterationCount(totalFiles: Int): Int = minOf(32, totalFiles * 2)

    private fun readJavaFile(path: Path): String =
        Files.readString(path)
            .replace(FOLD_START_REGEX, "")
            .replace(FOLD_END_TAG, "")

    private companion object {
        private val FOLD_START_REGEX = "<fold[^>]*>".toRegex()
        private const val FOLD_END_TAG = "</fold>"
    }
}
