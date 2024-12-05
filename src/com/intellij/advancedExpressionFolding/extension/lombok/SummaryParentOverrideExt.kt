package com.intellij.advancedExpressionFolding.extension.lombok

import com.intellij.advancedExpressionFolding.expression.Expression
import com.intellij.advancedExpressionFolding.extension.*
import com.intellij.psi.PsiClass
import com.intellij.psi.PsiElement

private data class ReferenceWithMethods(
    val element: PsiElement,
    val methods: List<String>
)

object SummaryParentOverrideExt : BaseExtension() {

    fun PsiClass.addParentSummary(): Expression? {
        summaryParentOverride.on(name) ?: return null

        sequenceOf(this.extendsList, this.implementsList).filterNotNull().map { parent ->
            parent.referenceElements.zip(parent.referencedTypes).mapNotNull { (refElement, type) ->
                refElement.referenceNameElement?.let { element ->
                    val overriddenMethods = type.resolve()?.methods?.filter { method ->
                        //TODO: support sharedMethod from GrandparentClass as well here
                        findMethodBySignature(method, false) != null
                    }?.map {
                        it.name
                    } ?: emptyList()
                    ReferenceWithMethods(element, overriddenMethods)
                }
            }
        }.flatten().toList().takeIf {
            it.isNotEmpty()
        }?.mapNotNull {
            it.element.exprOnLastChar("(${asCount(it)}-${asString(it)})")
        }?.let {
            return it.exprWrap(this)
        }
        return null
    }

    private fun asCount(it: ReferenceWithMethods) = it.methods.size

    private fun asString(it: ReferenceWithMethods) = it.methods.joinToString(", ")
}