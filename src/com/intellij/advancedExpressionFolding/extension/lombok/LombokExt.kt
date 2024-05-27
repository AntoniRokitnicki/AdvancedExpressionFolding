package com.intellij.advancedExpressionFolding.extension.lombok

import com.intellij.advancedExpressionFolding.extension.*
import com.intellij.advancedExpressionFolding.extension.MethodBodyInspector.isDirtyGetter
import com.intellij.advancedExpressionFolding.extension.MethodBodyInspector.isDirtySetter
import com.intellij.advancedExpressionFolding.extension.PsiClassExt.ClassLevelAnnotation
import com.intellij.advancedExpressionFolding.extension.lombok.LombokFoldingAnnotation.*
import com.intellij.advancedExpressionFolding.extension.lombok.MethodType.*
import com.intellij.openapi.util.Key
import com.intellij.openapi.util.NlsSafe
import com.intellij.psi.PsiClass
import com.intellij.psi.PsiField
import com.intellij.psi.PsiJavaFile
import com.intellij.psi.PsiMethod
import com.intellij.psi.impl.source.PsiClassReferenceType

data class FieldLevelAnnotation(
    val classAnnotation: LombokFoldingAnnotation,
    val field: PsiField,
    val method: List<PsiMethod>,
    //val pure: Boolean = true,
    val arguments: List<String> = emptyList(),
)

object LombokExt : BaseExtension(), GenericCallback<PsiField, List<FieldLevelAnnotation>> {
    override val callbackKey: Key<() -> List<FieldLevelAnnotation>> by lazy {
        Key.create("lombok-callback")
    }

    fun PsiClass.addLombokSupport(): List<ClassLevelAnnotation> {
        val classLevelAnnotations = mutableListOf<ClassLevelAnnotation>()
        val fieldLevelAnnotations = mutableListOf<FieldLevelAnnotation>()

        classLevelAnnotations += foldLog(this.fields)
        classLevelAnnotations += foldBuilder()
        classLevelAnnotations += foldNoArgsConstructor(this.constructors)

        createFieldMap(this)?.let { fieldsMap ->
            classLevelAnnotations += foldArgsConstructor(this.constructors, fieldsMap.values)

            val methodTypeToMethodsMap: Map<MethodType, List<PsiMethod>> = groupMethodsByMethodType(this)
            classLevelAnnotations += foldProperties(methodTypeToMethodsMap, fieldsMap, fieldLevelAnnotations)
            classLevelAnnotations += foldData(methodTypeToMethodsMap, fieldsMap, fieldLevelAnnotations)
        }
        classOptimizations(classLevelAnnotations)

        val fieldLevelMap = fieldLevelAnnotations.groupBy {
            it.field
        }.toMutableMap()

        applyFieldLevel(fieldOptimizations(fieldLevelMap))
        return classLevelAnnotations
    }

    private fun applyFieldLevel(fieldLevelMap: Map<PsiField, List<FieldLevelAnnotation>>) {
        fieldLevelMap
            .forEach {
                it.key.callback = {
                    it.value
                }
                getNonSyntheticExpression(it.key)
            }
    }

    private fun PsiClass.foldBuilder(): List<ClassLevelAnnotation> {
        return allInnerClasses.filter {
            it.name?.endsWith("Builder") == true
        }.map {
            ClassLevelAnnotation(LOMBOK_BUILDER, emptyList())
        }
    }

    private fun foldArgsConstructor(
        constructors: Array<PsiMethod>,
        fields: Collection<PsiField>
    ): List<ClassLevelAnnotation> {
        return constructors.flatMap {
            it.foldArgsConstructor(fields)
        }
    }

    private fun PsiMethod.foldArgsConstructor(
        fields: Collection<PsiField>
    ): MutableList<ClassLevelAnnotation> {
        val list = mutableListOf<ClassLevelAnnotation>()
        if (fields.all {
                it.isFinal()
            } && isAllArgsConstructor(this, fields)) {
            list.add(ClassLevelAnnotation(LOMBOK_REQUIRED_ARGS_CONSTRUCTOR, listOf(this), arguments = detectModifier()))
        } else if (isAllArgsConstructor(this, fields)) {
            list.add(ClassLevelAnnotation(LOMBOK_ALL_ARGS_CONSTRUCTOR, listOf(this), arguments = detectModifier()))
        }
        return list
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
                ClassLevelAnnotation(LOMBOK_NO_ARGS_CONSTRUCTOR, listOf(it), arguments = it.detectModifier())
            } else {
                null
            }
        }?.let {
            listOf(it)
        } ?: emptyList()
    }

    private fun isAllArgsConstructor(method: PsiMethod, fields: Collection<PsiField>): Boolean {
        return method.hasParameters()
                && fields.isNotEmpty()
                && method.parameterList.parametersCount == fields.size
                && MethodBodyInspector.isAllArgsConstructor(method, fields)
    }

    private fun isNoArgsConstructor(method: PsiMethod): Boolean {
        return !method.hasParameters() && MethodBodyInspector.isPureNoArgsConstructor(method)
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

    private fun foldProperties(
        methodTypeToMethodsMap: Map<MethodType, List<PsiMethod>>,
        fieldsMap: Map<@NlsSafe String, PsiField>,
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
                            val arguments = methodType.createFieldArgument(dirty)?.let(::listOf) ?: emptyList()
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
        fieldsMap: Map<@NlsSafe String, PsiField>,
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


    private fun fieldOptimizations(fieldLevelMap: MutableMap<PsiField, List<FieldLevelAnnotation>>): Map<PsiField, List<FieldLevelAnnotation>> {
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
            val values = LombokFoldingAnnotation.values().toMutableList()
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
        LombokFoldingAnnotation.values().forEach { groupingAnnotation ->
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


    internal fun hasLombokImports(clazz: PsiClass) =
        clazz.containingFile.asInstance<PsiJavaFile>()?.importList?.importStatements?.any {
            it.qualifiedName?.startsWith("lombok") ?: false
        } ?: false


}


enum class MethodType {
    GETTER {
        override fun isDirty(method: PsiMethod) = method.isDirtyGetter()

        override fun createFieldArgument(dirty: Boolean): String? = if (dirty) {
            "dirty"
        } else {
            null
        }

    },
    SETTER {
        override fun isDirty(method: PsiMethod) = method.isDirtySetter()

        override fun createFieldArgument(dirty: Boolean): String? = if (dirty) {
            "dirty"
        } else {
            null
        }
    },
    TO_STRING,
    EQUALS,
    HASHCODE,
    OTHER,

    ;

    open fun isDirty(method: PsiMethod): Boolean = false
    open fun createFieldArgument(dirty: Boolean): String? = null
}

private fun PsiMethod.findMethodType(): MethodType =
    when {
        isGetter() -> GETTER
        isSetter() -> SETTER
        isToString() -> TO_STRING
        isEquals() -> EQUALS
        isHashCode() -> HASHCODE
        else -> OTHER
    }
