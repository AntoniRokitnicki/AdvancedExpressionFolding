package com.intellij.advancedExpressionFolding.processor.language.kotlin

import com.intellij.advancedExpressionFolding.expression.Expression
import com.intellij.advancedExpressionFolding.expression.property.IGetter
import com.intellij.advancedExpressionFolding.expression.property.INameable
import com.intellij.advancedExpressionFolding.expression.semantic.WrapperExpression
import com.intellij.advancedExpressionFolding.expression.semantic.kotlin.IfNullSafeExpression
import com.intellij.advancedExpressionFolding.processor.asInstance
import com.intellij.advancedExpressionFolding.processor.core.BaseExtension
import com.intellij.advancedExpressionFolding.processor.core.BuildExpressionExt
import com.intellij.advancedExpressionFolding.processor.end
import com.intellij.advancedExpressionFolding.processor.start
import com.intellij.advancedExpressionFolding.processor.toTextRange
import com.intellij.openapi.editor.Document
import com.intellij.psi.*
import com.intellij.psi.util.elementType


object IfNullSafeExt : BaseExtension() {

    @JvmStatic
    fun createExpression(element: PsiPolyadicExpression, document: Document): Expression? {
        if (!ifNullSafe) {
            return null
        }
        if (element.operands.size < 2) {
            return null
        }

        val lists = cutToLists(element,document).takeIf {
            it.isNotEmpty()
        } ?: return null

        val untouched = (element.operands.toList() - lists.flatten().toSet()).mapNotNull {
            BuildExpressionExt.getAnyExpression(it, document)
        }

        return WrapperExpression(element, element.textRange, lists.filter {
            it.size > 1
        }.flatMap {
            buildChain(it, document, element)
        } + untouched)

    }

    private fun buildChain(
        it: LinkedHashSet<PsiElement>,
        document: Document,
        element: PsiPolyadicExpression
    ): Collection<Expression> {
        singleChain(it, document, element)?.let { return listOf(it) }
        return it.map {
            BuildExpressionExt.getAnyExpression(it, document)
        }
    }

    private fun cutToLists(
        element: PsiPolyadicExpression,
        document: Document?
    ): MutableList<LinkedHashSet<PsiElement>> {
        val lists = mutableListOf<LinkedHashSet<PsiElement>>()

        var currentList = LinkedHashSet<PsiElement>()
        element.operands.toList().zipWithNext { prev, curr ->
            val and = element.getTokenBeforeOperand(curr).elementType == JavaTokenType.ANDAND

            val firstNotNullWithAnd = and && prev.isEqualsNotNullBinaryExpression()
            if (firstNotNullWithAnd && (curr.isEqualsNotNullBinaryExpression())) {
                if (isNext((prev as PsiBinaryExpression).lOperand, (curr as PsiBinaryExpression).lOperand)) {
                    currentList += prev
                    currentList += curr
                } else {
                    if (currentList.isNotEmpty()) {
                        lists += currentList
                        currentList = LinkedHashSet()
                    }
                }
            } else {
                if (firstNotNullWithAnd) {
                    val lOperand = (prev as PsiBinaryExpression).lOperand
                    if (curr is PsiPrefixExpression) {
                        // !isActive
                        val isNegation = curr.operationTokenType == JavaTokenType.EXCL
                        val callExpression = curr.operand as? PsiMethodCallExpression
                        if (isNegation && callExpression != null && isNext(lOperand, callExpression)) {
                            if (callExpression.let { BuildExpressionExt.getAnyExpression(it, document) } is IGetter) {
                                currentList += prev
                                currentList += curr
                            }
                        }
                    } else if (BuildExpressionExt.getAnyExpression(curr, document) is IGetter) {
                        // isActive
                        if (isNext(lOperand, curr)) {
                            currentList += prev
                            currentList += curr
                        }
                    }
                }
                if (currentList.isNotEmpty()) {
                    lists += currentList
                    currentList = LinkedHashSet()
                }
            }
            null
        }
        if (currentList.isNotEmpty()) {
            lists += currentList
        }
        return lists
    }

    fun singleChain(
        currentList: LinkedHashSet<PsiElement>,
        document: Document,
        element: PsiPolyadicExpression
    ): Expression? {
        val elementList = mutableListOf<PsiElement>()
        for (candidate in currentList) {
            val element = when (candidate) {
                is PsiBinaryExpression -> candidate.lOperand
                is PsiPrefixExpression -> candidate.operand ?: return null
                else -> candidate
            }
            elementList += element
        }
        var replacementText = elementList
            .map {
                BuildExpressionExt.getAnyExpression(it, document)
            }
            .map {
                if (it is INameable) {
                    it.name
                } else {
                    (it.element as? PsiMethodCallExpression)?.methodExpression?.referenceName ?: return null
                }
            }.joinToString(separator = "?.") {
               it
            }

        val iGetter = BuildExpressionExt.getAnyExpression(elementList.first(), document).asInstance<IGetter>()
        val parentGetter = iGetter?.receiver
        if (parentGetter != null) {

            var parent = parentGetter
            while (true) {
                val expression = parent.asInstance<IGetter>()?.receiver
                if (expression != null) {
                    parent = expression
                } else {
                    break
                }
            }


            val toMutableList =
                iGetter.buildFoldRegions(parentGetter.element, document, null).reversed().toMutableList()
            toMutableList.removeAt(toMutableList.lastIndex)

            var prefix = ""
            if (parent !is IGetter && parent is INameable) {
                prefix = parent.name
                if (toMutableList.isNotEmpty()) {
                    prefix += "."
                }
            }

            prefix += toMutableList.map {
                it.placeholderText
            }.joinToString(".")

            replacementText = "$prefix.$replacementText"
        }

        val lastGetter = currentList.last() is PsiMethodCallExpression
        val lastGetterNegation = currentList.last() is PsiPrefixExpression

        replacementText += if (lastGetter) {
            " == true"
        } else if (lastGetterNegation) {
            " == false"
        } else {
            " != null"
        }

        val startOffset = currentList.first().start()
        val endOffset = currentList.last().end()

        return IfNullSafeExpression(element, (startOffset to endOffset).toTextRange(), replacementText)
    }


    private fun PsiMethodCallExpression.firstChildQualifierExpression(): PsiExpression? {
        return firstChild.asInstance<PsiReferenceExpression>()?.qualifierExpression
    }


    private val PsiExpression.operandReference: PsiElement?
        get() {
            if (this is PsiReferenceExpression) {
                return this.resolve()
            }
            if (this is PsiMethodCallExpression) {
                return this.firstChildQualifierExpression()
            }
            return null
        }

    private val PsiExpression.operandParentReference: PsiElement?
        get() {
            if (this is PsiMethodCallExpression) {
                val qualifierExpression = firstChildQualifierExpression()
                if (qualifierExpression is PsiReferenceExpression) {
                    return qualifierExpression.operandReference
                } else if (qualifierExpression is PsiMethodCallExpression) {
                    return qualifierExpression.operandReference
                }
            }
            return null
        }

    private fun isNext(
        prev: PsiExpression,
        curr: PsiExpression
    ): Boolean {
        val first = prev.operandReference.toString()
        val second = curr.operandParentReference.toString()
        return first == second
    }


    private fun PsiExpression?.isEqualsNotNullBinaryExpression(): Boolean {
        val psiBinaryExpression = this as? PsiBinaryExpression ?: return false
        (psiBinaryExpression.rOperand as? PsiLiteralExpression)
            ?.let {
                if (it.value == null) {
                    it
                } else {
                    null
                }
            } ?: return false
        val token = psiBinaryExpression.operationTokenType
        return token == JavaTokenType.NE
    }

}



