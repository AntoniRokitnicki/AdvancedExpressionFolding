package com.intellij.advancedExpressionFolding

import org.junit.jupiter.api.Assertions.assertDoesNotThrow
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test
import data.VarTestData

class GradleExampleScopeTest {
    @Test
    fun `examples classes accessible`() {
        assertDoesNotThrow { VarTestData.main(arrayOf()) }
    }

    @Test
    fun `examples resources available`() {
        val resource = javaClass.classLoader.getResource("data/VarTestData.java")
        assertNotNull(resource)
    }
}
