package com.intellij.advancedExpressionFolding.learning

import com.intellij.advancedExpressionFolding.settings.AdvancedExpressionFoldingSettings
import com.intellij.openapi.application.ApplicationManager
import com.intellij.openapi.project.Project
import com.intellij.openapi.startup.StartupActivity

class FoldingLearningStartupActivity : StartupActivity.Background {
    override fun runActivity(project: Project) {
        if (!AdvancedExpressionFoldingSettings.getInstance().state.learnFoldingPreference) {
            FoldingRnnState.get().deleteArtifacts()
            return
        }
        FoldingRnnPredictor.get(project)
        ApplicationManager.getApplication().executeOnPooledThread {
            FoldingRnnTrainer.get().maybeTrain()
        }
    }
}
