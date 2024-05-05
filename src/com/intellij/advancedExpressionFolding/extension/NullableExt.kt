package com.intellij.advancedExpressionFolding.extension

import com.intellij.advancedExpressionFolding.expression.Expression
import com.intellij.advancedExpressionFolding.expression.custom.FieldAnnotationExpression
import com.intellij.advancedExpressionFolding.extension.NullableExt.FieldFoldingAnnotation.Companion.findByName
import com.intellij.advancedExpressionFolding.extension.NullableExt.FieldFoldingAnnotation.NOT_NULL
import com.intellij.advancedExpressionFolding.extension.NullableExt.FieldFoldingAnnotation.NULLABLE
import com.intellij.psi.*

object NullableExt : BaseExtension() {

    enum class FieldFoldingAnnotation(val annotation: String) {
        NOT_NULL("NotNull"),
        NULLABLE("Nullable"),

        ;

        companion object {
            fun findByName(annotationName: String?): FieldFoldingAnnotation? {
                annotationName ?: return null
                return values().firstOrNull {
                    it.annotation.contains(annotationName)
                }
            }
        }

    }

    @JvmStatic
    fun createExpression(field: PsiField): Expression? {
        if (!field.isNotStatic()) {
            return null
        }
        val typeElement = field.typeElement ?: return null
        val annotations = field.annotations
        return fieldAnnotationExpression(annotations, typeElement)
    }

    @JvmStatic
    fun createExpression(psiParameter: PsiParameter): Expression? {
        val typeElement = psiParameter.typeElement ?: return null
        val annotations = psiParameter.annotations
        return fieldAnnotationExpression(annotations, typeElement)
    }

    @JvmStatic
    fun createExpression(psiMethod: PsiMethod): Expression? {
        val typeElement = psiMethod.returnTypeElement ?: return null
        val annotations = psiMethod.annotations
        return fieldAnnotationExpression(annotations, typeElement)
    }

    private fun fieldAnnotationExpression(
        annotations: Array<out PsiAnnotation>,
        typeElement: PsiTypeElement
    ): FieldAnnotationExpression? {
        return annotations.mapNotNull {
            val fieldFoldingAnnotation = findByName(it.qualifiedName) ?: return@mapNotNull null
            Pair(fieldFoldingAnnotation, it)
        }.firstOrNull()
            ?.let { (foldingAnnotation, annotationElement) ->
                val typeSuffix = when (foldingAnnotation) {
                    NOT_NULL -> "!!"
                    NULLABLE -> "?"
                }
                FieldAnnotationExpression(typeElement, annotationElement, typeSuffix)
            }
    }
}