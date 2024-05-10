package com.intellij.advancedExpressionFolding.extension

import com.intellij.advancedExpressionFolding.expression.Expression
import com.intellij.advancedExpressionFolding.extension.BaseExtension.Companion.isBoolean
import com.intellij.advancedExpressionFolding.extension.BaseExtension.Companion.isInt
import com.intellij.advancedExpressionFolding.extension.BaseExtension.Companion.isObject
import com.intellij.advancedExpressionFolding.extension.BaseExtension.Companion.isString
import com.intellij.advancedExpressionFolding.extension.BaseExtension.Companion.isVoid
import com.intellij.advancedExpressionFolding.extension.Keys.IGNORED
import com.intellij.openapi.editor.FoldingGroup
import com.intellij.openapi.util.TextRange
import com.intellij.psi.*
import com.intellij.psi.search.LocalSearchScope
import com.intellij.psi.search.searches.ReferencesSearch
import java.util.*
import kotlin.reflect.KClass

inline fun String.filter(predicate: (String) -> Boolean): String? = takeIf(predicate)

fun PsiElement.isIgnored(): Boolean = getUserData(IGNORED) ?: false
fun PsiElement.markIgnored() = putUserData(IGNORED, true)

operator fun TextRange.plus(string: String): TextRange =
    TextRange.create(startOffset+string.length, endOffset+string.length)
operator fun TextRange.plus(addon: IntRange): TextRange =
    TextRange.create(startOffset + addon.first, endOffset + addon.last)
fun PsiElement.start(): Int = textRange.startOffset
fun PsiElement.end(): Int = textRange.endOffset

fun PsiExpressionList.filterOutWhiteSpaceAndTokens() = children.filter {
    !it.isIgnorable()
}

fun PsiElement.isIgnorable() = this is PsiJavaToken || isWhitespace()

fun PsiElement.isWhitespace() = this is PsiWhiteSpace

fun PsiElement.realNextSibling(): PsiElement? {
    var sibling = nextSibling
    while (sibling != null && sibling.isIgnorable()) {
        sibling = sibling.nextSibling
    }
    return sibling
}


fun PsiModifierListOwner.isNotStatic() = !hasModifierProperty(PsiModifier.STATIC)
fun PsiModifierListOwner.isNotFinal() = !hasModifierProperty(PsiModifier.FINAL)

fun PsiMethod.isSetterOrBuilder(): Boolean = isSetter() || isBuilder()

fun PsiMethod.isSetter(): Boolean {
    fun isSetter(text: String) = text.startsWith("set") && text.length > 3 && Character.isUpperCase(text[3])
    return parameterList.parametersCount == 1 && returnType.isVoid() && isSetter(name)
}

fun PsiMethod.isGetter(): Boolean {
    fun isGetterAux(name: String, prefix: String) =
        name.startsWith(prefix) && name.length > prefix.length && Character.isUpperCase(name[prefix.length])

    fun isGetter(name: String) = isGetterAux(name, "get") || isGetterAux(name, "is")

    return parameterList.parametersCount == 0 && !returnType.isVoid() && (isGetter(name) || containingClass?.isRecord == true)
}

fun PsiMethod.isToString() = name == "toString"
        && returnType.isString()
        && !hasParameters()

fun PsiMethod.isEquals() = name == "equals"
        && returnType.isBoolean()
        && parameterList.parametersCount == 1
        && parameterList.parameters[0].type.isObject()

fun PsiMethod.isHashCode() = name == "hashCode"
        && returnType.isInt()
        && !hasParameters()

fun PsiClass?.isBuilder() : Boolean {
    if (this == null) {
        return false
    }
    val userData = getClassType()
    if (userData == null) {
        allMethods.forEach {
            if (it.name == "build") {
                setClassType(PsiClassExt.ClassType.BUILDER)
                return true
            }
        }
    }
    return userData == PsiClassExt.ClassType.BUILDER
}
fun PsiElement.setClassType(type: PsiClassExt.ClassType) {
    putUserData(Keys.CLASS_TYPE_KEY, type)
    putCopyableUserData(Keys.CLASS_TYPE_KEY, type)
}
fun PsiElement.getClassType() : PsiClassExt.ClassType? = getUserData(Keys.CLASS_TYPE_KEY)

fun PsiElement.findLocalReference(element: PsiElement): PsiReference? = ReferencesSearch.search(this, LocalSearchScope(element)).findFirst()

fun KClass<*>.group(): FoldingGroup = FoldingGroup.newGroup(qualifiedName)

fun PsiField.setProperty(getter: PsiMethod?,setter: PsiMethod?) {
    getter?.let {
        putUserData(Keys.GETTER_KEY, it)
    }
    setter?.let {
        putUserData(Keys.SETTER_KEY, it)
    }
}
fun PsiField.getter(): PsiMethod? = getUserData(Keys.GETTER_KEY)
fun PsiField.setter(): PsiMethod? = getUserData(Keys.SETTER_KEY)

fun PsiMethod.isBuilder(): Boolean = containingClass?.isBuilder() == true

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
                @Suppress("UNCHECKED_CAST")
                parent as? T
            } else {
                findParents(parentClass, *classQueue.toTypedArray())
            }
        }
        parent = parent.parent
    }
    return null
}

fun IntRange.toTextRange(): TextRange = TextRange(this.first, this.last)

inline fun <reified T> Any?.asInstance(): T? = if (T::class.isInstance(this)) {
    this as T
} else {
    null
}


fun <T> Array<T>.firstOrNullIfNotEmpty(): T? {
    return if (isEmpty() || size > 1) {
        null
    } else {
        first()
    }
}


fun Array<out PsiElement>.asInstance(vararg elements: Class<out PsiElement>): Array<out PsiElement>? {
    if (elements.size != this.size) {
        return null
    }
    val classQueue = LinkedList(elements.asList())
    forEach {
        val next = classQueue.poll()
        if (!next.isInstance(it)) {
            return null
        }
    }
    return this
}


fun String.equalsIgnoreSpaces(second: String): Boolean = filterNot(Char::isWhitespace) == second.filterNot(Char::isWhitespace)


fun Boolean.asNull() : Any? = if (this) {
    true
} else {
    null
}

fun exprList() = mutableListOf<Expression?>()