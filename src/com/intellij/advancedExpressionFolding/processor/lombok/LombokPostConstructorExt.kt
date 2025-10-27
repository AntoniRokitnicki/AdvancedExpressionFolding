package com.intellij.advancedExpressionFolding.processor.lombok

import com.intellij.advancedExpressionFolding.expression.Expression
import com.intellij.advancedExpressionFolding.expression.semantic.lombok.MethodAnnotationExpression
import com.intellij.advancedExpressionFolding.processor.asInstance
import com.intellij.advancedExpressionFolding.processor.isVoid
import com.intellij.advancedExpressionFolding.processor.core.BaseExtension
import com.intellij.advancedExpressionFolding.processor.group
import com.intellij.advancedExpressionFolding.processor.util.GenericCallback
import com.intellij.openapi.editor.FoldingGroup
import com.intellij.openapi.util.Key
import com.intellij.psi.PsiExpressionStatement
import com.intellij.psi.PsiMethod
import com.intellij.psi.PsiMethodCallExpression
import com.intellij.psi.PsiModifier
import com.intellij.psi.PsiThisExpression

object LombokPostConstructorExt : BaseExtension() {

    private data class PostConstructorAnnotation(
        val annotationText: String,
        val group: FoldingGroup,
    )

    private object MethodCallback : GenericCallback<PsiMethod, PostConstructorAnnotation> {
        override val callbackKey: Key<() -> PostConstructorAnnotation> =
            Key.create("lombok-post-constructor-method-callback")
    }

    fun prepare(clazz: com.intellij.psi.PsiClass) {
        if (clazz.isInterface || clazz.constructors.isEmpty()) {
            return
        }

        val constructors = clazz.constructors.filter { it.body != null }
        if (constructors.isEmpty()) {
            return
        }

        val methodCalls = mutableMapOf<PsiMethod, MutableList<PsiExpressionStatement>>()
        var orderedMethods: List<PsiMethod>? = null

        constructors.forEach { constructor ->
            val calls = constructor.collectTrailingPostConstructorCalls(clazz)
            if (orderedMethods == null && calls.isNotEmpty()) {
                orderedMethods = calls.map { it.first }
            }
            calls.forEach { (method, statement) ->
                methodCalls.getOrPut(method) { mutableListOf() }.add(statement)
            }
        }

        val validMethods = methodCalls.filter { (method, statements) ->
            method.isValidPostConstructor(constructors.size, statements.size)
        }

        if (validMethods.isEmpty()) {
            return
        }

        val validMethodSet = validMethods.keys
        val canonicalOrder = orderedMethods?.filter { it in validMethodSet }.orEmpty()

        val baseAnnotation = LombokFoldingAnnotation.LOMBOK_POST_CONSTRUCTOR.annotation
        val annotationTexts = mutableMapOf<PsiMethod, String>()
        if (validMethodSet.size <= 1 || canonicalOrder.isEmpty()) {
            validMethodSet.forEach { method ->
                annotationTexts[method] = baseAnnotation
            }
        } else {
            canonicalOrder.forEachIndexed { index, method ->
                annotationTexts[method] = "$baseAnnotation(${index + 1})"
            }
            validMethodSet
                .filterNot { it in annotationTexts }
                .forEach { method -> annotationTexts[method] = baseAnnotation }
        }

        annotationTexts.forEach { (method, annotationText) ->
            val group = group("@PostConstructor-${method.hashCode()}")
            MethodCallback.initCallback(method, PostConstructorAnnotation(annotationText, group))
        }
    }

    private fun PsiMethod.collectTrailingPostConstructorCalls(
        clazz: com.intellij.psi.PsiClass,
    ): List<Pair<PsiMethod, PsiExpressionStatement>> {
        val body = body ?: return emptyList()
        val statements = body.statements
        if (statements.isEmpty()) {
            return emptyList()
        }

        val calls = mutableListOf<Pair<PsiMethod, PsiExpressionStatement>>()
        for (statement in statements.reversed()) {
            val expressionStatement = statement.asInstance<PsiExpressionStatement>() ?: break
            val callExpression = expressionStatement.expression.asInstance<PsiMethodCallExpression>() ?: break
            if (callExpression.argumentList.expressionCount != 0) {
                break
            }

            val qualifier = callExpression.methodExpression.qualifierExpression
            if (qualifier != null && qualifier !is PsiThisExpression) {
                break
            }

            val resolved = callExpression.methodExpression.resolve().asInstance<PsiMethod>() ?: break
            if (resolved.containingClass != clazz) {
                break
            }

            calls.add(0, resolved to expressionStatement)
        }
        return calls
    }

    private fun PsiMethod.isValidPostConstructor(
        totalConstructors: Int,
        callCount: Int,
    ): Boolean {
        if (isConstructor) return false
        if (callCount != totalConstructors) return false
        if (hasModifierProperty(PsiModifier.STATIC)) return false
        if (hasModifierProperty(PsiModifier.ABSTRACT)) return false
        if (parameterList.parametersCount != 0) return false
        if (throwsList.referenceElements.isNotEmpty()) return false
        val type = returnType
        if (type != null && !type.isVoid()) return false
        return true
    }

    fun methodAnnotation(method: PsiMethod): Expression? {
        val annotation = with(MethodCallback) { method.callback?.invoke() } ?: return null
        return MethodAnnotationExpression(
            method,
            listOf(annotation.annotationText),
            emptyList(),
            group = annotation.group,
        )
    }
}
