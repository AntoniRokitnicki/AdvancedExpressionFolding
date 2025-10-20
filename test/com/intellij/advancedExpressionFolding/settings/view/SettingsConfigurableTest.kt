package com.intellij.advancedExpressionFolding.settings.view

import com.intellij.ui.components.ActionLink
import org.junit.jupiter.api.Assertions.assertDoesNotThrow
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

    private fun createExamplePanel(configurable: SettingsConfigurable): JPanel {
        return configurable.createExamplePanel(
            mapOf<ExampleFile, Description?>(ExampleFile("Example.java") to Description("Example description")),
            null
        )
    }

    private fun triggerAction(actionLink: ActionLink) {
        val event = ActionEvent(actionLink, ActionEvent.ACTION_PERFORMED, actionLink.text)
        actionLink.getListeners(ActionListener::class.java).forEach { listener ->
            listener.actionPerformed(event)
        }
    }
}
