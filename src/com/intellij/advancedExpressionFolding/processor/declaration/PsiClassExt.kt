package com.intellij.advancedExpressionFolding.processor.declaration

import com.intellij.advancedExpressionFolding.expression.Expression
import com.intellij.advancedExpressionFolding.processor.addIfEnabled
import com.intellij.advancedExpressionFolding.processor.exprList
import com.intellij.advancedExpressionFolding.processor.exprWrap
import com.intellij.advancedExpressionFolding.processor.language.kotlin.MethodDefaultParameterExt
import com.intellij.advancedExpressionFolding.processor.lombok.AnnotationExt
import com.intellij.advancedExpressionFolding.processor.lombok.LombokPostConstructorExt
import com.intellij.advancedExpressionFolding.processor.lombok.SummaryParentOverrideExt.addParentSummary
import com.intellij.advancedExpressionFolding.settings.State
import com.intellij.advancedExpressionFolding.settings.state.IHidingSuppressionState
import com.intellij.advancedExpressionFolding.settings.state.IKotlinLanguageState
import com.intellij.advancedExpressionFolding.settings.state.ILombokState
import com.intellij.psi.PsiClass

object PsiClassExt :
    IHidingSuppressionState by State()(),
    IKotlinLanguageState by State()(),
    ILombokState by State()() {

    enum class ClassType {
        BUILDER,
    }

    fun createExpression(clazz: PsiClass): Expression? {
        val list = exprList()
        list.addIfEnabled(summaryParentOverride) {
            clazz.addParentSummary()
        }
        list.addIfEnabled(methodDefaultParameters) {
            MethodDefaultParameterExt.enhanceMethodsWithDefaultParams(clazz)
        }
        list.addIfEnabled(lombok) {
            LombokPostConstructorExt.prepare(clazz)
            AnnotationExt.addClassLevelAnnotations(clazz)
        }
        return list.exprWrap(clazz)
    }

}
