package com.intellij.advancedExpressionFolding.processor.controlflow

import com.intellij.advancedExpressionFolding.expression.Expression
import com.intellij.advancedExpressionFolding.expression.semantic.kotlin.MapEntryComponent
import com.intellij.advancedExpressionFolding.expression.semantic.kotlin.MapEntryForEachExpression
import com.intellij.advancedExpressionFolding.processor.argumentCount
import com.intellij.advancedExpressionFolding.settings.State
import com.intellij.advancedExpressionFolding.settings.state.IKotlinLanguageState
import com.intellij.psi.CommonClassNames
import com.intellij.psi.PsiForeachStatement
import com.intellij.psi.PsiMethodCallExpression
import com.intellij.psi.PsiParameter
import com.intellij.psi.PsiReferenceExpression
import com.intellij.psi.PsiVariable
import com.intellij.psi.SyntaxTraverser
import com.intellij.psi.util.InheritanceUtil

/// Shows `for (Map.Entry<K, V> e : map.entrySet())` as `for ((key, value) in map)` and each `e.getKey()` /
/// `e.getValue()` in its body as `key` / `value`. Folds only a loop whose entry is read through those two calls alone
/// and whose body has no other `key` / `value`, so the shown names never hide what the code does.
object MapEntryDestructuringExt : IKotlinLanguageState by State()() {

    private val COMPONENTS = mapOf("getKey" to "key", "getValue" to "value")

    fun getForEachExpression(element: PsiForeachStatement): Expression? {
        if (!mapEntryDestructuring || element.rParenth == null) {
            return null
        }
        val usedComponents = usedComponents(element) ?: return null
        val entrySetCall = element.iteratedValue as PsiMethodCallExpression
        return MapEntryForEachExpression(
            element,
            element.iterationParameter,
            entrySetCall.methodExpression.qualifierExpression!!,
            entrySetCall,
            destructuringHeader(usedComponents),
        )
    }

    fun getComponentExpression(element: PsiMethodCallExpression): Expression? {
        if (!mapEntryDestructuring) {
            return null
        }
        val name = componentName(element) ?: return null
        val entry = element.methodExpression.qualifierExpression as? PsiReferenceExpression ?: return null
        val parameter = entry.resolve() as? PsiParameter ?: return null
        val loop = parameter.declarationScope as? PsiForeachStatement ?: return null
        if (loop.iterationParameter != parameter || usedComponents(loop) == null) {
            return null
        }
        return MapEntryComponent(element, name)
    }

    /// Names of the components the loop body reads (`key`, `value`), or `null` when the loop cannot be destructured.
    private fun usedComponents(loop: PsiForeachStatement): Set<String>? {
        val entrySetCall = loop.iteratedValue as? PsiMethodCallExpression ?: return null
        if (entrySetCall.methodExpression.referenceName != "entrySet" || entrySetCall.argumentCount != 0) {
            return null
        }
        val map = entrySetCall.methodExpression.qualifierExpression ?: return null
        if (!InheritanceUtil.isInheritor(map.type, CommonClassNames.JAVA_UTIL_MAP)) {
            return null
        }
        val body = loop.body ?: return null
        val entry = loop.iterationParameter
        val references = SyntaxTraverser.psiTraverser(body).filter(PsiReferenceExpression::class.java).toList()
        val entryUses = references.filter { it.referenceName == entry.name && it.resolve() == entry }
        val components = entryUses.map { componentReadBy(it) ?: return null }.toSet()
        val namesCollide = references.any { it !in entryUses && it.readsBareComponentName() } ||
            SyntaxTraverser.psiTraverser(body).filter(PsiVariable::class.java).any { it.name in COMPONENTS.values }
        return components.takeUnless { namesCollide }
    }

    // A component the body never reads shows as `_`, the way Kotlin skips it; with none read both names stay.
    private fun destructuringHeader(usedComponents: Set<String>): String {
        val names = COMPONENTS.values.map { name -> if (usedComponents.isEmpty() || name in usedComponents) name else "_" }
        return names.joinToString(", ", prefix = "(", postfix = ") in ")
    }

    private fun componentReadBy(use: PsiReferenceExpression): String? {
        val methodExpression = use.parent as? PsiReferenceExpression ?: return null
        val call = methodExpression.parent as? PsiMethodCallExpression ?: return null
        return componentName(call).takeIf { methodExpression.qualifierExpression == use }
    }

    private fun PsiReferenceExpression.readsBareComponentName(): Boolean =
        qualifierExpression == null && referenceName in COMPONENTS.values && parent !is PsiMethodCallExpression

    private fun componentName(call: PsiMethodCallExpression): String? =
        COMPONENTS[call.methodExpression.referenceName]?.takeIf { call.argumentCount == 0 }
}
