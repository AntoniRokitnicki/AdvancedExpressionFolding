package com.intellij.advancedExpressionFolding.action

import com.intellij.advancedExpressionFolding.processor.language.kotlin.MethodDefaultParameterExt
import com.intellij.advancedExpressionFolding.processor.toJavaPsiFile
import com.intellij.advancedExpressionFolding.view.FindUsageCustomView
import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.application.runReadAction
import com.intellij.openapi.fileTypes.FileTypeManager
import com.intellij.openapi.progress.ProgressIndicator
import com.intellij.openapi.progress.ProgressManager
import com.intellij.openapi.progress.Task
import com.intellij.openapi.project.Project
import com.intellij.openapi.project.ProjectManager
import com.intellij.psi.JavaRecursiveElementVisitor
import com.intellij.psi.PsiClass
import com.intellij.psi.search.FileTypeIndex
import com.intellij.psi.search.GlobalSearchScope

class FindMethodsWithDefaultParametersAction : AnAction() {
    override fun actionPerformed(e: AnActionEvent) {
        val project = e.project ?: return
        FindMethodsWithDefaultParametersActionExecutor.runOnProject(project)
    }

    override fun isDumbAware() = false
}

object FindMethodsWithDefaultParametersActionExecutor {
    fun runOnProject(project: Project) {
        val findUsageCustomView = FindUsageCustomView(project, "find methods with default parameters")
        var processed = 0

        ProgressManager.getInstance().run(
            object : Task.Backgroundable(project, "Finding methods with default parameters", true) {
                override fun run(indicator: ProgressIndicator) {
                    try {
                        runReadAction {
                            FileTypeIndex.processFiles(
                                FileTypeManager.getInstance().getFileTypeByExtension("java"),
                                { file ->
                                    file.toJavaPsiFile(project)?.let { javaFile ->
                                        javaFile.accept(MethodDefaultParameterExtVisitor(findUsageCustomView))
                                        processed++
                                        indicator.text = "Processing file $processed"
                                        FindMethodsWithDefaultParametersActionTestHook.onFileProcessed(
                                            file.name,
                                            processed,
                                            indicator
                                        )
                                    }
                                    !indicator.isCanceled
                                },
                                GlobalSearchScope.projectScope(project)
                            )
                        }
                    } finally {
                        findUsageCustomView.finish()
                        indicator.fraction = 1.0
                        indicator.text = "Finished processing $processed files"
                        FindMethodsWithDefaultParametersActionTestHook.onFinished(processed, indicator)
                    }
                }
            }
        )
    }

    fun runOnCurrentProject() {
        val project = ProjectManager.getInstance().openProjects.firstOrNull() ?: return
        runOnProject(project)
    }
}

private class MethodDefaultParameterExtVisitor(
    private val findUsageCustomView: FindUsageCustomView
) : JavaRecursiveElementVisitor() {
    override fun visitClass(clazz: PsiClass) {
        MethodDefaultParameterExt.overloadedMethodsWithDefaultParams(clazz)
            .forEach { method ->
                val range = method.nameIdentifier?.textRange ?: method.textRange
                findUsageCustomView.addToUsage(method, range)
            }
        super.visitClass(clazz)
    }
}
