package com.intellij.advancedExpressionFolding.processor.methodcall.dynamic

import com.intellij.advancedExpressionFolding.processor.methodcall.MethodCallFactory
import org.junit.jupiter.api.Assertions.assertDoesNotThrow
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import java.nio.file.Files
import kotlin.io.path.createDirectories
import kotlin.io.path.writeText

class ConfigurationParserTest {

    @Test
    fun parseSkipsEntriesMissingRequiredKeys() {
        val originalUserHome = System.getProperty("user.home")
        val temporaryHome = Files.createTempDirectory("dynamic-ajf2-test")

        try {
            System.setProperty("user.home", temporaryHome.toString())

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
