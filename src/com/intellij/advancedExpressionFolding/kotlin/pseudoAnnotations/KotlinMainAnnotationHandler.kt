package com.intellij.advancedExpressionFolding.kotlin.pseudoAnnotations

import com.intellij.codeInsight.completion.InsertionContext
import com.intellij.openapi.command.WriteCommandAction
import com.intellij.openapi.editor.ScrollType
import com.intellij.psi.util.PsiTreeUtil
import org.jetbrains.kotlin.psi.KtAnnotationEntry
import org.jetbrains.kotlin.psi.KtNamedFunction

class KotlinMainAnnotationHandler(
    private val generator: KotlinMainGenerator = KotlinMainGenerator()
) {
    fun handle(context: InsertionContext) {
        val element = context.file.findElementAt(context.startOffset) ?: return
        val annotation = PsiTreeUtil.getParentOfType(element, KtAnnotationEntry::class.java, false) ?: return
        val function = PsiTreeUtil.getParentOfType(annotation, KtNamedFunction::class.java, false) ?: return
        val file = function.containingKtFile

        WriteCommandAction.runWriteCommandAction(context.project) {
            annotation.delete()
            generator.removeExistingMain(file)
            val mainFunctionText = generator.buildMainFunction(function) ?: return@runWriteCommandAction
            val inserted = generator.insertMainFunction(mainFunctionText, file) ?: return@runWriteCommandAction
            generator.reformat(inserted)
            val caretModel = context.editor.caretModel
            caretModel.moveToOffset(inserted.textOffset)
            context.editor.scrollingModel.scrollToCaret(ScrollType.MAKE_VISIBLE)
        }
    }
}
