package com.intellij.advancedExpressionFolding.processor.cache

import com.intellij.advancedExpressionFolding.expression.Expression
import com.intellij.advancedExpressionFolding.processor.core.BuildExpressionExt.buildExpression
import com.intellij.advancedExpressionFolding.settings.StateDelegate
import com.intellij.openapi.editor.Document
import com.intellij.psi.PsiElement
import org.jetbrains.annotations.Contract

object CacheExt : StateDelegate() {

    context(doc: Document, isSynthetic: Boolean)
    fun PsiElement.invalidateExpired(): Boolean {
        val element = this
        val versionKey = Keys.getVersionKey(isSynthetic)
        val lastVersion = element.getUserData(versionKey)
        val hashCode = doc.text.hashCode()
        val expired = lastVersion != hashCode
        if (expired) {
            Keys.clearAllOnExpire(element)
            element.putUserData(versionKey, hashCode)
        }
        return expired
    }

    fun PsiElement.invalidateExpired(document: Document, synthetic: Boolean): Boolean =
        with(document) {
            with(synthetic) {
                invalidateExpired()
            }
        }

    // method is run from different threads
    @JvmStatic
    @Contract("_, _, true -> !null")
    fun getExpression(element: PsiElement, document: Document, synthetic: Boolean): Expression? =
        with(document) {
            with(synthetic) {
                getExpression(element)
            }
        }

    context(doc: Document, isSynthetic: Boolean)
    fun getExpression(element: PsiElement): Expression? {
        val key = Keys.getKey(isSynthetic)
        val cachedExpression = if (element.invalidateExpired()) {
            null
        } else {
            element.getUserData(key)
        }
        return when (cachedExpression) {
            null -> {
                val newExpression = buildExpression(element, doc, isSynthetic)
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
