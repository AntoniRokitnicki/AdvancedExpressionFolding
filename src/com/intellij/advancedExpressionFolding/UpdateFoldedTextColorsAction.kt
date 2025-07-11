package com.intellij.advancedExpressionFolding

import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.editor.colors.EditorColors
import com.intellij.openapi.editor.colors.EditorColorsManager
import com.intellij.openapi.editor.markup.TextAttributes
import com.intellij.ui.JBColor
import java.awt.Color.decode

class UpdateFoldedTextColorsAction : AnAction() {
    override fun actionPerformed(event: AnActionEvent) = changeFoldingColors()

    fun changeFoldingColors() {
        val scheme = EditorColorsManager.getInstance().globalScheme
        val textAttributes = scheme.getAttributes(EditorColors.FOLDED_TEXT_ATTRIBUTES)
        val foregroundColor = if (!JBColor.isBright()) {
            decode("#7ca0bb")
        } else {
            decode("#000091")
        }
        val backgroundColor = null
        val foldedAttributes = TextAttributes(
            foregroundColor,
            backgroundColor,
            textAttributes.effectColor,
            textAttributes.effectType,
            textAttributes.fontType
        )
        scheme.setAttributes(EditorColors.FOLDED_TEXT_ATTRIBUTES, foldedAttributes)
    }
}
