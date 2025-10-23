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
import com.intellij.psi.JavaPsiFacade
import com.intellij.psi.PsiClass
import com.intellij.psi.PsiEllipsisType
import com.intellij.psi.PsiIdentifier
import com.intellij.psi.PsiMethod
import com.intellij.psi.PsiModifier
import com.intellij.psi.PsiParameter
import com.intellij.psi.codeStyle.CodeStyleManager
import com.intellij.psi.codeStyle.JavaCodeStyleManager
import com.intellij.psi.util.PsiTreeUtil
import com.intellij.psi.util.PsiUtil
import com.intellij.util.ProcessingContext

class InheritConstructorsAnnotationCompletionContributor(
    private val state: IState = getInstance().state
) : CompletionContributor(), IState by state {
    init {
        extend(
            CompletionType.BASIC,
            com.intellij.patterns.PlatformPatterns.psiElement(PsiIdentifier::class.java)
                .withParent(com.intellij.psi.PsiJavaCodeReferenceElement::class.java)
                .withSuperParent(2, com.intellij.psi.PsiAnnotation::class.java)
                .withSuperParent(3, com.intellij.psi.PsiModifierList::class.java)
                .withSuperParent(4, PsiClass::class.java),
            object : CompletionProvider<CompletionParameters>() {
                override fun addCompletions(
                    parameters: CompletionParameters,
                    context: ProcessingContext,
                    result: CompletionResultSet
                ) {
                    if (!pseudoAnnotations) return

                    val lookup = LookupElementBuilder.create("InheritConstructors")
                        .withLookupString("@InheritConstructors")
                        .withPresentableText("@InheritConstructors")
                        .withInsertHandler { insertionContext, _ ->
                            handleInsert(insertionContext)
                        }
                        .withAutoCompletionPolicy(AutoCompletionPolicy.NEVER_AUTOCOMPLETE)

                    result.addElement(lookup)
                }
            }
        )
    }

    private fun handleInsert(ctx: InsertionContext) {
        val project = ctx.project
        val element = ctx.file.findElementAt(ctx.startOffset) ?: return
        val psiClass = PsiTreeUtil.getParentOfType(element, PsiClass::class.java, false) ?: return
        val superClass = psiClass.superClass ?: return

        WriteCommandAction.runWriteCommandAction(project) {
            psiClass.modifierList?.findAnnotation("InheritConstructors")?.delete()

            val accessibleConstructors = superClass.constructors.filter { constructor ->
                !constructor.hasModifierProperty(PsiModifier.PRIVATE) &&
                    PsiUtil.isAccessible(constructor, psiClass, psiClass)
            }

            if (accessibleConstructors.isEmpty()) {
                return@runWriteCommandAction
            }

            val existingSignatures = psiClass.constructors.map { signature(it) }.toSet()
            val factory = JavaPsiFacade.getElementFactory(project)
            val newConstructors = accessibleConstructors
                .filter { signature(it) !in existingSignatures }
                .mapNotNull { constructor ->
                    createConstructorText(constructor, psiClass.name ?: return@mapNotNull null)?.let {
                        factory.createMethodFromText(it, psiClass)
                    }
                }

            newConstructors.forEach { created ->
                val inserted = psiClass.add(created) as PsiMethod
                JavaCodeStyleManager.getInstance(project).shortenClassReferences(inserted)
                CodeStyleManager.getInstance(project).reformat(inserted)
            }
        }
    }

    private fun signature(method: PsiMethod): String =
        method.parameterList.parameters.joinToString(prefix = "(", postfix = ")") { parameter ->
            val type = parameter.type
            if (type is PsiEllipsisType) {
                type.componentType.canonicalText + "..."
            } else {
                type.canonicalText
            }
        }

    private fun createConstructorText(constructor: PsiMethod, className: String): String? {
        val visibility = when {
            constructor.hasModifierProperty(PsiModifier.PUBLIC) -> "public "
            constructor.hasModifierProperty(PsiModifier.PROTECTED) -> "protected "
            else -> ""
        }

        val annotations = constructor.modifierList.annotations
            .map { it.text }
            .filter { it.isNotBlank() }

        val parameterTexts = constructor.parameterList.parameters.mapIndexed { index, parameter ->
            createParameterText(parameter, index)
        }
        val parameterList = parameterTexts.joinToString(", ", prefix = "(", postfix = ")")

        val parameterNames = constructor.parameterList.parameters.mapIndexed { index, parameter ->
            parameter.name ?: "param$index"
        }.joinToString(", ")

        val throwsClause = constructor.throwsList.referencedTypes
            .takeIf { it.isNotEmpty() }
            ?.joinToString(", ", prefix = " throws ") { it.presentableText }
            .orEmpty()

        return buildString {
            annotations.forEach {
                appendLine(it)
            }
            append(visibility)
            append(className)
            append(parameterList)
            append(throwsClause)
            appendLine(" {")
            append("        super(")
            append(parameterNames)
            appendLine(");")
            appendLine("    }")
        }
    }

    private fun createParameterText(parameter: PsiParameter, index: Int): String {
        val annotations = parameter.modifierList?.annotations
            ?.map { it.text }
            ?.filter { it.isNotBlank() }
            ?.joinToString(separator = " ")
            ?.let { "$it " }
            .orEmpty()

        val finalModifier = if (parameter.hasModifierProperty(PsiModifier.FINAL)) "final " else ""
        val type = parameter.type
        val typeText = if (type is PsiEllipsisType) {
            type.componentType.presentableText + "..."
        } else {
            type.presentableText
        }
        val name = parameter.name ?: "param$index"
        return annotations + finalModifier + typeText + " " + name
    }
}
