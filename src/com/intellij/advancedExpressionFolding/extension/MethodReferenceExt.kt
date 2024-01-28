package com.intellij.advancedExpressionFolding.extension

import com.intellij.advancedExpressionFolding.Expression
import com.intellij.advancedExpressionFolding.OptionalMapSafeCallParam
import com.intellij.advancedExpressionFolding.StreamMapCallParam
import com.intellij.psi.PsiExpressionList
import com.intellij.psi.PsiMethod
import com.intellij.psi.PsiMethodCallExpression
import com.intellij.psi.PsiMethodReferenceExpression

object MethodReferenceExt : ExpressionExt {
    @JvmStatic
    fun createExpression(element: PsiMethodReferenceExpression): Expression? {
        val optional = isOptionalSetting()
        val spread = isStreamSpreadSetting()

        if (!(optional || spread)) {
            return null
        }

        val reference = element.reference ?: return null
        val psiMethod = reference.resolve() ?: return null

        if (psiMethod !is PsiMethod || !psiMethod.parameterList.isEmpty) {
            return null
        }

        val parentMethodCall = element.findParents(
            PsiMethodCallExpression::class.java,
            PsiExpressionList::class.java,
            PsiMethodCallExpression::class.java
        )?.methodExpression?.reference?.resolve() as? PsiMethod

        if (parentMethodCall?.name?.filter { it == "map" || it == "flatMap" } != null) {
            val className = parentMethodCall.containingClass?.qualifiedName
            return when (className) {
                "java.util.Optional" -> OptionalMapSafeCallParam(element, element.textRange, psiMethod.guessPropertyName())
                "java.util.stream.Stream" -> StreamMapCallParam(element, element.textRange, psiMethod.guessPropertyName())
                else -> null
            }
        }
        return null
    }

}

