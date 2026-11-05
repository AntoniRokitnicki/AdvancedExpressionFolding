package com.intellij.advancedExpressionFolding.processor.lombok

import com.intellij.advancedExpressionFolding.expression.Expression
import com.intellij.advancedExpressionFolding.expression.semantic.SimpleExpression
import com.intellij.advancedExpressionFolding.processor.asInstance
import com.intellij.advancedExpressionFolding.processor.expr
import com.intellij.advancedExpressionFolding.processor.isOverride
import com.intellij.advancedExpressionFolding.processor.one
import com.intellij.advancedExpressionFolding.processor.signature
import com.intellij.advancedExpressionFolding.processor.takeIfTrue
import com.intellij.advancedExpressionFolding.processor.cache.Keys.METHOD_TO_PARENT_CLASS_KEY
import com.intellij.openapi.util.removeUserData
import com.intellij.psi.*
import com.intellij.psi.util.MethodSignature

private data class ParentWithOverrides(
    val nameElement: PsiIdentifier,
    val overriddenMethods: List<PsiMethod>
)

object SummaryParentOverrideExt {

    fun PsiClass.rememberParentOfOverriddenMethods() {
        val methodToParentClass: MutableMap<MethodSignature, String> = parentsWithOverrides().flatMap { parent ->
            parent.overriddenMethods.map { method -> method.signature to parent.nameElement.text }
        }.toMap(mutableMapOf())
        if (methodToParentClass.isEmpty()) {
            removeUserData(METHOD_TO_PARENT_CLASS_KEY)
        } else {
            putUserData(METHOD_TO_PARENT_CLASS_KEY, methodToParentClass)
        }
    }

    /**
     * Pairs each parent name in `extends` / `implements` with its override summary,
     * e.g. `(2-equals, hashCode)` or `(nothing overridden)`.
     */
    fun PsiClass.parentOverrideLabels(): List<Pair<PsiIdentifier, String>> =
        parentsWithOverrides().map { parent ->
            val methodNames = parent.overriddenMethods.map { method -> method.name }
            val label = when {
                methodNames.isNotEmpty() -> "(${methodNames.size}-${methodNames.joinToString(", ")})"
                else -> "(nothing overridden)"
            }
            parent.nameElement to label
        }

    private fun PsiClass.parentsWithOverrides(): List<ParentWithOverrides> =
        sequenceOf(extendsList, implementsList).filterNotNull().flatMap { parents ->
            parents.referenceElements.zip(parents.referencedTypes).mapNotNull { (reference, type) ->
                val nameElement = reference.referenceNameElement.asInstance<PsiIdentifier>() ?: return@mapNotNull null
                //TODO: support sharedMethod from GrandparentClass as well here
                val overriddenMethods = type.resolve()?.methods?.filter { method ->
                    findMethodBySignature(method, false) != null
                } ?: emptyList()
                ParentWithOverrides(nameElement, overriddenMethods)
            }
        }.toList()

    fun summaryParent(
        method: PsiMethod,
        list: MutableList<Expression?>
    ) {
        val hasOverride = method.annotations.any {
            it.isOverride()
        }
        list += hasOverride.takeIfTrue()?.let {
            method.body
        }?.let { body ->
            createOverridesComment(method, body)
        }
    }

    private fun createOverridesComment(
        element: PsiMethod,
        body: PsiCodeBlock
    ): SimpleExpression? {
        val oneLiner = body.statements.one
        return if (oneLiner) {
            body.rBrace
        } else {
            body.lBrace
        }?.let { brace ->
            val signature = element.signature
            element.containingClass?.getUserData(METHOD_TO_PARENT_CLASS_KEY)
                ?.get(signature)?.let {
                    val prefix = if (oneLiner) {
                        '}'
                    } else {
                        '{'
                    }
                    brace.expr("$prefix // overrides from $it")
                }
        }
    }
}


