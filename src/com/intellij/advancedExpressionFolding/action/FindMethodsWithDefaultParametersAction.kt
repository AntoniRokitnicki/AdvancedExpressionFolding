package com.intellij.advancedExpressionFolding.action

import com.intellij.advancedExpressionFolding.expression.semantic.SimpleExpression
import com.intellij.advancedExpressionFolding.expression.semantic.WrapperExpression
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
import com.intellij.openapi.util.TextRange
import com.intellij.psi.JavaRecursiveElementVisitor
import com.intellij.psi.PsiClass
import com.intellij.psi.PsiJavaFile
import com.intellij.psi.PsiMethod
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

        ProgressManager.getInstance().run(
            object : Task.Backgroundable(project, "Finding methods with default parameters", true) {
                override fun run(indicator: ProgressIndicator) {
                    val javaFiles = runReadAction {
                        FileTypeIndex.getFiles(
                            FileTypeManager.getInstance().getFileTypeByExtension("java"),
                            GlobalSearchScope.projectScope(project)
                        ).toList()
                    }

                    var processed = 0
                    val total = javaFiles.size.takeIf { it > 0 } ?: 1

                    for (virtualFile in javaFiles) {
                        if (indicator.isCanceled) {
                            break
                        }

                        runReadAction {
                            virtualFile.toJavaPsiFile(project)?.let { javaFile ->
                                collectDefaultParameterUsages(javaFile).forEach { range ->
                                    findUsageCustomView.addUsage(javaFile, range)
                                }
                            }
                        }

                        processed++
                        indicator.fraction = processed.toDouble() / total
                        indicator.text = "Processing file $processed of ${javaFiles.size}"
                        FindMethodsWithDefaultParametersActionTestHook.onFileProcessed(
                            virtualFile.name,
                            processed,
                            indicator
                        )

                        if (indicator.isCanceled) {
                            break
                        }
                    }

                    indicator.fraction = 1.0
                    indicator.text = if (indicator.isCanceled) {
                        "Canceled after processing $processed files"
                    } else {
                        "Finished processing $processed files"
                    }

                    FindMethodsWithDefaultParametersActionTestHook.onFinished(processed, indicator)
                    findUsageCustomView.finish()
                }
            }
        )
    }

    fun runOnCurrentProject() {
        val project = ProjectManager.getInstance().openProjects.firstOrNull() ?: return
        runOnProject(project)
    }
}

private fun collectDefaultParameterUsages(javaFile: PsiJavaFile): List<TextRange> {
    val rangesByMethod = linkedMapOf<PsiMethod, TextRange>()
    javaFile.accept(object : JavaRecursiveElementVisitor() {
        override fun visitClass(aClass: PsiClass) {
            val wrapper = MethodDefaultParameterExt.enhanceMethodsWithDefaultParams(aClass) as? WrapperExpression
            wrapper?.chain
                ?.filterIsInstance<SimpleExpression>()
                ?.mapNotNull { expression ->
                    val method = expression.element.parent.parent as? PsiMethod ?: return@mapNotNull null
                    method to expression.textRange
                }
                ?.forEach { (method, range) ->
                    rangesByMethod[method] = range
                }

            super.visitClass(aClass)
        }
    })

    return rangesByMethod.values.toList()
}
