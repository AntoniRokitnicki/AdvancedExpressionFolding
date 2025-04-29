package com.intellij.advancedExpressionFolding.extension

import com.intellij.advancedExpressionFolding.expression.Expression
import com.intellij.advancedExpressionFolding.expression.custom.SimpleExpression
import com.intellij.advancedExpressionFolding.extension.Keys.METHOD_TO_PARENT_CLASS_KEY
import com.intellij.psi.PsiCodeBlock
import com.intellij.psi.PsiMethod

object SummaryParentExt : BaseExtension() {

    fun summaryParent(
        element: PsiMethod,
        list: MutableList<Expression?>
    ) {
        val overrides = element.annotations.filter {
            it.textMatches("@Override")
        }
        val hideOverride = overrides.exprHide()
        list += hideOverride
        list += overrides.mapNotNull {
            it.prevWhiteSpace().exprHide()
        }
        list += summaryParentOverride.on(hideOverride)?.let {
            element.body
        }?.let { body ->
            createOverridesComment(element, body)
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
