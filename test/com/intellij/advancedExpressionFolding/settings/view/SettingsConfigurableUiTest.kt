package com.intellij.advancedExpressionFolding.settings.view

import com.intellij.advancedExpressionFolding.icons.AdvancedExpressionFoldingIcons
import com.intellij.advancedExpressionFolding.processor.util.Consts.Emoji
import com.intellij.openapi.application.ApplicationManager
import com.intellij.testFramework.LightPlatformTestCase
import com.intellij.ui.components.JBCheckBox
import com.intellij.ui.dsl.builder.panel
import com.intellij.util.ui.UIUtil
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Assertions.assertSame
import org.junit.jupiter.api.Assertions.assertTrue

class SettingsConfigurableUiTest : LightPlatformTestCase() {

    fun testIconsAreAttachedToPlayfulCheckboxes() {
        val checkboxes = mutableMapOf<String, JBCheckBox>()
        val expected = mapOf(
            "Emojify code" to AdvancedExpressionFoldingIcons.Emojify,
            "Log folding" to AdvancedExpressionFoldingIcons.LogFolding,
            "Log folding: collapse Text Blocks" to AdvancedExpressionFoldingIcons.LogFoldingTextBlocks,
            "Replace the 'final' modifier with ${Emoji.FINAL_LOCK}" to AdvancedExpressionFoldingIcons.FinalEmoji,
        )

        ApplicationManager.getApplication().invokeAndWait {
            val configurable = SettingsConfigurable()
            val state = com.intellij.advancedExpressionFolding.settings.AdvancedExpressionFoldingSettings.State()
            val dialogPanel = with(configurable) {
                panel {
                    registerCheckbox(state::emojify, "Emojify code") {
                        icon(AdvancedExpressionFoldingIcons.Emojify)
                    }
                    registerCheckbox(state::logFolding, "Log folding") {
                        icon(AdvancedExpressionFoldingIcons.LogFolding)
                    }
                    registerCheckbox(state::logFoldingTextBlocks, "Log folding: collapse Text Blocks") {
                        icon(AdvancedExpressionFoldingIcons.LogFoldingTextBlocks)
                    }
                    registerCheckbox(state::finalEmoji, "Replace the 'final' modifier with ${Emoji.FINAL_LOCK}") {
                        icon(AdvancedExpressionFoldingIcons.FinalEmoji)
                    }
                }
            }
            UIUtil.uiTraverser(dialogPanel)
                .filter(JBCheckBox::class.java)
                .forEach { checkboxes[it.text] = it }
            dialogPanel.javaClass.methods.firstOrNull { method ->
                method.name == "dispose" && method.parameterCount == 0
            }?.let { method ->
                method.isAccessible = true
                method.invoke(dialogPanel)
            }
        }

        expected.forEach { (label, icon) ->
            val checkbox = checkboxes[label] ?: error("Missing checkbox with text '$label'")
            val attached = checkbox.getClientProperty(SettingsConfigurable.CHILD_ICON_PROPERTY)
            assertSame(icon, attached, "Icon property should match for '$label'")
            val paintedIcon = checkbox.icon
            assertNotNull(paintedIcon, "Combined icon should be present for '$label'")
            assertTrue(
                paintedIcon.iconWidth > icon.iconWidth,
                "Combined icon should include default checkbox for '$label'",
            )
        }

        // Resources are released as soon as the panel is no longer needed.
    }
}
