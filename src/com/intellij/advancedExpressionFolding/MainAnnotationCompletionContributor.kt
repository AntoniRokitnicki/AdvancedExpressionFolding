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
import com.intellij.psi.CommonClassNames
import com.intellij.psi.JavaPsiFacade
import com.intellij.psi.PsiDocumentManager
import com.intellij.psi.PsiParserFacade
import com.intellij.psi.codeStyle.CodeStyleManager
import com.intellij.psi.codeStyle.JavaCodeStyleManager
import com.intellij.psi.util.PsiTreeUtil
import com.intellij.psi.util.MethodSignatureUtil
import com.intellij.psi.util.TypeConversionUtil
import com.intellij.util.ProcessingContext
import com.intellij.util.IncorrectOperationException

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

                    val lookup = LookupElementBuilder.create("Main")
                        .withLookupString("@Main")
                        .withPresentableText("@Main")
                        .withInsertHandler { ctx, _ ->
                            handleMainInsert(ctx)
                        }
                        .withAutoCompletionPolicy(AutoCompletionPolicy.NEVER_AUTOCOMPLETE)

                    result.addElement(lookup)
                }
            }
        )

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

                    val lookup = LookupElementBuilder.create("NotFullyImplemented")
                        .withLookupString("@NotFullyImplemented")
                        .withPresentableText("@NotFullyImplemented")
                        .withInsertHandler { ctx, _ ->
                            handleNotFullyImplementedInsert(ctx)
                        }
                        .withAutoCompletionPolicy(AutoCompletionPolicy.NEVER_AUTOCOMPLETE)

                    result.addElement(lookup)
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

    private fun handleNotFullyImplementedInsert(ctx: InsertionContext) {
        val project = ctx.project
        val element = ctx.file.findElementAt(ctx.startOffset)
        val annotation = PsiTreeUtil.getParentOfType(element, PsiAnnotation::class.java, false)
        val psiClass = PsiTreeUtil.getParentOfType(element, PsiClass::class.java, false) ?: return

        WriteCommandAction.runWriteCommandAction(project) {
            val documentManager = PsiDocumentManager.getInstance(project)
            documentManager.doPostponedOperationsAndUnblockDocument(ctx.document)

            annotation?.delete()
            psiClass.modifierList?.findAnnotation("NotFullyImplemented")?.delete()

            val methodsToImplement = collectUnimplementedInterfaceMethods(psiClass)
            if (methodsToImplement.isEmpty()) return@runWriteCommandAction

            val elementFactory = JavaPsiFacade.getElementFactory(project)
            val parserFacade = PsiParserFacade.SERVICE.getInstance(project)
            val javaCodeStyleManager = JavaCodeStyleManager.getInstance(project)
            val codeStyleManager = CodeStyleManager.getInstance(project)

            val addedMethods = methodsToImplement.mapNotNull { methodInfo ->
                val methodText = buildMethodText(methodInfo)
                val newMethod = try {
                    elementFactory.createMethodFromText(methodText, psiClass)
                } catch (ignored: IncorrectOperationException) {
                    null
                } ?: return@mapNotNull null

                val inserted = psiClass.add(newMethod) as? PsiMethod ?: return@mapNotNull null
                ensurePrecedingBlankLine(inserted, parserFacade)
                javaCodeStyleManager.shortenClassReferences(inserted)
                codeStyleManager.reformat(inserted)
                inserted
            }

            addedMethods.firstOrNull()?.let { method ->
                method.body?.statements?.firstOrNull()?.let { statement ->
                    ctx.editor.caretModel.moveToOffset(statement.textOffset)
                } ?: ctx.editor.caretModel.moveToOffset(method.textOffset)
            }

            documentManager.commitDocument(ctx.document)
        }
    }

    private fun collectUnimplementedInterfaceMethods(psiClass: PsiClass): List<MethodToImplement> {
        val result = LinkedHashMap<String, MethodToImplement>()
        val queue = ArrayDeque<Pair<PsiClass, PsiSubstitutor>>()

        for (iface in psiClass.interfaces) {
            val substitutor = TypeConversionUtil.getSuperClassSubstitutor(iface, psiClass, PsiSubstitutor.EMPTY)
            queue.addLast(iface to substitutor)
        }

        while (queue.isNotEmpty()) {
            val (iface, substitutor) = queue.removeFirst()
            if (!iface.isInterface) continue

            for (method in iface.methods) {
                if (method.isConstructor || method.hasModifierProperty(PsiModifier.STATIC) || method.hasModifierProperty(PsiModifier.DEFAULT)) {
                    continue
                }
                val containingInterface = method.containingClass
                if (containingInterface?.qualifiedName == CommonClassNames.JAVA_LANG_OBJECT) {
                    continue
                }

                val signature = method.getSignature(substitutor)
                val key = signature.toString()
                if (result.containsKey(key)) continue

                val existing = MethodSignatureUtil.findMethodBySignature(psiClass, signature, true)
                val hasConcreteImplementation = existing != null &&
                    !existing.hasModifierProperty(PsiModifier.ABSTRACT) &&
                    (existing.hasModifierProperty(PsiModifier.DEFAULT) || existing.containingClass?.isInterface == false)
                if (hasConcreteImplementation) continue

                result[key] = MethodToImplement(method, substitutor)
            }

            for (superInterface in iface.interfaces) {
                val combinedSubstitutor = TypeConversionUtil.getSuperClassSubstitutor(superInterface, iface, substitutor)
                queue.addLast(superInterface to combinedSubstitutor)
            }
        }

        return result.values.toList()
    }

    private fun ensurePrecedingBlankLine(element: PsiElement, parserFacade: PsiParserFacade) {
        val parent = element.parent ?: return
        val prevSibling = element.prevSibling
        when {
            prevSibling is PsiWhiteSpace -> {
                val newlineCount = prevSibling.text.count { it == '\n' }
                if (newlineCount < 2) {
                    prevSibling.replace(parserFacade.createWhiteSpaceFromText("\n\n"))
                }
            }
            else -> parent.addBefore(parserFacade.createWhiteSpaceFromText("\n\n"), element)
        }
    }

    private fun buildMethodText(methodInfo: MethodToImplement): String {
        val method = methodInfo.method
        val substitutor = methodInfo.substitutor

        val typeParametersText = method.typeParameterList?.text?.takeIf { it.isNotEmpty() }?.let { "$it " } ?: ""
        val returnType = substitutor.substitute(method.returnType)?.getCanonicalText(true) ?: PsiType.VOID.canonicalText

        val parameters = method.parameterList.parameters.joinToString(", ") { parameter ->
            val type = substitutor.substitute(parameter.type)
            val typeText = when (type) {
                is PsiEllipsisType -> type.componentType.canonicalText + "..."
                else -> type?.getCanonicalText(true) ?: "java.lang.Object"
            }
            "$typeText ${parameter.name ?: "param"}"
        }

        val throwsText = method.throwsList.referencedTypes
            .mapNotNull { substitutor.substitute(it)?.getCanonicalText(true) }
            .joinToString(", ")

        val header = buildString {
            append("@Override\n")
            append("public ")
            append(typeParametersText)
            append(returnType)
            append(' ')
            append(method.name)
            append('(')
            append(parameters)
            append(')')
            if (throwsText.isNotEmpty()) {
                append(" throws ")
                append(throwsText)
            }
        }

        return "$header {\n        throw new NotImplementedException();\n    }"
    }

    private data class MethodToImplement(val method: PsiMethod, val substitutor: PsiSubstitutor)

    private fun positionCursorAtMainMethod(code: String, insertOffset: Int, ctx: InsertionContext) {
        val mainMethodOffset = insertOffset + 1 + code.indexOf("public static void main")
        ctx.editor.caretModel.moveToOffset(mainMethodOffset)
    }
}
