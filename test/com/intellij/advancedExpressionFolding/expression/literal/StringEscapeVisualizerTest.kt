package com.intellij.advancedExpressionFolding.expression.literal

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class StringEscapeVisualizerTest {
    @Test
    fun rendersVisibleTokens() {
        assertEquals("a⏎b", StringEscapeVisualizer.render("a\nb"))
        assertEquals("a⏎b", StringEscapeVisualizer.render("a\r\nb"))
        assertEquals("a␍b", StringEscapeVisualizer.render("a\rb"))
    }

    @Test
    fun rendersAsciiTokensWhenRequested() {
        val property = "advanced.expression.folding.stringEscapes.visualizeNewlines.asciiOnly"
        val original = System.getProperty(property)
        try {
            System.setProperty(property, "true")
            assertEquals("a\\nb", StringEscapeVisualizer.render("a\nb"))
            assertEquals("a\\nb", StringEscapeVisualizer.render("a\r\nb"))
            assertEquals("a\\rb", StringEscapeVisualizer.render("a\rb"))
        } finally {
            if (original == null) {
                System.clearProperty(property)
            } else {
                System.setProperty(property, original)
            }
        }
    }
}
