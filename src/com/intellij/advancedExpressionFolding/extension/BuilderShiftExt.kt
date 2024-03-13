package com.intellij.advancedExpressionFolding.extension


import com.intellij.advancedExpressionFolding.expression.Expression
import com.intellij.advancedExpressionFolding.expression.custom.BuilderShiftExpression
import com.intellij.advancedExpressionFolding.extension.Keys.BUILDER
import com.intellij.advancedExpressionFolding.extension.Keys.isOn
import com.intellij.advancedExpressionFolding.extension.Keys.turnOn
import com.intellij.openapi.util.NullUtils.hasNull
import com.intellij.psi.*
import com.intellij.psi.util.PsiTreeUtil
import com.intellij.psi.util.descendantsOfType

object BuilderShiftExt : BaseExtension() {

    @JvmStatic
    fun markIfBuilder(
        element: PsiMethodCallExpression,
        psiClass: PsiClass
    ) {
        if (fieldShiftOld && psiClass.isBuilder()) {
            element.markBuilder()
        }
    }

    @JvmStatic
    fun createExpression(
        element: PsiMethodCallExpression,
        psiClass: PsiClass?
    ): Expression? {
        if (!fieldShiftOld || !psiClass.isBuilder()) {
            return null
        }

        val methodExpression =
            (element.argumentList.expressions.firstOrNull() as? PsiMethodCallExpression)?.methodExpression

        val getterId = methodExpression?.descendantsOfType<PsiIdentifier>(true)?.last()
        val getterName = methodExpression?.referenceName?.let(PropertyUtil::guessPropertyName)

        val builderSetterName = element.methodExpression.referenceName

        if (getterName == builderSetterName && !hasNull(getterId, methodExpression, builderSetterName)) {
            BUILDER.turnOn(methodExpression!!)
            val dotAndBrackets = -1..2
            val range = getterId!!.textRange + dotAndBrackets
            return BuilderShiftExpression(methodExpression, range, "<<")
        }

        return null
    }

    @JvmStatic
    fun isShifted(element: PsiMethodCallExpression): Boolean {
        if (!fieldShiftOld) {
            return false
        }

        val getterUsedInBuilder = PsiTreeUtil.findFirstParent(element, false) {
            var builder = false
            if (it is PsiMethodCallExpression) {
                builder = it.isMarkedAsBuilder()
            }
            builder || (it !is PsiMethodCallExpression && it !is PsiReferenceExpression && it !is PsiExpressionList)
        }.takeIf {
            it?.isMarkedAsBuilder() ?: false
        }
        return getterUsedInBuilder != null
    }

    private fun PsiElement?.isMarkedAsBuilder(): Boolean = this?.let { BUILDER.isOn(it) } ?: false
    private fun PsiElement.markBuilder() = BUILDER.turnOn(this)
}

