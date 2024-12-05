package com.intellij.advancedExpressionFolding.extension.lombok

import com.intellij.advancedExpressionFolding.expression.Expression
import com.intellij.advancedExpressionFolding.extension.*
import com.intellij.advancedExpressionFolding.extension.Keys.METHOD_TO_PARENT_CLASS_KEY
import com.intellij.openapi.rd.util.putUserData
import com.intellij.openapi.util.removeUserData
import com.intellij.psi.*
import com.intellij.psi.util.MethodSignature

private data class ReferenceWithMethods(
    val element: PsiElement,
    val methods: List<String>
)

object SummaryParentOverrideExt : BaseExtension() {

    fun PsiClass.addParentSummary(): Expression? {
        summaryParentOverride.on(name) ?: return null
        val parentToMethods = mutableMapOf<String, List<String>>()
        val methodToParentClass: MutableMap<MethodSignature, String> = mutableMapOf<MethodSignature, String>() //TODO: String->Pointer?

        sequenceOf(this.extendsList, this.implementsList).filterNotNull().map { parent ->
            parent.referenceElements.zip(parent.referencedTypes).mapNotNull { (refElement, type) ->
                refElement.referenceNameElement?.let { element ->
                    val className = element.asInstance<PsiIdentifier>()?.text ?: return@let null
                    val overriddenMethods = type.resolve()?.methods?.filter { method ->
                        //TODO: support sharedMethod from GrandparentClass as well here
                        findMethodBySignature(method, false) != null
                    }?.map {
                        val sig = it.getSignature()
                        methodToParentClass[sig] = className
                        it.name
                    } ?: emptyList()
                    // element=FirstInterface
                    // overriddenMethods=["interfaceMethodOne", "sharedMethod"]
                    parentToMethods[className] = overriddenMethods
                    ReferenceWithMethods(element, overriddenMethods)
                }
            }
        }.flatten().toList().takeIf {
            it.isNotEmpty()
        }?.mapNotNull {
            it.element.exprOnLastChar("(${asCount(it)}-${asString(it)})")
        }?.let {
            this.putUserData(METHOD_TO_PARENT_CLASS_KEY, methodToParentClass)
            return it.exprWrap(this)
        }
        this.removeUserData(METHOD_TO_PARENT_CLASS_KEY)
        return null
    }

    private fun asCount(it: ReferenceWithMethods) = it.methods.size

    private fun asString(it: ReferenceWithMethods) = it.methods.joinToString(", ")
}


