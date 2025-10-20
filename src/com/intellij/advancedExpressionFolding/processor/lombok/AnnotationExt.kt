package com.intellij.advancedExpressionFolding.processor.lombok

import com.intellij.advancedExpressionFolding.expression.semantic.lombok.ClassAnnotationExpression
import com.intellij.advancedExpressionFolding.expression.semantic.lombok.CustomClassAnnotation
import com.intellij.advancedExpressionFolding.processor.*
import com.intellij.advancedExpressionFolding.processor.lombok.LombokExt.addLombokSupport
import com.intellij.advancedExpressionFolding.processor.lombok.LombokExt.lombokPatternOff
import com.intellij.advancedExpressionFolding.processor.lombok.LombokFoldingAnnotation.SERIAL
import com.intellij.advancedExpressionFolding.processor.util.Consts.SUPERSCRIPT_MAPPING
import com.intellij.psi.PsiClass
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiField
import com.intellij.psi.PsiJavaFile

object AnnotationExt {

    data class ClassLevelAnnotation(
        val classAnnotation: LombokFoldingAnnotation,
        val elementsToHide: List<PsiElement>,
        val pure: Boolean = true,
        val arguments: List<String> = emptyList(),
    )

    fun addClassLevelAnnotations(clazz: PsiClass): ClassAnnotationExpression? {
        (clazz.isIgnored() || clazz.isRecord).off() ?: return null

        lombokPatternOff?.run {
            val regex = patternAsRegex()
            clazz.qualifiedName?.let {
                if (regex?.containsMatchIn(it) == true) return null
            }
        }

        val serialField = hasSerialField(clazz)
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
        val classLevelAnnotations = ClassAnnotationExpression(clazz, changes.map { hidingAnnotation ->
            val notPureSuffix = createNotPureSuffix(hidingAnnotation)
            val args = argsAsString(hidingAnnotation)
            CustomClassAnnotation(hidingAnnotation.classAnnotation.annotation + notPureSuffix + args)
        }, elementsToFold)
        return classLevelAnnotations
    }

    private var PATTERN_CACHE : Regex? = null
    private fun patternAsRegex(): Regex? {
        if (PATTERN_CACHE?.toString() != lombokPatternOff) {
            PATTERN_CACHE = lombokPatternOff?.toRegex()
        }
        return PATTERN_CACHE
    }

    private fun argsAsString(hidingAnnotation: ClassLevelAnnotation) =
        hidingAnnotation.arguments.takeIf {
            it.isNotEmpty()
        }?.joinToString(separator = ",")
            ?.let {
                "($it)"
            } ?: ""

    private fun createNotPureSuffix(hidingAnnotation: ClassLevelAnnotation) =
        hidingAnnotation.pure.takeIf {
            !it
        }?.let {
            SUPERSCRIPT_MAPPING['*']
        } ?: ""

    private fun addSerialVersionUID(
        serialField: PsiField?
    ): List<ClassLevelAnnotation> {
        return serialField?.let {
            it.markIgnored()
            listOf(ClassLevelAnnotation(SERIAL, listOf(it)))
        } ?: emptyList()
    }

    private fun hasSerialField(clazz: PsiClass): PsiField? = clazz.fields.firstOrNull {
        it.name == "serialVersionUID"
    }

    private fun hasLombokImports(clazz: PsiClass) =
        clazz.containingFile.asInstance<PsiJavaFile>()?.importList?.importStatements?.any {
            it.qualifiedName?.startsWith("lombok") ?: false
        } ?: false

}
