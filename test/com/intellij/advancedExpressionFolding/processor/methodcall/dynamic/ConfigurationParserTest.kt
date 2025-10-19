package com.intellij.advancedExpressionFolding.processor.methodcall.dynamic

import com.intellij.advancedExpressionFolding.processor.methodcall.MethodCallFactory
import com.intellij.openapi.application.ApplicationManager
import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.Assertions.assertDoesNotThrow
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import java.nio.file.FileSystems
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.attribute.PosixFilePermissions
import java.util.Comparator
import kotlin.io.path.createDirectories
import kotlin.io.path.writeText

class ConfigurationParserTest {

    @Test
    fun parseSkipsEntriesMissingRequiredKeys() {
        withTemporaryConfigDir { configRoot ->
            val configPath = configRoot.resolve("advanced-expression-folding/dynamic-ajf2.toml")
            configPath.parent?.createDirectories()
            configPath.writeText(
                """
                [missingNewName]
                method = "sourceMethod"
                """.trimIndent()
            )

            val parsed = ConfigurationParser.parse()
            assertTrue(parsed.isEmpty(), "Expected entries without required keys to be skipped")

            if (ApplicationManager.getApplication() != null) {
                assertDoesNotThrow {
                    MethodCallFactory.refreshMethodCallMappings(ConfigurationParser)
                }
            }
        }
    }

    @Test
    fun parseReturnsEmptyListWhenConfigFileMissing() {
        withTemporaryConfigDir {
            val parsed = ConfigurationParser.parse()
            assertTrue(parsed.isEmpty(), "Expected empty list when dynamic configuration file is absent")

            if (ApplicationManager.getApplication() != null) {
                assertDoesNotThrow {
                    MethodCallFactory.refreshMethodCallMappings(ConfigurationParser)
                }
            }
        }
    }

    @Test
    fun parseSkipsEntriesWithBlankValues() {
        withTemporaryConfigDir { configRoot ->
            val configPath = configRoot.resolve("advanced-expression-folding/dynamic-ajf2.toml")
            configPath.parent?.createDirectories()
            configPath.writeText(
                """
                [blankValues]
                method = ""
                newName = "   "
                """.trimIndent()
            )

            val parsed = ConfigurationParser.parse()
            assertTrue(parsed.isEmpty(), "Expected entries with blank values to be skipped")

            if (ApplicationManager.getApplication() != null) {
                assertDoesNotThrow {
                    MethodCallFactory.refreshMethodCallMappings(ConfigurationParser)
                }
            }
        }
    }

    @Test
    fun parseIgnoresWorldReadableFileOnPosix() {
        if (!FileSystems.getDefault().supportedFileAttributeViews().contains("posix")) {
            return
        }

        withTemporaryConfigDir { configRoot ->
            val configPath = configRoot.resolve("advanced-expression-folding/dynamic-ajf2.toml")
            configPath.parent?.createDirectories()
            configPath.writeText(
                """
                [valid]
                method = "com.example.Source.method"
                newName = "Method"
                """.trimIndent()
            )

            Files.setPosixFilePermissions(configPath, PosixFilePermissions.fromString("rw-r--r--"))

            val parsed = ConfigurationParser.parse()
            assertTrue(parsed.isEmpty(), "Expected insecure configuration file to be ignored")
        }
    }

    private fun withTemporaryConfigDir(block: (Path) -> Unit) {
        cleanupConfigDir(testConfigRoot)
        Files.createDirectories(testConfigRoot)
        block(testConfigRoot)
        cleanupConfigDir(testConfigRoot)
    }

    companion object {
        private val testConfigRoot: Path = Files.createTempDirectory("dynamic-ajf2-test")
        private val originalConfigPath: String? = System.getProperty("idea.config.path")

        @JvmStatic
        @BeforeAll
        fun prepareConfigPath() {
            System.setProperty("idea.config.path", testConfigRoot.toString())
        }

        @JvmStatic
        @AfterAll
        fun cleanup() {
            cleanupConfigDir(testConfigRoot)
            Files.deleteIfExists(testConfigRoot)
            if (originalConfigPath != null) {
                System.setProperty("idea.config.path", originalConfigPath)
            } else {
                System.clearProperty("idea.config.path")
            }
        }

        private fun cleanupConfigDir(root: Path) {
            if (!Files.exists(root)) {
                return
            }

            Files.walk(root).use { paths ->
                paths.sorted(Comparator.reverseOrder()).forEach { path ->
                    if (path != root) {
                        Files.deleteIfExists(path)
                    }
                }
            }
        }
    }
}
