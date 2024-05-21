package com.intellij.advancedExpressionFolding.extension

import com.intellij.advancedExpressionFolding.expression.custom.AbstractMultiExpression
import com.intellij.advancedExpressionFolding.expression.custom.FieldAnnotationExpression
import com.intellij.advancedExpressionFolding.extension.LombokExt.lombokDirtyOff
import com.intellij.advancedExpressionFolding.extension.LombokFoldingAnnotation.*
import com.intellij.advancedExpressionFolding.extension.MethodBodyInspector.isDirtyGetter
import com.intellij.advancedExpressionFolding.extension.MethodBodyInspector.isDirtySetter
import com.intellij.advancedExpressionFolding.extension.MethodType.*
import com.intellij.advancedExpressionFolding.extension.PsiClassExt.HidingAnnotation
import com.intellij.openapi.util.Key
import com.intellij.openapi.util.NlsSafe
import com.intellij.psi.PsiClass
import com.intellij.psi.PsiField
import com.intellij.psi.PsiJavaFile
import com.intellij.psi.PsiMethod
import com.intellij.psi.impl.source.PsiClassReferenceType
import java.util.*
import java.util.EnumSet.of

object LombokExt : BaseExtension(), GenericCallback<PsiField, Pair<PsiMethod, String?>> {

    fun PsiClass.addLombokSupport(): List<HidingAnnotation> {
        val hidingAnnotations = mutableListOf<HidingAnnotation>()
        hidingAnnotations += foldLog(this.fields)
        val fieldsMap = createFieldMap(this) ?: return hidingAnnotations
        val methodTypeToMethodsMap: Map<MethodType, List<PsiMethod>> = groupMethodsByMethodType(this)

        hidingAnnotations += foldProperties(methodTypeToMethodsMap, fieldsMap)
        hidingAnnotations += foldData(methodTypeToMethodsMap, fieldsMap)

        optimizations(hidingAnnotations)
        return hidingAnnotations
    }

    private fun foldLog(
        fields: Array<PsiField>
    ): List<HidingAnnotation> {
        return fields.firstOrNull {
            (it.type as? PsiClassReferenceType)?.name?.contains("Logger") == true
        }?.let { logField ->
            val dirty = logField.name != "log"
            logField.markIgnored()
            val arguments = dirty.on(logField.name)?.let {
                listOf(it)
            } ?: emptyList()
            listOf(HidingAnnotation(LOG, listOf(logField), arguments = arguments))
        } ?:emptyList()
    }

    private fun foldProperties(
        methodTypeToMethodsMap: Map<MethodType, List<PsiMethod>>,
        fieldsMap: Map<@NlsSafe String, PsiField>
    ): List<HidingAnnotation> {


        return mapOf(
            GETTER to LOMBOK_GETTER,
            SETTER to LOMBOK_SETTER,
        ).mapNotNull { (methodType, annotation) ->
            val methods = methodTypeToMethodsMap.getMethodsOfType(methodType)
            return@mapNotNull methods.filter { method ->
                val field = fieldsMap[method.guessPropertyName()]
                when (methodType) {
                    GETTER -> field?.metadata?.getter = method
                    else -> field?.metadata?.setter = method
                }
                field?.let {
                    method.propertyField = it
                }
                field != null
            }.takeIf {
                it.isNotEmpty()
            }?.let { properties ->
                val fields = fieldsMap.values

                val dirtyFields = methods.filter {
                    methodType.isDirty(it)
                }.mapNotNull {
                    it.propertyField
                }
                if (methods.size == fields.size && dirtyFields.isEmpty()) {
                    properties
                } else {
                    foldOnFieldLevel(fields, methodType, dirtyFields)
                    null
                }
            }?.let {
                HidingAnnotation(annotation, it, pure = true)
            }
        }
    }

    private fun foldData(
        methodTypeToMethodsMap: Map<MethodType, List<PsiMethod>>,
        fieldsMap: Map<@NlsSafe String, PsiField>
    ): List<HidingAnnotation> {
        return mapOf(
            TO_STRING to LOMBOK_TO_STRING,
            EQUALS to LOMBOK_EQUALS,
            HASHCODE to LOMBOK_HASHCODE,
        ).mapNotNull { (methodType, annotation) ->
            val method = methodTypeToMethodsMap.getMethodsOfType(methodType).firstOrNull() ?: return@mapNotNull null
            val lombokFields = method.getFieldsUsed(fieldsMap.values)
            val pure = lombokFields.size == fieldsMap.size
            HidingAnnotation(annotation, listOf(method), pure)
        }
    }

    private fun foldOnFieldLevel(
        fields: Collection<PsiField>,
        methodType: MethodType,
        dirtyFields: List<PsiField>
    ) {
        fields.forEach { field ->
            val propertyMethod = when (methodType) {
                GETTER -> field.metadata.getter
                else -> field.metadata.setter
            }
            propertyMethod?.let { getter ->
                val dirty = dirtyFields.contains(field)

                var callbackCalled = false
                val callback = {
                    callbackCalled = true
                    val fieldAnnotation = methodType.createFieldLevelAnnotation(dirty)
                    getter to fieldAnnotation
                }
                field.useCallback(callback = callback, executeWithinCallback = {
                    val expr = getNonSyntheticExpression(field).asInstance<AbstractMultiExpression>()
                    callbackCalled.off(expr)?.let {
                        simulateSecondCallback(callback, it, field)
                    }
                })
            }
        }
    }

    private fun simulateSecondCallback(
        callback: () -> Pair<PsiMethod, String?>,
        multiplexer: AbstractMultiExpression,
        field: PsiField
    ) {
        val (getter2, getterAnnotation) = callback()
        getterAnnotation?.let {
            getter2.markIgnored()
            multiplexer.addChild(FieldAnnotationExpression(
                field,
                listOf(getterAnnotation),
                listOf(getter2, getter2.prevWhiteSpace())
            ))
        }
    }

    private fun optimizations(allAnnotations: MutableList<HidingAnnotation>) {
        val annotations = allAnnotations.groupBy {
            it.classAnnotation
        }

        val usedAnnotations = annotations.keys.toMutableSet()
        LombokFoldingAnnotation.values().forEach { groupingAnnotation ->
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
            it.qualifiedName?.contains("lombok") ?: false
        } ?: false

    override val callbackKey: Key<() -> Pair<PsiMethod, String?>> by lazy {
        Key.create("lombok-callback")
    }
}


enum class MethodType {
    GETTER {
        override fun isDirty(method: PsiMethod) = method.isDirtyGetter()

        override fun createFieldLevelAnnotation(dirty: Boolean): String? = if (dirty) {
            if (lombokDirtyOff) {
                null
            } else {
                "@Getter(dirty)"
            }
        } else {
            "@Getter"
        }

    },
    SETTER {
        override fun isDirty(method: PsiMethod) = method.isDirtySetter()

        override fun createFieldLevelAnnotation(dirty: Boolean): String? = if (dirty) {
            if (lombokDirtyOff) {
                null
            } else {
                "@Setter(dirty)"
            }
        } else {
            "@Setter"
        }
    },
    TO_STRING,
    EQUALS,
    HASHCODE,
    OTHER,

    ;

    open fun isDirty(method: PsiMethod): Boolean = false
    open fun createFieldLevelAnnotation(dirty: Boolean): String?  = null
}

enum class LombokFoldingAnnotation(val annotation: String) {
    LOMBOK_GETTER("@Getter"),
    LOMBOK_SETTER("@Setter"),
    LOMBOK_TO_STRING("@ToString"),
    LOMBOK_EQUALS("@Equals"),
    LOMBOK_HASHCODE("@HashCode"),

    LOMBOK_DATA("@Data") {
        override fun children(): EnumSet<LombokFoldingAnnotation> =
            of(LOMBOK_GETTER, LOMBOK_SETTER, LOMBOK_EQUALS, LOMBOK_HASHCODE)
    },
    LOMBOK_EQUALS_AND_HASHCODE("@EqualsAndHashCode") {
        override fun children(): EnumSet<LombokFoldingAnnotation> = of(LOMBOK_EQUALS, LOMBOK_HASHCODE)
    },

    SERIAL("@Serial"),
    LOG("@Log"),

    ;

    open fun children(): EnumSet<LombokFoldingAnnotation>? = null
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
