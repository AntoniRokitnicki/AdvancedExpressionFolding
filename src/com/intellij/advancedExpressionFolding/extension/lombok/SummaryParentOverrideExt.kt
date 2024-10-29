package com.intellij.advancedExpressionFolding.extension.lombok

import com.intellij.advancedExpressionFolding.expression.Expression
import com.intellij.advancedExpressionFolding.extension.*
import com.intellij.psi.PsiClass
import com.intellij.psi.PsiElement
import com.intellij.psi.impl.search.JavaAllOverridingMethodsSearcher
import com.intellij.psi.impl.search.MethodSuperSearcher

private data class ReferenceWithMethods(
    val element: PsiElement,
    val methods: List<String>
)

object SummaryParentOverrideExt : BaseExtension() {

    fun PsiClass.addParentSummary(): Expression? {
        summaryParentOverride.on() ?: return null
        val className = this.name ?: return null

        sequenceOf(this.extendsList, this.implementsList).filterNotNull().mapNotNull { parent ->
            val a = parent.referencedTypes
            parent.referenceElements.zip(a).mapNotNull { (refElement, type) ->
                val c = type.resolve()
                refElement.referenceNameElement?.let { element ->
                    val overriddenMethods = c?.methods?.filter { method ->
                        this.findMethodBySignature(method, false) != null
                    }?.map { it.name } ?: emptyList()
                    ReferenceWithMethods(element, overriddenMethods)
                }
            }
        }.flatten().toList().takeIf {
            it.isNotEmpty()
        }?.mapNotNull {
            val size = it.methods.size
            it.element.exprOnLastChar("($size-${asString(it)})")
        }?.let {
            return it.exprWrap(this)
        }
        return null
    }

    private fun asString(it: ReferenceWithMethods) = it.methods.joinToString(", ")
}