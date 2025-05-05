package com.intellij.advancedExpressionFolding.extension.clazz

import com.intellij.advancedExpressionFolding.extension.*
import com.intellij.advancedExpressionFolding.extension.clazz.AnnotationExt.ClassLevelAnnotation
import com.intellij.advancedExpressionFolding.extension.clazz.LombokFoldingAnnotation.*
import com.intellij.advancedExpressionFolding.extension.clazz.LombokMethodExt.interfaceSupport
import com.intellij.advancedExpressionFolding.extension.clazz.LombokMethodExt.isFinder
import com.intellij.advancedExpressionFolding.extension.clazz.MethodType.*
import com.intellij.openapi.editor.FoldingGroup
import com.intellij.openapi.util.Key
import com.intellij.psi.PsiClass
import com.intellij.psi.PsiField
import com.intellij.psi.PsiMethod
import com.intellij.psi.PsiParameter
import com.intellij.psi.impl.source.PsiClassReferenceType

data class FieldLevelAnnotation(
    val classAnnotation: LombokFoldingAnnotation,
    val field: PsiField,
    val method: List<PsiMethod>,
    val arguments: List<String> = emptyList(),
    val group: FoldingGroup? = null,
)

object LombokExt : BaseExtension(), GenericCallback<PsiField, List<FieldLevelAnnotation>> {
    override val callbackKey: Key<() -> List<FieldLevelAnnotation>> by lazy {
        Key.create("lombok-field-callback")
    }

    fun PsiClass.addLombokSupport(): List<ClassLevelAnnotation> {
        if (isInterface) {
            return interfaceSupport() ?: emptyList()
        }

        val classLevelAnnotations = mutableListOf<ClassLevelAnnotation>()
        val fieldLevelAnnotations = mutableListOf<FieldLevelAnnotation>()

        classLevelAnnotations += foldLog(this.fields)
        classLevelAnnotations += foldBuilder()
        classLevelAnnotations += foldNoArgsConstructor(this.constructors)

        createFieldMap(this)?.let { fieldsMap ->
            classLevelAnnotations += foldArgsConstructor(this.constructors, fieldsMap.values, fieldLevelAnnotations)

            val methodTypeToMethodsMap: Map<MethodType, List<PsiMethod>> = groupMethodsByMethodType(this)
            classLevelAnnotations += foldProperties(methodTypeToMethodsMap, fieldsMap, fieldLevelAnnotations)
            classLevelAnnotations += foldData(methodTypeToMethodsMap, fieldsMap, fieldLevelAnnotations)
        }
        classOptimizations(classLevelAnnotations)
        fieldOptimizations(fieldLevelAnnotations)

        val fieldLevelMap: MutableMap<PsiField, List<FieldLevelAnnotation>> = fieldLevelAnnotations.groupBy {
            it.field
        }.toMutableMap()

        applyFieldLevel(fieldOptimizationsPerField(fieldLevelMap))
        return classLevelAnnotations
    }

    private fun fieldOptimizations(fieldLevelAnnotations: MutableList<FieldLevelAnnotation>) {
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

    private fun List<FieldLevelAnnotation>.singleConstructorManyFields(
        fieldLevelAnnotations: MutableList<FieldLevelAnnotation>
    ) {
        fun constructorAnnotationFirstArgument(annotation: FieldLevelAnnotation): String = annotation.arguments.first().substringBefore("-", "")
        fun constructorAnnotationSecondArgument(annotation: FieldLevelAnnotation): String = annotation.arguments.first().substringAfter("-", "")

        groupBy {
            constructorAnnotationFirstArgument(it)
        }.takeIf {
            it.size == 1
        }?.run {
            val group = group()
            values.flatten().takeIf {
                it.size > 1
            }?.forEachIndexed { constructorIndex, annotation ->
                val index = fieldLevelAnnotations.indexOf(annotation)
                val firstArg = constructorAnnotationSecondArgument(annotation)
                val methods = if (constructorIndex > 0) {
                    emptyList()
                } else {
                    annotation.method
                }
                fieldLevelAnnotations[index] = annotation.copy(arguments = listOf(firstArg) + annotation.arguments.drop(1), group = group, method = methods)
            }
        }
    }

    private fun List<FieldLevelAnnotation>.singleConstructor(
        fieldLevelAnnotations: MutableList<FieldLevelAnnotation>
    ) {
        val first = first()
        val index = fieldLevelAnnotations.indexOf(first)
        fieldLevelAnnotations[index] = first.copy(arguments = first.arguments.drop(1))
    }


    private fun applyFieldLevel(fieldLevelMap: Map<PsiField, List<FieldLevelAnnotation>>) {
        fieldLevelMap
            .forEach { (field, annotations) ->
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

    private fun PsiClass.foldBuilder(): List<ClassLevelAnnotation> {
        return allInnerClasses.filter {
            it.name?.endsWith("Builder") == true
        }.mapNotNull {
            val namelessBuilder = it.name == "${name}Builder"
            if (namelessBuilder) {
                ClassLevelAnnotation(LOMBOK_BUILDER, emptyList())
            } else if (it.name != null){
                ClassLevelAnnotation(LOMBOK_BUILDER, emptyList(), arguments = listOf(it.name!!))
            } else {
                null
            }
        }
    }

    private fun foldArgsConstructor(
        constructors: Array<PsiMethod>,
        fields: Collection<PsiField>,
        fieldLevelAnnotations: MutableList<FieldLevelAnnotation>
    ): List<ClassLevelAnnotation> {
        val classLevelAnnotations = constructors.flatMapIndexed { index: Int, psiMethod: PsiMethod ->
            psiMethod.foldArgsConstructor(fields, fieldLevelAnnotations, index)
        }
        return classLevelAnnotations
    }

    private fun PsiMethod.foldArgsConstructor(
        fields: Collection<PsiField>,
        fieldLevelAnnotations: MutableList<FieldLevelAnnotation>,
        index: Int
    ): MutableList<ClassLevelAnnotation> {
        val list = mutableListOf<ClassLevelAnnotation>()
        val detectModifier = detectModifier()
        if (fields.all {
                it.isFinal()
            } && isAllArgsConstructor( fields)) {
            list.add(ClassLevelAnnotation(LOMBOK_REQUIRED_ARGS_CONSTRUCTOR, listOf(this), arguments = detectModifier))
        } else if (isAllArgsConstructor(fields)) {
            list.add(ClassLevelAnnotation(LOMBOK_ALL_ARGS_CONSTRUCTOR, listOf(this), arguments = detectModifier))
        } else if (this.hasParameters()) {
            if ((hasParamsAndFields(fields))) {
                MethodBodyInspector.createParameterFieldMap(this, fields)?.let { paramToFieldMap ->
                    createConstructorOnFields(index + 1, detectModifier, paramToFieldMap, fieldLevelAnnotations)
                }
            }
        }
        return list
    }

    private fun PsiMethod.createConstructorOnFields(
        constructorIndex: Int,
        detectModifier: List<String>,
        paramToFieldMap: Map<PsiParameter, PsiField>,
        fieldLevelAnnotations: MutableList<FieldLevelAnnotation>
    ) {
        val usedFields = parameterList.parameters.mapNotNull {
            paramToFieldMap[it]
        }
        usedFields.forEachIndexed { paramIndex, field ->
            val args = mutableListOf<String>()
            args.add("$constructorIndex-${paramIndex+1}")
            args.addAll(detectModifier)
            fieldLevelAnnotations.add(
                FieldLevelAnnotation(
                    LOMBOK_FIELD_CONSTRUCTOR,
                    field,
                    listOf(this),
                    arguments = args
                )
            )
        }
    }

    private fun PsiMethod.detectModifier2(): String? {
        val modifier = this.modifier()
        return if (!modifier.isPublic()) {
            modifier.modifier
        } else {
            null
        }
    }

    private fun PsiMethod.detectModifier(): List<String> {
        val modifier = this.modifier()
        val arguments = if (!modifier.isPublic()) {
            listOf(modifier.modifier)
        } else {
            emptyList()
        }
        return arguments
    }

    private fun foldNoArgsConstructor(constructors: Array<PsiMethod>): List<ClassLevelAnnotation> {
        return constructors.firstNotNullOfOrNull {
            if (isNoArgsConstructor(it)) {
                val args = mutableListOf<String>()
                it.detectModifier2()?.run(args::add)
                it.body?.getComment()?.text?.run(args::add)
                ClassLevelAnnotation(LOMBOK_NO_ARGS_CONSTRUCTOR, listOf(it), arguments = args)
            } else {
                null
            }
        }?.let {
            listOf(it)
        } ?: emptyList()
    }

    private fun PsiMethod.isAllArgsConstructor(fields: Collection<PsiField>): Boolean {
        return parameterList.parametersCount == fields.size
                && (hasParamsAndFields(fields)
                        && MethodBodyInspector.isAllArgsConstructor(this, fields))
    }

    private fun PsiMethod.hasParamsAndFields(fields: Collection<PsiField>) = hasParameters() && fields.isNotEmpty()

    private fun isNoArgsConstructor(method: PsiMethod) = !method.hasParameters() && MethodBodyInspector.isPureNoArgsConstructor(method)

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

    private fun foldProperties(
        methodTypeToMethodsMap: Map<MethodType, List<PsiMethod>>,
        fieldsMap: Map<String, PsiField>,
        fieldLevelAnnotations: MutableList<FieldLevelAnnotation>
    ): List<ClassLevelAnnotation> {
        return mapOf(
            GETTER to LOMBOK_GETTER,
            SETTER to LOMBOK_SETTER,
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
                            val arguments = methodType.createFieldArgument(dirty, field, method)?.let(::listOf) ?: emptyList()
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

    private fun foldData(
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

    private fun fieldOptimizationsPerField(fieldLevelMap: MutableMap<PsiField, List<FieldLevelAnnotation>>): Map<PsiField, List<FieldLevelAnnotation>> {
        return fieldLevelMap.mapValues { (field, list) ->
            if (list.size < 2 && list.none {
                    it.arguments.isEmpty()
                }) {
                return@mapValues list
            }

            val outList = list.toMutableList()

            val usedAnnotations = list.map {
                it.classAnnotation
            }.toMutableList()
            val values = LombokFoldingAnnotation.entries.toMutableList()
            values.remove(LOMBOK_VALUE_SIMPLE)
            values.forEach { groupingAnnotation ->
                groupingAnnotation.children()?.let { neededKids ->
                    if (groupingAnnotation == LOMBOK_VALUE && field.isFinal()) {
                        neededKids.remove(LOMBOK_REQUIRED_ARGS_CONSTRUCTOR)
                    }

                    if (usedAnnotations.containsAll(neededKids)) {
                        if (groupingAnnotation == LOMBOK_DATA || groupingAnnotation == LOMBOK_VALUE) {
                            neededKids.add(LOMBOK_TO_STRING)

                        }
                        val removable = outList.filter {
                            neededKids.contains(it.classAnnotation)
                        }
                        if (removable.isNotEmpty()) {
                            outList.removeAll(removable)
                            outList.add(
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
            outList
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

    private fun PsiMethod.getFieldsUsed(fieldsMap: Collection<PsiField>): List<PsiField> {
        body ?: return emptyList()
        return fieldsMap.mapNotNull { field ->
            field.findLocalReference(this)
                ?.let {
                    field
                }
        }
    }

    private fun groupMethodsByMethodType(clazz: PsiClass) = clazz.methodsNotStatic.groupBy {
        it.findMethodType()
    }

    private fun createFieldMap(clazz: PsiClass) =
        clazz.fieldsNotStatic.associateBy {
            it.name
        }.takeIf {
            it.isNotEmpty()
        }

    private fun Map<MethodType, List<PsiMethod>>.getMethodsOfType(
        methodType: MethodType
    ) = this[methodType] ?: emptyList()

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

