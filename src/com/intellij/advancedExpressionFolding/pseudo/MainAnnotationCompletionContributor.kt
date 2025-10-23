package com.intellij.advancedExpressionFolding.pseudo

import com.intellij.advancedExpressionFolding.processor.isVoid
import com.intellij.advancedExpressionFolding.settings.AdvancedExpressionFoldingSettings.Companion.getInstance
import com.intellij.advancedExpressionFolding.settings.ILombokState
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

class MainAnnotationCompletionContributor(private val state: IState = getInstance().state) : CompletionContributor(),
    ILombokState by state {
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
            "java.lang.String" -> "\"\""
            "java.lang.StringBuilder" -> "new java.lang.StringBuilder()"
            "java.lang.StringBuffer" -> "new java.lang.StringBuffer()"
            "java.math.BigDecimal" -> "java.math.BigDecimal.ZERO"
            "java.math.BigInteger" -> "java.math.BigInteger.ZERO"
            "java.util.Date" -> "new java.util.Date()"
            "java.time.LocalDate" -> "java.time.LocalDate.now()"
            "java.time.LocalDateTime" -> "java.time.LocalDateTime.now()"
            "java.time.ZonedDateTime" -> "java.time.ZonedDateTime.now()"
            else -> "null"
        }

        return if (isEllipsis) "new ${effectiveType.presentableText}[]{}" else base
    }

    private fun initializerValue(type: PsiType): String {
        return if (type is PsiEllipsisType) {
            val componentType = type.componentType
            val elementDefault = defaultValue(componentType)
            val arrayContents = if (elementDefault == "null") "" else elementDefault
            if (arrayContents.isEmpty()) {
                "new ${componentType.presentableText}[]{}"
            } else {
                "new ${componentType.presentableText}[]{${elementDefault}}"
            }
        } else {
            defaultValue(type)
        }
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
                add("        $typeText ${it.name} = ${initializerValue(type)};")
            }
            if (constructorParams.isNotEmpty()) add("")
            methodParams.forEach {
                val type = it.type
                val typeText = if (type is PsiEllipsisType) type.componentType.presentableText + "[]" else type.presentableText
                add("        $typeText ${it.name} = ${initializerValue(type)};")
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
        val insertOffset = clazz.lBrace?.let { it.textOffset + 1 } ?: clazz.textRange.endOffset

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
}
