package com.intellij.advancedExpressionFolding.processor.methodcall.dynamic

import com.intellij.advancedExpressionFolding.processor.methodcall.MethodCallFactory
import com.intellij.openapi.application.PathManager
import org.junit.jupiter.api.Assertions.assertDoesNotThrow
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import java.nio.file.Files
import java.nio.file.Path
import java.util.Comparator
import kotlin.io.path.createDirectories
import kotlin.io.path.writeText

class ConfigurationParserTest {

    @Test
    fun parseSkipsEntriesMissingRequiredKeys() {
        withTemporaryConfigDir {
            val configPath = dynamicConfigPath()
            configPath.parent?.createDirectories()
            configPath.writeText(
                """
                [missingNewName]
                method = "sourceMethod"
                """.trimIndent(),
            )

            val parsed = ConfigurationParser.parse()
            assertTrue(parsed.isEmpty(), "Expected entries without required keys to be skipped")

            assertDoesNotThrow {
                MethodCallFactory.refreshMethodCallMappings(ConfigurationParser)
            }
        }
    }

    @Test
    fun parseReturnsEmptyListWhenConfigFileMissing() {
        withTemporaryConfigDir {
            val parsed = ConfigurationParser.parse()
            assertTrue(parsed.isEmpty(), "Expected empty list when dynamic configuration file is absent")

            assertDoesNotThrow {
                MethodCallFactory.refreshMethodCallMappings(ConfigurationParser)
            }
        }
    }

    @Test
    fun parseSkipsEntriesWithBlankValues() {
        withTemporaryConfigDir {
            val configPath = dynamicConfigPath()
            configPath.parent?.createDirectories()
            configPath.writeText(
                """
                [blankValues]
                method = ""
                newName = "   "
                """.trimIndent(),
            )

            val parsed = ConfigurationParser.parse()
            assertTrue(parsed.isEmpty(), "Expected entries with blank values to be skipped")

            assertDoesNotThrow {
                MethodCallFactory.refreshMethodCallMappings(ConfigurationParser)
            }
        }
    }

    private fun dynamicConfigPath(): Path {
        return PathManager.getConfigDir()
            .resolve("advanced-expression-folding")
            .resolve("dynamic-ajf2.toml")
    }

    private fun withTemporaryConfigDir(block: () -> Unit) {
        val originalConfigPath = System.getProperty(PathManager.PROPERTY_CONFIG_PATH)
        val originalConfigDir = System.getProperty("idea.config.dir")
        val temporaryConfigDir = Files.createTempDirectory("dynamic-ajf2-test")

        try {
            System.setProperty(PathManager.PROPERTY_CONFIG_PATH, temporaryConfigDir.toString())
            System.setProperty("idea.config.path", temporaryConfigDir.toString())
            System.setProperty("idea.config.dir", temporaryConfigDir.toString())
            block()
        } finally {
            Files.walk(temporaryConfigDir).use { stream ->
                stream.sorted(Comparator.reverseOrder())
                    .forEach { Files.deleteIfExists(it) }
            }
            restoreProperty(PathManager.PROPERTY_CONFIG_PATH, originalConfigPath)
            restoreProperty("idea.config.path", originalConfigPath)
            restoreProperty("idea.config.dir", originalConfigDir)
        }
    }

    private fun restoreProperty(key: String, value: String?) {
        if (value != null) {
            System.setProperty(key, value)
        } else {
            System.clearProperty(key)
        }
    }
}
