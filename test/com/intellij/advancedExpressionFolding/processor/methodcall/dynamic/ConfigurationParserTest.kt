package com.intellij.advancedExpressionFolding.processor.methodcall.dynamic

import com.intellij.advancedExpressionFolding.processor.methodcall.MethodCallFactory
import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertDoesNotThrow
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import java.nio.file.Files
import java.nio.file.Path
import kotlin.io.path.deleteIfExists
import kotlin.io.path.writeText

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ConfigurationParserTest {

    private lateinit var configDir: Path
    private var originalConfigPath: String? = null

    @BeforeAll
    fun setUpConfigDir() {
        originalConfigPath = System.getProperty(CONFIG_PATH_PROPERTY)
        configDir = Files.createTempDirectory("dynamic-ajf2-test")
        System.setProperty(CONFIG_PATH_PROPERTY, configDir.toString())
    }

    @AfterEach
    fun cleanConfigFile() {
        configDir.resolve(CONFIG_FILE_NAME).deleteIfExists()
    }

    @AfterAll
    fun tearDownConfigDir() {
        cleanConfigFile()
        configDir.deleteIfExists()
        val original = originalConfigPath
        if (original != null) {
            System.setProperty(CONFIG_PATH_PROPERTY, original)
        } else {
            System.clearProperty(CONFIG_PATH_PROPERTY)
        }
    }

    @Test
    fun parseSkipsEntriesMissingRequiredKeys() {
        val configPath = configDir.resolve(CONFIG_FILE_NAME)
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

    @Test
    fun parseReturnsEmptyListWhenConfigurationMissing() {
        val parsed = ConfigurationParser.parse()
        assertTrue(parsed.isEmpty(), "Expected no dynamic mappings when configuration is absent")

        assertDoesNotThrow {
            MethodCallFactory.refreshMethodCallMappings(ConfigurationParser)
        }
    }

    @Test
    fun parseReturnsEmptyListWhenTomlCorrupted() {
        val configPath = configDir.resolve(CONFIG_FILE_NAME)
        configPath.writeText("invalid = [[[toml")

        val parsed = ConfigurationParser.parse()
        assertTrue(parsed.isEmpty(), "Expected parser to ignore corrupt dynamic configuration")

        assertDoesNotThrow {
            MethodCallFactory.refreshMethodCallMappings(ConfigurationParser)
        }
    }

    companion object {
        private const val CONFIG_PATH_PROPERTY = "idea.config.path"
        private const val CONFIG_FILE_NAME = "dynamic-ajf2.toml"
    }
}
