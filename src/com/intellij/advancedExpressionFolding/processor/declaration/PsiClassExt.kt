package com.intellij.advancedExpressionFolding.processor.declaration

import com.intellij.advancedExpressionFolding.expression.Expression
import com.intellij.advancedExpressionFolding.processor.BaseExtension
import com.intellij.advancedExpressionFolding.processor.addIfEnabled
import com.intellij.advancedExpressionFolding.processor.exprList
import com.intellij.advancedExpressionFolding.processor.exprWrap
import com.intellij.advancedExpressionFolding.processor.language.kotlin.MethodDefaultParameterExt
import com.intellij.advancedExpressionFolding.processor.lombok.AnnotationExt
import com.intellij.advancedExpressionFolding.processor.lombok.SummaryParentOverrideExt.addParentSummary
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
