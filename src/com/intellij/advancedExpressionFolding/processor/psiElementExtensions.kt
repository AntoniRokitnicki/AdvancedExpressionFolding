package com.intellij.advancedExpressionFolding.processor

import com.intellij.advancedExpressionFolding.processor.EModifier.*
import com.intellij.advancedExpressionFolding.processor.cache.Keys
import com.intellij.openapi.util.Key
import com.intellij.advancedExpressionFolding.processor.core.BaseExtension.Companion.isBoolean
import com.intellij.advancedExpressionFolding.processor.core.BaseExtension.Companion.isInt
import com.intellij.advancedExpressionFolding.processor.core.BaseExtension.Companion.isObject
import com.intellij.advancedExpressionFolding.processor.core.BaseExtension.Companion.isString
import com.intellij.advancedExpressionFolding.processor.core.BaseExtension.Companion.isVoid
import com.intellij.advancedExpressionFolding.processor.declaration.PsiClassExt
import com.intellij.advancedExpressionFolding.processor.util.PropertyUtil
import com.intellij.openapi.project.Project
import com.intellij.openapi.vfs.VirtualFile
import com.intellij.psi.*
import com.intellij.psi.impl.source.PsiClassReferenceType
import com.intellij.psi.search.LocalSearchScope
import com.intellij.psi.search.searches.ReferencesSearch
import com.intellij.psi.util.MethodSignature
import java.util.*


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

inline fun String.filter(predicate: (String) -> Boolean): String? = takeIf(predicate)

fun PsiElement.isIgnored(): Boolean = getUserData(Keys.ignoredKey) ?: false

fun PsiElement.markIgnored(value: Boolean = true) = putUserData(Keys.ignoredKey, value)

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
val PsiMethod.keywords: List<PsiKeyword>
    get() = modifierList.children.filterIsInstance<PsiKeyword>()

fun PsiModifierListOwner.isPublic() = hasModifierProperty(PsiModifier.PUBLIC)
fun PsiModifierListOwner.isProtected() = hasModifierProperty(PsiModifier.PROTECTED)
fun PsiModifierListOwner.isPrivate() = hasModifierProperty(PsiModifier.PRIVATE)
fun PsiModifierListOwner.isStatic() = hasModifierProperty(PsiModifier.STATIC)
fun PsiModifierListOwner.isFinal() = hasModifierProperty(PsiModifier.FINAL)

fun PsiModifierListOwner.isNotStatic() = !isStatic()

fun PsiKeyword.isPrivate() = tokenType == JavaTokenType.PRIVATE_KEYWORD
fun PsiKeyword.isProtected() = tokenType == JavaTokenType.PROTECTED_KEYWORD
fun PsiKeyword.isPublic() = tokenType == JavaTokenType.PUBLIC_KEYWORD

fun PsiAnnotation.isOverride() = qualifiedName == "java.lang.Override"
fun PsiAnnotation.isSuppressWarnings() = qualifiedName == "java.lang.SuppressWarnings"

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
    @Suppress("UNCHECKED_CAST")
    putUserData(Keys.CLASS_TYPE_KEY.key as Key<PsiClassExt.ClassType>, type)
    @Suppress("UNCHECKED_CAST")
    putCopyableUserData(Keys.CLASS_TYPE_KEY.key as Key<PsiClassExt.ClassType>, type)
}

@Suppress("UNCHECKED_CAST")
fun PsiElement.getClassType(): PsiClassExt.ClassType? = getUserData(Keys.CLASS_TYPE_KEY.key as Key<PsiClassExt.ClassType>)

var PsiMethod.propertyField: PsiField?
    @Suppress("UNCHECKED_CAST")
    get() = getUserData(Keys.FIELD_KEY.key as Key<PsiField>)
    @Suppress("UNCHECKED_CAST")
    set(value) = putUserData(Keys.FIELD_KEY.key as Key<PsiField>, value)

val PsiField.metadata: Keys.FieldMetaData
    get() {
        @Suppress("UNCHECKED_CAST")
        var userData = getUserData(Keys.FIELD_META_DATA_KEY.key as Key<Keys.FieldMetaData>)
        if (userData == null) {
            userData = Keys.FieldMetaData()
            @Suppress("UNCHECKED_CAST")
            putUserData(Keys.FIELD_META_DATA_KEY.key as Key<Keys.FieldMetaData>, userData)
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

val PsiElement.identifier: PsiIdentifier?
    get() = children.firstOrNull {
        it is PsiIdentifier
    } as? PsiIdentifier

fun PsiCodeBlock.hasComments(): Boolean = children.any {
    it is PsiComment
}

fun PsiCodeBlock.getComment(): PsiElement? = children.firstOrNull {
    it is PsiComment
}

fun PsiMethod.getSignature(): MethodSignature = getSignature(PsiSubstitutor.EMPTY)

fun VirtualFile.toJavaPsiFile(project: Project): PsiJavaFile? {
    if (!isValid) {
        return null
    }
    return PsiManager.getInstance(project).findFile(this) as PsiJavaFile?
}


fun PsiElement?.prevWhiteSpace(): PsiWhiteSpace? = this?.prevSibling as? PsiWhiteSpace
fun PsiElement?.nextWhiteSpace(): PsiWhiteSpace? = this?.nextSibling as? PsiWhiteSpace

fun PsiElement.findLocalReference(element: PsiElement): PsiReference? =
    ReferencesSearch.search(this, LocalSearchScope(element)).findFirst()
