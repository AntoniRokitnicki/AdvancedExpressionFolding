package com.intellij.advancedExpressionFolding.processor.methodcall.dynamic

import com.intellij.advancedExpressionFolding.processor.methodcall.MethodCallFactory
import org.junit.jupiter.api.Assertions.assertDoesNotThrow
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import java.nio.file.Files
import java.nio.file.Path
import kotlin.io.path.createDirectories
import kotlin.io.path.writeText

class ConfigurationParserTest {

    @Test
    fun parseReturnsDynamicMethodCallsForValidEntries() {
        withTemporaryHome { temporaryHome ->
            val configPath = temporaryHome.resolve("dynamic-ajf2.toml")
            configPath.parent?.createDirectories()
            configPath.writeText(
                """
                [valid]
                method = "sourceMethod"
                newName = "Renamed method"
                """.trimIndent(),
            )

            val parsed = ConfigurationParser.parse()

            assertEquals(1, parsed.size)
            val data = parsed.single().data
            assertEquals("sourceMethod", data.method)
            assertEquals("Renamed method", data.newName)
        }
    }

    @Test
    fun parseSkipsEntriesMissingRequiredKeys() {
        withTemporaryHome { temporaryHome ->
            val configPath = temporaryHome.resolve("dynamic-ajf2.toml")
            configPath.parent?.createDirectories()
            configPath.writeText(
                """
                [missingNewName]
                method = "sourceMethod"
                """.trimIndent()
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
        withTemporaryHome {
            val parsed = ConfigurationParser.parse()
            assertTrue(parsed.isEmpty(), "Expected empty list when dynamic configuration file is absent")

            assertDoesNotThrow {
                MethodCallFactory.refreshMethodCallMappings(ConfigurationParser)
            }
        }
    }

    @Test
    fun parseSkipsEntriesWithBlankValues() {
        withTemporaryHome { temporaryHome ->
            val configPath = temporaryHome.resolve("dynamic-ajf2.toml")
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

            assertDoesNotThrow {
                MethodCallFactory.refreshMethodCallMappings(ConfigurationParser)
            }
        }
    }

    private fun withTemporaryHome(block: (Path) -> Unit) {
        val originalUserHome = System.getProperty("user.home")
        val temporaryHome = Files.createTempDirectory("dynamic-ajf2-test")

        try {
            System.setProperty("user.home", temporaryHome.toString())
            block(temporaryHome)
        } finally {
            Files.deleteIfExists(temporaryHome.resolve("dynamic-ajf2.toml"))
            Files.deleteIfExists(temporaryHome)
            if (originalUserHome != null) {
                System.setProperty("user.home", originalUserHome)
            } else {
                System.clearProperty("user.home")
            }
        }
    }
}
