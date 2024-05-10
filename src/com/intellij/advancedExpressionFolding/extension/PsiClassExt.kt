package com.intellij.advancedExpressionFolding.extension

import com.intellij.advancedExpressionFolding.expression.Expression
import com.intellij.advancedExpressionFolding.expression.custom.ClassAnnotationExpression
import com.intellij.advancedExpressionFolding.extension.FoldingAnnotation.*
import com.intellij.advancedExpressionFolding.extension.MethodType.*
import com.intellij.psi.*
import java.util.*
import java.util.EnumSet.of

typealias CustomClassAnnotation = String

enum class MethodType {
    GETTER,
    SETTER,
    TO_STRING,
    EQUALS,
    HASHCODE,
    OTHER,

    ;

    fun isSetter() = this == SETTER
}

enum class FoldingAnnotation(val annotation: String) {
    LOMBOK_GETTER("@Getter"),
    LOMBOK_SETTER("@Setter"),
    LOMBOK_TO_STRING("@ToString"),
    LOMBOK_EQUALS("@Equals"),
    LOMBOK_HASHCODE("@HashCode"),

    LOMBOK_DATA("@Data") {
        override fun children(): EnumSet<FoldingAnnotation> =
            of(LOMBOK_GETTER, LOMBOK_SETTER, LOMBOK_EQUALS, LOMBOK_HASHCODE)
    },
    LOMBOK_EQUALS_AND_HASHCODE("@EqualsAndHashCode") {
        override fun children(): EnumSet<FoldingAnnotation> = of(LOMBOK_EQUALS, LOMBOK_HASHCODE)
    },

    SERIAL("@Serial"),

    ;

    open fun children(): EnumSet<FoldingAnnotation>? = null
}

typealias MethodTypeToMethodsMap = Map<MethodType, List<PsiMethod>>


object PsiClassExt : BaseExtension() {

    enum class ClassType {
        BUILDER,
        NOT_NULL,
    }

    data class HidingAnnotation(
        val classAnnotation: FoldingAnnotation,
        val elementsToHide: List<PsiElement>,
        val pure: Boolean = true
    )

    @JvmStatic
    fun createExpression(clazz: PsiClass): Expression? {
        if (clazz.isIgnored() || !lombok || clazz.isRecord || clazz.isInterface) {
            return null
        }

        val serialField = isSerial(clazz)
        if (hasLombokImports(clazz) && serialField == null) {
            clazz.markIgnored()
            return null
        }

        val changes = addLombokSupport(clazz) + addSerialVersionUID(serialField)
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
                "*"
            } ?: ""
            hidingAnnotation.classAnnotation.annotation + notPureSuffix
        }, elementsToFold)
    }

    private fun addSerialVersionUID(
        serialField: PsiField?
    ): List<HidingAnnotation> {
        return serialField?.let {
            serialField.markIgnored()
            listOf(HidingAnnotation(SERIAL, listOf(it)))
        } ?: emptyList()
    }

    private fun addLombokSupport(
        clazz: PsiClass
    ): List<HidingAnnotation> {
        val fieldsMap = clazz.fields.filter {
            it.isNotStatic()
        }.associateBy {
            it.name
        }.takeIf {
            it.isNotEmpty()
        } ?: return emptyList()


        val methodTypeToMethodsMap: MethodTypeToMethodsMap = clazz.methods.filter {
            it.isNotStatic()
        }.groupBy {
            it.findMethodType()
        }

        val hidingAnnotations = mutableListOf<HidingAnnotation>()

        mapOf(
            GETTER to LOMBOK_GETTER,
            SETTER to LOMBOK_SETTER
        ).forEach { (methodType, annotation) ->
            val methods = methodTypeToMethodsMap.getMethodsOfType(methodType)
            methods.filter { method ->
                val psiField = fieldsMap[method.guessPropertyName()]
                when (methodType) {
                    GETTER -> psiField?.setProperty(method, null)
                    SETTER -> psiField?.setProperty(null, method)
                    else -> {}
                }
                psiField != null
            }.takeIf {
                it.isNotEmpty()
            }?.let { properties ->
                val pure = methods.size == fieldsMap.values.filter {
                    if (methodType.isSetter()) {
                        it.isNotFinal()
                    } else {
                        true
                    }
                }.size
                hidingAnnotations += HidingAnnotation(annotation, properties, pure)
            }
        }

        mapOf(
            TO_STRING to LOMBOK_TO_STRING,
            EQUALS to LOMBOK_EQUALS,
            HASHCODE to LOMBOK_HASHCODE,
        ).forEach { (methodType, annotation) ->
            val method = methodTypeToMethodsMap.getMethodsOfType(methodType).firstOrNull() ?: return@forEach
            val lombokFields = method.getFieldsUsed(fieldsMap.values)
            val pure = lombokFields.size == fieldsMap.size
            hidingAnnotations += HidingAnnotation(annotation, listOf(method), pure)
        }

        optimizations(hidingAnnotations)
        return hidingAnnotations
    }

    private fun optimizations(allAnnotations: MutableList<HidingAnnotation>) {
        val annotations = allAnnotations.groupBy {
            it.classAnnotation
        }

        val usedAnnotations = annotations.keys.toMutableSet()
        FoldingAnnotation.values().forEach { groupingAnnotation ->
            groupingAnnotation.children()?.let { neededKids ->
                if (usedAnnotations.containsAll(neededKids)) {
                    if (groupingAnnotation == LOMBOK_DATA) {
                        neededKids.add(LOMBOK_TO_STRING)
                    }

                    val hidingAnnotations = neededKids.mapNotNull {
                        annotations[it]
                    }.flatten()

                    val pure = hidingAnnotations.all {
                        it.pure
                    }


                    allAnnotations.removeAll {
                        it.classAnnotation in neededKids
                    }
                    usedAnnotations.removeAll(neededKids)

                    val elementsToHide = hidingAnnotations.flatMap(HidingAnnotation::elementsToHide)
                    allAnnotations.add(HidingAnnotation(groupingAnnotation, elementsToHide, pure))
                }
            }
        }
    }

    private fun PsiMethod.getFieldsUsed(fieldsMap: Collection<PsiField>): List<PsiField> {
        body ?: return emptyList()
        return fieldsMap.mapNotNull { field ->
            field.findLocalReference(this)
                ?.let {
                    field
                }
        }
    }

    private fun MethodTypeToMethodsMap.getMethodsOfType(methodType: MethodType) = this[methodType] ?: emptyList()

    private fun PsiMethod.findMethodType(): MethodType =
        when {
            isGetter() -> GETTER
            isSetter() -> SETTER
            isToString() -> TO_STRING
            isEquals() -> EQUALS
            isHashCode() -> HASHCODE
            else -> OTHER
        }

    private fun isSerial(clazz: PsiClass): PsiField? = clazz.fields.firstOrNull {
        it.name == "serialVersionUID"
    }

    private fun hasLombokImports(clazz: PsiClass) =
        clazz.containingFile.asInstance<PsiJavaFile>()?.importList?.importStatements?.any {
            it.qualifiedName?.contains("lombok") ?: false
        } ?: false


    fun PsiElement.prevWhiteSpace(): PsiWhiteSpace? = prevSibling as? PsiWhiteSpace
    fun PsiElement.nextWhiteSpace(): PsiWhiteSpace? = nextSibling as? PsiWhiteSpace
}
