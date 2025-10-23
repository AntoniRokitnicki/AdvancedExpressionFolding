package com.intellij.advancedExpressionFolding.processor.util

import com.intellij.advancedExpressionFolding.processor.argumentExpressions
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiExpressionStatement
import com.intellij.psi.PsiMethodCallExpression
import com.intellij.psi.PsiMethodReferenceExpression

object MethodNameUtil {

    fun startsWith(string: String?, prefix: String): Boolean = string?.startsWith(prefix) == true

    fun isSetter(text: String): Boolean {
        return text.startsWith("set") && text.length > 3 && text[3].isUpperCase()
    }

    fun isPureMethodReference(element: PsiMethodCallExpression): Boolean {
        return PsiTreeUtilEx.findChildByTypeHierarchy(
            element,
            PsiMethodReferenceExpression::class.java,
            PsiExpressionStatement::class.java,
            PsiMethodReferenceExpression::class.java
        ) != null
    }

    fun hasOptionalChainOperations(element: PsiMethodCallExpression): Boolean {
        return PsiTreeUtilEx.findAncestorsUntilClass(element, PsiExpressionStatement::class.java).firstOrNull() != null
    }

    fun isGetter(element: PsiElement, expression: PsiMethodCallExpression): Boolean {
        return expression.argumentExpressions.isEmpty() && isGetter(element.text)
    }

    fun isGetter(name: String): Boolean = isGetterAux(name, "get") || isGetterAux(name, "is")

    private fun isGetterAux(name: String?, prefix: String): Boolean {
        return startsWith(name, prefix) && name!!.length > prefix.length && name[prefix.length].isUpperCase()
    }
}
