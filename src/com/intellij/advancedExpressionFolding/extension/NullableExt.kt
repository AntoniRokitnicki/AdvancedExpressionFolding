package com.intellij.advancedExpressionFolding.extension

import com.intellij.advancedExpressionFolding.expression.Expression
import com.intellij.advancedExpressionFolding.expression.custom.FieldAnnotationExpression
import com.intellij.advancedExpressionFolding.extension.NullableExt.FieldFoldingAnnotation.Companion.findByName
import com.intellij.advancedExpressionFolding.extension.NullableExt.FieldFoldingAnnotation.NOT_NULL
import com.intellij.advancedExpressionFolding.extension.NullableExt.FieldFoldingAnnotation.NULLABLE
import com.intellij.psi.*

object NullableExt : BaseExtension() {

    enum class FieldFoldingAnnotation(vararg var annotations: String) {
        NOT_NULL("NotNull", "NonNull"),
        NULLABLE("Nullable"),
        ;
        init {
            this.annotations = annotations.map {
                it.lowercase()
            }.toTypedArray()
        }


        companion object {
            fun findByName(annotationName: String?): FieldFoldingAnnotation? {
                val name = annotationName?.let {
                    it.lowercase()
                }?.let { name ->
                    if (name.contains(".")) {
                        name.substringAfterLast(".")
                    } else {
                        name
                    }
                } ?: return null
                return values().firstOrNull { e ->
                    e.annotations.firstOrNull { single ->
                        single.contains(name)
                    } != null
                }
            }
        }
    }

    @JvmStatic
    fun createExpression(field: PsiField): Expression? {
        return fieldAnnotationExpression(field.annotations, field.typeElement)
    }

    @JvmStatic
    fun createExpression(psiParameter: PsiParameter): Expression? {
        return fieldAnnotationExpression(psiParameter.annotations, psiParameter.typeElement)
    }

    @JvmStatic
    fun createExpression(psiMethod: PsiMethod): Expression? {
        return fieldAnnotationExpression(psiMethod.annotations, psiMethod.returnTypeElement)
    }

    @JvmStatic
    fun createExpression(psiRecordComponent: PsiRecordComponent): FieldAnnotationExpression? {
        return fieldAnnotationExpression(psiRecordComponent.annotations, psiRecordComponent.typeElement)
    }

    private fun fieldAnnotationExpression(
        annotations: Array<out PsiAnnotation>,
        typeElement: PsiTypeElement?
    ): FieldAnnotationExpression? {
        typeElement?.takeIf {
            lombok
        } ?: return null

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