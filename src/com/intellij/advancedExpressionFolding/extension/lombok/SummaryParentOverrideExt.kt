package com.intellij.advancedExpressionFolding.extension.lombok

import com.intellij.advancedExpressionFolding.expression.Expression
import com.intellij.advancedExpressionFolding.extension.*
import com.intellij.psi.PsiClass
import com.intellij.psi.impl.search.JavaAllOverridingMethodsSearcher
import com.intellij.psi.impl.search.MethodSuperSearcher

object SummaryParentOverrideExt : BaseExtension() {

    fun PsiClass.addParentSummary(): Expression? {
        summaryParentOverride.on() ?: return null
        val className = this.name ?: return null

        sequenceOf(this.extendsList, this.implementsList).filterNotNull().mapNotNull { parent ->
            val a = parent.referencedTypes
            parent.referenceElements.mapNotNull {
                //TODO: not first but matching, maybe use zip here?
                val c= a.first().resolve()
                //TODO: use JavaAllOverridingMethodsSearcher or something else to find all methods overridden or implemented(in case of interfaces) in current class(stored in this) that are taken from c or its children and print it out here
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


