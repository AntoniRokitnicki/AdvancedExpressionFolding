package com.intellij.advancedExpressionFolding

import com.intellij.openapi.project.Project
import com.intellij.openapi.startup.ProjectActivity

class MethodCallFoldingLoader : ProjectActivity {
    override suspend fun execute(project: Project) {
        // eager loading
        MethodCallFoldingLoaderService.factory()
    }

}
