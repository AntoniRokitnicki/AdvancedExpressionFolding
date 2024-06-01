package com.intellij.advancedExpressionFolding.extension

import com.intellij.advancedExpressionFolding.expression.Expression
import com.intellij.advancedExpressionFolding.expression.custom.CheckNotNullExpression
import com.intellij.advancedExpressionFolding.expression.custom.FieldAnnotationExpression
import com.intellij.advancedExpressionFolding.expression.custom.FieldConstExpression
import com.intellij.advancedExpressionFolding.expression.custom.NullAnnotationExpression
import com.intellij.advancedExpressionFolding.extension.NullableExt.FieldFoldingAnnotation.Companion.findByName
import com.intellij.advancedExpressionFolding.extension.NullableExt.FieldFoldingAnnotation.NOT_NULL
import com.intellij.advancedExpressionFolding.extension.NullableExt.FieldFoldingAnnotation.NULLABLE
import com.intellij.advancedExpressionFolding.extension.lombok.LombokExt
import com.intellij.advancedExpressionFolding.extension.lombok.LombokExt.callback
import com.intellij.advancedExpressionFolding.extension.methodcall.dynamic.DynamicExt
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
    fun createExpression(element: PsiMethod, document: Document): Expression? {
        val list = exprList(fieldAnnotationExpression(element.annotations, element.returnTypeElement))
        if (expressionFunc) {
            list.add(ExperimentalExt.createSingleExpressionFunctions(element))
        }
        if (dynamic) {
            list.add(DynamicExt.createExpression(element))
        }
        if (list.isNotEmpty()) {
            list.addAll(element.parameterList.parameters.map {
                getNonSyntheticExpression(it, document)
            })
        }
        return list.exprWrap(element)
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
        val typeElement = field.typeElement.takeIf {
            !field.isIgnored()
        } ?: return null

        val fieldAnnotations = mutableListOf<String>()
        val elementsToHide = mutableListOf<PsiElement?>()

        field.callback?.invoke()?.let { annotations ->
            annotations.forEach { fieldLevelAnnotation ->
                elementsToHide.addAll(fieldLevelAnnotation.method)
                elementsToHide.addAll(fieldLevelAnnotation.method.mapNotNull {
                    it.prevWhiteSpace()
                })
                fieldAnnotations += LombokExt.createFieldLevelAnnotation(fieldLevelAnnotation)
            }
        }

        val list = exprList()

        list += fieldAnnotations.takeIfSizeNot(0)?.let {
            FieldAnnotationExpression(field, it, elementsToHide)
        }

        list += nullable.on()?.let {
            val typeExpression = fieldAnnotationExpression(field.annotations, typeElement, false)
            typeExpression ?: findPropertyAnnotation(field, typeElement)
        }

        list += const.on()?.let {
            fieldConstExpression(field, typeElement, document)
        }

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
                val name = annotationName?.run {
                    lowercase()
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
        return if (field.isStatic() && field.isFinal()) {
            if (field.hideConstType()) {
                field.createConst(typeElement)
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
        val constFolding = field.createConst(null)
        experimental.on() ?: return constFolding

        return foldFieldConstructor(field, document, constFolding) ?: constFolding
    }

    private fun PsiField.createConst(
        typeElement: PsiTypeElement?,
    ): FieldConstExpression {
        val modifiers = modifierList!!
        return FieldConstExpression(typeElement, modifiers, constText())
    }

    private fun foldFieldConstructor(
        field: PsiField,
        document: Document,
        constFolding: FieldConstExpression? = null
    ): Expression? {
        experimental.on() ?: return constFolding

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

    private fun PsiField.hideConstType() = (type.isPrimitiveOrString() && hasLiteralConstInitializer()) || (enum && isNotStaticEnumInitializer()) || isFactoryMethod()

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

    private fun readCheckNotNullMethods(element: PsiParameter, document: Document): Expression? {
        val typeToAppend = element.typeElement ?: return null

        return element.parent.parent.asInstance<PsiMethod>()?.let {
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
                element.findLocalReference(it!!.element) != null
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



