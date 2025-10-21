package com.intellij.advancedExpressionFolding.pseudo

import com.intellij.advancedExpressionFolding.settings.AdvancedExpressionFoldingSettings
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
import com.intellij.psi.PsiClassType
import com.intellij.psi.PsiElementFactory
import com.intellij.psi.PsiIdentifier
import com.intellij.psi.PsiJavaCodeReferenceElement
import com.intellij.psi.PsiMethod
import com.intellij.psi.PsiModifier
import com.intellij.psi.PsiModifierList
import com.intellij.psi.PsiParameterList
import com.intellij.psi.PsiPrimitiveType
import com.intellij.psi.PsiReferenceList
import com.intellij.psi.PsiType
import com.intellij.psi.PsiTypeParameterList
import com.intellij.psi.PsiTypes
import com.intellij.psi.codeStyle.CodeStyleManager
import com.intellij.psi.codeStyle.JavaCodeStyleManager
import com.intellij.psi.util.PsiTreeUtil
import com.intellij.util.ProcessingContext
import java.util.Locale

class OptionalAnnotationCompletionContributor(
    private val state: IState = AdvancedExpressionFoldingSettings.Companion.getInstance().state
) : CompletionContributor(), IState by state {

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

                    val lookup = LookupElementBuilder.create("Optional")
                        .withLookupString("@Optional")
                        .withPresentableText("@Optional")
                        .withInsertHandler { insertionContext, _ ->
                            handleOptionalInsert(insertionContext)
                        }
                        .withAutoCompletionPolicy(AutoCompletionPolicy.NEVER_AUTOCOMPLETE)

                    result.addElement(lookup)
                }
            }
        )
    }

    private fun handleOptionalInsert(ctx: InsertionContext) {
        val project = ctx.project
        val element = ctx.file.findElementAt(ctx.startOffset) ?: return
        val annotation = PsiTreeUtil.getParentOfType(element, PsiAnnotation::class.java, false) ?: return
        val psiClass = PsiTreeUtil.getParentOfType(annotation, PsiClass::class.java, false) ?: return

        if (psiClass.isInterface || psiClass.isAnnotationType || psiClass.isEnum) {
            return
        }

        val factory = JavaPsiFacade.getElementFactory(project)
        val codeStyleManager = CodeStyleManager.getInstance(project)
        val javaCodeStyleManager = JavaCodeStyleManager.getInstance(project)

        WriteCommandAction.runWriteCommandAction(project) {
            annotation.delete()

            val methodsToWrap = psiClass.methods.filter { shouldGenerateWrapper(it, psiClass) }
            if (methodsToWrap.isEmpty()) return@runWriteCommandAction

            methodsToWrap.forEach { method ->
                val optionalMethod = createOptionalWrapperMethod(psiClass, method, factory)
                val added = psiClass.addAfter(optionalMethod, method)
                javaCodeStyleManager.shortenClassReferences(added)
                codeStyleManager.reformat(added)
            }
        }
    }

    private fun shouldGenerateWrapper(method: PsiMethod, psiClass: PsiClass): Boolean {
        if (method.isConstructor) return false
        if (method.hasModifierProperty(PsiModifier.ABSTRACT)) return false
        if (method.hasModifierProperty(PsiModifier.NATIVE)) return false

        val returnType = method.returnType ?: return false
        if (returnType == PsiTypes.voidType()) return false
        if (returnType is PsiClassType && returnType.resolve()?.qualifiedName == "java.util.Optional") return false

        val optionalMethodName = buildOptionalMethodName(method.name)
        val parameterTypes = method.parameterList.parameters.map { parameter ->
            parameter.type.canonicalText
        }

        val existing = psiClass.findMethodsByName(optionalMethodName, false)
        if (existing.any { hasSameParameterTypes(it, parameterTypes) }) {
            return false
        }

        return true
    }

    private fun hasSameParameterTypes(method: PsiMethod, parameterTypes: List<String>): Boolean {
        if (method.parameterList.parametersCount != parameterTypes.size) {
            return false
        }

        return method.parameterList.parameters.map { it.type.canonicalText } == parameterTypes
    }

    private fun createOptionalWrapperMethod(
        psiClass: PsiClass,
        method: PsiMethod,
        factory: PsiElementFactory
    ): PsiMethod {
        val returnType = method.returnType ?: PsiType.NULL
        val optionalTypeText = "java.util.Optional<${toOptionalGenericType(returnType)}>"
        val optionalType = factory.createTypeFromText(optionalTypeText, psiClass)
        val optionalMethodName = buildOptionalMethodName(method.name)
        val optionalMethod = factory.createMethod(optionalMethodName, optionalType)

        copyModifiers(method, optionalMethod)
        copyTypeParameters(method, optionalMethod)
        copyParameters(method, optionalMethod)
        copyThrowsList(method, optionalMethod)

        val arguments = method.parameterList.parameters.withIndex().joinToString(", ") { (index, parameter) ->
            parameter.name
        }
        val call = "${method.name}(${arguments})"
        val bodyText = "{\n    return java.util.Optional.ofNullable($call);\n}"
        val body = factory.createCodeBlockFromText(bodyText, optionalMethod)
        optionalMethod.body?.replace(body)

        return optionalMethod
    }

    private fun toOptionalGenericType(type: PsiType): String = when (type) {
        is PsiPrimitiveType -> type.boxedTypeName ?: type.presentableText
        else -> type.presentableText
    }

    private fun copyModifiers(source: PsiMethod, target: PsiMethod) {
        val modifiers = listOf(
            PsiModifier.PUBLIC,
            PsiModifier.PROTECTED,
            PsiModifier.PRIVATE,
            PsiModifier.STATIC,
            PsiModifier.FINAL,
            PsiModifier.SYNCHRONIZED,
            PsiModifier.STRICTFP
        )

        modifiers.forEach { modifier ->
            target.modifierList.setModifierProperty(modifier, source.hasModifierProperty(modifier))
        }
    }

    private fun copyTypeParameters(source: PsiMethod, target: PsiMethod) {
        val typeParameterList = source.typeParameterList
        if (typeParameterList != null && typeParameterList.text.isNotBlank()) {
            val copy = typeParameterList.copy() as PsiTypeParameterList
            target.typeParameterList?.replace(copy)
        }
    }

    private fun copyParameters(source: PsiMethod, target: PsiMethod) {
        val parameterList = source.parameterList
        if (parameterList.parametersCount == 0) {
            target.parameterList.replace(parameterList.copy() as PsiParameterList)
            return
        }

        val copy = parameterList.copy() as PsiParameterList
        target.parameterList.replace(copy)
    }

    private fun copyThrowsList(source: PsiMethod, target: PsiMethod) {
        val throwsList = source.throwsList
        if (throwsList.referenceElements.isEmpty()) {
            return
        }

        val copy = throwsList.copy() as PsiReferenceList
        target.throwsList.replace(copy)
    }

    private fun buildOptionalMethodName(methodName: String): String {
        if (methodName.isEmpty()) return "optional"
        return "optional" + methodName.replaceFirstChar { char ->
            if (char.isLowerCase()) char.titlecase(Locale.getDefault()) else char.toString()
        }
    }
}
