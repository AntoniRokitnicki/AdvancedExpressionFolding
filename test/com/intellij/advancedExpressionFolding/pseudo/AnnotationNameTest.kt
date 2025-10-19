package com.intellij.advancedExpressionFolding.pseudo

import org.junit.jupiter.api.Assertions.assertDoesNotThrow
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class AnnotationNameTest {

    @Test
    fun `should accept valid annotation names`() {
        assertDoesNotThrow { AnnotationName("Loggable") }
        assertDoesNotThrow { AnnotationName("com.example.Valid") }
        assertDoesNotThrow { AnnotationName("_Underscore") }
    }

    @ParameterizedTest
    @ValueSource(strings = ["", " ", "1Invalid", "Invalid-Name", "com..example", ".Leading", "Trailing.", "inv@lid"])
    fun `should reject invalid annotation names`(candidate: String) {
        assertThrows(IllegalArgumentException::class.java) {
            AnnotationName(candidate)
        }
    }
}
