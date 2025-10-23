package com.intellij.advancedExpressionFolding

import com.intellij.advancedExpressionFolding.processor.isVoid
import com.intellij.advancedExpressionFolding.settings.AdvancedExpressionFoldingSettings.Companion.getInstance
import com.intellij.advancedExpressionFolding.settings.IState
import com.intellij.codeInsight.completion.*
import com.intellij.codeInsight.lookup.AutoCompletionPolicy
import com.intellij.codeInsight.lookup.LookupElementBuilder
import com.intellij.openapi.command.WriteCommandAction
import com.intellij.patterns.PlatformPatterns
import com.intellij.psi.*
import com.intellij.psi.PsiParserFacade
import com.intellij.psi.codeStyle.CodeStyleManager
import com.intellij.psi.codeStyle.JavaCodeStyleManager
import com.intellij.psi.util.PsiTreeUtil
import com.intellij.util.ProcessingContext

class MainAnnotationCompletionContributor(private val state: IState = getInstance().state) : CompletionContributor(), IState by state {
    init {
        extend(
            CompletionType.BASIC,
            PlatformPatterns.psiElement(PsiIdentifier::class.java)
                .withParent(PsiJavaCodeReferenceElement::class.java)
                .withSuperParent(2, PsiAnnotation::class.java)
                .withSuperParent(3, PsiModifierList::class.java)
                .withSuperParent(4, PsiMethod::class.java),
            object : CompletionProvider<CompletionParameters>() {
                override fun addCompletions(
                    parameters: CompletionParameters,
                    context: ProcessingContext,
                    result: CompletionResultSet
                ) {
                    if (!pseudoAnnotations) return

                    listOf(
                        LookupElementBuilder.create("Main")
                            .withLookupString("@Main")
                            .withPresentableText("@Main")
                            .withInsertHandler { ctx, _ ->
                                handleMainInsert(ctx)
                            },
                        LookupElementBuilder.create("Cache")
                            .withLookupString("@Cache")
                            .withPresentableText("@Cache")
                            .withInsertHandler { ctx, _ ->
                                handleCacheInsert(ctx, "Cache")
                            },
                        LookupElementBuilder.create("Memoize")
                            .withLookupString("@Memoize")
                            .withPresentableText("@Memoize")
                            .withInsertHandler { ctx, _ ->
                                handleCacheInsert(ctx, "Memoize")
                            }
                    ).forEach { lookup ->
                        result.addElement(
                            lookup.withAutoCompletionPolicy(AutoCompletionPolicy.NEVER_AUTOCOMPLETE)
                        )
                    }
                }
            }
        )
    }

    private fun handleMainInsert(ctx: InsertionContext) {
        val project = ctx.project
        val element = ctx.file.findElementAt(ctx.startOffset)
        val method = PsiTreeUtil.getParentOfType(element, PsiMethod::class.java, false) ?: return
        val immediateClass = PsiTreeUtil.getParentOfType(method, PsiClass::class.java, false) ?: return
        val topClass = findTopLevelClass(immediateClass)

        WriteCommandAction.runWriteCommandAction(project) {
            method.modifierList.findAnnotation("Main")?.delete()
            removeExistingMain(topClass)
            val mainCode = generateMainCode(method, immediateClass).trimEnd()
            insertFormatted(mainCode, topClass, ctx)
        }
    }

    private fun findTopLevelClass(psiClass: PsiClass): PsiClass {
        var current = psiClass
        while (true) {
            current.containingClass?.let {
                current = it
            } ?: break
        }
        return current
    }

    private fun removeExistingMain(psiClass: PsiClass) {
        psiClass.methods
            .filter { it.name == "main" && it.hasModifierProperty(PsiModifier.STATIC) }
            .forEach { it.delete() }
    }

    private fun defaultValue(type: PsiType): String {
        val isEllipsis = type is PsiEllipsisType
        val effectiveType = if (isEllipsis) type.componentType else type

        val base = when (effectiveType.canonicalText) {
            "boolean" -> "false"
            "char" -> "'\\0'"
            "byte", "short", "int", "long" -> "0"
            "float" -> "0.0f"
            "double" -> "0.0"
            "java.util.Date" -> "new java.util.Date()"
            "java.time.LocalDate" -> "java.time.LocalDate.now()"
            "java.time.LocalDateTime" -> "java.time.LocalDateTime.now()"
            "java.time.ZonedDateTime" -> "java.time.ZonedDateTime.now()"
            else -> "null"
        }

        return if (isEllipsis) "new ${effectiveType.presentableText}[]{}" else base
    }

    private fun generateMainCode(method: PsiMethod, ownerClass: PsiClass): String {
        val isConstructor = method.isConstructor
        val isStatic = method.hasModifierProperty(PsiModifier.STATIC)
        val containingClass = method.containingClass ?: return ""

        val constructorParams = if (!isConstructor && !isStatic) {
            containingClass.constructors.firstOrNull { it.parameterList.parametersCount > 0 }?.parameterList?.parameters.orEmpty()
        } else emptyArray()

        val methodParams = method.parameterList.parameters

        val paramDecls = buildList {
            constructorParams.forEach {
                val type = it.type
                val typeText = if (type is PsiEllipsisType) type.componentType.presentableText + "[]" else type.presentableText
                add("        $typeText ${it.name} = ${defaultValue(type)};")
            }
            if (constructorParams.isNotEmpty()) add("")
            methodParams.forEach {
                val type = it.type
                val typeText = if (type is PsiEllipsisType) type.componentType.presentableText + "[]" else type.presentableText
                add("        $typeText ${it.name} = ${defaultValue(type)};")
            }
        }.joinToString("\n")

        val constructorArgs = constructorParams.joinToString(", ") { it.name }
        val methodArgs = methodParams.joinToString(", ") { it.name }

        val call = when {
            isConstructor -> "new ${ownerClass.name}($methodArgs);"
            isStatic -> "${method.name}($methodArgs);"
            method.returnType == null || method.returnType.isVoid() -> "new ${ownerClass.name}($constructorArgs).${method.name}($methodArgs);"
            else -> "System.out.println(new ${ownerClass.name}($constructorArgs).${method.name}($methodArgs));"
        }

        return buildString {
            appendLine("    public static void main(String[] args) {")
            if (paramDecls.isNotEmpty()) appendLine(paramDecls)
            appendLine("        $call")
            appendLine("    }")
        }.trimEnd()
    }

    private fun insertFormatted(code: String, clazz: PsiClass, ctx: InsertionContext) {
        val project = clazz.project
        val doc = ctx.document
        val insertOffset = clazz.lBrace?.textOffset?.plus(1) ?: clazz.textRange.endOffset

        val documentManager = PsiDocumentManager.getInstance(project)
        documentManager.doPostponedOperationsAndUnblockDocument(doc)

        doc.insertString(insertOffset, "\n$code\n")
        documentManager.commitDocument(doc)

        val insertedMethod = clazz.findMethodsByName("main", false)
            .maxByOrNull { it.textOffset }

        if (insertedMethod != null) {
            JavaCodeStyleManager.getInstance(project).shortenClassReferences(insertedMethod)
            CodeStyleManager.getInstance(project).reformat(insertedMethod)
        }

        positionCursorAtMainMethod(code, insertOffset, ctx)
    }

    private fun positionCursorAtMainMethod(code: String, insertOffset: Int, ctx: InsertionContext) {
        val mainMethodOffset = insertOffset + 1 + code.indexOf("public static void main")
        ctx.editor.caretModel.moveToOffset(mainMethodOffset)
    }

    private fun handleCacheInsert(ctx: InsertionContext, annotationName: String) {
        val project = ctx.project
        val element = ctx.file.findElementAt(ctx.startOffset) ?: return
        val method = PsiTreeUtil.getParentOfType(element, PsiMethod::class.java, false) ?: return
        val containingClass = method.containingClass ?: return
        val body = method.body ?: return
        val returnType = method.returnType ?: return
        if (returnType == PsiType.VOID) return

        WriteCommandAction.runWriteCommandAction(project) {
            method.modifierList.findAnnotation(annotationName)?.delete()

            val elementFactory = JavaPsiFacade.getElementFactory(project)
            val codeStyleManager = CodeStyleManager.getInstance(project)
            val javaStyleManager = JavaCodeStyleManager.getInstance(project)
            val parserFacade = PsiParserFacade.SERVICE.getInstance(project)

            val methodName = method.name
            val implementationName = "${methodName}\$impl"
            val isStatic = method.hasModifierProperty(PsiModifier.STATIC)
            val parameters = method.parameterList.parameters
            val parameterNames = parameters.joinToString(", ") { it.name }
            val cacheFieldName = "${methodName}Cache"
            val boxedReturnTypeName = (returnType as? PsiPrimitiveType)?.boxedTypeName ?: returnType.presentableText

            fun ensureWhitespaceBefore(target: PsiElement) {
                val prev = target.prevSibling
                if (prev == null || prev.text.isNotBlank()) {
                    target.parent.addBefore(parserFacade.createWhiteSpaceFromText("\n"), target)
                }
            }

            fun ensureField(fieldName: String, fieldText: String) {
                if (containingClass.fields.any { it.name == fieldName }) return
                val field = elementFactory.createFieldFromText(fieldText, containingClass)
                val added = containingClass.addBefore(field, method)
                ensureWhitespaceBefore(method)
                javaStyleManager.shortenClassReferences(added)
                codeStyleManager.reformat(added)
            }

            val fieldModifiers = buildString {
                append("private ")
                if (isStatic) append("static ")
            }

            if (parameters.isEmpty()) {
                ensureField(
                    cacheFieldName,
                    "$fieldModifiers${returnType.presentableText} $cacheFieldName;"
                )
                ensureField(
                    "${methodName}CacheInitialized",
                    "$fieldModifiers boolean ${methodName}CacheInitialized;"
                )
            } else {
                val mapFieldModifiers = buildString {
                    append(fieldModifiers)
                    append("final ")
                }
                ensureField(
                    cacheFieldName,
                    "$mapFieldModifiers java.util.Map<java.util.List<Object>, $boxedReturnTypeName> $cacheFieldName = new java.util.HashMap<>();"
                )
            }

            if (containingClass.findMethodsByName(implementationName, false).isEmpty()) {
                val implementationMethod = method.copy() as PsiMethod
                implementationMethod.setName(implementationName)
                val modifiers = implementationMethod.modifierList
                modifiers.setModifierProperty(PsiModifier.PUBLIC, false)
                modifiers.setModifierProperty(PsiModifier.PROTECTED, false)
                modifiers.setModifierProperty(PsiModifier.PRIVATE, true)
                if (isStatic) {
                    modifiers.setModifierProperty(PsiModifier.STATIC, true)
                } else {
                    modifiers.setModifierProperty(PsiModifier.STATIC, false)
                }
                containingClass.addAfter(implementationMethod, method)
                val inserted = containingClass.findMethodsByName(implementationName, false)
                    .maxByOrNull { it.textOffset }
                if (inserted != null) {
                    javaStyleManager.shortenClassReferences(inserted)
                    codeStyleManager.reformat(inserted)
                }
            }

            val methodBodyText = buildString {
                appendLine("{")
                if (parameters.isEmpty()) {
                    appendLine("    if (${methodName}CacheInitialized) {")
                    appendLine("        return $cacheFieldName;")
                    appendLine("    }")
                    appendLine("    ${returnType.presentableText} __result = $implementationName();")
                    appendLine("    $cacheFieldName = __result;")
                    appendLine("    ${methodName}CacheInitialized = true;")
                    appendLine("    return __result;")
                } else {
                    val keyExpression = parameters.joinToString(", ") { it.name }
                    appendLine("    java.util.List<Object> __cacheKey = java.util.Arrays.asList($keyExpression);")
                    appendLine("    java.util.Map<java.util.List<Object>, $boxedReturnTypeName> __cache = $cacheFieldName;")
                    appendLine("    $boxedReturnTypeName __cachedValue = __cache.get(__cacheKey);")
                    appendLine("    if (__cachedValue != null || __cache.containsKey(__cacheKey)) {")
                    appendLine("        return __cachedValue;")
                    appendLine("    }")
                    appendLine("    ${returnType.presentableText} __result = $implementationName($parameterNames);")
                    appendLine("    __cache.put(__cacheKey, __result);")
                    appendLine("    return __result;")
                }
                append("}")
            }

            val newBody = elementFactory.createCodeBlockFromText(methodBodyText, body)
            body.replace(newBody)
            javaStyleManager.shortenClassReferences(method)
            codeStyleManager.reformat(method)

            javaStyleManager.shortenClassReferences(containingClass)
            codeStyleManager.reformat(containingClass)
        }
    }
}
