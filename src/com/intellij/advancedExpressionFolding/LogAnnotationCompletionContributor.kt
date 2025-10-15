package com.intellij.advancedExpressionFolding

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
import com.intellij.patterns.PlatformPatterns
import com.intellij.psi.JavaPsiFacade
import com.intellij.psi.PsiAnnotation
import com.intellij.psi.PsiClass
import com.intellij.psi.PsiCodeBlock
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiIdentifier
import com.intellij.psi.PsiMethod
import com.intellij.psi.PsiModifierList
import com.intellij.psi.PsiParameter
import com.intellij.psi.PsiDocumentManager
import com.intellij.psi.codeStyle.CodeStyleManager
import com.intellij.psi.PsiRecursiveElementVisitor
import com.intellij.psi.PsiReturnStatement
import com.intellij.psi.PsiThrowStatement
import com.intellij.psi.util.PsiTreeUtil
import com.intellij.util.ProcessingContext

class LogAnnotationCompletionContributor(private val state: IState = getInstance().state) : CompletionContributor(), IState by state {

    init {
        extend(
            CompletionType.BASIC,
            PlatformPatterns.psiElement(PsiIdentifier::class.java)
                .withParent(com.intellij.psi.PsiJavaCodeReferenceElement::class.java)
                .withSuperParent(2, PsiAnnotation::class.java)
                .withSuperParent(3, PsiModifierList::class.java)
                .withSuperParent(4, PsiMethod::class.java),
            MethodLogCompletionProvider()
        )

        extend(
            CompletionType.BASIC,
            PlatformPatterns.psiElement(PsiIdentifier::class.java)
                .withParent(com.intellij.psi.PsiJavaCodeReferenceElement::class.java)
                .withSuperParent(2, PsiAnnotation::class.java)
                .withSuperParent(3, PsiModifierList::class.java)
                .withSuperParent(4, PsiClass::class.java),
            ClassLogCompletionProvider()
        )
    }

    private inner class MethodLogCompletionProvider : CompletionProvider<CompletionParameters>() {
        override fun addCompletions(parameters: CompletionParameters, context: ProcessingContext, result: CompletionResultSet) {
            if (!pseudoAnnotations) return

            val lookup = LookupElementBuilder.create("Log")
                .withLookupString("@Log")
                .withPresentableText("@Log")
                .withInsertHandler { ctx, _ ->
                    handleMethodInsert(ctx)
                }
                .withAutoCompletionPolicy(AutoCompletionPolicy.NEVER_AUTOCOMPLETE)

            result.addElement(lookup)
        }
    }

    private inner class ClassLogCompletionProvider : CompletionProvider<CompletionParameters>() {
        override fun addCompletions(parameters: CompletionParameters, context: ProcessingContext, result: CompletionResultSet) {
            if (!pseudoAnnotations) return

            val lookup = LookupElementBuilder.create("Log")
                .withLookupString("@Log")
                .withPresentableText("@Log")
                .withInsertHandler { ctx, _ ->
                    handleClassInsert(ctx)
                }
                .withAutoCompletionPolicy(AutoCompletionPolicy.NEVER_AUTOCOMPLETE)

            result.addElement(lookup)
        }
    }

    private fun handleMethodInsert(ctx: InsertionContext) {
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

    private fun handleClassInsert(ctx: InsertionContext) {
        val project = ctx.project
        val documentManager = PsiDocumentManager.getInstance(project)
        documentManager.commitDocument(ctx.document)
        val psiClass = PsiTreeUtil.getParentOfType(ctx.file.findElementAt(ctx.startOffset), PsiClass::class.java, false) ?: return
        WriteCommandAction.runWriteCommandAction(project) {
            applyLogging(psiClass)
            CodeStyleManager.getInstance(project).reformat(psiClass)
        }
    }

    private fun applyLogging(psiClass: PsiClass) {
        psiClass.accept(object : PsiRecursiveElementVisitor() {
            override fun visitElement(element: PsiElement) {
                if (element is PsiClass) {
                    removeAnnotation(element)
                    element.methods.forEach { applyLogging(it) }
                    element.constructors.forEach { applyLogging(it) }
                }
                super.visitElement(element)
            }
        })
    }

    private fun removeAnnotation(method: PsiMethod) {
        method.modifierList.findAnnotation("Log")?.delete()
    }

    private fun removeAnnotation(psiClass: PsiClass) {
        psiClass.modifierList?.findAnnotation("Log")?.delete()
    }

    private fun applyLogging(method: PsiMethod) {
        val body = method.body ?: return
        if (isAlreadyLogged(body)) return

        val project = method.project
        val factory = JavaPsiFacade.getElementFactory(project)
        val entryExpression = buildEntryExpression(method)
        val exitExpression = buildExitExpression(method)

        val entryStatement = factory.createStatementFromText("System.out.println($entryExpression);", method)
        val exitStatement = factory.createStatementFromText("System.out.println($exitExpression);", method)

        val originalStatements = body.statements
        val firstStatement = originalStatements.firstOrNull()
        if (firstStatement == null) {
            body.add(entryStatement)
            body.add(exitStatement)
            return
        }

        body.addBefore(entryStatement, firstStatement)

        val lastOriginalStatement = originalStatements.lastOrNull()
        when (lastOriginalStatement) {
            is PsiReturnStatement, is PsiThrowStatement -> body.addBefore(exitStatement, lastOriginalStatement)
            else -> body.addAfter(exitStatement, lastOriginalStatement)
        }
    }

    private fun isAlreadyLogged(body: PsiCodeBlock): Boolean {
        val statements = body.statements
        if (statements.isEmpty()) return false
        val firstStatement = statements.first()
        if (!firstStatement.text.startsWith("System.out.println(\"Entering ")) return false
        return statements.any { it.text.startsWith("System.out.println(\"Exiting ") }
    }

    private fun buildEntryExpression(method: PsiMethod): String {
        val methodLabel = methodLabel(method)
        val parameterNames = method.parameterList.parameters.mapNotNull(PsiParameter::getName)
        val base = "\"Entering $methodLabel\""
        if (parameterNames.isEmpty()) {
            return base
        }

        val parameterExpressions = parameterNames.map { "\"$it=\" + $it" }
        val joinedParameters = parameterExpressions.joinToString(separator = " + \", \" + ")
        return base + " + \" with args: \" + " + joinedParameters
    }

    private fun buildExitExpression(method: PsiMethod): String {
        val methodLabel = methodLabel(method)
        return "\"Exiting $methodLabel\""
    }

    private fun methodLabel(method: PsiMethod): String {
        val className = method.containingClass?.name ?: "Anonymous"
        return if (method.isConstructor) {
            "$className.<init>"
        } else {
            "$className.${method.name}"
        }
    }

}
