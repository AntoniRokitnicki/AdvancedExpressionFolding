package com.intellij.advancedExpressionFolding.processor.lombok

import com.intellij.advancedExpressionFolding.processor.*
import com.intellij.advancedExpressionFolding.processor.lombok.AnnotationExt.ClassLevelAnnotation
import com.intellij.advancedExpressionFolding.processor.lombok.LombokConstructorExt.singleConstructor
import com.intellij.advancedExpressionFolding.processor.lombok.LombokConstructorExt.singleConstructorManyFields
import com.intellij.advancedExpressionFolding.processor.lombok.LombokFoldingAnnotation.*
import com.intellij.advancedExpressionFolding.processor.lombok.MethodType.*
import com.intellij.advancedExpressionFolding.processor.util.GenericCallback
import com.intellij.advancedExpressionFolding.settings.State
import com.intellij.advancedExpressionFolding.settings.state.ILombokState
import com.intellij.openapi.util.Key
import com.intellij.psi.PsiClass
import com.intellij.psi.PsiField
import com.intellij.psi.PsiMethod

object LombokFieldExt : ILombokState by State()(),
    GenericCallback<PsiField, List<FieldLevelAnnotation>> {

    override val callbackKey: Key<() -> List<FieldLevelAnnotation>> by lazy {
        Key.create("lombok-field-callback")
    }

    fun fieldOptimizations(fieldLevelAnnotations: MutableList<FieldLevelAnnotation>) {
        fieldLevelAnnotations.filter {
            it.classAnnotation == LOMBOK_FIELD_CONSTRUCTOR
        }.takeIf {
            it.isNotEmpty()
        }?.run {
            when (size) {
                1 -> singleConstructor(fieldLevelAnnotations)
                else -> singleConstructorManyFields(fieldLevelAnnotations)
            }
        }
    }

    fun fieldOptimizationsPerField(fieldLevelMap: Map<PsiField, MutableList<FieldLevelAnnotation>>): Map<PsiField, List<FieldLevelAnnotation>> {
        return fieldLevelMap.mapValues { (field, list) ->
            if (list.size < 2 && list.none {
                    it.arguments.isEmpty()
                }) {
                return@mapValues list
            }

            val usedAnnotations = list.asSequence()
                .filter {
                    it.arguments.isEmpty()
                }.map {
                    it.classAnnotation
                }.toSet()

            LombokFoldingAnnotation.entries
                .asSequence()
                .filterNot { it == LOMBOK_VALUE_SIMPLE }
                .forEach { groupingAnnotation ->
                    groupFields(groupingAnnotation, field, usedAnnotations, list)
                }
            list
        }
    }

    fun groupFields(
        groupingAnnotation: LombokFoldingAnnotation,
        field: PsiField,
        usedAnnotations: Set<LombokFoldingAnnotation>,
        list: MutableList<FieldLevelAnnotation>
    ) {
        groupingAnnotation.children()?.let { neededKids ->
            if (groupingAnnotation == LOMBOK_VALUE && field.isFinal()) {
                neededKids.remove(LOMBOK_REQUIRED_ARGS_CONSTRUCTOR)
            }

            if (usedAnnotations.containsAll(neededKids)) {
                if (groupingAnnotation == LOMBOK_DATA || groupingAnnotation == LOMBOK_VALUE) {
                    neededKids.add(LOMBOK_TO_STRING)
                }

                val removable = list.filter {
                    neededKids.contains(it.classAnnotation)
                }
                if (removable.isNotEmpty()) {
                    list.removeAll(removable)
                    list.add(
                        FieldLevelAnnotation(
                            groupingAnnotation,
                            field,
                            method = removable.flatMap {
                                it.method
                            })
                    )
                }
            }
        }
    }

    fun applyFieldLevel(fieldLevelMap: Map<PsiField, List<FieldLevelAnnotation>>) {
        fieldLevelMap.forEach { (field, annotations) ->
            initCallback(field, annotations)
        }
    }

    fun FieldLevelAnnotation.createFieldLevelAnnotation(): String {
        val arguments = arguments.takeIf {
            it.isNotEmpty()
        }?.joinToString(separator = ",")?.let {
            "($it)"
        } ?: ""
        return classAnnotation.annotation + arguments
    }

    fun foldProperties(
        methodTypeToMethodsMap: Map<MethodType, List<PsiMethod>>,
        fieldsMap: Map<String, PsiField>,
        fieldLevelAnnotations: MutableList<FieldLevelAnnotation>
    ): List<ClassLevelAnnotation> {
        return mapOf(
            GETTER to LOMBOK_GETTER,
            SETTER to LOMBOK_SETTER,
            WITH to LOMBOK_WITH,
        ).mapNotNull { (methodType, annotation) ->
            val methods = methodTypeToMethodsMap.getMethodsOfType(methodType)
            return@mapNotNull methods.mapNotNull { method ->
                val field = fieldsMap[method.guessPropertyName()]
                when (methodType) {
                    GETTER -> field?.metadata?.getter = method
                    else -> field?.metadata?.setter = method
                }
                field?.let {
                    method.propertyField = it
                }
                if (field != null) {
                    field to method
                } else {
                    null
                }
            }.takeIf {
                it.isNotEmpty()
            }?.let { properties ->
                val dirtyFields = properties.associate {
                    it.first to methodType.isDirty(it.second)
                }

                if (properties.size == fieldsMap.size && dirtyFields.values.all {
                        !it
                    }) {
                    properties.map {
                        it.second
                    }
                } else {
                    properties.mapNotNull { (field, method) ->
                        val dirty = dirtyFields[field] ?: false
                        if (dirty && lombokDirtyOff) {
                            null
                        } else {
                            val arguments =
                                methodType.createFieldArgument(dirty, field, method)?.let(::listOf) ?: emptyList()
                            FieldLevelAnnotation(annotation, field, listOf(method), arguments)
                        }
                    }.forEach(fieldLevelAnnotations::add)

                    null
                }
            }?.let {
                ClassLevelAnnotation(annotation, it, pure = true)
            }
        }
    }

    fun foldData(
        methodTypeToMethodsMap: Map<MethodType, List<PsiMethod>>,
        fieldsMap: Map<String, PsiField>,
        fieldLevelAnnotations: MutableList<FieldLevelAnnotation>
    ): List<ClassLevelAnnotation> {
        return mapOf(
            TO_STRING to LOMBOK_TO_STRING,
            EQUALS to LOMBOK_EQUALS,
            HASHCODE to LOMBOK_HASHCODE,
        ).mapNotNull { (methodType, annotation) ->
            val method = methodTypeToMethodsMap.getMethodsOfType(methodType).firstOrNull() ?: return@mapNotNull null
            val lombokFields = method.getFieldsUsed(fieldsMap.values)
            val pure = lombokFields.size == fieldsMap.size
            if (!pure) {
                lombokFields.map {
                    FieldLevelAnnotation(annotation, it, listOf(method))
                }.forEach(fieldLevelAnnotations::add)
                null
            } else {
                ClassLevelAnnotation(annotation, listOf(method), pure)
            }
        }
    }

    fun PsiMethod.getFieldsUsed(fieldsMap: Collection<PsiField>): List<PsiField> {
        body ?: return emptyList()
        return fieldsMap.mapNotNull { field ->
            field.findLocalReference(this)
                ?.let {
                    field
                }
        }
    }

    fun createFieldMap(clazz: PsiClass) =
        clazz.fieldsNotStatic.associateBy {
            it.name
        }.takeIf {
            it.isNotEmpty()
        }

    fun Map<MethodType, List<PsiMethod>>.getMethodsOfType(
        methodType: MethodType
    ) = this[methodType] ?: emptyList()
}
