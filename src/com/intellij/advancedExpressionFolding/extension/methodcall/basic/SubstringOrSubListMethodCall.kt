package com.intellij.advancedExpressionFolding.extension.methodcall.basic

import com.intellij.advancedExpressionFolding.expression.Expression
import com.intellij.advancedExpressionFolding.expression.literal.NumberLiteral
import com.intellij.advancedExpressionFolding.expression.operation.collection.Slice
import com.intellij.advancedExpressionFolding.extension.BuildExpressionExt
import com.intellij.advancedExpressionFolding.extension.Helper
import com.intellij.advancedExpressionFolding.extension.methodcall.AbstractMethodCall
import com.intellij.advancedExpressionFolding.extension.methodcall.Context
import com.intellij.advancedExpressionFolding.extension.methodcall.NeedsQualifier
import com.intellij.psi.PsiBinaryExpression
import com.intellij.psi.PsiExpression
import com.intellij.psi.PsiIdentifier
import com.intellij.psi.PsiMethodCallExpression

class SubstringOrSubListMethodCall : AbstractMethodCall(), NeedsQualifier {
    override fun permission() = slicingExpressionsCollapse

    override val methodNames by lazy { listOf("substring", "subList") }
    
    override val classNames by lazy { listOf(
        "java.lang.String",
        "java.util.List",
        "java.util.ArrayList"
    ) }
    
    override fun onSingleArgument(
        element: PsiMethodCallExpression,
        context: Context,
        argument: PsiExpression,
        argumentExpression: Expression
    ): Expression? {
        val qualifier = context.qualifierExpr
        
        if (argument is PsiBinaryExpression) {
            val position = Helper.getSlicePosition(element, qualifier, argument, context.document)
            if (position != null) {
                return Slice(
                    element,
                    element.textRange,
                    context.getOperands()
                )
            }
        }
        return Slice(
            element,
            element.textRange,
            context.getOperands()
        )
    }

    override fun onTwoArguments(
        element: PsiMethodCallExpression,
        context: Context,
        a1: PsiExpression,
        a2: PsiExpression,
        a1Expression: Expression,
        a2Expression: Expression
    ): Expression? {
        val qualifierExpression = context.qualifierExprNullable ?: return null
        val document = context.document

        if (a1 is PsiBinaryExpression) {
            val p1 = Helper.getSlicePosition(element, qualifierExpression, a1, document)
            if (p1 != null) {
                when {
                    a2Expression is NumberLiteral -> {
                        return Slice(
                            element,
                            element.textRange,
                            listOf(qualifierExpression, p1, a2Expression)
                        )
                    }

                    a2 is PsiBinaryExpression -> {
                        val p2 = Helper.getSlicePosition(element, qualifierExpression, a2, document)
                        if (p2 != null) {
                            return Slice(
                                element,
                                element.textRange,
                                listOf(qualifierExpression, p1, p2)
                            )
                        }
                    }

                    a2 is PsiMethodCallExpression -> {
                        val a2me = a2.methodExpression
                        val a2i = a2me.children
                            .firstOrNull { it is PsiIdentifier }
                        val q = a2me.qualifierExpression

                        if (a2i != null && q != null &&
                            (a2i.text == "length" || a2i.text == "size")) {

                            val a2qe = BuildExpressionExt.getAnyExpression(q, document)
                            if (a2qe == qualifierExpression) {
                                return Slice(
                                    element,
                                    element.textRange,
                                    listOf(qualifierExpression, p1)
                                )
                            }
                        }
                    }
                }
            }
        }

        if (a2 is PsiBinaryExpression) {
            val position = Helper.getSlicePosition(element, qualifierExpression, a2, document)
            if (position != null) {
                return Slice(
                    element,
                    element.textRange,
                    listOf(qualifierExpression, a1Expression, position)
                )
            }
        } else if (a2 is PsiMethodCallExpression) {
            val a2me = a2.methodExpression
            val a2i = a2me.children
                .firstOrNull { it is PsiIdentifier }
            val q = a2me.qualifierExpression

            if (a2i != null && q != null &&
                (a2i.text == "length" || a2i.text == "size")) {

                val a2qe = BuildExpressionExt.getAnyExpression(q, document)
                if (a2qe == qualifierExpression) {
                    return Slice(
                        element,
                        element.textRange,
                        listOf(qualifierExpression, a1Expression)
                    )
                }
            }
        }

        return Slice(
            element,
            element.textRange,
            context.getOperands()
        )
    }
}
