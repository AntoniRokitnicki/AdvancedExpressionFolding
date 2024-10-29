package com.intellij.advancedExpressionFolding.extension.lombok

import com.intellij.advancedExpressionFolding.expression.Expression
import com.intellij.advancedExpressionFolding.extension.*
import com.intellij.psi.PsiClass

object SummaryParentOverrideExt : BaseExtension() {

    fun PsiClass.addParentSummary(): Expression? {
        summaryParentOverride.on() ?: return null
        //val className = this.name ?: return null

        sequenceOf(this.extendsList, this.implementsList).filterNotNull().mapNotNull { parent ->
            val a = parent.referencedTypes
            parent.referenceElements.mapNotNull {
                it?.referenceNameElement
            }
        }.flatten().toList().takeIf {
            it.isNotEmpty()
        }?.mapNotNull {
            it.exprOnLastChar("BlaBlaBla")
        }?.let {
            return it.exprWrap(this)
        }
        return null

    }
}


