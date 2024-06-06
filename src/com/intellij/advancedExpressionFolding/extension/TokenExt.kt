package com.intellij.advancedExpressionFolding.extension

import com.intellij.advancedExpressionFolding.expression.Expression
import com.intellij.advancedExpressionFolding.extension.Consts.Emoji.*
import com.intellij.psi.JavaTokenType.*
import com.intellij.psi.PsiJavaToken

object TokenExt : BaseExtension() {

    @JvmStatic
    fun createExpression(element: PsiJavaToken): Expression? {
        emojify.on() ?: return null

        val emoji = when (element.tokenType) {
            NULL_KEYWORD -> NULL_HOLE
            VOID_KEYWORD -> VOID_SKULL
            FINAL_KEYWORD -> FINAL_LOCK
            TRUE_KEYWORD -> TRUE_CHECK_MARK
            FALSE_KEYWORD -> FALSE_CROSS_MARK
            IMPORT_KEYWORD -> IMPORT_SHIP
            PACKAGE_KEYWORD -> PACKAGE_PACKAGE
            THROWS_KEYWORD -> THROWS_BOOMERANG
            THROW_KEYWORD -> THROWS_BOOMERANG
            TRY_KEYWORD -> TRY_CROSSED_FINGERS
            CATCH_KEYWORD -> CATCH_FISHING_POLE
            THIS_KEYWORD -> THIS_ROUND_PUSHPIN
            STATIC_KEYWORD -> STATIC_HIGH_VOLTAGE
            PROTECTED_KEYWORD -> PROTECTED_SHIELD
            else -> null
        }

        return emoji?.toString()?.let {
            element.expr(it)
        }
    }

}
