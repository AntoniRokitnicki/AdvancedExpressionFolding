package com.intellij.advancedExpressionFolding

import com.intellij.advancedExpressionFolding.settings.AdvancedExpressionFoldingSettings.Companion.getInstance
import com.intellij.advancedExpressionFolding.settings.IState
import com.intellij.codeInsight.completion.*
import com.intellij.codeInsight.lookup.AutoCompletionPolicy
import com.intellij.codeInsight.lookup.LookupElementBuilder
import com.intellij.openapi.command.WriteCommandAction
import com.intellij.patterns.PlatformPatterns
import com.intellij.psi.*
import com.intellij.psi.codeStyle.CodeStyleManager
import com.intellij.psi.codeStyle.JavaCodeStyleManager
import com.intellij.psi.util.PsiTreeUtil
import com.intellij.util.ProcessingContext

class Slf4jAnnotationCompletionContributor(private val state: IState = getInstance().state) : CompletionContributor(), IState by state {
    init {
        extend(
            CompletionType.BASIC,
            PlatformPatterns.psiElement(PsiIdentifier::class.java)
                .withParent(PsiJavaCodeReferenceElement::class.java)
                .withSuperParent(2, PsiAnnotation::class.java)
                .withSuperParent(3, PsiModifierList::class.java)
                .withSuperParent(4, PsiClass::class.java),
            object : CompletionProvider<CompletionParameters>() {
                override fun addCompletions(
                    parameters: CompletionParameters,
                    context: ProcessingContext,
                    result: CompletionResultSet
                ) {
                    if (!pseudoAnnotations) return

                    val lookup = LookupElementBuilder.create("Slf4j")
                        .withLookupString("@Slf4j")
                        .withPresentableText("@Slf4j")
                        .withInsertHandler { ctx, _ ->
                            handleSlf4jInsert(ctx)
                        }
                        .withAutoCompletionPolicy(AutoCompletionPolicy.NEVER_AUTOCOMPLETE)

                    result.addElement(lookup)
                }
            }
        )
    }

    private fun handleSlf4jInsert(ctx: InsertionContext) {
        val project = ctx.project
        val element = ctx.file.findElementAt(ctx.startOffset) ?: return
        val psiClass = PsiTreeUtil.getParentOfType(element, PsiClass::class.java, false) ?: return

        WriteCommandAction.runWriteCommandAction(project) {
            removePseudoAnnotation(psiClass)
            removeExistingLoggerField(psiClass)

            val loggerFieldText = buildLoggerField(psiClass)
            insertLoggerField(psiClass, loggerFieldText, ctx)
        }
    }

    private fun removePseudoAnnotation(psiClass: PsiClass) {
        val annotations = psiClass.modifierList?.annotations ?: return
        annotations.firstOrNull { it.nameReferenceElement?.referenceName == "Slf4j" }?.delete()
    }

    private fun removeExistingLoggerField(psiClass: PsiClass) {
        psiClass.fields
            .filter { it.name == "log" }
            .forEach { it.delete() }
    }

    private fun buildLoggerField(psiClass: PsiClass): String {
        val classLiteral = psiClass.qualifiedName ?: buildNestedClassName(psiClass)
        return "private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger($classLiteral.class);"
    }

    private fun buildNestedClassName(psiClass: PsiClass): String {
        val className = psiClass.name ?: return ""
        val outerClass = psiClass.containingClass
        val packageName = (psiClass.containingFile as? PsiClassOwner)?.packageName?.takeIf { it.isNotEmpty() }

        val qualifier = when {
            outerClass != null -> buildNestedClassName(outerClass)
            packageName != null -> packageName
            else -> ""
        }

        return if (qualifier.isNotEmpty()) {
            "$qualifier.$className"
        } else {
            className
        }
    }

    private fun insertLoggerField(psiClass: PsiClass, fieldText: String, ctx: InsertionContext) {
        val project = psiClass.project
        val factory = JavaPsiFacade.getElementFactory(project)
        val parserFacade = PsiParserFacade.SERVICE.getInstance(project)
        val javaCodeStyleManager = JavaCodeStyleManager.getInstance(project)
        val codeStyleManager = CodeStyleManager.getInstance(project)
        val documentManager = PsiDocumentManager.getInstance(project)

        val field = factory.createFieldFromText(fieldText, psiClass)
        val newline = parserFacade.createWhiteSpaceFromText("\n")
        val anchor = psiClass.fields.firstOrNull()
            ?: psiClass.initializers.firstOrNull()
            ?: psiClass.methods.firstOrNull()
            ?: psiClass.innerClasses.firstOrNull()
        val rBrace = psiClass.rBrace

        val insertedField = when {
            anchor != null -> {
                psiClass.addBefore(newline, anchor)
                val addedField = psiClass.addBefore(field, anchor) as PsiField
                psiClass.addAfter(newline, addedField)
                addedField
            }
            rBrace != null -> {
                val addedField = psiClass.addBefore(field, rBrace) as PsiField
                psiClass.addBefore(newline, addedField)
                psiClass.addAfter(newline, addedField)
                addedField
            }
            else -> {
                val addedField = psiClass.add(field) as PsiField
                psiClass.addAfter(newline, addedField)
                addedField
            }
        }

        documentManager.doPostponedOperationsAndUnblockDocument(ctx.document)
        val shortenedField = javaCodeStyleManager.shortenClassReferences(insertedField) as? PsiField ?: insertedField
        documentManager.doPostponedOperationsAndUnblockDocument(ctx.document)
        val containingFile = psiClass.containingFile
        javaCodeStyleManager.optimizeImports(containingFile)
        codeStyleManager.reformat(shortenedField)
        documentManager.commitDocument(ctx.document)
    }
}
