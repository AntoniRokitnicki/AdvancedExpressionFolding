package com.intellij.advancedExpressionFolding.extension.lombok

import com.intellij.advancedExpressionFolding.extension.GenericCallback
import com.intellij.advancedExpressionFolding.extension.PsiClassExt.ClassLevelAnnotation
import com.intellij.advancedExpressionFolding.extension.isInterfaceDefault
import com.intellij.advancedExpressionFolding.extension.lombok.LombokExt.findMethodType
import com.intellij.advancedExpressionFolding.extension.lombok.MethodType.GETTER
import com.intellij.advancedExpressionFolding.extension.methodsNotStatic
import com.intellij.openapi.util.Key
import com.intellij.psi.PsiClass
import com.intellij.psi.PsiMethod

data class MethodLevelAnnotation(
    val classAnnotation: MethodType, //TODO: custom enum?
    val method: PsiMethod,
)

object LombokMethodExt : GenericCallback<PsiMethod, List<MethodLevelAnnotation>> {
    override val callbackKey: Key<() -> List<MethodLevelAnnotation>> by lazy {
        Key.create("lombok-method-callback")
    }

    fun PsiClass.interfaceSupport(): List<ClassLevelAnnotation>? {
        val k = methods
        val methodsNotStatic = methodsNotStatic
        val filterNot = methodsNotStatic.filterNot {
            isInterfaceDefault()
        }
        val r = filterNot.mapNotNull { method ->
            method.findMethodType().takeIf {
                it == GETTER
                //TODO: || it == SETTER
            }?.let { type ->
                method to type
                initCallback(method, listOf(MethodLevelAnnotation(type, method)))

            }
        }
        //TODO: dont join getter and setter, since method references are needed

        return null
    }
}