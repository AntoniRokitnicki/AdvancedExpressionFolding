package com.intellij.advancedExpressionFolding.extension.lombok

import com.intellij.advancedExpressionFolding.extension.GenericCallback
import com.intellij.advancedExpressionFolding.extension.PsiClassExt.ClassLevelAnnotation
import com.intellij.advancedExpressionFolding.extension.isInterfaceDefault
import com.intellij.advancedExpressionFolding.extension.lombok.LombokExt.findMethodType
import com.intellij.advancedExpressionFolding.extension.lombok.LombokInterfaceFoldingAnnotation.*
import com.intellij.advancedExpressionFolding.extension.lombok.MethodType.*
import com.intellij.advancedExpressionFolding.extension.methodsNotStatic
import com.intellij.openapi.util.Key
import com.intellij.psi.PsiClass
import com.intellij.psi.PsiMethod

enum class LombokInterfaceFoldingAnnotation(val annotation: String) {
    LOMBOK_INTERFACE_GETTER("@Getter"),
    LOMBOK_INTERFACE_SETTER("@Setter"),
    LOMBOK_INTERFACE_FINDER("@Finder"),
}

data class MethodLevelAnnotation(
    val methodAnnotation: LombokInterfaceFoldingAnnotation,
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
                it == GETTER || it == SETTER || it == FINDER
            }?.let { type ->
                val e = when (type) {
                    GETTER -> LOMBOK_INTERFACE_GETTER
                    SETTER -> LOMBOK_INTERFACE_SETTER
                    FINDER -> LOMBOK_INTERFACE_FINDER
                    else -> null
                }
                //TODO: better enum conversion, maybe a custom type?
                initCallback(method, listOf(MethodLevelAnnotation(e!!)))
            }
        }
        //TODO: dont join getter and setter, since method references are needed

        return null
    }

    fun PsiMethod.isFinder() = name.startsWith("find") && name.length > "find".length
            && parameterList.parameters.size == 1 && name.contains("By")
}