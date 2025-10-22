package com.intellij.advancedExpressionFolding.processor.lombok

import com.intellij.advancedExpressionFolding.processor.*
import com.intellij.advancedExpressionFolding.processor.core.BaseExtension
import com.intellij.advancedExpressionFolding.processor.lombok.AnnotationExt.ClassLevelAnnotation
import com.intellij.advancedExpressionFolding.processor.lombok.LombokConstructorExt.foldArgsConstructor
import com.intellij.advancedExpressionFolding.processor.lombok.LombokConstructorExt.foldNoArgsConstructor
import com.intellij.advancedExpressionFolding.processor.lombok.LombokFieldExt.applyFieldLevel
import com.intellij.advancedExpressionFolding.processor.lombok.LombokFieldExt.createFieldMap
import com.intellij.advancedExpressionFolding.processor.lombok.LombokFieldExt.fieldOptimizations
import com.intellij.advancedExpressionFolding.processor.lombok.LombokFieldExt.fieldOptimizationsPerField
import com.intellij.advancedExpressionFolding.processor.lombok.LombokFieldExt.foldData
import com.intellij.advancedExpressionFolding.processor.lombok.LombokFieldExt.foldProperties
import com.intellij.advancedExpressionFolding.processor.lombok.LombokFoldingAnnotation.*
import com.intellij.advancedExpressionFolding.processor.lombok.LombokMethodExt.interfaceSupport
import com.intellij.advancedExpressionFolding.processor.lombok.LombokMethodExt.isFinder
import com.intellij.advancedExpressionFolding.processor.lombok.MethodType.*
import com.intellij.psi.PsiClass
import com.intellij.psi.PsiClassType
import com.intellij.psi.PsiField
import com.intellij.psi.PsiMethod
import com.intellij.psi.impl.source.PsiClassReferenceType

object LombokExt : BaseExtension() {

    fun PsiClass.addLombokSupport(): List<ClassLevelAnnotation> {
        if (isInterface) {
            return interfaceSupport() ?: emptyList()
        }

        val classLevelAnnotations = mutableListOf<ClassLevelAnnotation>()
        val fieldLevelAnnotations = mutableListOf<FieldLevelAnnotation>()

        classLevelAnnotations += foldLog(this.fields)

        val fieldsMap = createFieldMap(this)

        classLevelAnnotations += foldBuilder(fieldsMap, fieldLevelAnnotations)
        classLevelAnnotations += foldNoArgsConstructor(this.constructors)

        fieldsMap?.let { fieldsMap ->
            classLevelAnnotations += foldArgsConstructor(this.constructors, fieldsMap.values, fieldLevelAnnotations)

            val methodTypeToMethodsMap = methodsNotStatic.groupBy {
                it.findMethodType()
            }
            classLevelAnnotations += foldProperties(methodTypeToMethodsMap, fieldsMap, fieldLevelAnnotations)
            classLevelAnnotations += foldData(methodTypeToMethodsMap, fieldsMap, fieldLevelAnnotations)
        }
        classOptimizations(classLevelAnnotations)
        fieldOptimizations(fieldLevelAnnotations)

        val fieldLevelMap: Map<PsiField, MutableList<FieldLevelAnnotation>> = fieldLevelAnnotations.groupBy {
            it.field
        }.mapValues {
            it.value.toMutableList()
        }

        applyFieldLevel(fieldOptimizationsPerField(fieldLevelMap))

        return classLevelAnnotations
    }

    private fun PsiClass.foldBuilder(
        fieldsMap: Map<String, PsiField>?,
        fieldLevelAnnotations: MutableList<FieldLevelAnnotation>
    ): List<ClassLevelAnnotation> {
        val builderFieldAnnotations = linkedMapOf<PsiField, FieldLevelAnnotation>()
        val classAnnotations = allInnerClasses.asSequence()
            .filter { it.name?.endsWith("Builder") == true && it.isBuilder() }
            .mapNotNull { builderClass ->
                val matchedFields = fieldsMap?.let { map ->
                    builderClass.collectBuilderFields(map)
                } ?: emptyList()

                if (fieldsMap != null) {
                    if (matchedFields.isEmpty()) {
                        return@mapNotNull null
                    }

                    if (matchedFields.size < fieldsMap.size) {
                        matchedFields.forEach { (field, method) ->
                            builderFieldAnnotations.merge(field, FieldLevelAnnotation(LOMBOK_BUILDER, field, listOf(method))) { existing, new ->
                                existing.copy(method = existing.method + new.method)
                            }
                        }
                    }
                }

                val namelessBuilder = builderClass.name == "${name}Builder"
                when {
                    namelessBuilder -> ClassLevelAnnotation(LOMBOK_BUILDER, emptyList())
                    builderClass.name != null -> ClassLevelAnnotation(LOMBOK_BUILDER, emptyList(), arguments = listOf(builderClass.name!!))
                    else -> null
                }
            }
            .toList()

        if (fieldsMap != null && builderFieldAnnotations.isNotEmpty() && builderFieldAnnotations.size < fieldsMap.size) {
            fieldLevelAnnotations += builderFieldAnnotations.values
        }

        return classAnnotations
    }

    private fun PsiClass.collectBuilderFields(
        fieldsMap: Map<String, PsiField>
    ): List<Pair<PsiField, PsiMethod>> {
        return methods.asSequence()
            .filter { method ->
                method.parameterList.parametersCount == 1 &&
                        (method.returnType as? PsiClassType)?.resolve() == this
            }
            .mapNotNull { method ->
                val propertyName = method.guessPropertyName()
                val field = fieldsMap[propertyName] ?: fieldsMap[method.name]
                field?.let { it to method }
            }
            .toList()
    }

    private fun foldLog(
        fields: Array<PsiField>
    ): List<ClassLevelAnnotation> {
        return fields.filter {
            (it.type as? PsiClassReferenceType)?.name?.contains("Logger") == true
        }.map { logField ->
            val dirty = logField.name != "log"
            logField.markIgnored()
            val arguments = dirty.on(logField.name)?.let {
                listOf(it)
            } ?: emptyList()
            ClassLevelAnnotation(LOMBOK_LOG, listOf(logField), arguments = arguments)
        }
    }

    private fun classOptimizations(allAnnotations: MutableList<ClassLevelAnnotation>) {
        val annotations = allAnnotations.groupBy {
            it.classAnnotation
        }

        val usedAnnotations = annotations.keys.toMutableSet()
        LombokFoldingAnnotation.entries.forEach { groupingAnnotation ->
            groupingAnnotation.children()?.let { neededKids ->
                if (usedAnnotations.containsAll(neededKids)) {
                    if (groupingAnnotation == LOMBOK_DATA || groupingAnnotation == LOMBOK_VALUE) {
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

                    val elementsToHide = hidingAnnotations.flatMap(ClassLevelAnnotation::elementsToHide)
                    allAnnotations.add(ClassLevelAnnotation(groupingAnnotation, elementsToHide, pure))
                }
            }
        }
    }

    fun PsiMethod.findMethodType(): MethodType =
        when {
            isGetter() -> GETTER
            isSetter() -> SETTER
            isToString() -> TO_STRING
            isEquals() -> EQUALS
            isHashCode() -> HASHCODE
            isFinder() -> FIND_BY
            else -> OTHER
        }
}
