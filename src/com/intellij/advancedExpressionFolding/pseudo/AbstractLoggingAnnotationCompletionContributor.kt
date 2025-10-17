package com.intellij.advancedExpressionFolding.pseudo

import com.intellij.advancedExpressionFolding.settings.AdvancedExpressionFoldingSettings.Companion.getInstance
import com.intellij.advancedExpressionFolding.settings.IState
import com.intellij.codeInsight.completion.CompletionContributor
import com.intellij.codeInsight.completion.CompletionParameters
import com.intellij.codeInsight.completion.CompletionProvider
import com.intellij.codeInsight.completion.CompletionResultSet
import com.intellij.codeInsight.completion.CompletionType
import com.intellij.codeInsight.completion.InsertionContext
import com.intellij.codeInsight.lookup.AutoCompletionPolicy
import com.intellij.codeInsight.lookup.LookupElementBuilder
import com.intellij.openapi.command.WriteCommandAction
import com.intellij.psi.PsiAnnotation
import com.intellij.psi.PsiClass
import com.intellij.psi.PsiCodeBlock
import com.intellij.psi.PsiDocumentManager
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiIdentifier
import com.intellij.psi.PsiJavaCodeReferenceElement
import com.intellij.psi.PsiMethod
import com.intellij.psi.PsiRecursiveElementVisitor
import com.intellij.psi.PsiModifierList
import com.intellij.psi.codeStyle.CodeStyleManager
import com.intellij.psi.util.PsiTreeUtil
import com.intellij.patterns.PlatformPatterns
import com.intellij.util.ProcessingContext

abstract class AbstractLoggingAnnotationCompletionContributor(
    private val state: IState = getInstance().state
) : CompletionContributor(), IState by state {

    protected abstract val annotationName: String

    init {
        extend(
            CompletionType.BASIC,
            PlatformPatterns.psiElement(PsiIdentifier::class.java)
                .withParent(PsiJavaCodeReferenceElement::class.java)
                .withSuperParent(2, PsiAnnotation::class.java)
                .withSuperParent(3, PsiModifierList::class.java)
                .withSuperParent(4, PsiMethod::class.java),
            MethodAnnotationCompletionProvider(),
        )

        extend(
            CompletionType.BASIC,
            PlatformPatterns.psiElement(PsiIdentifier::class.java)
                .withParent(PsiJavaCodeReferenceElement::class.java)
                .withSuperParent(2, PsiAnnotation::class.java)
                .withSuperParent(3, PsiModifierList::class.java)
                .withSuperParent(4, PsiClass::class.java),
            ClassAnnotationCompletionProvider(),
        )
    }

    protected fun handleMethodInsert(ctx: InsertionContext) {
        val project = ctx.project
        val documentManager = PsiDocumentManager.getInstance(project)
        documentManager.commitDocument(ctx.document)
        val method = PsiTreeUtil.getParentOfType(ctx.file.findElementAt(ctx.startOffset), PsiMethod::class.java, false) ?: return
        WriteCommandAction.runWriteCommandAction(project) {
            removeAnnotation(method)
            applyLogging(method)
            CodeStyleManager.getInstance(project).reformat(method)
        }
    }

    protected fun handleClassInsert(ctx: InsertionContext) {
        val project = ctx.project
        val documentManager = PsiDocumentManager.getInstance(project)
        documentManager.commitDocument(ctx.document)
        val psiClass = PsiTreeUtil.getParentOfType(ctx.file.findElementAt(ctx.startOffset), PsiClass::class.java, false) ?: return
        WriteCommandAction.runWriteCommandAction(project) {
            applyLogging(psiClass)
            CodeStyleManager.getInstance(project).reformat(psiClass)
        }
    }

    private fun applyLogging(method: PsiMethod) {
        val body = method.body ?: return
        if (isAlreadyLogged(body)) {
            removeLogging(method)
            return
        }

        addLogging(method, body)
    }

    protected open fun shouldRemoveClassLogging(psiClass: PsiClass): Boolean = false

    protected open fun removeClassLogging(psiClass: PsiClass) {}

    protected fun applyLogging(psiClass: PsiClass) {
        psiClass.accept(object : PsiRecursiveElementVisitor() {
            override fun visitElement(element: PsiElement) {
                if (element is PsiClass) {
                    removeAnnotation(element)
                    if (shouldRemoveClassLogging(element)) {
                        removeClassLogging(element)
                    } else {
                        element.methods.forEach { applyLogging(it) }
                        element.constructors.forEach { applyLogging(it) }
                    }
                }
                super.visitElement(element)
            }
        })
    }

    private fun removeAnnotation(method: PsiMethod) {
        method.modifierList.findAnnotation(annotationName)?.delete()
    }

    private fun removeAnnotation(psiClass: PsiClass) {
        psiClass.modifierList?.findAnnotation(annotationName)?.delete()
    }

    protected abstract fun isAlreadyLogged(body: PsiCodeBlock): Boolean

    protected abstract fun addLogging(method: PsiMethod, body: PsiCodeBlock)

    protected abstract fun removeLogging(method: PsiMethod)

    private inner class MethodAnnotationCompletionProvider : CompletionProvider<CompletionParameters>() {
        override fun addCompletions(parameters: CompletionParameters, context: ProcessingContext, result: CompletionResultSet) {
            if (!pseudoAnnotations) return

            val lookup = LookupElementBuilder.create(annotationName)
                .withLookupString("@$annotationName")
                .withPresentableText("@$annotationName")
                .withInsertHandler { ctx, _ ->
                    handleMethodInsert(ctx)
                }
                .withAutoCompletionPolicy(AutoCompletionPolicy.NEVER_AUTOCOMPLETE)

            result.addElement(lookup)
        }
    }

    private inner class ClassAnnotationCompletionProvider : CompletionProvider<CompletionParameters>() {
        override fun addCompletions(parameters: CompletionParameters, context: ProcessingContext, result: CompletionResultSet) {
            if (!pseudoAnnotations) return

            val lookup = LookupElementBuilder.create(annotationName)
                .withLookupString("@$annotationName")
                .withPresentableText("@$annotationName")
                .withInsertHandler { ctx, _ ->
                    handleClassInsert(ctx)
                }
                .withAutoCompletionPolicy(AutoCompletionPolicy.NEVER_AUTOCOMPLETE)

            result.addElement(lookup)
        }
    }
}
