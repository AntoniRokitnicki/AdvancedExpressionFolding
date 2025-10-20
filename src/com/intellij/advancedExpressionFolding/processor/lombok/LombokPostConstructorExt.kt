package com.intellij.advancedExpressionFolding.processor.lombok

import com.intellij.advancedExpressionFolding.expression.Expression
import com.intellij.advancedExpressionFolding.expression.semantic.lombok.MethodAnnotationExpression
import com.intellij.advancedExpressionFolding.processor.asInstance
import com.intellij.advancedExpressionFolding.processor.core.BaseExtension
import com.intellij.advancedExpressionFolding.processor.group
import com.intellij.advancedExpressionFolding.processor.prevWhiteSpace
import com.intellij.advancedExpressionFolding.processor.util.GenericCallback
import com.intellij.openapi.editor.FoldingGroup
import com.intellij.openapi.util.Key
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiExpressionStatement
import com.intellij.psi.PsiMethod
import com.intellij.psi.PsiMethodCallExpression
import com.intellij.psi.PsiModifier
import com.intellij.psi.PsiThisExpression
import com.intellij.psi.PsiType

object LombokPostConstructorExt : BaseExtension() {

    private data class PostConstructorAnnotation(
        val elementsToHide: List<PsiElement?>,
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

        constructors.forEach { constructor ->
            val callStatement = constructor.body?.statements?.lastOrNull()
                .asInstance<PsiExpressionStatement>() ?: return@forEach
            val callExpression = callStatement.expression.asInstance<PsiMethodCallExpression>() ?: return@forEach
            if (callExpression.argumentList.expressionCount != 0) {
                return@forEach
            }

            val qualifier = callExpression.methodExpression.qualifierExpression
            if (qualifier != null && qualifier !is PsiThisExpression) {
                return@forEach
            }

            val resolved = callExpression.methodExpression.resolve().asInstance<PsiMethod>() ?: return@forEach
            if (resolved.containingClass != clazz) {
                return@forEach
            }

            methodCalls.getOrPut(resolved) { mutableListOf() }.add(callStatement)
        }

        methodCalls.forEach { (method, statements) ->
            if (!method.isValidPostConstructor(constructors.size, statements.size)) {
                return@forEach
            }

            val group = group("@PostConstructor-${method.hashCode()}")
            val elementsToHide = statements.flatMap { statement ->
                listOf(statement.prevWhiteSpace(), statement)
            }

            MethodCallback.initCallback(method, PostConstructorAnnotation(elementsToHide, group))
        }
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
        if (type != null && type != PsiType.VOID) return false
        return true
    }

    fun methodAnnotation(method: PsiMethod): Expression? {
        val annotation = with(MethodCallback) { method.callback?.invoke() } ?: return null
        val annotationText = LombokFoldingAnnotation.LOMBOK_POST_CONSTRUCTOR.annotation
        return MethodAnnotationExpression(
            method,
            listOf(annotationText),
            annotation.elementsToHide,
            group = annotation.group,
        )
    }
}
