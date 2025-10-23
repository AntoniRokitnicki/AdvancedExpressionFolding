package com.intellij.advancedExpressionFolding.processor.lombok

import com.intellij.advancedExpressionFolding.expression.Expression
import com.intellij.advancedExpressionFolding.expression.semantic.lombok.InterfacePropertiesExpression
import com.intellij.advancedExpressionFolding.expression.semantic.lombok.NullAnnotationExpression
import com.intellij.advancedExpressionFolding.processor.asInstance
import com.intellij.advancedExpressionFolding.processor.core.BaseExtension
import com.intellij.advancedExpressionFolding.processor.core.getNonSyntheticExpression
import com.intellij.advancedExpressionFolding.processor.exprWrap
import com.intellij.advancedExpressionFolding.processor.group
import com.intellij.advancedExpressionFolding.processor.identifier
import com.intellij.advancedExpressionFolding.processor.language.kotlin.NullableExt
import com.intellij.advancedExpressionFolding.processor.lombok.LombokMethodExt.addInterfaceAnnotations
import com.intellij.advancedExpressionFolding.processor.lombok.LombokMethodExt.callback
import com.intellij.openapi.editor.FoldingGroup
import com.intellij.psi.PsiMethod
import com.intellij.psi.PsiParameter
import com.intellij.psi.util.parentOfTypes

object InterfacePropertiesExt : BaseExtension() {

    fun foldProperties(method: PsiMethod): Expression? {
        val group = group("interfaceExtensionProperties")
        val list = method.callback?.invoke()?.takeIf {
            method.identifier != null
        }?.let { annotations ->
            annotations.flatMap { methodLevelAnnotation ->
                method.addInterfaceAnnotations(methodLevelAnnotation, method.identifier!!)
                    .plus(getNullableInterfaceProperty(method, methodLevelAnnotation, group))
            }
        }
        return list?.exprWrap(method, group)?.run {
            InterfacePropertiesExpression(method, this)
        }
    }

    private fun getNullableInterfaceProperty(
        method: PsiMethod,
        annotation: MethodLevelAnnotation,
        group: FoldingGroup
    ): NullAnnotationExpression? {
        return when (annotation.methodAnnotation) {
            LombokInterfaceFoldingAnnotation.LOMBOK_INTERFACE_GETTER ->
                NullableExt.fieldAnnotationExpression(
                    method.annotations,
                    method.returnTypeElement,
                    group = group,
                    foldPrevWhiteSpace = true
                )

            LombokInterfaceFoldingAnnotation.LOMBOK_INTERFACE_SETTER -> {
                method.parameterList.parameters.firstOrNull()?.let {
                    NullableExt.fieldAnnotationExpression(
                        it.annotations,
                        method.returnTypeElement,
                        group = group,
                        foldPrevWhiteSpace = true
                    )
                }
            }
            //TODO: nullable @FindBy
            else -> null
        }
    }

    fun ignoreFoldingParameter(parameter: PsiParameter): Boolean {
        parameter.parentOfTypes(PsiMethod::class)?.takeIf {
            it.body == null
        }?.let { method ->
            method.containingClass?.takeIf {
                it.isInterface
            }?.run {
                getNonSyntheticExpression(method)
                    .asInstance<InterfacePropertiesExpression>()
                    ?.run {
                        return true
                    }
            }
        }
        return false
    }

}
