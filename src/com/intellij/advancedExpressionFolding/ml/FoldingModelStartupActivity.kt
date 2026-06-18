package com.intellij.advancedExpressionFolding.ml

import com.intellij.openapi.application.PathManager
import com.intellij.openapi.components.service
import com.intellij.openapi.project.Project
import com.intellij.openapi.startup.StartupActivity
import java.nio.file.Files
import java.nio.file.Path

class FoldingModelStartupActivity : StartupActivity.DumbAware {
    override fun runActivity(project: Project) {
        if (Files.exists(modelPath())) {
            service<FoldingModelService>().warmup()
        }
    }

    private fun modelPath(): Path =
        Path.of(PathManager.getConfigPath(), "advancedExpressionFolding", "folding-model.zip")
}
