package com.intellij.advancedExpressionFolding.action

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.awt.Color.decode

class UpdateFoldedTextColorsActionTest {

    @Test
    fun `provides folded text color presentation for dark theme`() {
        val presentation = UpdateFoldedTextColorsAction.foldedTextColorPresentationForTheme(false)

        assertEquals("soft blue", presentation.label)
        assertEquals(decode("#7ca0bb"), presentation.color)
    }

    @Test
    fun `provides folded text color presentation for light theme`() {
        val presentation = UpdateFoldedTextColorsAction.foldedTextColorPresentationForTheme(true)

        assertEquals("dark navy", presentation.label)
        assertEquals(decode("#000091"), presentation.color)
    }
}
