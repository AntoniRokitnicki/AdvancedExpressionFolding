package com.intellij.advancedExpressionFolding.extension

import com.intellij.advancedExpressionFolding.expression.Expression
import com.intellij.advancedExpressionFolding.expression.custom.ClassAnnotationExpression
import com.intellij.psi.*

typealias CustomClassAnnotation = String

object PsiClassExt : BaseExtension() {

    enum class ClassType {
        BUILDER, DESTRUCTURING
    }

    data class JavaProperty(val field: PsiField, val methods: List<PsiMethod>) {
        fun hasGetterOrSetter(): Boolean = methods.count() == 2
    }

    @JvmStatic
    fun createExpression(clazz: PsiClass): Expression? {
        if (clazz.isIgnored() || !lombok) {
            return null
        }

        val serialField = isSerial(clazz)
        if (hasLombokImports(clazz) && serialField == null) {
            clazz.markIgnored()
            return null
        }

        val changes = listOfNotNull(addLombokSupport(clazz), addSerialVersionUID(serialField))
        if (changes.isEmpty()) {
            clazz.markIgnored()
            return null
        }

        val (customClassAnnotations, elementsToFold) = changes.unzip()
        return ClassAnnotationExpression(clazz, customClassAnnotations.flatten(), elementsToFold.flatten())
    }

    private fun addSerialVersionUID(
        serialField: PsiField?
    ): Pair<MutableList<CustomClassAnnotation>, MutableList<PsiElement>>? {
        serialField?.let { field ->
            val customClassAnnotations = mutableListOf<CustomClassAnnotation>()
            val elementsToFold = mutableListOf<PsiElement>()
            customClassAnnotations += "@Serial"
            elementsToFold += field
            field.prevWhiteSpace()?.let {
                elementsToFold += it
            }
            return Pair(customClassAnnotations, elementsToFold)
        }
        return null
    }

    private fun addLombokSupport(
        clazz: PsiClass
    ): Pair<MutableList<CustomClassAnnotation>, MutableList<PsiElement>>? {
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
                val customClassAnnotations = mutableListOf<CustomClassAnnotation>()
                val elementsToFold = mutableListOf<PsiElement>()
                customClassAnnotations += "@Getter"
                customClassAnnotations += "@Setter"
                val methods = propertyList.flatMap { it.methods }
                elementsToFold += methods
                elementsToFold += methods.mapNotNull { it.prevWhiteSpace() }
                return Pair(customClassAnnotations, elementsToFold)
            }
        }
        return null
    }

    private fun isSerial(clazz: PsiClass): PsiField? = clazz.fields.firstOrNull { it.name == "serialVersionUID" }

    private fun hasLombokImports(clazz: PsiClass) =
        (clazz.containingFile as? PsiJavaFile)?.importList?.importStatements?.any {
            it.qualifiedName?.contains("lombok") ?: false
        } ?: false


    private fun PsiElement.prevWhiteSpace(): PsiWhiteSpace? = prevSibling as? PsiWhiteSpace
}





