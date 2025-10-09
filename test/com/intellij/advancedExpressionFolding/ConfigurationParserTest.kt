package com.intellij.advancedExpressionFolding

import com.intellij.advancedExpressionFolding.processor.methodcall.dynamic.ConfigurationParser
import com.intellij.testFramework.junit5.TestApplication
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.io.TempDir
import java.nio.file.Files
import java.nio.file.Path

@TestApplication
class ConfigurationParserTest {
    @TempDir
    lateinit var tempDir: Path

    private lateinit var originalUserHome: String

    @BeforeEach
    fun setUp() {
        originalUserHome = System.getProperty("user.home")
        System.setProperty("user.home", tempDir.toString())
    }

    @AfterEach
    fun tearDown() {
        System.setProperty("user.home", originalUserHome)
    }

    @Test
    fun `add update and remove dynamic methods`() {
        val configFile = tempDir.resolve("dynamic-ajf2.toml")

        assertTrue(ConfigurationParser.parse().isEmpty())

        ConfigurationParser.addOrUpdateMethod("staticMethod", "changedStaticMethod")
        val added = ConfigurationParser.parse()
        assertEquals(1, added.size)
        val data = added.single().data
        assertEquals("staticMethod", data.method)
        assertEquals("changedStaticMethod", data.newName)
        assertTrue(Files.exists(configFile))

        ConfigurationParser.addOrUpdateMethod("staticMethod", "changedAgain")
        val updated = ConfigurationParser.parse()
        assertEquals("changedAgain", updated.single().data.newName)

        ConfigurationParser.remove("staticMethod")
        assertTrue(ConfigurationParser.parse().isEmpty())
        assertFalse(Files.exists(configFile))
    }
}
