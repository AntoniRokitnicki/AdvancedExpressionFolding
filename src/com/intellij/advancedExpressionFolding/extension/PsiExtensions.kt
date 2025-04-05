@file:Suppress("unused")

package com.intellij.advancedExpressionFolding.extension

import com.intellij.advancedExpressionFolding.expression.Expression
import com.intellij.advancedExpressionFolding.expression.custom.HideExpression
import com.intellij.advancedExpressionFolding.expression.custom.SimpleExpression
import com.intellij.advancedExpressionFolding.expression.custom.WrapAroundExpression
import com.intellij.advancedExpressionFolding.expression.custom.WrapperExpression
import com.intellij.advancedExpressionFolding.extension.BaseExtension.Companion.isBoolean
import com.intellij.advancedExpressionFolding.extension.BaseExtension.Companion.isInt
import com.intellij.advancedExpressionFolding.extension.BaseExtension.Companion.isObject
import com.intellij.advancedExpressionFolding.extension.BaseExtension.Companion.isString
import com.intellij.advancedExpressionFolding.extension.BaseExtension.Companion.isVoid
import com.intellij.advancedExpressionFolding.extension.EModifier.*
import com.intellij.advancedExpressionFolding.extension.Keys.IGNORED
import com.intellij.lang.folding.FoldingDescriptor
import com.intellij.openapi.editor.FoldingGroup
import com.intellij.openapi.project.Project
import com.intellij.openapi.util.TextRange
import com.intellij.openapi.vfs.VirtualFile
import com.intellij.psi.*
import com.intellij.psi.impl.source.PsiClassReferenceType
import com.intellij.psi.search.LocalSearchScope
import com.intellij.psi.search.searches.ReferencesSearch
import com.intellij.psi.util.MethodSignature
import java.util.*
import kotlin.reflect.KClass

val PsiField.enum: Boolean
    get() = (type as? PsiClassType)?.resolve()?.isEnum == true
val PsiField.typeResolved: PsiClass?
    get() = type.typeResolved
val PsiType.typeResolved: PsiClass?
    get() = asInstance<PsiClassReferenceType>()?.resolve()

val PsiField.singletonField: Boolean
    get() = type.asInstance<PsiClassReferenceType>()?.resolve() == containingClass
val PsiField.initializerType: PsiClass?
    get() = initializer.asInstance<PsiReferenceExpression>()?.qualifierExpression.asInstance<PsiReferenceExpression>()
        ?.resolve().asInstance<PsiClass>()

inline fun PsiElement.textRangeChar(positionMethod: PsiElement.() -> Int, startOffset: Int, endOffset: Int): TextRange {
    val position = positionMethod()
    return TextRange(position + startOffset, position + endOffset)
}

val PsiElement.textRangeLastChar: TextRange
    get() = textRangeChar(PsiElement::end, -1, 0)
val PsiElement.textRangeFirstChar: TextRange
    get() = textRangeChar(PsiElement::start, 0, 1)

inline fun String.filter(predicate: (String) -> Boolean): String? = takeIf(predicate)

fun PsiElement.isIgnored(): Boolean = getUserData(IGNORED) ?: false
fun PsiElement.markIgnored(value: Boolean = true) = putUserData(IGNORED, value)


fun Pair<Int, Int>.toTextRange() = TextRange(first, second)
fun IntRange.toTextRange() = TextRange(this.first, this.last)
operator fun TextRange.plus(string: String): TextRange =
    TextRange.create(startOffset + string.length, endOffset + string.length)

operator fun TextRange.plus(addon: IntRange): TextRange =
    TextRange.create(startOffset + addon.first, endOffset + addon.last)

fun PsiElement.start(): Int = textRange.startOffset
fun PsiElement.end(): Int = textRange.endOffset

fun PsiExpressionList.filterOutWhiteSpaceAndTokens() = children.filter {
    !it.isIgnorable()
}

fun PsiElement.isIgnorable() = this is PsiJavaToken || isWhitespace()

fun PsiElement.isWhitespace() = this is PsiWhiteSpace

val PsiElement.prevRealSibling: PsiElement?
    get() {
        return generateSequence(this.prevSibling) { it.prevSibling }
            .firstOrNull { it !is PsiWhiteSpace }
    }

fun PsiElement.realNextSibling(): PsiElement? {
    var sibling = nextSibling
    while (sibling != null && sibling.isIgnorable()) {
        sibling = sibling.nextSibling
    }
    return sibling
}

enum class EModifier(val modifier: String) {
    PUBLIC("public"),
    PRIVATE("private"),
    PROTECTED("protected"),
    DEFAULT("default");

    fun isPublic() = this == PUBLIC
    fun isProtected() = this == PROTECTED
    fun isPrivate() = this == PRIVATE
    fun isDefault() = this == DEFAULT
}

fun PsiModifierListOwner.modifier(): EModifier {
    return when {
        isPublic() -> PUBLIC
        isPrivate() -> PRIVATE
        isProtected() -> PROTECTED
        else -> DEFAULT
    }
}

fun PsiModifierListOwner.isPublic() = hasModifierProperty(PsiModifier.PUBLIC)
fun PsiModifierListOwner.isProtected() = hasModifierProperty(PsiModifier.PROTECTED)
fun PsiModifierListOwner.isPrivate() = hasModifierProperty(PsiModifier.PRIVATE)
fun PsiModifierListOwner.isDefault() = !isPublic() && !isProtected() && !isPrivate()
fun PsiModifierListOwner.isStatic() = hasModifierProperty(PsiModifier.STATIC)
fun PsiModifierListOwner.isFinal() = hasModifierProperty(PsiModifier.FINAL)

//TODO: look up why
@Deprecated("doesn't work", ReplaceWith("body == null"))
fun PsiModifierListOwner.isInterfaceDefault() = hasModifierProperty(PsiModifier.DEFAULT)

fun PsiModifierListOwner.isNotStatic() = !isStatic()
fun PsiModifierListOwner.isNotFinal() = !isFinal()

fun PsiKeyword.isPrivate() = tokenType == JavaTokenType.PRIVATE_KEYWORD
fun PsiKeyword.isProtected() = tokenType == JavaTokenType.PROTECTED_KEYWORD
fun PsiKeyword.isPublic() = tokenType == JavaTokenType.PUBLIC_KEYWORD


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

fun PsiClass?.isBuilder(): Boolean {
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

val PsiClass.fieldsNotStatic: Sequence<PsiField>
    get() = fields.asSequence().filter {
        it.isNotStatic()
    }
val PsiClass.methodsNotStatic: Sequence<PsiMethod>
    get() = methods.asSequence().filter {
        it.isNotStatic()
    }

fun PsiElement.setClassType(type: PsiClassExt.ClassType) {
    putUserData(Keys.CLASS_TYPE_KEY, type)
    putCopyableUserData(Keys.CLASS_TYPE_KEY, type)
}

fun PsiElement.getClassType(): PsiClassExt.ClassType? = getUserData(Keys.CLASS_TYPE_KEY)

fun PsiElement.findLocalReference(element: PsiElement): PsiReference? =
    ReferencesSearch.search(this, LocalSearchScope(element)).findFirst()

fun KClass<*>.group(addon: String = ""): FoldingGroup = FoldingGroup.newGroup(qualifiedName + addon)

var PsiMethod.propertyField: PsiField?
    get() = getUserData(Keys.FIELD_KEY)
    set(value) = putUserData(Keys.FIELD_KEY, value)

val PsiField.metadata: Keys.FieldMetaData
    get() {
        var userData = getUserData(Keys.FIELD_META_DATA_KEY)
        if (userData == null) {
            userData = Keys.FieldMetaData()
            putUserData(Keys.FIELD_META_DATA_KEY, userData)
        }
        return userData
    }

fun PsiField.hasLiteralConstInitializer() = initializer is PsiLiteralExpression

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


fun PsiElement.prevWhiteSpace(): PsiWhiteSpace? = prevSibling as? PsiWhiteSpace
fun PsiElement.nextWhiteSpace(): PsiWhiteSpace? = nextSibling as? PsiWhiteSpace

inline fun <reified T> Any?.asInstance(): T? = if (T::class.isInstance(this)) {
    this as T
} else {
    null
}

inline fun <reified T> Any?.isInstance(): Boolean = this is T

fun <E, T : Collection<E>?> T.takeIfSize(size: Int): T? = this.takeIf {
    it?.size == size
}

fun <E, T : Collection<E>?> T.takeIfSizeNot(size: Int): T? = this.takeIf {
    it?.size != size
}

fun <T> Array<T>?.takeIfSize(size: Int): Array<T>? = this.takeIf {
    it?.size == size
}

fun <T> Array<T>?.takeIfSizeNot(size: Int): Array<T>? = this.takeIf {
    it?.size != size
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


fun String.equalsIgnoreSpaces(second: String): Boolean =
    filterNot(Char::isWhitespace) == second.filterNot(Char::isWhitespace)


fun Boolean?.on(): Any? = if (this == true) {
    on(true)
} else {
    null
}

fun <T> Boolean.on(element: T?): T? = if (this) {
    element
} else {
    null
}

fun Boolean.off(): Any? = if (this) {
    null
} else {
    true
}

fun <T> Boolean.off(element: T?): T? = if (this) {
    null
} else {
    element
}

fun List<PsiElement>.expr(
    text: String,
    group: FoldingGroup? = null,
    foldPrevWhiteSpace: Boolean = false
): Expression? {
    if (this.isEmpty()) {
        return null
    }
    val map = map { element ->
        element.expr(
            text = text,
            group = group,
            foldPrevWhiteSpace = foldPrevWhiteSpace
        )
    }
    return map.exprWrap(first().parent)
}

fun PsiElement.exprOnLastChar(
    text: String,
    group: FoldingGroup? = null,
    foldPrevWhiteSpace: Boolean = false,
): SimpleExpression? = expr(
    text = this.text.last() + text, group = group, foldPrevWhiteSpace = foldPrevWhiteSpace, textRange = textRangeChar(PsiElement::end, -1, 0)
)

fun PsiElement.expr(
    text: String,
    group: FoldingGroup? = null,
    foldPrevWhiteSpace: Boolean = false,
    textRange: TextRange = this.textRange,
): SimpleExpression? {
    textRange.isEmpty.takeIf {
        !it
    } ?: return null

    return SimpleExpression(
        this,
        text = text,
        textRange = textRange,
        group = group,
        foldPrevWhiteSpace = foldPrevWhiteSpace
    )
}

fun List<PsiElement>.exprHide(
    group: FoldingGroup? = null,
    foldPrevWhiteSpace: Boolean = false
): Expression? {
    if (this.isEmpty()) {
        return null
    }
    val map = map { element ->
        element.exprHide(
            group = group,
            foldPrevWhiteSpace = foldPrevWhiteSpace
        )
    }
    return map.exprWrap(first().parent)
}

fun PsiElement?.exprHide(
    group: FoldingGroup? = null,
    foldPrevWhiteSpace: Boolean = false,
    textRange: TextRange? = this?.textRange,
): HideExpression? {
    this ?: return null
    textRange?.isEmpty?.takeIf {
        !it
    } ?: return null


    return HideExpression(
        this,
        textRange,
        group = group,
        foldPrevWhiteSpace = foldPrevWhiteSpace
    )
}

fun PsiElement.exprWrapAround(
    group: FoldingGroup? = null,
    textBefore: String? = null,
    foldPrevWhiteSpace: Boolean = false,
    //TODO:
    textAfter: String? = null,
    foldNextWhiteSpace: Boolean = false
) =
    WrapAroundExpression(
        this,
        textRange,
        group = group,
        textBefore = textBefore,
        foldPrevWhiteSpace = foldPrevWhiteSpace,
        textAfter = textAfter,
        foldNextWhiteSpace = foldNextWhiteSpace
    )

fun Collection<Expression?>.exprWrap(
    parent: PsiElement,
): Expression? {
    val chain = filterNotNull()
    if (chain.isEmpty()) {
        return null
    }
    chain.singleOrNull()?.run {
        return this
    }
    return WrapperExpression(parent, chain = chain)
}

fun exprList(elements: Iterable<Expression?>) = mutableListOf(elements)
fun exprList(vararg elements: Expression?) = mutableListOf(*elements)
fun foldingList(vararg elements: FoldingDescriptor) = mutableListOf(*elements)
inline fun MutableList<Expression?>.addIfEnabled(featureFlag: Boolean, function: () -> Expression?) {
    if (featureFlag) {
        add(function.invoke())
    }
}



fun <T> Iterable<T>.distinctNot(): List<T> {
    return groupBy {
        it
    }.filter {
        it.value.size > 1
    }.flatMap {
        it.value
    }
}

fun <T> Array<T>.distinctNot(): List<T> {
    return groupBy {
        it
    }.filter {
        it.value.size > 1
    }.flatMap {
        it.value
    }
}

val PsiElement.identifier: PsiIdentifier?
    get() = this.children.firstOrNull {
        it is PsiIdentifier
    } as? PsiIdentifier


fun PsiCodeBlock.hasComments(): Boolean = children.any {
    it is PsiComment
}

fun PsiCodeBlock.getComment(): PsiElement? = children.firstOrNull {
    it is PsiComment
}

fun <K, V> Map<K, V>.isUnique(): Boolean =
    keys.sameSize(keys.distinct()) && values.sameSize(values.distinct())

fun <K, V, K2, V2> Map<K, V>?.sameSize(otherMap: Map<K2, V2>?) = this?.size == otherMap?.size
fun <E, E2> Collection<E>?.sameSize(otherCollection: Collection<E2>?) = this?.size == otherCollection?.size


fun PsiMethod.getSignature(): MethodSignature = getSignature(PsiSubstitutor.EMPTY)

fun VirtualFile.toJavaPsiFile(project: Project): PsiJavaFile? {
    if (!isValid) {
        return null
    }
    return PsiManager.getInstance(project).findFile(this) as PsiJavaFile?
}