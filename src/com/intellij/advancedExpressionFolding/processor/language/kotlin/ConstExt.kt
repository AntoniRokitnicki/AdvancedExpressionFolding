package com.intellij.advancedExpressionFolding.processor.language.kotlin

import com.intellij.advancedExpressionFolding.expression.Expression
import com.intellij.advancedExpressionFolding.expression.semantic.kotlin.FieldConstExpression
import com.intellij.advancedExpressionFolding.processor.*
import com.intellij.advancedExpressionFolding.processor.core.BaseExtension
import com.intellij.psi.PsiField
import com.intellij.psi.PsiMethodCallExpression
import com.intellij.psi.PsiReferenceExpression
import com.intellij.psi.PsiTypeElement

object ConstExt : BaseExtension() {

    fun fieldConstExpression(
        field: PsiField,
        typeElement: PsiTypeElement?
    ): Expression? {
        return if (field.isStatic() && field.isFinal()) {
            if (field.hideConstType()) {
                field.createConst(typeElement)
            } else {
                field.createConst(null)
            }
        } else {
            null
        }
    }

    private fun PsiField.constText(): String {
        @Suppress("SpellCheckingInspection")
        return when (this.enum) {
            true -> "econst"
            false -> "const"
        }
    }

    private fun PsiField.createConst(
        typeElement: PsiTypeElement?,
    ): FieldConstExpression? {
        val modifiers = modifierList ?: return null
        return FieldConstExpression(typeElement, modifiers, constText())
    }

    private fun PsiField.hideConstType() =
        (type.isPrimitiveOrString() && hasLiteralConstInitializer()) || (enum && isNotStaticEnumInitializer()) || isFactoryMethod()

    /**
     * example: static final Pattern PATTERN = Pattern.compile(".*");
     */
    private fun PsiField.isFactoryMethod(): Boolean {
        val methodCall = initializer.asInstance<PsiMethodCallExpression>()
        val typeResolved = typeResolved
        return methodCall?.type?.typeResolved == typeResolved &&
                methodCall?.methodExpression
                    .asInstance<PsiReferenceExpression>()
                    ?.qualifierExpression
                    .asInstance<PsiReferenceExpression>()
                    ?.resolve() == typeResolved
    }

    private fun PsiField.isNotStaticEnumInitializer() = initializerType == typeResolved

}
