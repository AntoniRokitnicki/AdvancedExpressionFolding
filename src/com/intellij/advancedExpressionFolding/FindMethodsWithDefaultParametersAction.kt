package com.intellij.advancedExpressionFolding

import com.intellij.advancedExpressionFolding.expression.semantic.SimpleExpression
import com.intellij.advancedExpressionFolding.expression.semantic.WrapperExpression
import com.intellij.advancedExpressionFolding.extension.clazz.MethodDefaultParameterExt
import com.intellij.advancedExpressionFolding.extension.toJavaPsiFile
import com.intellij.advancedExpressionFolding.view.FindUsageCustomView
import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.application.runReadAction
import com.intellij.openapi.fileTypes.FileTypeManager
import com.intellij.openapi.progress.ProgressIndicator
import com.intellij.openapi.progress.ProgressManager
import com.intellij.openapi.progress.Task
import com.intellij.psi.JavaRecursiveElementVisitor
import com.intellij.psi.PsiClass
import com.intellij.psi.PsiJavaFile
import com.intellij.psi.PsiMethod
import com.intellij.psi.search.FileTypeIndex
import com.intellij.psi.search.GlobalSearchScope

class FindMethodsWithDefaultParametersAction : AnAction() {
    override fun actionPerformed(e: AnActionEvent) {
        val project = e.project ?: return
        val findUsageCustomView = FindUsageCustomView(project, "find methods with default parameters")
        var processed = 0

        ProgressManager.getInstance().run(object : Task.Backgroundable(project, "Finding methods with default parameters", true) {
            override fun run(indicator: ProgressIndicator) {
                runReadAction {
                    FileTypeIndex.processFiles(FileTypeManager.getInstance().getFileTypeByExtension("java"), { file ->
                        file.toJavaPsiFile(project)?.let { javaFile ->
                            javaFile.accept(MethodDefaultParameterExtVisitor(findUsageCustomView, javaFile))
                            processed++
                            indicator.text = "Processing file $processed"
                        }
                        !indicator.isCanceled
                    }, GlobalSearchScope.projectScope(project))
                }
                indicator.fraction = 1.0
                indicator.text = "Finished processing $processed files"
            }
        })

    }

    class MethodDefaultParameterExtVisitor(
        private val findUsageCustomView: FindUsageCustomView,
        private val javaFile: PsiJavaFile
    ) : JavaRecursiveElementVisitor() {
        override fun visitClass(clazz: PsiClass) {
            val wrappedExpression =
                MethodDefaultParameterExt.enhanceMethodsWithDefaultParams(clazz) as? WrapperExpression
            wrappedExpression?.run {
                chain.filterIsInstance<SimpleExpression>()
                    .groupBy {
                        it.element.parent.parent as? PsiMethod
                    }.filterKeys {
                        it != null
                    }.forEach { (_, expressions) ->
                        val textRange = expressions.last().textRange
                        findUsageCustomView.addToUsage(javaFile, textRange)
                    }
            }
            super.visitClass(clazz)
        }
    }

    override fun isDumbAware() = false

}
