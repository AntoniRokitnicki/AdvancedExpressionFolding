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
        if (!mapEntryDestructuring || element.rParenth == null || !isDestructurable(element)) {
            return null
        }
        val entrySetCall = element.iteratedValue as PsiMethodCallExpression
        return MapEntryForEachExpression(
            element,
            element.iterationParameter,
            entrySetCall.methodExpression.qualifierExpression!!,
            entrySetCall,
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
        if (loop.iterationParameter != parameter || !isDestructurable(loop)) {
            return null
        }
        return MapEntryComponent(element, name)
    }

    private fun isDestructurable(loop: PsiForeachStatement): Boolean {
        val entrySetCall = loop.iteratedValue as? PsiMethodCallExpression ?: return false
        if (entrySetCall.methodExpression.referenceName != "entrySet" || entrySetCall.argumentCount != 0) {
            return false
        }
        val map = entrySetCall.methodExpression.qualifierExpression ?: return false
        if (!InheritanceUtil.isInheritor(map.type, CommonClassNames.JAVA_UTIL_MAP)) {
            return false
        }
        val body = loop.body ?: return false
        val entry = loop.iterationParameter
        val references = SyntaxTraverser.psiTraverser(body).filter(PsiReferenceExpression::class.java).toList()
        val entryUses = references.filter { it.referenceName == entry.name && it.resolve() == entry }
        return entryUses.all(::isComponentCallQualifier) &&
            references.none { it !in entryUses && it.readsBareComponentName() } &&
            SyntaxTraverser.psiTraverser(body).filter(PsiVariable::class.java).none { it.name in COMPONENTS.values }
    }

    private fun isComponentCallQualifier(use: PsiReferenceExpression): Boolean {
        val methodExpression = use.parent as? PsiReferenceExpression ?: return false
        val call = methodExpression.parent as? PsiMethodCallExpression ?: return false
        return methodExpression.qualifierExpression == use && componentName(call) != null
    }

    private fun PsiReferenceExpression.readsBareComponentName(): Boolean =
        qualifierExpression == null && referenceName in COMPONENTS.values && parent !is PsiMethodCallExpression

    private fun componentName(call: PsiMethodCallExpression): String? =
        COMPONENTS[call.methodExpression.referenceName]?.takeIf { call.argumentCount == 0 }
}
