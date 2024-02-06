package com.intellij.advancedExpressionFolding.extension

import com.intellij.advancedExpressionFolding.expression.Expression
import com.intellij.advancedExpressionFolding.expression.optional.OptionalMapSafeCallParam
import com.intellij.advancedExpressionFolding.expression.stream.StreamMapCallParam
import com.intellij.psi.PsiExpressionList
import com.intellij.psi.PsiMethod
import com.intellij.psi.PsiMethodCallExpression
import com.intellij.psi.PsiMethodReferenceExpression

object MethodReferenceExt : IExtension {
    @JvmStatic
    fun createExpression(element: PsiMethodReferenceExpression): Expression? {
        val optional = isOptional()
        val spread = isStreamSpread()

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

