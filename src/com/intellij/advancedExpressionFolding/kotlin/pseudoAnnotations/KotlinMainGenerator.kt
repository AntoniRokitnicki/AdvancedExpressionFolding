package com.intellij.advancedExpressionFolding.kotlin.pseudoAnnotations

import com.intellij.psi.codeStyle.CodeStyleManager
import com.intellij.psi.util.PsiTreeUtil
import org.jetbrains.kotlin.lexer.KtTokens
import org.jetbrains.kotlin.psi.KtClass
import org.jetbrains.kotlin.psi.KtClassOrObject
import org.jetbrains.kotlin.psi.KtFile
import org.jetbrains.kotlin.psi.KtNamedFunction
import org.jetbrains.kotlin.psi.KtObjectDeclaration
import org.jetbrains.kotlin.psi.KtParameter
import org.jetbrains.kotlin.psi.KtPsiFactory

class KotlinMainGenerator(
    private val valueProvider: KotlinParameterValueProvider = KotlinParameterValueProvider()
) {
    fun removeExistingMain(file: KtFile) {
        file.declarations
            .filterIsInstance<KtNamedFunction>()
            .filter { it.name == MAIN_FUNCTION_NAME }
            .sortedByDescending { it.textOffset }
            .forEach { it.delete() }
    }

    fun buildMainFunction(function: KtNamedFunction): String? {
        val functionName = function.name ?: return null
        val container = resolveContainer(function)
        val usedNames = linkedSetOf<String>()

        val constructorValues = container.constructorParameters
            .filterNot { it.defaultValue != null }
            .mapIndexed { index, parameter ->
                valueProvider.from(parameter, index, usedNames)
            }

        val parameterValues = function.valueParameters.mapIndexed { index, parameter ->
            valueProvider.from(parameter, constructorValues.size + index, usedNames)
        }

        val callExpression = buildCallExpression(function, container, functionName, constructorValues, parameterValues)
            ?: return null

        val lines = mutableListOf("fun $MAIN_FUNCTION_NAME() {")

        appendDeclarations(lines, constructorValues)
        appendDeclarations(lines, parameterValues)

        if (callExpression.isNotEmpty()) {
            lines.add("    $callExpression")
        }

        lines.add("}")
        return lines.joinToString("\n")
    }

    fun insertMainFunction(text: String, file: KtFile): KtNamedFunction? {
        val project = file.project
        val psiFactory = KtPsiFactory(project, false)
        val mainFunction = psiFactory.createFunction(text)
        val anchor = file.declarations.lastOrNull()

        val inserted = if (anchor != null) {
            file.addAfter(psiFactory.createWhiteSpace("\n\n"), anchor)
            file.addAfter(mainFunction, anchor) as? KtNamedFunction
        } else {
            file.add(mainFunction) as? KtNamedFunction
        }

        return inserted
    }

    fun reformat(function: KtNamedFunction) {
        CodeStyleManager.getInstance(function.project).reformat(function)
    }

    private fun appendDeclarations(lines: MutableList<String>, values: List<ParameterValue>) {
        if (values.isEmpty()) return
        values.forEach { lines.add("    ${it.declaration}") }
    }

    private fun buildCallExpression(
        function: KtNamedFunction,
        container: ContainerInfo,
        functionName: String,
        constructorValues: List<ParameterValue>,
        parameterValues: List<ParameterValue>
    ): String? {
        val arguments = parameterValues.map { it.argument }
        val invocationTarget = when (container) {
            is ContainerInfo.TopLevel -> functionName
            is ContainerInfo.ClassContainer -> {
                val classCall = buildClassConstructorCall(container.klass, constructorValues.map { it.argument })
                if (classCall.isEmpty()) return null
                "$classCall.$functionName"
            }
            is ContainerInfo.ObjectContainer -> {
                val qualified = buildQualifiedName(container.objectDeclaration)
                if (qualified.isEmpty()) functionName else "$qualified.$functionName"
            }
            is ContainerInfo.CompanionContainer -> {
                val ownerName = container.owner.name ?: return functionName
                "$ownerName.Companion.$functionName"
            }
        }

        val call = callWithArguments(invocationTarget, arguments)
        return if (shouldPrintReturnValue(function)) {
            "println($call)"
        } else {
            call
        }
    }

    private fun shouldPrintReturnValue(function: KtNamedFunction): Boolean {
        val returnType = function.typeReference?.text?.trim() ?: return false
        val normalized = returnType.removeSuffix("?")
        return normalized !in setOf("Unit", "kotlin.Unit", "Nothing", "kotlin.Nothing")
    }

    private fun buildClassConstructorCall(klass: KtClass, arguments: List<String>): String {
        val className = buildQualifiedName(klass)
        if (className.isEmpty()) return ""
        val args = arguments.filter { it.isNotEmpty() }
        val argsList = if (args.isEmpty()) "" else args.joinToString(", ")
        return if (argsList.isEmpty()) "$className()" else "$className($argsList)"
    }

    private fun callWithArguments(target: String, arguments: List<String>): String {
        val args = arguments.filter { it.isNotEmpty() }
        return if (args.isEmpty()) "$target()" else "$target(${args.joinToString(", ")})"
    }

    private fun resolveContainer(function: KtNamedFunction): ContainerInfo {
        val objectDeclaration = PsiTreeUtil.getParentOfType(function, KtObjectDeclaration::class.java, true)
        if (objectDeclaration != null) {
            val ownerClass = PsiTreeUtil.getParentOfType(objectDeclaration, KtClass::class.java, true)
            return if (objectDeclaration.hasModifier(KtTokens.COMPANION_KEYWORD) && ownerClass != null) {
                ContainerInfo.CompanionContainer(objectDeclaration, ownerClass)
            } else {
                ContainerInfo.ObjectContainer(objectDeclaration)
            }
        }

        val klass = PsiTreeUtil.getParentOfType(function, KtClass::class.java, true)
        return if (klass != null) {
            ContainerInfo.ClassContainer(klass)
        } else {
            ContainerInfo.TopLevel
        }
    }

    private fun buildQualifiedName(element: KtClassOrObject): String {
        val names = mutableListOf<String>()
        var current: KtClassOrObject? = element
        while (current != null) {
            val name = current.name ?: return ""
            names.add(name)
            current = PsiTreeUtil.getParentOfType(current, KtClassOrObject::class.java, true)
        }
        return names.asReversed().joinToString(".")
    }

    private sealed interface ContainerInfo {
        val constructorParameters: List<KtParameter>

        data object TopLevel : ContainerInfo {
            override val constructorParameters: List<KtParameter> = emptyList()
        }

        data class ClassContainer(val klass: KtClass) : ContainerInfo {
            override val constructorParameters: List<KtParameter>
                get() = klass.primaryConstructor?.valueParameters ?: emptyList()
        }

        data class ObjectContainer(val objectDeclaration: KtObjectDeclaration) : ContainerInfo {
            override val constructorParameters: List<KtParameter> = emptyList()
        }

        data class CompanionContainer(val companion: KtObjectDeclaration, val owner: KtClass) : ContainerInfo {
            override val constructorParameters: List<KtParameter> = emptyList()
        }
    }

    companion object {
        private const val MAIN_FUNCTION_NAME = "main"
    }
}
