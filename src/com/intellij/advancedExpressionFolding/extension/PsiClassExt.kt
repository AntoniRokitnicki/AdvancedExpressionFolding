package com.intellij.advancedExpressionFolding.extension

import com.intellij.advancedExpressionFolding.expression.Expression
import com.intellij.advancedExpressionFolding.expression.custom.ClassAnnotationExpression
import com.intellij.advancedExpressionFolding.extension.Consts.SUPERSCRIPT_MAPPING
import com.intellij.advancedExpressionFolding.extension.lombok.LombokExt.addLombokSupport
import com.intellij.advancedExpressionFolding.extension.lombok.LombokFoldingAnnotation
import com.intellij.advancedExpressionFolding.extension.lombok.LombokFoldingAnnotation.SERIAL
import com.intellij.advancedExpressionFolding.extension.lombok.SummaryParentOverrideExt.addParentSummary
import com.intellij.psi.PsiClass
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiField
import com.intellij.psi.PsiJavaFile

object PsiClassExt : BaseExtension() {

    enum class ClassType {
        BUILDER,
    }

    data class ClassLevelAnnotation(
        val classAnnotation: LombokFoldingAnnotation,
        val elementsToHide: List<PsiElement>,
        val pure: Boolean = true,
        val arguments: List<String> = emptyList(),
    )

    @JvmStatic
    fun createExpression(clazz: PsiClass): Expression? {
        clazz.addParentSummary()?.run {
            //TODO: work together with others
            return this
        }

        (clazz.isIgnored() || !lombok || clazz.isRecord).off() ?: return null

        val serialField = isSerial(clazz)
        if (hasLombokImports(clazz) && serialField == null) {
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
    ): List<ClassLevelAnnotation> {
        return serialField?.let {
            it.markIgnored()
            listOf(ClassLevelAnnotation(SERIAL, listOf(it)))
        } ?: emptyList()
    }


    private fun isSerial(clazz: PsiClass): PsiField? = clazz.fields.firstOrNull {
        it.name == "serialVersionUID"
    }

    private fun hasLombokImports(clazz: PsiClass) =
        clazz.containingFile.asInstance<PsiJavaFile>()?.importList?.importStatements?.any {
            it.qualifiedName?.startsWith("lombok") ?: false
        } ?: false


}






