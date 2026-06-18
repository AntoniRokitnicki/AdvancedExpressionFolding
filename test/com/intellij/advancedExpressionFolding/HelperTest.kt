package com.intellij.advancedExpressionFolding

import com.intellij.advancedExpressionFolding.processor.util.Helper
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class HelperTest {
    @Test
    fun startsWithHandlesNullAndPrefix() {
        assertTrue(Helper.startsWith("sample", "sam"))
        assertFalse(Helper.startsWith(null, "sam"))
    }

    @Test
    fun eraseGenericsRemovesGenericParts() {
        assertEquals("java.util.List", Helper.eraseGenerics("java.util.List<String>"))
    }
}
