package com.intellij.advancedExpressionFolding.extension.clazz

import com.intellij.advancedExpressionFolding.expression.Expression
import com.intellij.advancedExpressionFolding.expression.custom.SimpleExpression
import com.intellij.advancedExpressionFolding.extension.*
import com.intellij.advancedExpressionFolding.extension.Keys.METHOD_TO_PARENT_CLASS_KEY
import com.intellij.openapi.util.removeUserData
import com.intellij.psi.*
import com.intellij.psi.util.MethodSignature

private data class ReferenceWithMethods(
    val element: PsiElement,
    val methods: List<String>
)

object SummaryParentOverrideExt : BaseExtension() {

    fun PsiClass.addParentSummary(): Expression? {
        val parentToMethods = mutableMapOf<String, List<String>>()
        val methodToParentClass: MutableMap<MethodSignature, String> = mutableMapOf() //TODO: String->Pointer?

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
            val overriddenMethodsLabel = when {
                it.methods.isNotEmpty() -> "(${asCount(it)}-${asString(it)})"
                else -> "(nothing overridden)"
            }
            it.element.exprOnLastChar(overriddenMethodsLabel)
        }?.let {
            this.putUserData(METHOD_TO_PARENT_CLASS_KEY, methodToParentClass)
            return it.exprWrap(this)
        }
        this.removeUserData(METHOD_TO_PARENT_CLASS_KEY)
        return null
    }

    private fun asCount(it: ReferenceWithMethods) = it.methods.size

    private fun asString(it: ReferenceWithMethods) = it.methods.joinToString(", ")

    fun summaryParent(
        method: PsiMethod,
        list: MutableList<Expression?>
    ) {
        val hasOverride = method.annotations.any {
            it.isOverride()
        }
        list += hasOverride.on()?.let {
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
            val signature = element.getSignature()
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


