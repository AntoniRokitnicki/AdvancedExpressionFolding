package com.intellij.advancedExpressionFolding.onboarding

import com.intellij.advancedExpressionFolding.settings.view.SettingsConfigurable
import com.intellij.ide.BrowserUtil
import com.intellij.ide.util.PropertiesComponent
import com.intellij.notification.NotificationAction
import com.intellij.notification.NotificationGroupManager
import com.intellij.notification.NotificationType
import com.intellij.openapi.application.ApplicationManager
import com.intellij.openapi.options.ShowSettingsUtil
import com.intellij.openapi.project.Project
import com.intellij.openapi.startup.ProjectActivity

private const val ONBOARDING_SHOWN_KEY = "advanced.expression.folding.onboarding.tour.shown"
private const val ONBOARDING_DOC_URL =
    "https://github.com/AntoniRokitnicki/AdvancedExpressionFolding/blob/main/docs/beginner-onboarding-tour.md"

class BeginnerOnboardingTourActivity : ProjectActivity {
    override suspend fun execute(project: Project) {
        val application = ApplicationManager.getApplication()
        if (application.isUnitTestMode || application.isHeadlessEnvironment) {
            return
        }

        val properties = PropertiesComponent.getInstance()
        if (properties.getBoolean(ONBOARDING_SHOWN_KEY, false)) {
            return
        }

        val notificationGroup = NotificationGroupManager.getInstance()
            .getNotificationGroup("Advanced Expression Folding")
        val notification = notificationGroup.createNotification(
            "Quick tour: Advanced Expression Folding",
            """
            <p>Welcome aboard! Follow these three steps to see the plugin in action:</p>
            <ol>
              <li>Open the folding settings to review the beginner-friendly defaults.</li>
              <li>Use the “Enable all” and “Disable all” buttons to feel how folds change readability.</li>
              <li>Check out the bundled examples panel to pull sample files into your project.</li>
            </ol>
            """.trimIndent(),
            NotificationType.INFORMATION
        )

        notification.addAction(NotificationAction.createSimpleExpiring("Review settings") {
            ShowSettingsUtil.getInstance().showSettingsDialog(project, SettingsConfigurable::class.java)
        })

        notification.addAction(NotificationAction.createSimpleExpiring("Read quick guide") {
            BrowserUtil.browse(ONBOARDING_DOC_URL)
        })

        notification.notify(project)
        properties.setValue(ONBOARDING_SHOWN_KEY, true)
    }
}
