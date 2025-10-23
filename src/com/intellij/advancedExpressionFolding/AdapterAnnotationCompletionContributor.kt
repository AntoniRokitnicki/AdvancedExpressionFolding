package com.intellij.advancedExpressionFolding

import com.intellij.advancedExpressionFolding.settings.AdvancedExpressionFoldingSettings.Companion.getInstance
import com.intellij.advancedExpressionFolding.settings.IState
import com.intellij.codeInsight.completion.*
import com.intellij.codeInsight.lookup.AutoCompletionPolicy
import com.intellij.codeInsight.lookup.LookupElementBuilder
import com.intellij.lang.java.JavaLanguage
import com.intellij.openapi.command.WriteCommandAction
import com.intellij.patterns.PlatformPatterns
import com.intellij.psi.*
import com.intellij.psi.codeStyle.CodeStyleManager
import com.intellij.psi.codeStyle.JavaCodeStyleManager
import com.intellij.psi.util.PsiTreeUtil
import com.intellij.util.ProcessingContext

class AdapterAnnotationCompletionContributor(private val state: IState = getInstance().state) : CompletionContributor(), IState by state {
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

                    val lookup = LookupElementBuilder.create("Adapter")
                        .withLookupString("@Adapter")
                        .withPresentableText("@Adapter")
                        .withInsertHandler { ctx, _ ->
                            handleAdapterInsert(ctx)
                        }
                        .withAutoCompletionPolicy(AutoCompletionPolicy.NEVER_AUTOCOMPLETE)

                    result.addElement(lookup)
                }
            }
        )
    }

    private fun handleAdapterInsert(ctx: InsertionContext) {
        val project = ctx.project
        val element = ctx.file.findElementAt(ctx.startOffset) ?: return
        val targetClass = PsiTreeUtil.getParentOfType(element, PsiClass::class.java, false) ?: return

        WriteCommandAction.runWriteCommandAction(project) {
            val config = extractConfigAndRemoveAnnotations(targetClass, ctx) ?: return@runWriteCommandAction
            generateAdapter(targetClass, ctx, config)
        }
    }

    private fun extractConfigAndRemoveAnnotations(targetClass: PsiClass, ctx: InsertionContext): AdapterConfig? {
        val document = ctx.document
        val project = ctx.project
        val psiDocumentManager = PsiDocumentManager.getInstance(project)
        psiDocumentManager.doPostponedOperationsAndUnblockDocument(document)

        val classRange = targetClass.textRange ?: return null
        val fullText = document.text
        val regex = Regex("@Adapter(\\s*\\(([^)]*)\\))?")

        val prefixMatches = regex.findAll(fullText.substring(0, classRange.startOffset))
            .map { it to 0 }
            .toList()
        val classMatches = regex.findAll(fullText.substring(classRange.startOffset, classRange.endOffset))
            .map { it to classRange.startOffset }
            .toList()

        val allMatches = prefixMatches + classMatches
        if (allMatches.isEmpty()) return null

        val params = allMatches.mapNotNull { (match, _) ->
            match.groupValues.getOrNull(2)?.takeIf { it.isNotBlank() }
        }.lastOrNull()
        val config = AdapterConfig.fromParameters(params)

        val chars = document.charsSequence
        allMatches.asReversed().forEach { (match, base) ->
            val start = base + match.range.first
            var end = base + match.range.last + 1
            if (end < chars.length && chars[end] == '\r') {
                end++
            }
            if (end < chars.length && chars[end] == '\n') {
                end++
            }
            document.deleteString(start, end)
        }

        psiDocumentManager.commitDocument(document)
        return config
    }

    private fun generateAdapter(targetClass: PsiClass, ctx: InsertionContext, config: AdapterConfig) {
        val adapterName = targetClass.name?.let { name ->
            if (name.endsWith("Adapter")) name else name + "Adapter"
        } ?: return

        val adapterCode = buildAdapterClassText(targetClass, adapterName, config) ?: return
        val project = ctx.project
        val factory = JavaPsiFacade.getElementFactory(project)
        val psiFileFactory = PsiFileFactory.getInstance(project)
        val tempFile = psiFileFactory.createFileFromText(
            "${adapterName}_.java",
            JavaLanguage.INSTANCE,
            adapterCode
        )
        val adapterPsiClass = PsiTreeUtil.findChildOfType(tempFile, PsiClass::class.java) ?: return
        val parent = targetClass.parent

        removeExistingAdapter(parent, adapterName)

        val parserFacade = PsiParserFacade.SERVICE.getInstance(project)
        val whitespace = parserFacade.createWhiteSpaceFromText("\n\n")
        val addedWhitespace = parent.addAfter(whitespace, targetClass)
        val inserted = parent.addAfter(adapterPsiClass, addedWhitespace)

        PsiDocumentManager.getInstance(project).commitAllDocuments()

        JavaCodeStyleManager.getInstance(project).shortenClassReferences(inserted)
        CodeStyleManager.getInstance(project).reformat(inserted)
    }

    private fun removeExistingAdapter(parent: PsiElement, adapterName: String) {
        when (parent) {
            is PsiJavaFile -> parent.classes.filter { it.name == adapterName }.forEach { it.delete() }
            is PsiClass -> parent.innerClasses.filter { it.name == adapterName }.forEach { it.delete() }
        }
    }

    private fun buildAdapterClassText(targetClass: PsiClass, adapterName: String, config: AdapterConfig): String? {
        val typeParametersDeclaration = targetClass.typeParameterList?.text ?: ""
        val typeParameterUsage = targetClass.typeParameters.takeIf { it.isNotEmpty() }
            ?.joinToString(prefix = "<", postfix = ">") { it.name ?: "" }
            ?: ""

        val extendsOrImplements = when {
            targetClass.isInterface -> " implements ${targetClass.name}${typeParameterUsage}"
            targetClass.hasModifierProperty(PsiModifier.ABSTRACT) -> " extends ${targetClass.name}${typeParameterUsage}"
            else -> return null
        }

        val visibility = when {
            targetClass.hasModifierProperty(PsiModifier.PUBLIC) -> "public "
            targetClass.hasModifierProperty(PsiModifier.PROTECTED) -> "protected "
            targetClass.hasModifierProperty(PsiModifier.PRIVATE) -> "private "
            else -> ""
        }

        val classModifiers = buildString {
            append(visibility)
            if (targetClass.containingClass != null) {
                append("static ")
            }
        }

        val methods = collectAbstractMethods(targetClass)
        val methodBlocks = methods.joinToString(separator = "\n\n") { method ->
            buildMethodStub(method, config)
        }

        return buildString {
            append(classModifiers)
            append("class ")
            append(adapterName)
            append(typeParametersDeclaration)
            append(extendsOrImplements)
            append(" {\n")
            if (methodBlocks.isNotBlank()) {
                append(methodBlocks.prependIndent("    "))
                append('\n')
            }
            append('}')
        }
    }

    private fun collectAbstractMethods(targetClass: PsiClass): List<PsiMethod> {
        return targetClass.allMethods
            .filter { method ->
                !method.isConstructor &&
                method.containingClass?.let { containing ->
                    (containing == targetClass || containing.isInterface || containing.hasModifierProperty(PsiModifier.ABSTRACT)) &&
                        method.hasModifierProperty(PsiModifier.ABSTRACT)
                } ?: false
            }
            .distinctBy { it.getSignature(PsiSubstitutor.EMPTY) }
    }

    private fun buildMethodStub(method: PsiMethod, config: AdapterConfig): String {
        val visibility = when {
            method.hasModifierProperty(PsiModifier.PUBLIC) -> "public "
            method.hasModifierProperty(PsiModifier.PROTECTED) -> "protected "
            method.hasModifierProperty(PsiModifier.PRIVATE) -> "private "
            else -> if (method.containingClass?.isInterface == true) "public " else ""
        }

        val typeParameters = method.typeParameterList?.text?.let { if (it.isNotBlank()) "$it " else "" } ?: ""
        val returnTypeText = method.returnTypeElement?.text ?: "void"
        val parameters = method.parameterList.parameters
            .mapIndexed { index, parameter ->
                val name = parameter.name ?: "param$index"
                val typeText = parameter.typeElement?.text ?: parameter.type.presentableText
                buildString {
                    if (parameter.modifierList?.annotations?.isNotEmpty() == true) {
                        append(parameter.modifierList!!.annotations.joinToString(separator = " ") { it.text })
                        append(' ')
                    }
                    if (parameter.hasModifierProperty(PsiModifier.FINAL)) {
                        append("final ")
                    }
                    append(typeText)
                    append(' ')
                    append(name)
                }
            }
            .joinToString(", ")
        val throwsClause = method.throwsList.referenceElements.takeIf { it.isNotEmpty() }
            ?.joinToString(prefix = " throws ") { it.text }
            ?: ""

        val body = buildMethodBody(method.returnType, config)

        return buildString {
            append("@Override\n")
            append(visibility)
            append(typeParameters)
            append(returnTypeText)
            append(' ')
            append(method.name)
            append('(')
            append(parameters)
            append(')')
            append(throwsClause)
            append(" {\n")
            body?.takeIf { it.isNotBlank() }?.let {
                append(it.prependIndent("        "))
                append('\n')
            }
            append("    }")
        }
    }

    private fun buildMethodBody(returnType: PsiType?, config: AdapterConfig): String? {
        if (config.throwException) {
            return "throw new UnsupportedOperationException();"
        }

        if (returnType == null || returnType == PsiType.VOID) {
            return null
        }

        val value = defaultValue(returnType, config.primitiveWrapperDefaults)
        return if (value != null) {
            "return $value;"
        } else {
            "return null;"
        }
    }

    private fun defaultValue(type: PsiType, primitiveWrapperDefaults: Boolean): String? {
        return when (type) {
            PsiType.BOOLEAN -> "false"
            PsiType.CHAR -> "'\\0'"
            PsiType.BYTE, PsiType.SHORT, PsiType.INT -> "0"
            PsiType.LONG -> "0L"
            PsiType.FLOAT -> "0.0f"
            PsiType.DOUBLE -> "0.0d"
            else -> when (val canonical = type.canonicalText) {
                "java.lang.Boolean" -> if (primitiveWrapperDefaults) "false" else null
                "java.lang.Character" -> if (primitiveWrapperDefaults) "'\\0'" else null
                "java.lang.Byte", "java.lang.Short", "java.lang.Integer" -> if (primitiveWrapperDefaults) "0" else null
                "java.lang.Long" -> if (primitiveWrapperDefaults) "0L" else null
                "java.lang.Float" -> if (primitiveWrapperDefaults) "0.0f" else null
                "java.lang.Double" -> if (primitiveWrapperDefaults) "0.0d" else null
                else -> null
            }
        }
    }

    private data class AdapterConfig(
        val throwException: Boolean,
        val primitiveWrapperDefaults: Boolean
    ) {
        companion object {
            fun fromParameters(parameters: String?): AdapterConfig {
                var throwException = false
                var primitiveWrapperDefaults = false

                parameters
                    ?.split(',')
                    ?.map { it.trim() }
                    ?.filter { it.isNotEmpty() }
                    ?.forEach { pair ->
                        val parts = pair.split('=', limit = 2)
                        val name = parts.getOrNull(0)?.trim()
                        val value = parts.getOrNull(1)?.trim()?.removeSurrounding("\"")

                        when (name) {
                            "throw" -> throwException = value.equals("true", ignoreCase = true)
                            "primitiveWrapperReturns" -> primitiveWrapperDefaults = value
                                ?.substringAfterLast('.')
                                ?.equals("DEFAULT", ignoreCase = true)
                                ?: false
                        }
                    }

                return AdapterConfig(throwException, primitiveWrapperDefaults)
            }
        }
    }
}
