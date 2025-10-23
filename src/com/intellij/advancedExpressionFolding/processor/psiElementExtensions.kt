package com.intellij.advancedExpressionFolding.processor

import com.intellij.advancedExpressionFolding.processor.EModifier.*
import com.intellij.advancedExpressionFolding.processor.cache.Keys
import com.intellij.advancedExpressionFolding.processor.cache.Keys.IGNORED
import com.intellij.advancedExpressionFolding.processor.declaration.PsiClassExt
import com.intellij.advancedExpressionFolding.processor.util.PropertyUtil
import com.intellij.openapi.project.Project
import com.intellij.openapi.vfs.VirtualFile
import com.intellij.psi.*
import com.intellij.psi.impl.source.PsiClassReferenceType
import com.intellij.psi.search.LocalSearchScope
import com.intellij.psi.search.searches.ReferencesSearch
import com.intellij.psi.util.MethodSignature

fun isNull(type: PsiType?): Boolean = (type as? PsiPrimitiveType)?.name == "null"

fun PsiType?.isInt(): Boolean = (this as? PsiPrimitiveType)?.name == "int"
fun PsiType?.isVoid(): Boolean = (this as? PsiPrimitiveType)?.name == "void"
fun PsiType?.isBoolean(): Boolean = (this as? PsiPrimitiveType)?.name == "boolean"
fun PsiType?.isString() = asInstance<PsiClassReferenceType>()?.name == "String"
fun PsiType?.isPrimitive() = asInstance<PsiPrimitiveType>() != null
fun PsiType?.isPrimitiveOrString() = isPrimitive() || isString()
fun PsiType?.isObject() = this?.canonicalText == "java.lang.Object"

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

fun PsiElement.isIgnored(): Boolean = getUserData(IGNORED) ?: false
fun PsiElement.markIgnored(value: Boolean = true) = putUserData(IGNORED, value)

fun PsiExpressionList.filterOutWhiteSpaceAndTokens() = children.filter {
    !it.isIgnorable()
}

fun PsiElement.isIgnorable() = this is PsiJavaToken || isWhitespace()

fun PsiElement.isWhitespace() = this is PsiWhiteSpace


val PsiCall.argumentExpressions: Array<PsiExpression>
    get() = argumentList?.expressions ?: PsiExpression.EMPTY_ARRAY

val PsiCall.firstArgument: PsiExpression?
    get() = argumentExpressions.firstOrNull()

val PsiCall.singleArgument: PsiExpression?
    get() = argumentExpressions.singleOrNull()

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
    val matchesReturnType = returnType.isVoid() || returnType?.typeResolved == containingClass
    return parameterList.parametersCount == 1 && matchesReturnType && isSetter(name)
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
    val userData = classType
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

val PsiElement.classType: PsiClassExt.ClassType?
    get() = getUserData(Keys.CLASS_TYPE_KEY)

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
    tailrec fun search(element: PsiElement, index: Int): T? {
        val target = parents.getOrNull(index) ?: return null
        val found = generateSequence(element.parent) { it.parent }
            .firstOrNull { target.isInstance(it) }
            ?: return null
        return if (index == parents.lastIndex) {
            parentClass.cast(found)
        } else {
            search(found, index + 1)
        }
    }
    return search(this, 0)
}

val PsiElement.identifier: PsiIdentifier?
    get() = children.firstOrNull {
        it is PsiIdentifier
    } as? PsiIdentifier

fun PsiCodeBlock.hasComments(): Boolean = children.any {
    it is PsiComment
}

val PsiCodeBlock.comment: PsiElement?
    get() = children.firstOrNull {
        it is PsiComment
    }

val PsiMethod.signature: MethodSignature
    get() = getSignature(PsiSubstitutor.EMPTY)

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
