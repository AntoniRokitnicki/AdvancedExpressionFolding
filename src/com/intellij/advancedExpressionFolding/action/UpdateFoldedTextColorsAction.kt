package com.intellij.advancedExpressionFolding.action

import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.editor.colors.EditorColors
import com.intellij.openapi.editor.colors.EditorColorsManager
import com.intellij.openapi.editor.markup.TextAttributes
import com.intellij.ui.JBColor
import java.awt.Color
import java.awt.Color.decode

class UpdateFoldedTextColorsAction : AnAction() {
    override fun actionPerformed(event: AnActionEvent) = changeFoldingColors()

    companion object {

        data class FoldedTextColorPresentation(val label: String, val color: Color)

        fun foldedTextColorPresentationForTheme(brightTheme: Boolean): FoldedTextColorPresentation {
            val color = if (!brightTheme) {
                decode("#7ca0bb")
            } else {
                decode("#000091")
            }
            val label = if (!brightTheme) {
                "soft blue"
            } else {
                "dark navy"
            }
            return FoldedTextColorPresentation(label, color)
        }

        fun foldedTextColorPresentationForCurrentTheme(): FoldedTextColorPresentation =
            foldedTextColorPresentationForTheme(JBColor.isBright())

        fun changeFoldingColors() {
            val scheme = EditorColorsManager.getInstance().globalScheme
            val textAttributes = scheme.getAttributes(EditorColors.FOLDED_TEXT_ATTRIBUTES)
            val presentation = foldedTextColorPresentationForCurrentTheme()
            val backgroundColor = null
            val foldedAttributes = TextAttributes(
                presentation.color,
                backgroundColor,
                textAttributes.effectColor,
                textAttributes.effectType,
                textAttributes.fontType
            )
            scheme.setAttributes(EditorColors.FOLDED_TEXT_ATTRIBUTES, foldedAttributes)
        }
    }
}
