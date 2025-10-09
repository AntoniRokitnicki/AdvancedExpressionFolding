package com.intellij.advancedExpressionFolding.processor.cache

import com.intellij.advancedExpressionFolding.expression.Expression
import com.intellij.advancedExpressionFolding.processor.declaration.PsiClassExt
import com.intellij.lang.folding.FoldingDescriptor
import com.intellij.openapi.util.Key
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiField
import com.intellij.psi.PsiMethod
import com.intellij.psi.util.MethodSignature

private const val PREFIX = "AEF-"

enum class Keys(
    suffix: String,
    factory: (String) -> Key<*> = { Key<Any?>(it) }
) {
    BUILDER("builder"),
    CLASS_TYPE_KEY("classType"),
    FIELD_KEY("field"),
    FIELD_META_DATA_KEY("field-metadata"),
    IGNORED("ignored"),
    SYNTHETIC_KEY("syn", { Key.create<Any?>(it) }),
    NOT_SYNTHETIC_KEY("!syn", { Key.create<Any?>(it) }),
    VERSION_SYNTHETIC_KEY("ver-syn", { Key.create<Any?>(it) }),
    VERSION_NOT_SYNTHETIC_KEY("ver-!syn", { Key.create<Any?>(it) }),
    FULL_CACHE("-full", { Key.create<Any?>(it) }),
    METHOD_TO_PARENT_CLASS_KEY("methodToParentClass");

    val key: Key<*> = factory(PREFIX + suffix)

    data class FieldMetaData(
        var getter: PsiMethod? = null,
        var setter: PsiMethod? = null,
    )

    companion object {
        @Suppress("UNCHECKED_CAST")
        val ignoredKey: Key<Boolean> = IGNORED.key as Key<Boolean>

        fun clearAllOnExpire(psiElement: PsiElement) = clearAll(psiElement)

        fun clearAll(psiElement: PsiElement) {
            entries.forEach { psiElement.putUserData(it.key, null) }
        }

        @Suppress("UNCHECKED_CAST")
        fun getVersionKey(synthetic: Boolean): Key<Int> = when {
            synthetic -> VERSION_SYNTHETIC_KEY.key as Key<Int>
            else -> VERSION_NOT_SYNTHETIC_KEY.key as Key<Int>
        }

        @Suppress("UNCHECKED_CAST")
        fun getKey(synthetic: Boolean): Key<Expression> = when {
            synthetic -> SYNTHETIC_KEY.key as Key<Expression>
            else -> NOT_SYNTHETIC_KEY.key as Key<Expression>
        }
    }
}

fun Key<Boolean>.isOn(element: PsiElement): Boolean = element.getUserData(this) ?: false
fun Key<Boolean>.turnOn(element: PsiElement) = element.putUserData(this, true)

