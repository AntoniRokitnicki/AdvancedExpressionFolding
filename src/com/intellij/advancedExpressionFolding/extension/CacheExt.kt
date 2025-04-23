package com.intellij.advancedExpressionFolding.extension

import com.intellij.advancedExpressionFolding.AdvancedExpressionFoldingSettings
import com.intellij.advancedExpressionFolding.expression.Expression
import com.intellij.advancedExpressionFolding.extension.BuildExpressionExt.buildExpression
import com.intellij.advancedExpressionFolding.extension.Keys.getKey
import com.intellij.advancedExpressionFolding.extension.Keys.getVersionKey
import com.intellij.openapi.editor.Document
import com.intellij.psi.PsiElement
import org.jetbrains.annotations.Contract

object CacheExt : AdvancedExpressionFoldingSettings.StateDelegate() {

    fun PsiElement.invalidateExpired(document: Document, synthetic: Boolean): Boolean {
        val versionKey = getVersionKey(synthetic)
        val lastVersion = getUserData(versionKey)
        val hashCode = document.text.hashCode()
        val expired = lastVersion != hashCode
        if (expired) {
            Keys.clearAllOnExpire(this)
            putUserData(versionKey, hashCode)
        }
        return expired
    }

    // method is run from different threads
    @JvmStatic
    @Contract("_, _, true -> !null")
    fun getExpression(element: PsiElement, document: Document, synthetic: Boolean): Expression? {
            val key = getKey(synthetic)
            val cachedExpression = if (element.invalidateExpired(document, synthetic)) {
                null
            } else {
                element.getUserData(key)
            }
            return when (cachedExpression) {
                null -> {
                    val newExpression = buildExpression(element, document, synthetic)
                    element.putUserData(key, newExpression.ofNullable())
                    newExpression
                }
                else -> cachedExpression.getOrNull()
            }
    }

    private val NULL_OBJECT: Expression = object : Expression() {
        override fun toString(): String = "NULL_OBJECT"
    }
    private fun Expression?.ofNullable(): Expression = this ?: NULL_OBJECT
    private fun Expression?.getOrNull(): Expression? = when {
        this === NULL_OBJECT -> null
        else -> this
    }

}