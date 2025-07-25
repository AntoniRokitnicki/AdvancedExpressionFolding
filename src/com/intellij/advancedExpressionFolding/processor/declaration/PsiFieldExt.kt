package com.intellij.advancedExpressionFolding.processor.declaration

import com.intellij.advancedExpressionFolding.expression.Expression
import com.intellij.advancedExpressionFolding.expression.semantic.lombok.FieldAnnotationExpression
import com.intellij.advancedExpressionFolding.expression.semantic.lombok.NullAnnotationExpression
import com.intellij.advancedExpressionFolding.processor.*
import com.intellij.advancedExpressionFolding.processor.NullableExt.fieldAnnotationExpression
import com.intellij.advancedExpressionFolding.processor.NullableExt.findPropertyAnnotation
import com.intellij.advancedExpressionFolding.processor.language.kotlin.ConstExt
import com.intellij.advancedExpressionFolding.processor.lombok.FieldLevelAnnotation
import com.intellij.advancedExpressionFolding.processor.lombok.LombokFieldExt.callback
import com.intellij.advancedExpressionFolding.processor.lombok.LombokFieldExt.createFieldLevelAnnotation
import com.intellij.openapi.editor.Document
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiField
import com.intellij.psi.PsiRecordComponent

object PsiFieldExt : BaseExtension() {

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

    fun createExpression(psiRecordComponent: PsiRecordComponent): NullAnnotationExpression? {
        return fieldAnnotationExpression(psiRecordComponent.annotations, psiRecordComponent.typeElement, true)
    }

}
