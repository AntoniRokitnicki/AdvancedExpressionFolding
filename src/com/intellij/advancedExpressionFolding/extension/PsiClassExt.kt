package com.intellij.advancedExpressionFolding.extension

import com.intellij.advancedExpressionFolding.expression.Expression
import com.intellij.advancedExpressionFolding.expression.custom.ClassAnnotationExpression
import com.intellij.advancedExpressionFolding.extension.Consts.SUPERSCRIPT_MAPPING
import com.intellij.advancedExpressionFolding.extension.lombok.LombokExt
import com.intellij.advancedExpressionFolding.extension.lombok.LombokExt.addLombokSupport
import com.intellij.advancedExpressionFolding.extension.lombok.LombokFoldingAnnotation
import com.intellij.advancedExpressionFolding.extension.lombok.LombokFoldingAnnotation.SERIAL
import com.intellij.psi.PsiClass
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiField

object PsiClassExt : BaseExtension() {

    enum class ClassType {
        BUILDER,
    }

    data class HidingAnnotation(
        val classAnnotation: LombokFoldingAnnotation,
        val elementsToHide: List<PsiElement>,
        val pure: Boolean = true,
        val arguments: List<String> = emptyList(),
    )

    @JvmStatic
    fun createExpression(clazz: PsiClass): Expression? {
        (clazz.isIgnored() || !lombok || clazz.isRecord || clazz.isInterface).off() ?: return null

        val serialField = isSerial(clazz)
        if (LombokExt.hasLombokImports(clazz) && serialField == null) {
            clazz.markIgnored()
            return null
        }

        val changes = clazz.addLombokSupport() + addSerialVersionUID(serialField)
        if (changes.isEmpty()) {
            clazz.markIgnored()
            return null
        }

        val elementsToHide = changes.map {
            it.elementsToHide
        }.flatten()
        val elementsToFold = elementsToHide.mapNotNull { method ->
            method.prevWhiteSpace()
        } + elementsToHide
        return ClassAnnotationExpression(clazz, changes.map { hidingAnnotation ->
            val notPureSuffix = hidingAnnotation.pure.takeIf { it ->
                !it
            }?.let {
                SUPERSCRIPT_MAPPING['*']
            } ?: ""
            val args = hidingAnnotation.arguments.takeIf {
                it.isNotEmpty()
            }?.joinToString(separator = ",")
                ?.let {
                    "($it)"
                } ?: ""

            hidingAnnotation.classAnnotation.annotation + notPureSuffix + args
        }, elementsToFold)
    }

    private fun addSerialVersionUID(
        serialField: PsiField?
    ): List<HidingAnnotation> {
        return serialField?.let {
            it.markIgnored()
            listOf(HidingAnnotation(SERIAL, listOf(it)))
        } ?: emptyList()
    }


    private fun isSerial(clazz: PsiClass): PsiField? = clazz.fields.firstOrNull {
        it.name == "serialVersionUID"
    }

}






