package com.intellij.advancedExpressionFolding.settings.view

import com.intellij.ui.components.ActionLink
import com.intellij.ui.components.JBCheckBox
import org.junit.jupiter.api.Assertions.assertDoesNotThrow
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test
import java.awt.event.ActionEvent
import java.awt.event.ActionListener
import javax.swing.JPanel

class SettingsConfigurableTest {

    @Test
    fun downloadExamplesActionWithoutProjectDoesNotThrow() {
        val configurable = SettingsConfigurable()
        val downloadLink = configurable.createDownloadExamplesLink()

        assertDoesNotThrow {
            triggerAction(downloadLink)
        }
    }

    @Test
    fun exampleActionWithoutProjectDoesNotThrow() {
        val configurable = SettingsConfigurable()
        val examplePanel = createExamplePanel(configurable)
        val exampleLink = examplePanel.components.filterIsInstance<ActionLink>().first()

        assertDoesNotThrow {
            triggerAction(exampleLink)
        }
    }

    @Test
    fun onboardingQuestToggleDoesNotThrow() {
        val configurable = SettingsConfigurable()
        val questsPanel = configurable.createOnboardingPanel()
        val questCheckbox = questsPanel.components.filterIsInstance<JBCheckBox>().firstOrNull()

        assertNotNull(questCheckbox, "Expected at least one onboarding quest checkbox")

        questCheckbox ?: return

        assertDoesNotThrow {
            questCheckbox.doClick()
        }
    }

    private fun createExamplePanel(configurable: SettingsConfigurable): JPanel {
        return configurable.createExamplePanel(mapOf<ExampleFile, Description?>("Example.java" to null), null)
    }

    private fun triggerAction(actionLink: ActionLink) {
        val event = ActionEvent(actionLink, ActionEvent.ACTION_PERFORMED, actionLink.text)
        actionLink.getListeners(ActionListener::class.java).forEach { listener ->
            listener.actionPerformed(event)
        }
    }
}
