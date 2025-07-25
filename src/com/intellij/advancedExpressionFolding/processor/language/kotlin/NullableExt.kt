package com.intellij.advancedExpressionFolding.processor.language.kotlin

import com.intellij.advancedExpressionFolding.expression.Expression
import com.intellij.advancedExpressionFolding.expression.semantic.kotlin.CheckNotNullExpression
import com.intellij.advancedExpressionFolding.expression.semantic.lombok.NullAnnotationExpression
import com.intellij.advancedExpressionFolding.processor.*
import com.intellij.openapi.editor.Document
import com.intellij.openapi.editor.FoldingGroup
import com.intellij.psi.*

/**
 * [data.NullableAnnotationTestData]
 * [data.NullableAnnotationCheckNotNullTestData]
 */
object NullableExt : BaseExtension() {

    fun findPropertyAnnotation(field: PsiField, typeElement: PsiTypeElement?): Expression? {
        return field.metadata.getter
            ?.takeIf {
                it.annotations.firstOrNull() != null
            }?.let { method ->
                fieldAnnotationExpression(method.annotations, method.returnTypeElement!!)?.let {
                    method.markIgnored()
                    NullAnnotationExpression(typeElement!!, null, it.typeSuffix)
                }
            }
            ?: field.metadata.setter?.let { method ->
                val second =
                    method.parameterList.parameters.firstOrNull()?.annotations.asInstance<Array<out PsiAnnotation>>()
                second?.firstOrNull()?.let {
                    Pair(method, second)
                }
            }?.let { (method, ann) ->
                fieldAnnotationExpression(ann, method.returnTypeElement!!)?.let {
                    method.markIgnored()
                    NullAnnotationExpression(typeElement!!, null, it.typeSuffix)
                }
            }
    }

    fun readCheckNotNullMethods(element: PsiParameter, document: Document): Expression? {
        val typeToAppend = element.typeElement ?: return null

        return element.parent.parent.asInstance<PsiMethod>()?.let {
            it.body?.statements
        }?.let { statements ->
            val expressionMutableList = statements.asSequence().map {
                it.firstChild.asInstance<PsiMethodCallExpression>()
            }.takeWhile {
                it != null
            }.map {
                getAnyExpression(it!!, document)
            }.toMutableList<Expression?>()

            val methodCallFound = expressionMutableList.mapNotNull {
                it.asInstance<CheckNotNullExpression>()
            }.takeWhile {
                it.isMethodParameterWrappable
            }.firstOrNull {
                element.findLocalReference(it.element) != null
            }

            expressionMutableList += methodCallFound?.let { checkNotNullExpression ->
                checkNotNullExpression.ignored = true
                val methodCall = checkNotNullExpression.element
                val typeSuffix = NullAnnotationExpression(typeToAppend, null, "!!!")
                val child = methodCall.parent.prevWhiteSpace()?.let {
                    CheckNotNullExpression(it, it.textRange, "", typeSuffix)
                } ?: typeSuffix
                CheckNotNullExpression(methodCall.parent, methodCall.parent.textRange, "", child)
            }
            expressionMutableList.exprWrap(element)
        }
    }

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
                val name = annotationName?.run(String::lowercase)?.let { name ->
                    if (name.contains(".")) {
                        name.substringAfterLast(".")
                    } else {
                        name
                    }
                } ?: return null
                return entries.firstOrNull { e ->
                    e.annotations.firstOrNull { single ->
                        single.contains(name)
                    } != null
                }
            }
        }
    }

    fun fieldAnnotationExpression(
        annotations: Array<out PsiAnnotation>,
        typeElement: PsiTypeElement?,
        foldPrevWhiteSpace: Boolean = false,
        group: FoldingGroup? = null,
    ): NullAnnotationExpression? {
        typeElement?.takeIf {
            nullable
        } ?: return null

        return annotations.mapNotNull {
            if (it.isIgnored()) {
                return null
            }
            val fieldFoldingAnnotation = FieldFoldingAnnotation.Companion.findByName(it.qualifiedName) ?: return@mapNotNull null
            Pair(fieldFoldingAnnotation, it)
        }.firstOrNull()
            ?.let { (foldingAnnotation, annotationElement) ->
                val typeSuffix = when (foldingAnnotation) {
                    FieldFoldingAnnotation.NOT_NULL -> "!!"
                    FieldFoldingAnnotation.NULLABLE -> "?"
                }
                NullAnnotationExpression(typeElement, annotationElement, typeSuffix, foldPrevWhiteSpace, group)
            }
    }

}
