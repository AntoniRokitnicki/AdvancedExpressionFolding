package com.intellij.advancedExpressionFolding.processor.cache

import com.intellij.advancedExpressionFolding.expression.Expression
import com.intellij.advancedExpressionFolding.processor.declaration.PsiClassExt
import com.intellij.lang.folding.FoldingDescriptor
import com.intellij.openapi.util.Key
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiField
import com.intellij.psi.PsiMethod
import com.intellij.psi.util.MethodSignature
import org.jetbrains.annotations.TestOnly
import java.lang.reflect.Modifier

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
    private val VERSION_SYNTHETIC_KEY: Key<Long> = Key.create("${PREFIX}ver-syn")
    private val VERSION_NOT_SYNTHETIC_KEY: Key<Long> = Key.create("${PREFIX}ver-!syn")

    val FULL_CACHE: Key<Array<FoldingDescriptor>> = Key.create("${PREFIX}-full")

    val METHOD_TO_PARENT_CLASS_KEY = Key<MutableMap<MethodSignature, String>>("${PREFIX}methodToParentClass")

    private val values: Set<Key<*>> by lazy {
        Keys::class.java.declaredFields
            .asSequence()
            .filter { Modifier.isStatic(it.modifiers) }
            .filter { Key::class.java.isAssignableFrom(it.type) }
            .mapNotNull { field ->
                field.isAccessible = true
                field.get(null) as? Key<*>
            }
            .toSet()
    }

    @TestOnly
    internal val allKeys: Set<Key<*>> = values

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
    fun getVersionKey(synthetic: Boolean): Key<Long> {
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
