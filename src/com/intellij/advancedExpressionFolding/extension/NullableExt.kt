package com.intellij.advancedExpressionFolding.extension

import com.intellij.advancedExpressionFolding.expression.Expression
import com.intellij.advancedExpressionFolding.expression.custom.CheckNotNullExpression
import com.intellij.advancedExpressionFolding.expression.custom.FieldAnnotationExpression
import com.intellij.advancedExpressionFolding.expression.custom.NullAnnotationExpression
import com.intellij.advancedExpressionFolding.extension.NullableExt.FieldFoldingAnnotation.Companion.findByName
import com.intellij.advancedExpressionFolding.extension.NullableExt.FieldFoldingAnnotation.NOT_NULL
import com.intellij.advancedExpressionFolding.extension.NullableExt.FieldFoldingAnnotation.NULLABLE
import com.intellij.advancedExpressionFolding.extension.clazz.FieldLevelAnnotation
import com.intellij.advancedExpressionFolding.extension.clazz.LombokFieldExt.callback
import com.intellij.advancedExpressionFolding.extension.clazz.LombokFieldExt.createFieldLevelAnnotation
import com.intellij.advancedExpressionFolding.extension.methodcall.dynamic.DynamicExt
import com.intellij.openapi.editor.Document
import com.intellij.openapi.editor.FoldingGroup
import com.intellij.psi.*

/**
 * [data.NullableAnnotationTestData]
 * [data.NullableAnnotationCheckNotNullTestData]
 */
object NullableExt : BaseExtension() {

    @JvmStatic
    fun createExpression(method: PsiMethod, document: Document): Expression? {
        if (interfaceExtensionProperties && method.body == null) {
            // For interfaces only; other functionality was not tested with the interfaces’ methods
            return InterfacePropertiesExt.foldProperties(method)
        }
        val list = exprList(fieldAnnotationExpression(method.annotations, method.returnTypeElement))

        if (summaryParentOverride) {
            SummaryParentExt.summaryParent(method, list)
        }

        list.addIfEnabled(expressionFunc) {
            SingleExpressionFunctionExt.createSingleExpressionFunctions(method, document)
        }

        list.addIfEnabled(dynamic) {
            DynamicExt.createExpression(method)
        }

        // works ok now, but may lead to issues
        if (true) {
            getAnyExpression(method.modifierList, document).let(list::add)
            method.returnTypeElement?.let {
                getAnyExpression(it, document).let(list::add)
            }
            getAnyExpressions(method.body?.statements, document).let(list::addAll)
            getAnyExpressions(method.parameterList.parameters, document).let(list::addAll)
        }
        return list.exprWrap(method)
    }

    @JvmStatic
    fun createExpression(psiRecordComponent: PsiRecordComponent): NullAnnotationExpression? {
        return fieldAnnotationExpression(psiRecordComponent.annotations, psiRecordComponent.typeElement, true)
    }

    @JvmStatic
    fun createExpression(parameter: PsiParameter, document: Document): Expression? {
        if (interfaceExtensionProperties && InterfacePropertiesExt.ignoreFoldingParameter(parameter)) {
            return null
        }

        val list = exprList(readCheckNotNullMethods(parameter, document))
        list += fieldAnnotationExpression(
            parameter.annotations,
            parameter.typeElement,
            true
        )
        return list.exprWrap(parameter)
    }

    @JvmStatic
    fun createExpression(field: PsiField, document: Document): Expression? {
        val typeElement = field.typeElement.takeIf {
            !field.isIgnored()
        } ?: return null

        val list = exprList()

        field.callback?.invoke()?.let { annotations: List<FieldLevelAnnotation> ->

            val annotationsAsString = mutableListOf<String>()
            val elementsToHide = mutableListOf<PsiElement?>()
            annotations.forEach { fieldLevelAnnotation ->
                elementsToHide += fieldLevelAnnotation.method
                elementsToHide += fieldLevelAnnotation.method.mapNotNull {
                    it.prevWhiteSpace()
                }
                annotationsAsString += fieldLevelAnnotation.createFieldLevelAnnotation()
            }
            val group = annotations.firstOrNull() {
                it.group != null
            }?.group
            annotationsAsString.takeIfSizeNot(0)?.let {
                list += FieldAnnotationExpression(field, it, elementsToHide, group = group)
            }
        }
        list.addIfEnabled(nullable) {
            val typeExpression = fieldAnnotationExpression(field.annotations, typeElement, false)
            typeExpression ?: findPropertyAnnotation(field, typeElement)
        }

        list.addIfEnabled(constructorReferenceNotation) {
            ConstructorReferenceExt.foldFieldConstructor(field, document)
        }

        list.addIfEnabled(const) {
            ConstExt.fieldConstExpression(field, typeElement)
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
            val fieldFoldingAnnotation = findByName(it.qualifiedName) ?: return@mapNotNull null
            Pair(fieldFoldingAnnotation, it)
        }.firstOrNull()
            ?.let { (foldingAnnotation, annotationElement) ->
                val typeSuffix = when (foldingAnnotation) {
                    NOT_NULL -> "!!"
                    NULLABLE -> "?"
                }
                NullAnnotationExpression(typeElement, annotationElement, typeSuffix, foldPrevWhiteSpace, group)
            }
    }

}
