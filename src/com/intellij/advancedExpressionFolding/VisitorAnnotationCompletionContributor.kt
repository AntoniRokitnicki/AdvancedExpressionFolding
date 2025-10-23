package com.intellij.advancedExpressionFolding

import com.intellij.advancedExpressionFolding.settings.AdvancedExpressionFoldingSettings.Companion.getInstance
import com.intellij.advancedExpressionFolding.settings.IState
import com.intellij.codeInsight.completion.CompletionContributor
import com.intellij.codeInsight.completion.CompletionParameters
import com.intellij.codeInsight.completion.CompletionProvider
import com.intellij.codeInsight.completion.CompletionResultSet
import com.intellij.codeInsight.completion.CompletionType
import com.intellij.codeInsight.lookup.AutoCompletionPolicy
import com.intellij.codeInsight.lookup.LookupElementBuilder
import com.intellij.codeInsight.completion.InsertionContext
import com.intellij.openapi.command.WriteCommandAction
import com.intellij.openapi.util.text.StringUtil
import com.intellij.patterns.PlatformPatterns
import com.intellij.psi.JavaPsiFacade
import com.intellij.psi.PsiAnnotation
import com.intellij.psi.PsiClass
import com.intellij.psi.PsiClassType
import com.intellij.psi.PsiElementFactory
import com.intellij.psi.PsiIdentifier
import com.intellij.psi.PsiJavaCodeReferenceElement
import com.intellij.psi.PsiMethod
import com.intellij.psi.codeStyle.CodeStyleManager
import com.intellij.psi.codeStyle.JavaCodeStyleManager
import com.intellij.psi.util.PsiTreeUtil
import com.intellij.psi.PsiClassObjectAccessExpression
import com.intellij.psi.PsiDocumentManager
import com.intellij.psi.PsiModifier
import com.intellij.psi.search.searches.ClassInheritorsSearch
import com.intellij.util.ProcessingContext

class VisitorAnnotationCompletionContributor(private val state: IState = getInstance().state) : CompletionContributor(), IState by state {
    init {
        extend(
            CompletionType.BASIC,
            PlatformPatterns.psiElement(PsiIdentifier::class.java)
                .withParent(PsiJavaCodeReferenceElement::class.java)
                .withSuperParent(2, PsiAnnotation::class.java)
                .withSuperParent(3, com.intellij.psi.PsiModifierList::class.java)
                .withSuperParent(4, PsiClass::class.java),
            object : CompletionProvider<CompletionParameters>() {
                override fun addCompletions(
                    parameters: CompletionParameters,
                    context: ProcessingContext,
                    result: CompletionResultSet
                ) {
                    if (!pseudoAnnotations) return

                    val lookup = LookupElementBuilder.create("Visitor")
                        .withLookupString("@Visitor")
                        .withPresentableText("@Visitor")
                        .withInsertHandler { ctx, _ ->
                            handleVisitorInsert(ctx)
                        }
                        .withAutoCompletionPolicy(AutoCompletionPolicy.NEVER_AUTOCOMPLETE)

                    result.addElement(lookup)
                }
            }
        )
    }

    private fun handleVisitorInsert(ctx: InsertionContext) {
        val file = ctx.file
        val project = file.project
        val document = ctx.document
        val elementAtStart = file.findElementAt(ctx.startOffset) ?: return
        val annotation = PsiTreeUtil.getParentOfType(elementAtStart, PsiAnnotation::class.java, false) ?: return
        val ownerClass = PsiTreeUtil.getParentOfType(annotation, PsiClass::class.java, false) ?: return
        val annotationOffset = annotation.textOffset

        WriteCommandAction.runWriteCommandAction(project) {
            val insertion = "(Visitor.class)"
            document.insertString(ctx.tailOffset, insertion)
            PsiDocumentManager.getInstance(project).commitDocument(document)

            val refreshedAnnotation = PsiTreeUtil.getParentOfType(file.findElementAt(annotationOffset), PsiAnnotation::class.java, false)
                ?: return@runWriteCommandAction

            val elementFactory = JavaPsiFacade.getElementFactory(project)
            val (visitorType, visitorClass) = findVisitorTarget(refreshedAnnotation, elementFactory) ?: run {
                refreshedAnnotation.delete()
                return@runWriteCommandAction
            }
            refreshedAnnotation.delete()

            val hierarchy = collectHierarchy(ownerClass)

            hierarchy.forEachIndexed { index, elementClass ->
                val shouldAnnotateOverride = index != 0
                ensureAcceptMethod(
                    elementClass,
                    visitorType,
                    visitorClass,
                    elementFactory,
                    shouldAnnotateOverride
                )
                ensureVisitMethod(visitorClass, elementClass, elementFactory)
            }
        }
    }

    private fun collectHierarchy(root: PsiClass): List<PsiClass> {
        val inheritors = ClassInheritorsSearch.search(root, root.useScope, true)
            .toList()
            .sortedBy { it.qualifiedName ?: it.name ?: "" }
        return listOf(root) + inheritors
    }

    private fun resolveVisitorType(annotation: PsiAnnotation): PsiClassType? {
        val attribute = annotation.parameterList.attributes.firstOrNull()?.value as? PsiClassObjectAccessExpression ?: return null
        val type = attribute.operand.type
        return type as? PsiClassType
    }

    private fun findVisitorTarget(annotation: PsiAnnotation, elementFactory: PsiElementFactory): Pair<PsiClassType, PsiClass>? {
        val resolvedType = resolveVisitorType(annotation)
        val resolvedClass = resolvedType?.resolve()
        if (resolvedClass != null && resolvedClass.isInterface) {
            return elementFactory.createType(resolvedClass) to resolvedClass
        }

        val file = annotation.containingFile ?: return null
        val candidate = PsiTreeUtil.findChildrenOfType(file, PsiClass::class.java)
            .filter { it.isInterface }
            .minWithOrNull(
                compareBy<PsiClass> { it.name != "Visitor" }
                    .thenBy { !(it.name?.endsWith("Visitor") ?: false) }
                    .thenBy { it.name ?: it.qualifiedName ?: "" }
            )
            ?: return null

        return elementFactory.createType(candidate) to candidate
    }

    private fun ensureAcceptMethod(
        ownerClass: PsiClass,
        visitorType: PsiClassType,
        visitorClass: PsiClass,
        elementFactory: PsiElementFactory,
        shouldAnnotateOverride: Boolean
    ) {
        val existing = ownerClass.findMethodsByName("accept", false).firstOrNull { method ->
            val parameters = method.parameterList.parameters
            parameters.size == 1 && parameters[0].type.canonicalText == visitorType.canonicalText
        }
        if (existing != null) return

        val parameterName = StringUtil.decapitalize(visitorClass.name ?: "visitor").ifEmpty { "visitor" }
        val methodText = when {
            ownerClass.isInterface -> "void accept(${visitorType.presentableText} $parameterName);"
            ownerClass.hasModifierProperty(PsiModifier.ABSTRACT) -> "public abstract void accept(${visitorType.presentableText} $parameterName);"
            else -> buildString {
                if (shouldAnnotateOverride) {
                    append("@Override\n")
                }
                append("public void accept(")
                append(visitorType.presentableText)
                append(' ')
                append(parameterName)
                append(") {\n")
                append("    ")
                append(parameterName)
                append(".visit(this);\n")
                append('}')
            }
        }

        val method = elementFactory.createMethodFromText(methodText, ownerClass)
        val added = ownerClass.add(method) as PsiMethod
        JavaCodeStyleManager.getInstance(ownerClass.project).shortenClassReferences(added)
        CodeStyleManager.getInstance(ownerClass.project).reformat(added)
    }

    private fun ensureVisitMethod(
        visitorInterface: PsiClass,
        elementClass: PsiClass,
        elementFactory: PsiElementFactory
    ) {
        val elementType = elementFactory.createType(elementClass)
        val alreadyExists = visitorInterface.findMethodsByName("visit", false).any { method ->
            val parameters = method.parameterList.parameters
            parameters.size == 1 && parameters[0].type.canonicalText == elementType.canonicalText
        }
        if (alreadyExists) return

        val parameterName = StringUtil.decapitalize(elementClass.name ?: "element").ifEmpty { "element" }
        val methodText = "void visit(${elementType.presentableText} $parameterName);"

        val method = elementFactory.createMethodFromText(methodText, visitorInterface)
        val added = visitorInterface.add(method) as PsiMethod
        JavaCodeStyleManager.getInstance(visitorInterface.project).shortenClassReferences(added)
        CodeStyleManager.getInstance(visitorInterface.project).reformat(added)
    }
}
