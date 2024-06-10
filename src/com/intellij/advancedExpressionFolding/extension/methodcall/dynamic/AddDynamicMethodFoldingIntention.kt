package com.intellij.advancedExpressionFolding.extension.methodcall.dynamic

import com.intellij.advancedExpressionFolding.FoldingService
import com.intellij.advancedExpressionFolding.extension.methodcall.MethodCallFactory
import com.intellij.advancedExpressionFolding.extension.methodcall.MethodName
import com.intellij.codeInsight.folding.CodeFoldingManager
import com.intellij.codeInsight.intention.IntentionAction
import com.intellij.openapi.application.runInEdt
import com.intellij.openapi.editor.Editor
import com.intellij.openapi.project.Project
import com.intellij.openapi.ui.Messages
import com.intellij.psi.PsiFile
import com.intellij.psi.PsiMethodCallExpression
import com.intellij.psi.util.PsiTreeUtil
import org.jetbrains.annotations.Nls

class AddDynamicMethodFoldingIntention : IntentionAction {

    override fun getText(): @Nls(capitalization = Nls.Capitalization.Sentence) String = "AJF2: Dynamic method folding"

    override fun getFamilyName(): @Nls(capitalization = Nls.Capitalization.Sentence) String = "AJF2"

    override fun isAvailable(project: Project, editor: Editor, file: PsiFile): Boolean {
        val element = file.findElementAt(editor.caretModel.offset)
        val methodCall = PsiTreeUtil.getParentOfType(element, PsiMethodCallExpression::class.java)
        return methodCall != null
    }

    override fun invoke(project: Project, editor: Editor, file: PsiFile) {

        val element = file.findElementAt(editor.caretModel.offset)
        val methodCall = PsiTreeUtil.getParentOfType(element, PsiMethodCallExpression::class.java)

        methodCall?.methodExpression?.referenceName?.let { methodName ->
            when {
                methodName.exists() -> {
                    val dialogResult = methodName.showRenameDialog()
                    dialogResult?.run {
                        val (action, newMethodName) = dialogResult
                        when (action) {
                            Action.RENAME -> {
                                ConfigurationParser.addOrUpdateMethod(methodName, newMethodName)
                            }
                            Action.REMOVE -> methodName.remove()
                            Action.CANCEL -> return
                        }
                        runInEdt {
                            FoldingService.get().clearAllKeys(project)
                            MethodCallFactory.refreshMethodCallMappings()
                            CodeFoldingManager.getInstance(project).updateFoldRegions(editor)
                        }
                    }
                }
                else -> {
                    val newName = methodName.getNewNameFromUser() ?: return
                    ConfigurationParser.addOrUpdateMethod(methodName, newName)
                }
            }
        }
    }

    override fun startInWriteAction() = true

    private fun MethodName.exists() = MethodCallFactory.findByMethodName(this)?.any {
        it is DynamicMethodCall
    } == true

    private fun MethodName.getNewNameFromUser(): String? {
        return Messages.showInputDialog(
            "Enter new method name:",
            "Add Dynamic Method Folding",
            Messages.getQuestionIcon(),
            this,
            null
        )
    }

    private fun MethodName.remove() = ConfigurationParser.remove(this)

}

