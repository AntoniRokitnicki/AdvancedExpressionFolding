package com.intellij.advancedExpressionFolding.extension

import com.intellij.advancedExpressionFolding.Expression
import com.intellij.advancedExpressionFolding.PropertyUtil
import com.intellij.psi.*

typealias CustomClassAnnotation = String

object PsiClassExt {
    @JvmStatic
    fun createExpression(clazz: PsiClass): Expression? {
        if (clazz.isIgnored()) {
            return null
        }

        val serialField = isSerial(clazz)
        if (hasLombokImports(clazz) && serialField == null) {
            clazz.markIgnored()
            return null
        }

        val customClassAnnotations = mutableListOf<CustomClassAnnotation>()
        val elementsToFold = mutableListOf<PsiElement>()

        addLombokSupport(clazz, customClassAnnotations, elementsToFold)
        addSerialVersionUID(serialField, customClassAnnotations, elementsToFold)

        if (customClassAnnotations.isNotEmpty()) {
            return CustomClassAnnotationExpression(clazz, customClassAnnotations, elementsToFold)
        }
        clazz.markIgnored()
        return null
    }

    private fun addSerialVersionUID(
        serialField: PsiField?,
        customClassAnnotations: MutableList<CustomClassAnnotation>,
        elementsToFold: MutableList<PsiElement>
    ) {
        serialField?.let { field ->
            customClassAnnotations += "@Serial"
            elementsToFold += field
            field.prevWhiteSpace()?.let {
                elementsToFold += it
            }
        }
    }

    private fun addLombokSupport(
        clazz: PsiClass,
        customClassAnnotations: MutableList<CustomClassAnnotation>,
        elementsToFold: MutableList<PsiElement>
    ) {
        val fieldsMap = clazz.fields.filter {
            !it.hasModifierProperty(PsiModifier.STATIC)
        }.associateBy {
            it.name
        }
        if (fieldsMap.isNotEmpty()) {
            val propertyList = clazz.methods.filter {
                it.isGetterOrSetter()
            }.groupBy {
                PropertyUtil.guessPropertyName(it.name)
            }.mapNotNull { (name, methods) ->
                fieldsMap[name]?.let {
                    JavaProperty(it, methods)
                }
            }

            if (fieldsMap.size == propertyList.filter { it.hasGetterOrSetter() }.size) {
                customClassAnnotations += "@Getter"
                customClassAnnotations += "@Setter"
                val methods = propertyList.flatMap { it.methods }
                elementsToFold += methods
                elementsToFold += methods.mapNotNull { it.prevWhiteSpace() }
            }
        }
    }

    private fun isSerial(clazz: PsiClass): PsiField? = clazz.fields.firstOrNull { it.name == "serialVersionUID" }

    private fun hasLombokImports(clazz: PsiClass) =
        (clazz.containingFile as? PsiJavaFile)?.importList?.importStatements?.any {
            it.qualifiedName?.contains("lombok") ?: false
        } ?: false

}

private fun PsiElement.prevWhiteSpace(): PsiWhiteSpace? = prevSibling as? PsiWhiteSpace


