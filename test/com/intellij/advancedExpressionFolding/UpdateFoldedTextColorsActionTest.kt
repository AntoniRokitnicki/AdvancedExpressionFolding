package com.intellij.advancedExpressionFolding

import com.intellij.advancedExpressionFolding.action.UpdateFoldedTextColorsAction
import com.intellij.openapi.application.ApplicationManager
import com.intellij.openapi.editor.colors.EditorColors
import com.intellij.openapi.editor.colors.EditorColorsManager
import com.intellij.openapi.editor.colors.EditorColorsScheme
import com.intellij.openapi.editor.markup.TextAttributes
import com.intellij.ui.JBColor
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNull
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.awt.Color

class UpdateFoldedTextColorsActionTest : BaseTest() {

    private lateinit var scheme: EditorColorsScheme
    private lateinit var originalAttributes: TextAttributes
    private var originalIsBright: Boolean = true

    @BeforeEach
    fun captureOriginalSchemeAttributes() {
        scheme = EditorColorsManager.getInstance().globalScheme
        val attributes = requireNotNull(
            scheme.getAttributes(EditorColors.FOLDED_TEXT_ATTRIBUTES)
        ) { "Global scheme must provide folded text attributes for tests" }
        originalAttributes = attributes.clone()
        originalIsBright = JBColor.isBright()
    }

    @AfterEach
    fun restoreOriginalSchemeAttributes() {
        JBColor.setDark(!originalIsBright)
        scheme.setAttributes(EditorColors.FOLDED_TEXT_ATTRIBUTES, originalAttributes.clone())
    }

    @Test
    fun changeFoldingColorsUsesSoftBlueForegroundForDarkThemes() {
        JBColor.setDark(true)

        ApplicationManager.getApplication().invokeAndWait {
            UpdateFoldedTextColorsAction.changeFoldingColors()
        }

        val updated = requireNotNull(
            EditorColorsManager.getInstance().globalScheme.getAttributes(EditorColors.FOLDED_TEXT_ATTRIBUTES)
        )
        assertEquals(Color.decode("#7ca0bb"), updated.foregroundColor)
        assertNull(updated.backgroundColor)
        assertEquals(originalAttributes.effectColor, updated.effectColor)
        assertEquals(originalAttributes.effectType, updated.effectType)
        assertEquals(originalAttributes.fontType, updated.fontType)
    }

    @Test
    fun changeFoldingColorsUsesDarkNavyForegroundForBrightThemes() {
        JBColor.setDark(false)

        ApplicationManager.getApplication().invokeAndWait {
            UpdateFoldedTextColorsAction.changeFoldingColors()
        }

        val updated = requireNotNull(
            EditorColorsManager.getInstance().globalScheme.getAttributes(EditorColors.FOLDED_TEXT_ATTRIBUTES)
        )
        assertEquals(Color.decode("#000091"), updated.foregroundColor)
        assertNull(updated.backgroundColor)
        assertEquals(originalAttributes.effectColor, updated.effectColor)
        assertEquals(originalAttributes.effectType, updated.effectType)
        assertEquals(originalAttributes.fontType, updated.fontType)
    }
}
