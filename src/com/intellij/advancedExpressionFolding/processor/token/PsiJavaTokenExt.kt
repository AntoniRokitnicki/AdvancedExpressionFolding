package com.intellij.advancedExpressionFolding.processor.token

import com.intellij.advancedExpressionFolding.expression.Expression
import com.intellij.advancedExpressionFolding.processor.expr
import com.intellij.advancedExpressionFolding.processor.takeIfTrue
import com.intellij.advancedExpressionFolding.settings.State
import com.intellij.advancedExpressionFolding.settings.state.IEmojiVisibilityState
import com.intellij.psi.JavaTokenType.*
import com.intellij.psi.PsiClass
import com.intellij.psi.PsiJavaCodeReferenceElement
import com.intellij.psi.PsiJavaToken

object PsiJavaTokenExt : IEmojiVisibilityState by State()() {

    fun createExpression(element: PsiJavaToken): Expression? {
        emojify.takeIfTrue() ?: return null

        val emoji = when (element.tokenType) {
            NULL_KEYWORD -> "🕳️"
            VOID_KEYWORD -> "💀"
            FINAL_KEYWORD -> "🔒"
            TRUE_KEYWORD -> "✅"
            FALSE_KEYWORD -> "❌"
            IMPORT_KEYWORD -> "🚢"
            PACKAGE_KEYWORD -> "📦"
            THROWS_KEYWORD -> "🪃"
            THROW_KEYWORD -> "🪃"
            TRY_KEYWORD -> "🤞"
            CATCH_KEYWORD -> "🎣"
            THIS_KEYWORD -> "📍"
            STATIC_KEYWORD -> "⚡"
            PROTECTED_KEYWORD -> "🛡️"
            ABSTRACT_KEYWORD -> "🎨"
            BREAK_KEYWORD -> "✋"
            CASE_KEYWORD -> "📦"
            CLASS_KEYWORD -> "🏛️"
            DO_KEYWORD -> "▶️"
            ELSE_KEYWORD -> "🔄"
            ENUM_KEYWORD -> "📊"
            //EXTENDS_KEYWORD -> "↔️"
            FINALLY_KEYWORD -> "🏁"
            FOR_KEYWORD -> "🔁"
            WHILE_KEYWORD -> "♾️"
            INSTANCEOF_KEYWORD -> "is"
            INTERFACE_KEYWORD -> "🖥️"
            INT_KEYWORD -> "🔢"
            LONG_KEYWORD -> "📏"
            NATIVE_KEYWORD -> "🏕️"
            //NEW_KEYWORD -> "✨"
            PRIVATE_KEYWORD -> "🚫"
            SUPER_KEYWORD -> "💪"
            SWITCH_KEYWORD -> "🔀"
            TRANSIENT_KEYWORD -> "🚂"
            RETURN_KEYWORD -> "🔙"
            VOLATILE_KEYWORD -> "☢️"
            BYTE_KEYWORD -> "💾"
            FLOAT_KEYWORD -> "🏊"
            DOUBLE_KEYWORD -> "⚖️"
            BOOLEAN_KEYWORD -> "🔘"
            CHAR_KEYWORD -> "🅲"
            REQUIRES_KEYWORD -> "🚧"
            EXPORTS_KEYWORD -> "🚢"
            YIELD_KEYWORD -> "🚸"
            RECORD_KEYWORD -> "📀"
            IDENTIFIER -> run {
                if (!element.textMatches("String")) return@run null

                val reference = element.parent as? PsiJavaCodeReferenceElement ?: return@run null
                val resolved = reference.resolve()
                if (resolved is PsiClass && resolved.qualifiedName == "java.lang.String") {
                    "🪡"
                } else {
                    null
                }
            }
            //IF_KEYWORD -> "🤔"
            //ANDAND -> "🤝"
            //OROR -> "🚪"
            // TODO: String -> "🪡"
            else -> null
        }

        return emoji?.let {
            element.expr(it)
        }
    }
}
