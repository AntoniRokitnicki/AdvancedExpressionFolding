package com.intellij.advancedExpressionFolding.extension

import com.intellij.advancedExpressionFolding.expression.Expression
import com.intellij.advancedExpressionFolding.extension.lombok.AnnotationExt
import com.intellij.advancedExpressionFolding.extension.lombok.MethodDefaultParameterExt
import com.intellij.advancedExpressionFolding.extension.lombok.SummaryParentOverrideExt.addParentSummary
import com.intellij.psi.PsiClass

object PsiClassExt : BaseExtension() {

    enum class ClassType {
        BUILDER,
    }

    @JvmStatic
    fun createExpression(clazz: PsiClass): Expression? {
        val list = exprList()
        list.addIfEnabled(summaryParentOverride) {
            clazz.addParentSummary()
        }
        list.addIfEnabled(methodDefaultParameters) {
            MethodDefaultParameterExt.enhanceMethodsWithDefaultParams(clazz)
        }
        list.addIfEnabled(lombok) {
            AnnotationExt.addClassLevelAnnotations(clazz)
        }
        return list.exprWrap(clazz)
    }

}
