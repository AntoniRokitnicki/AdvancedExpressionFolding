package com.intellij.advancedExpressionFolding.processor.util

import com.intellij.advancedExpressionFolding.expression.Expression
import com.intellij.advancedExpressionFolding.expression.literal.NumberLiteral
import com.intellij.advancedExpressionFolding.processor.core.BuildExpressionExt
import com.intellij.openapi.editor.Document
import com.intellij.openapi.util.TextRange
import com.intellij.psi.PsiBinaryExpression
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiIdentifier
import com.intellij.psi.PsiMethodCallExpression
import com.intellij.psi.PsiReferenceExpression

object PsiTreeUtilEx {

    fun findChildExpressions(element: PsiElement, expressions: MutableList<Expression>, document: Document) {
        for (child in element.children) {
            val expression = BuildExpressionExt.getNonSyntheticExpression(child, document)
            if (expression != null) {
                expressions.add(expression)
            }
            if (expression == null || expression.textRange != child.textRange) {
                findChildExpressions(child, expressions, document)
            }
        }
    }

    inline fun findAncestorsUntilClass(element: PsiElement, ancestorClass: Class<out PsiElement>): Sequence<PsiElement> {
        return findAncestorsUntil(element) { parent -> !ancestorClass.isInstance(parent) }
    }

    inline fun findAncestorsUntil(
        element: PsiElement,
        crossinline untilPredicate: (PsiElement) -> Boolean
    ): Sequence<PsiElement> {
        return generateSequence(element.parent) { it?.parent }
            .takeWhile { it != null && untilPredicate(it) }
            .filterNotNull()
    }

    fun <T : PsiElement> findChildByTypeHierarchy(
        element: PsiElement,
        childClass: Class<T>,
        vararg children: Class<out PsiElement>
    ): T? {
        val classQueue = children.toList()
        val next = classQueue.firstOrNull() ?: return null
        for (child in element.children) {
            if (next.isInstance(child)) {
                return if (classQueue.size == 1) {
                    childClass.cast(child)
                } else {
                    val remaining = classQueue.drop(1).toTypedArray()
                    findChildByTypeHierarchy(child, childClass, *remaining)
                }
            }
        }
        return null
    }

    fun getSlicePosition(
        parent: PsiElement,
        qualifierExpression: Expression,
        a2b: PsiBinaryExpression,
        document: Document
    ): NumberLiteral? {
        val rOperand = a2b.rOperand ?: return null
        val lOperand = a2b.lOperand
        if (a2b.operationSign.text == "-" &&
            (lOperand is PsiMethodCallExpression || lOperand is PsiReferenceExpression)
        ) {
            val s = BuildExpressionExt.getAnyExpression(rOperand, document)
            if (s is NumberLiteral) {
                val methodExpression = when (lOperand) {
                    is PsiMethodCallExpression -> lOperand.methodExpression
                    is PsiReferenceExpression -> lOperand
                    else -> return null
                }
                val identifier = methodExpression.children.firstOrNull { it is PsiIdentifier } as? PsiIdentifier
                if (identifier != null && (identifier.text == "length" || identifier.text == "size") &&
                    methodExpression.qualifierExpression != null
                ) {
                    val qualifier = BuildExpressionExt.getAnyExpression(methodExpression.qualifierExpression!!, document)
                    if (qualifier == qualifierExpression) {
                        return NumberLiteral(
                            parent,
                            TextRange.create(a2b.operationSign.textRange.startOffset, a2b.textRange.endOffset),
                            null,
                            -s.number.toInt(),
                            false
                        )
                    }
                }
            }
        }
        return null
    }
}
