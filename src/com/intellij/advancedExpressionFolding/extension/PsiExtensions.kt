package com.intellij.advancedExpressionFolding.extension

import com.intellij.advancedExpressionFolding.PropertyUtil
import com.intellij.advancedExpressionFolding.extension.Keys.IGNORED
import com.intellij.openapi.util.TextRange
import com.intellij.psi.*
import java.util.*

inline fun String.filter(predicate: (String) -> Boolean): String? = takeIf(predicate)

fun PsiElement.isIgnored(): Boolean = getUserData(IGNORED) ?: false
fun PsiElement.markIgnored() = putUserData(IGNORED, true)

operator fun TextRange.plus(string: String): TextRange =
    TextRange.create(startOffset+string.length, endOffset+string.length)
operator fun TextRange.plus(addon: IntRange): TextRange =
    TextRange.create(startOffset + addon.first, endOffset + addon.last)
operator fun TextRange.minus(addon: IntRange): TextRange =
    TextRange.create(startOffset - addon.first, endOffset - addon.last)
fun PsiElement.start(): Int = textRange.startOffset
fun PsiElement.end(): Int = textRange.endOffset

fun PsiField.isStatic(): Boolean = modifierList?.hasModifierProperty(PsiModifier.STATIC) == true

fun PsiMethod.isSetter(): Boolean {
    fun isSetter(text: String) = text.startsWith("set") && text.length > 3 && Character.isUpperCase(text[3])
    return parameterList.parametersCount == 1 && returnType == PsiType.VOID && isSetter(name)
}

fun PsiMethod.isGetter(): Boolean {
    fun isGetterAux(name: String, prefix: String) =
        name.startsWith(prefix) && name.length > prefix.length && Character.isUpperCase(name[prefix.length])

    fun isGetter(name: String) = isGetterAux(name, "get") || isGetterAux(name, "is")

    return parameterList.parametersCount == 0 && returnType != PsiType.VOID && isGetter(name)
}

fun PsiMethod.isGetterOrSetter(): Boolean = isSetter() || isGetter()

fun PsiMethod.guessPropertyName(): String = PropertyUtil.guessPropertyName(name)

fun <T : PsiElement> PsiElement.findParents(
    parentClass: Class<T>,
    vararg parents: Class<out PsiElement>
): T? {
    val classQueue = LinkedList(parents.asList())
    val next = classQueue.poll()
    var parent = this.parent

    while (parent != null) {
        if (next != null && next.isInstance(parent)) {
            return if (classQueue.isEmpty()) {
                parent as? T
            } else {
                findParents(parentClass, *classQueue.toTypedArray())
            }
        }
        parent = parent.parent
    }
    return null
}
