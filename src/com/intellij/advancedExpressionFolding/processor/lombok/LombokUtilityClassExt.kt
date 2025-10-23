package com.intellij.advancedExpressionFolding.processor.lombok

import com.intellij.advancedExpressionFolding.processor.*
import com.intellij.advancedExpressionFolding.processor.core.BaseExtension
import com.intellij.advancedExpressionFolding.processor.lombok.AnnotationExt.ClassLevelAnnotation
import com.intellij.advancedExpressionFolding.processor.lombok.LombokFoldingAnnotation.LOMBOK_UTILITY_CLASS
import com.intellij.psi.PsiClass
import com.intellij.psi.PsiKeyword
import com.intellij.psi.PsiModifierListOwner
import com.intellij.psi.JavaTokenType

object LombokUtilityClassExt : BaseExtension() {

    fun foldUtilityClass(psiClass: PsiClass): List<ClassLevelAnnotation> {
        if (!psiClass.isFinal()) {
            return emptyList()
        }
        val nonStaticField = psiClass.fields.firstOrNull { !it.isExplicitlyStatic() }
        if (nonStaticField != null) {
            return emptyList()
        }
        val nonStaticMethod = psiClass.methods.firstOrNull { !it.isConstructor && !it.isExplicitlyStatic() }
        if (nonStaticMethod != null) {
            return emptyList()
        }
        val constructor = psiClass.constructors.singleOrNull() ?: return emptyList()
        if (!constructor.isPrivate() || constructor.parameterList.parametersCount != 0) {
            return emptyList()
        }
        if (!MethodBodyInspector.isUtilityClassConstructor(constructor)) {
            return emptyList()
        }
        constructor.markIgnored()
        return listOf(ClassLevelAnnotation(LOMBOK_UTILITY_CLASS, listOf(constructor)))
    }
    private fun PsiModifierListOwner.isExplicitlyStatic(): Boolean {
        if (isStatic()) {
            return true
        }
        val modifiers = modifierList ?: return false
        return modifiers.children.filterIsInstance<PsiKeyword>().any {
            it.tokenType == JavaTokenType.STATIC_KEYWORD
        }
    }
}
