package com.intellij.advancedExpressionFolding.processor.lombok

import com.intellij.advancedExpressionFolding.processor.*
import com.intellij.advancedExpressionFolding.processor.core.BaseExtension
import com.intellij.advancedExpressionFolding.processor.lombok.AnnotationExt.ClassLevelAnnotation
import com.intellij.advancedExpressionFolding.processor.lombok.LombokFoldingAnnotation.*
import com.intellij.psi.PsiField
import com.intellij.psi.PsiMethod
import com.intellij.psi.PsiParameter

object LombokConstructorExt : BaseExtension() {
    fun foldNoArgsConstructor(constructors: Array<PsiMethod>): List<ClassLevelAnnotation> {
        return constructors.firstNotNullOfOrNull {
            if (isNoArgsConstructor(it)) {
                val args = mutableListOf<String>()
                it.modifierAsArgument().run(args::addAll)
                if (it.modifier() != EModifier.PRIVATE) {
                    it.body?.getComment()?.text?.run(args::add)
                }
                ClassLevelAnnotation(LOMBOK_NO_ARGS_CONSTRUCTOR, listOf(it), arguments = args)
            } else {
                null
            }
        }?.let {
            listOf(it)
        } ?: emptyList()
    }

    fun foldArgsConstructor(
        constructors: Array<PsiMethod>,
        fields: Collection<PsiField>,
        fieldLevelAnnotations: MutableList<FieldLevelAnnotation>
    ): List<ClassLevelAnnotation> {
        val classLevelAnnotations = constructors.flatMapIndexed { index: Int, psiMethod: PsiMethod ->
            psiMethod.foldArgsConstructor(fields, fieldLevelAnnotations, index)
        }
        return classLevelAnnotations
    }

    fun PsiMethod.foldArgsConstructor(
        fields: Collection<PsiField>,
        fieldLevelAnnotations: MutableList<FieldLevelAnnotation>,
        index: Int
    ): MutableList<ClassLevelAnnotation> {
        val list = mutableListOf<ClassLevelAnnotation>()
        val detectModifier = modifierAsArgument()
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

    fun List<FieldLevelAnnotation>.singleConstructorManyFields(
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

    fun List<FieldLevelAnnotation>.singleConstructor(
        fieldLevelAnnotations: MutableList<FieldLevelAnnotation>
    ) {
        val first = first()
        val index = fieldLevelAnnotations.indexOf(first)
        fieldLevelAnnotations[index] = first.copy(arguments = first.arguments.drop(1))
    }

    fun PsiMethod.createConstructorOnFields(
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

    fun PsiMethod.isAllArgsConstructor(fields: Collection<PsiField>): Boolean {
        return parameterList.parametersCount == fields.size
                && (hasParamsAndFields(fields)
                && MethodBodyInspector.isAllArgsConstructor(this, fields))
    }

    fun isNoArgsConstructor(method: PsiMethod) = !method.hasParameters() && MethodBodyInspector.isPureNoArgsConstructor(method)

    fun PsiMethod.hasParamsAndFields(fields: Collection<PsiField>) = hasParameters() && fields.isNotEmpty()

    fun PsiMethod.modifierAsArgument(): List<String> {
        val modifier = this.modifier()
        val arguments = if (!modifier.isPublic()) {
            listOf(modifier.modifier)
        } else {
            emptyList()
        }
        return arguments
    }
}
