package com.intellij.advancedExpressionFolding.processor.cache

import com.intellij.advancedExpressionFolding.expression.Expression
import com.intellij.advancedExpressionFolding.processor.core.BuildExpressionExt.buildExpression
import com.intellij.advancedExpressionFolding.settings.StateDelegate
import com.intellij.openapi.editor.Document
import com.intellij.psi.PsiElement
import org.jetbrains.annotations.Contract

object CacheExt : StateDelegate() {

    fun PsiElement.invalidateExpired(document: Document, synthetic: Boolean): Boolean {
        val versionKey = Keys.getVersionKey(synthetic)
        val lastVersion = getUserData(versionKey)
        val currentStamp = document.modificationStamp
        val expired = lastVersion != null && lastVersion != currentStamp
        if (expired) {
            Keys.clearAllOnExpire(this)
        }
        if (lastVersion != currentStamp) {
            putUserData(versionKey, currentStamp)
        }
        return expired
    }

    // method is run from different threads
    @JvmStatic
    @Contract("_, _, true -> !null")
    fun getExpression(element: PsiElement, document: Document, synthetic: Boolean): Expression? {
            val key = Keys.getKey(synthetic)
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
