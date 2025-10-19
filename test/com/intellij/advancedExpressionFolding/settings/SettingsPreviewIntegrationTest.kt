package com.intellij.advancedExpressionFolding.settings

import com.intellij.advancedExpressionFolding.BaseTest
import com.intellij.advancedExpressionFolding.settings.view.SettingsConfigurable
import com.intellij.openapi.editor.EditorFactory
import com.intellij.openapi.ui.DialogPanel
import com.intellij.testFramework.runInEdtAndGet
import com.intellij.ui.components.JBCheckBox
import com.intellij.util.ui.UIUtil
import org.junit.jupiter.api.Assertions.assertNotEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class SettingsPreviewIntegrationTest : BaseTest() {

    @Test
    fun previewUpdatesWhenTogglingPrintln() {
        val configurable = object : SettingsConfigurable() {
            override fun currentProjectOrDefault() = this@SettingsPreviewIntegrationTest.fixture.project
        }
        val dialogPanel: DialogPanel = runInEdtAndGet { configurable.createComponent() }

        try {
            val initialPreview = runInEdtAndGet { configurable.previewTextForTests() }
            assertTrue(initialPreview.isNotBlank())
            val initialDescriptorCount = runInEdtAndGet { configurable.previewDescriptorCountForTests() }
            assertTrue(initialDescriptorCount > 0)

            val checkbox = runInEdtAndGet {
                UIUtil.findComponentsOfType(dialogPanel, JBCheckBox::class.java)
                    .first { it.text.contains("System.out.println") }
            }

            runInEdtAndGet { checkbox.doClick() }

            val updatedPreview = runInEdtAndGet { configurable.previewTextForTests() }
            val updatedDescriptorCount = runInEdtAndGet { configurable.previewDescriptorCountForTests() }
            assertTrue(updatedPreview.contains("System.out.println"))
            assertTrue(updatedDescriptorCount < initialDescriptorCount)
            assertNotEquals(initialPreview, updatedPreview)
        } finally {
            runInEdtAndGet {
                val factory = EditorFactory.getInstance()
                factory.allEditors
                    .filter { editor ->
                        val text = editor.document.text
                        text.contains("PreviewSample") || text.isBlank()
                    }
                    .forEach { factory.releaseEditor(it) }
                configurable.disposeUIResources()
            }
        }
    }
}
