package com.intellij.advancedExpressionFolding.settings

import com.intellij.advancedExpressionFolding.settings.AdvancedExpressionFoldingSettings
import com.intellij.advancedExpressionFolding.settings.view.SettingsConfigurable
import com.intellij.openapi.application.ApplicationManager
import com.intellij.openapi.editor.EditorFactory
import com.intellij.openapi.ui.DialogPanel
import com.intellij.openapi.projectRoots.Sdk
import com.intellij.testFramework.IdeaTestUtil
import com.intellij.testFramework.LightProjectDescriptor
import com.intellij.testFramework.fixtures.LightJavaCodeInsightFixtureTestCase5
import com.intellij.ui.components.JBCheckBox
import org.junit.jupiter.api.Assertions.assertDoesNotThrow
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test
import java.util.concurrent.atomic.AtomicReference

class SettingsConfigurablePreviewTest : LightJavaCodeInsightFixtureTestCase5(JAVA_18) {

    override fun getTestDataPath(): String = ""

    @Test
    fun `toggling getter setting updates preview`() {
        val configurable = SettingsConfigurable()
        try {
            val panelRef = AtomicReference<DialogPanel>()
            ApplicationManager.getApplication().invokeAndWait {
                panelRef.set(configurable.createComponent())
            }
            assertNotNull(panelRef.get())

            val state = AdvancedExpressionFoldingSettings.getInstance().state
            val checkboxRef = AtomicReference<JBCheckBox?>()
            ApplicationManager.getApplication().invokeAndWait {
                checkboxRef.set(configurable.checkboxForTest(state::getSetExpressionsCollapse))
            }
            val checkbox = checkboxRef.get()
            assertNotNull(checkbox, "Getter/setter checkbox should be available in settings")
            val toggle = checkbox!!

            fun placeholders(): List<String> {
                val result = AtomicReference<List<String>>()
                ApplicationManager.getApplication().invokeAndWait {
                    result.set(configurable.previewFoldPlaceholdersForTest())
                }
                return result.get()
            }

            val initialPlaceholders = placeholders()
            assertNotNull(initialPlaceholders)

            val disabledPlaceholders = assertDoesNotThrow<List<String>> {
                ApplicationManager.getApplication().invokeAndWait {
                    toggle.doClick()
                }
                placeholders()
            }

            val reenabledPlaceholders = assertDoesNotThrow<List<String>> {
                ApplicationManager.getApplication().invokeAndWait {
                    toggle.doClick()
                }
                placeholders()
            }

            assertNotNull(disabledPlaceholders)
            assertNotNull(reenabledPlaceholders)
        } finally {
            ApplicationManager.getApplication().invokeAndWait {
                configurable.disposeUIResources()
                val editorFactory = EditorFactory.getInstance()
                editorFactory.allEditors.forEach { editor ->
                    if (!editor.isDisposed) {
                        editorFactory.releaseEditor(editor)
                    }
                }
            }
        }

    }

    companion object {
        private val JAVA_18 = object : LightProjectDescriptor() {
            override fun getSdk(): Sdk = IdeaTestUtil.getMockJdk18()
        }
    }
}
