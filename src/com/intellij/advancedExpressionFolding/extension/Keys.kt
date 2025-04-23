package com.intellij.advancedExpressionFolding.extension

import com.intellij.advancedExpressionFolding.expression.Expression
import com.intellij.lang.folding.FoldingDescriptor
import com.intellij.openapi.util.Key
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiField
import com.intellij.psi.PsiMethod
import com.intellij.psi.util.MethodSignature

object Keys {
    private const val PREFIX = "AEF-"
    val BUILDER = Key<Boolean>("${PREFIX}builder")
    val CLASS_TYPE_KEY = Key<PsiClassExt.ClassType>("${PREFIX}classType")

    val FIELD_KEY = Key<PsiField>("${PREFIX}field")
    val FIELD_META_DATA_KEY = Key<FieldMetaData>("${PREFIX}field-metadata")
    data class FieldMetaData(
        var getter: PsiMethod? = null,
        var setter: PsiMethod? = null,
    )

    val IGNORED = Key<Boolean>("${PREFIX}ignored")

    private val SYNTHETIC_KEY: Key<Expression> = Key.create("${PREFIX}syn")
    private val NOT_SYNTHETIC_KEY: Key<Expression> = Key.create("${PREFIX}!syn")
    private val VERSION_SYNTHETIC_KEY: Key<Int> = Key.create("${PREFIX}ver-syn")
    private val VERSION_NOT_SYNTHETIC_KEY: Key<Int> = Key.create("${PREFIX}ver-!syn")

    val FULL_CACHE: Key<Array<FoldingDescriptor>> = Key.create("${PREFIX}-full")

    val METHOD_TO_PARENT_CLASS_KEY = Key<MutableMap<MethodSignature, String>>("${PREFIX}methodToParentClass")

    //TODO: convert Keys to enum
    private val values: Set<Key<*>> by lazy {
        setOf(
            BUILDER,
            CLASS_TYPE_KEY,
            FIELD_META_DATA_KEY,
            IGNORED,
            SYNTHETIC_KEY,
            NOT_SYNTHETIC_KEY,
            VERSION_SYNTHETIC_KEY,
            VERSION_NOT_SYNTHETIC_KEY,
            FIELD_KEY,
            FULL_CACHE,
        )
    }
    fun clearAllOnExpire(psiElement: PsiElement) {
        values.forEach {
            psiElement.putUserData(it, null)
        }
    }
    fun clearAll(psiElement: PsiElement) {
        values.forEach {
            psiElement.putUserData(it, null)
        }
    }
    fun getVersionKey(synthetic: Boolean): Key<Int> {
        return when {
            synthetic -> VERSION_SYNTHETIC_KEY
            else -> VERSION_NOT_SYNTHETIC_KEY
        }
    }
    fun getKey(synthetic: Boolean): Key<Expression> {
        return when {
            synthetic -> SYNTHETIC_KEY
            else -> NOT_SYNTHETIC_KEY
        }
    }
    fun Key<Boolean>.isOn(element: PsiElement) : Boolean = element.getUserData(this) ?: false
    fun Key<Boolean>.turnOn(element: PsiElement)  = element.putUserData(this, true)

}