package com.intellij.advancedExpressionFolding.processor.methodcall.dynamic

import com.intellij.advancedExpressionFolding.processor.methodcall.MethodCallFactory
import com.intellij.openapi.diagnostic.Logger
import org.junit.jupiter.api.Assertions.assertDoesNotThrow
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import java.nio.file.Files
import java.nio.file.Path
import java.util.concurrent.ConcurrentHashMap
import kotlin.io.path.createDirectories
import kotlin.io.path.writeText

class ConfigurationParserTest {

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

    @Test
    fun parseReturnsEmptyListAndLogsWarningWhenFileTooLarge() {
        withTemporaryHome { temporaryHome ->
            val configPath = temporaryHome.resolve("dynamic-ajf2.toml")
            configPath.parent?.createDirectories()
            val oversizedBytes = ByteArray((ConfigurationParser.MAX_SUPPORTED_FILE_SIZE_BYTES + 1).toInt()) { 'a'.code.toByte() }
            Files.write(configPath, oversizedBytes)

            val previousFactory = Logger.getFactory()
            val capturingFactory = CapturingLoggerFactory()
            Logger.setFactory(capturingFactory)

            try {
                val parsed = ConfigurationParser.parse()
                assertTrue(parsed.isEmpty(), "Expected empty list when configuration file exceeds supported size")

                val configurationWarnings = capturingFactory.getLoggerForCategory("#" + ConfigurationParser::class.java.name)?.warnings.orEmpty()
                val providerWarnings = capturingFactory.getLoggerForCategory("#" + IDynamicDataProvider::class.java.name)?.warnings.orEmpty()
                val recordedWarnings = configurationWarnings + providerWarnings

                assertTrue(
                    recordedWarnings.any { it.contains("exceeds supported size") },
                    "Expected warning about oversized configuration file to be logged"
                )
            } finally {
                Logger.setFactory(previousFactory)
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

private class CapturingLoggerFactory : Logger.Factory {
    private val loggers = ConcurrentHashMap<String, CapturingLogger>()

    override fun getLoggerInstance(category: String): Logger =
        loggers.computeIfAbsent(category) { CapturingLogger(it) }

    fun getLoggerForCategory(category: String): CapturingLogger? = loggers[category]
}

private class CapturingLogger(private val category: String) : Logger() {
    private val collectedWarnings = mutableListOf<String>()

    val warnings: List<String>
        get() = collectedWarnings

    override fun isDebugEnabled(): Boolean = false

    override fun debug(message: String, t: Throwable?) {
        // no-op for tests
    }

    override fun info(message: String, t: Throwable?) {
        // no-op for tests
    }

    override fun warn(message: String, t: Throwable?) {
        collectedWarnings.add(message)
    }

    override fun error(message: String, t: Throwable?, vararg details: String) {
        val detailText = if (details.isNotEmpty()) details.joinToString(prefix = "[", postfix = "]") else ""
        throw AssertionError("Unexpected error log from $category: $message $detailText", t)
    }
}
