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
import com.intellij.psi.JavaRecursiveElementVisitor
import com.intellij.psi.JavaPsiFacade
import com.intellij.psi.PsiClass
import com.intellij.psi.PsiCodeBlock
import com.intellij.psi.PsiDocumentManager
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiElementFactory
import com.intellij.psi.PsiExpression
import com.intellij.psi.PsiIdentifier
import com.intellij.psi.PsiJavaCodeReferenceElement
import com.intellij.psi.PsiJavaFile
import com.intellij.psi.PsiAnnotation
import com.intellij.psi.PsiLambdaExpression
import com.intellij.psi.PsiLiteralExpression
import com.intellij.psi.PsiMethod
import com.intellij.psi.PsiModifierList
import com.intellij.psi.PsiReturnStatement
import com.intellij.psi.PsiType
import com.intellij.psi.PsiTypeElement
import com.intellij.psi.PsiPrimitiveType
import com.intellij.psi.search.GlobalSearchScope
import com.intellij.psi.codeStyle.CodeStyleManager
import com.intellij.psi.codeStyle.JavaCodeStyleManager
import com.intellij.psi.util.PsiTreeUtil
import com.intellij.patterns.PlatformPatterns
import com.intellij.util.ProcessingContext

private const val OPTIONAL_QUALIFIED_NAME = "java.util.Optional"

class OptionalAnnotationCompletionContributor(private val state: IState = getInstance().state) :
    CompletionContributor(), IState by state {

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

    private fun handleMethodInsert(ctx: InsertionContext) {
        val project = ctx.project
        val documentManager = PsiDocumentManager.getInstance(project)
        documentManager.commitDocument(ctx.document)
        val element = ctx.file.findElementAt(ctx.startOffset) ?: return
        val method = PsiTreeUtil.getParentOfType(element, PsiMethod::class.java, false) ?: return

        WriteCommandAction.runWriteCommandAction(project) {
            method.modifierList.findAnnotation(ANNOTATION_NAME)?.delete()
            if (applyOptional(method)) {
                shortenAndFormat(method)
            }
        }
    }

    private fun handleClassInsert(ctx: InsertionContext) {
        val project = ctx.project
        val documentManager = PsiDocumentManager.getInstance(project)
        documentManager.commitDocument(ctx.document)
        val element = ctx.file.findElementAt(ctx.startOffset) ?: return
        val psiClass = PsiTreeUtil.getParentOfType(element, PsiClass::class.java, false) ?: return

        WriteCommandAction.runWriteCommandAction(project) {
            if (applyOptional(psiClass)) {
                shortenAndFormat(psiClass)
            }
        }
    }

    private fun applyOptional(psiClass: PsiClass): Boolean {
        var changed = false
        psiClass.modifierList?.findAnnotation(ANNOTATION_NAME)?.let {
            it.delete()
            changed = true
        }
        psiClass.methods.forEach { if (applyOptional(it)) changed = true }
        psiClass.innerClasses.forEach { if (applyOptional(it)) changed = true }
        return changed
    }

    private fun applyOptional(method: PsiMethod): Boolean {
        val returnTypeElement: PsiTypeElement = method.returnTypeElement ?: return false
        val originalType = returnTypeElement.type
        if (originalType == PsiType.VOID || isOptionalType(originalType)) {
            return false
        }

        val project = method.project
        val factory = JavaPsiFacade.getElementFactory(project)
        val optionalComponentText = boxedTypeText(originalType, method)
        val optionalTypeText = "$OPTIONAL_QUALIFIED_NAME<$optionalComponentText>"
        val optionalType = factory.createTypeFromText(optionalTypeText, method)
        returnTypeElement.replace(factory.createTypeElement(optionalType))

        updateReturnStatements(method, factory)
        addOptionalImport(method)
        return true
    }

    private fun updateReturnStatements(method: PsiMethod, factory: PsiElementFactory) {
        val body: PsiCodeBlock = method.body ?: return
        val returns = mutableListOf<PsiReturnStatement>()
        body.accept(object : JavaRecursiveElementVisitor() {
            override fun visitClass(aClass: PsiClass) {
                // Skip nested classes
            }

            override fun visitAnonymousClass(aClass: com.intellij.psi.PsiAnonymousClass) {
                // Skip anonymous classes
            }

            override fun visitLambdaExpression(expression: PsiLambdaExpression) {
                // Skip lambda expressions
            }

            override fun visitReturnStatement(statement: PsiReturnStatement) {
                returns += statement
            }
        })

        returns.forEach { statement ->
            val replacement = optionalReturnExpression(statement.returnValue)
            val newStatement = factory.createStatementFromText("return $replacement;", statement)
            statement.replace(newStatement)
        }
    }

    private fun optionalReturnExpression(expression: PsiExpression?): String {
        if (expression == null) {
            return "$OPTIONAL_QUALIFIED_NAME.empty()"
        }

        if (expression is PsiLiteralExpression && expression.value == null) {
            return "$OPTIONAL_QUALIFIED_NAME.empty()"
        }

        val type = expression.type

        val isPrimitive = type is PsiPrimitiveType && type != PsiType.VOID
        return when {
            isPrimitive -> "$OPTIONAL_QUALIFIED_NAME.of(${expression.text})"
            else -> "$OPTIONAL_QUALIFIED_NAME.ofNullable(${expression.text})"
        }
    }

    private fun boxedTypeText(type: PsiType, context: PsiElement): String {
        val boxed = if (type is PsiPrimitiveType && type != PsiType.VOID) type.getBoxedType(context) else null
        return (boxed ?: type).canonicalText
    }

    private fun isOptionalType(type: PsiType): Boolean {
        val resolved = com.intellij.psi.util.PsiUtil.resolveClassInType(type) ?: return false
        return resolved.qualifiedName == OPTIONAL_QUALIFIED_NAME
    }

    private fun addOptionalImport(method: PsiMethod) {
        val javaFile = method.containingFile as? PsiJavaFile ?: return
        val project = method.project
        val optionalClass = JavaPsiFacade.getInstance(project).findClass(OPTIONAL_QUALIFIED_NAME, GlobalSearchScope.allScope(project))
            ?: return
        JavaCodeStyleManager.getInstance(project).addImport(javaFile, optionalClass)
    }

    private fun shortenAndFormat(element: PsiElement) {
        val project = element.project
        JavaCodeStyleManager.getInstance(project).shortenClassReferences(element)
        CodeStyleManager.getInstance(project).reformat(element)
    }

    private inner class MethodAnnotationCompletionProvider : CompletionProvider<CompletionParameters>() {
        override fun addCompletions(parameters: CompletionParameters, context: ProcessingContext, result: CompletionResultSet) {
            if (!pseudoAnnotations) return

            val lookup = LookupElementBuilder.create(ANNOTATION_NAME)
                .withLookupString("@$ANNOTATION_NAME")
                .withPresentableText("@$ANNOTATION_NAME")
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

            val lookup = LookupElementBuilder.create(ANNOTATION_NAME)
                .withLookupString("@$ANNOTATION_NAME")
                .withPresentableText("@$ANNOTATION_NAME")
                .withInsertHandler { ctx, _ ->
                    handleClassInsert(ctx)
                }
                .withAutoCompletionPolicy(AutoCompletionPolicy.NEVER_AUTOCOMPLETE)

            result.addElement(lookup)
        }
    }

    private companion object {
        private const val ANNOTATION_NAME = "Optional"
    }
}
