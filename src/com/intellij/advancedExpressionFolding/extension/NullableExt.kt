package com.intellij.advancedExpressionFolding.extension

import com.intellij.advancedExpressionFolding.expression.Expression
import com.intellij.advancedExpressionFolding.expression.custom.*
import com.intellij.advancedExpressionFolding.extension.LombokExt.callback
import com.intellij.advancedExpressionFolding.extension.NullableExt.FieldFoldingAnnotation.Companion.findByName
import com.intellij.advancedExpressionFolding.extension.NullableExt.FieldFoldingAnnotation.NOT_NULL
import com.intellij.advancedExpressionFolding.extension.NullableExt.FieldFoldingAnnotation.NULLABLE
import com.intellij.openapi.editor.Document
import com.intellij.psi.*
import com.intellij.psi.impl.source.PsiClassReferenceType

/**
 * [data.NullableAnnotationTestData]
 * [data.NullableAnnotationCheckNotNullTestData]
 *
 *
 */
object NullableExt : BaseExtension() {

    @JvmStatic
    fun createExpression(psiMethod: PsiMethod): Expression? {
        val nullAnnotationExpression = fieldAnnotationExpression(psiMethod.annotations, psiMethod.returnTypeElement)
        if (expressionFunc) {
            return ExperimentalExt.createSingleExpressionFunctions(psiMethod, nullAnnotationExpression)
        }
        return nullAnnotationExpression
    }

    @JvmStatic
    fun createExpression(psiRecordComponent: PsiRecordComponent): NullAnnotationExpression? {
        return fieldAnnotationExpression(psiRecordComponent.annotations, psiRecordComponent.typeElement, true)
    }

    @JvmStatic
    fun createExpression(psiParameter: PsiParameter, document: Document): Expression? {
        return readCheckNotNullMethods(psiParameter, document) ?: fieldAnnotationExpression(
            psiParameter.annotations,
            psiParameter.typeElement,
            true
        )
    }

    @JvmStatic
    fun createExpression(field: PsiField, document: Document): Expression? {
        val typeElement = field.typeElement
        if (typeElement == null || field.isIgnored()) {
            return null
        }

        val list = exprList()
        val newAnnotations = mutableListOf<FieldAnnotationExpression>()

        field.callback?.let {
            val (getter, getterAnnotation) = it()
            getterAnnotation?.let {
                getter.markIgnored()
                newAnnotations += FieldAnnotationExpression(field, listOf(getterAnnotation), listOf(getter, getter.prevWhiteSpace()))
            }
        }

        list += nullable.on()?.let {
            val typeExpression = fieldAnnotationExpression(field.annotations, typeElement, false)
            typeExpression ?: findPropertyAnnotation(field, typeElement)
        }

        list += const.on()?.let {
            fieldConstExpression(field, typeElement, document)
        }

        list += newAnnotations
        return list.exprWrap(field)
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

    private fun fieldConstExpression(
        field: PsiField,
        typeElement: PsiTypeElement?,
        document: Document
    ): Expression? {
        return if (!field.isNotStatic() && !field.isNotFinal()) {
            if (foldConstType(field)) {
                FieldConstExpression(typeElement, field.modifierList!!, field.constText())
            } else {
                foldConstConstructor(field, document)
            }
        } else {
            foldFieldConstructor(field, document)
        }
    }

    private fun PsiField.constText(): String {
        @Suppress("SpellCheckingInspection")
        return when (this.enum) {
            true -> "econst"
            false -> "const"
        }
    }

    private fun foldConstConstructor(field: PsiField, document: Document): Expression {
        val constFolding = FieldConstExpression(null, field.modifierList!!, field.constText())
        experimental.on() ?: return constFolding

        return foldFieldConstructor(field, document, constFolding) ?: constFolding
    }

    private fun foldFieldConstructor(
        field: PsiField,
        document: Document,
        constFolding: FieldConstExpression? = null
    ): WrapperExpression? {
        val initializer = field.initializer.asInstance<PsiNewExpression>()
        val noParams = initializer?.argumentList?.isEmpty == true
        val anonymousClass = initializer?.anonymousClass

        var noBody = initializer?.classReference != null
        if (anonymousClass != null) {
            noBody = anonymousClass.methods.size + anonymousClass.fields.size == 0
        }

        val sameType = sameTypeOfFieldAndInitializer(initializer, field)
        if (noBody && sameType && initializer != null) {
            val list = exprList()
            initializer.classReference?.let {
                list += it.prevWhiteSpace()?.exprHide()
                list += it.exprHide()
            }
            initializer.anonymousClass?.let {
                list += it.prevWhiteSpace()?.exprHide()
                list += it.expr("{}")
            }
            list += initializer.exprWrapAround(textBefore = "::")

            if (noParams) {
                list += initializer.argumentList?.exprHide()
            } else {
                list += initializer.argumentList!!.expressions.map {
                    getAnyExpression(it, document)
                }
            }

            list += constFolding
            return list.exprWrap(field)
        }
        return null
    }


    //TODO: extract generic extension method
    private fun sameTypeOfFieldAndInitializer(initializer: PsiNewExpression?, field: PsiField) =
        initializer?.classOrAnonymousClassReference?.resolve() == field.type.asInstance<PsiClassReferenceType>()
            ?.resolve()

    private fun foldConstType(field: PsiField) =
        (field.type.isString() || field.type.isPrimitive()) && field.initializer is PsiLiteralExpression

    private fun findPropertyAnnotation(field: PsiField, typeElement: PsiTypeElement?): Expression? {
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

    private fun readCheckNotNullMethods(psiParameter: PsiParameter, document: Document): Expression? {
        val typeToAppend = psiParameter.typeElement ?: return null

        return psiParameter.parent.parent.asInstance<PsiMethod>()?.let {
            it.body?.statements
        }?.let { statements ->
            val methodCallFound = statements.map {
                it.firstChild.asInstance<PsiMethodCallExpression>()
            }.takeWhile {
                it != null
            }.map {
                getAnyExpression(it!!, document).asInstance<CheckNotNullExpression>()
            }.takeWhile {
                it != null && it.isMethodParameterWrappable
            }.firstOrNull {
                psiParameter.findLocalReference(it!!.element) != null
            }
            methodCallFound?.let { checkNotNullExpression ->
                checkNotNullExpression.ignored = true
                val methodCall = checkNotNullExpression.element
                val typeSuffix = NullAnnotationExpression(typeToAppend, null, "!!!")
                val child = methodCall.parent.prevWhiteSpace()?.let {
                    CheckNotNullExpression(it, it.textRange, "", typeSuffix)
                } ?: typeSuffix
                CheckNotNullExpression(methodCall.parent, methodCall.parent.textRange, "", child)
            }
        }
    }

    private fun fieldAnnotationExpression(
        annotations: Array<out PsiAnnotation>,
        typeElement: PsiTypeElement?,
        foldPrevWhiteSpace: Boolean = false,
    ): NullAnnotationExpression? {
        typeElement?.takeIf {
            nullable
        } ?: return null

        return annotations.mapNotNull {
            if (it.isIgnored()) {
                return null
            }
            val fieldFoldingAnnotation = findByName(it.qualifiedName) ?: return@mapNotNull null
            Pair(fieldFoldingAnnotation, it)
        }.firstOrNull()
            ?.let { (foldingAnnotation, annotationElement) ->
                val typeSuffix = when (foldingAnnotation) {
                    NOT_NULL -> "!!"
                    NULLABLE -> "?"
                }
                NullAnnotationExpression(typeElement, annotationElement, typeSuffix, foldPrevWhiteSpace)
            }
    }

}

