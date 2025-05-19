package com.intellij.advancedExpressionFolding.extension

import com.intellij.advancedExpressionFolding.expression.Expression
import com.intellij.advancedExpressionFolding.expression.custom.*
import com.intellij.lang.folding.FoldingDescriptor
import com.intellij.openapi.editor.FoldingGroup
import com.intellij.openapi.util.TextRange
import com.intellij.psi.PsiAnnotation
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiModifierListOwner
import kotlin.reflect.KClass

fun List<PsiElement>.expr(
    text: String,
    group: FoldingGroup? = null,
): Expression? {
    if (this.isEmpty()) {
        return null
    }
    val map = map { element ->
        element.expr(
            text = text,
            group = group,
        )
    }
    return map.exprWrap(first().parent)
}

fun PsiElement.exprOnLastChar(
    text: String,
    group: FoldingGroup? = null,
): SimpleExpression? = expr(
    text = this.text.last() + text, group = group, textRange = textRangeChar(PsiElement::end, -1, 0)
)

fun PsiElement.expr(
    text: String,
    group: FoldingGroup? = null,
    textRange: TextRange = this.textRange,
): SimpleExpression? {
    textRange.isEmpty.takeIf {
        !it
    } ?: return null

    return SimpleExpression(
        this,
        textRange = textRange,
        text = text,
        group = group,
    )
}

fun List<PsiElement>.exprHide(
    group: FoldingGroup? = null,
): Expression? {
    if (this.isEmpty()) {
        return null
    }
    val list = map { element ->
        element.exprHide(
            group = group,
        )
    }
    return list.exprWrap(first().parent)
}

fun PsiElement?.exprHide(
    group: FoldingGroup? = null,
    textRange: TextRange? = this?.textRange,
): HideExpression? {
    this ?: return null
    textRange?.isEmpty?.takeIf {
        !it
    } ?: return null

    return HideExpression(
        this,
        textRange,
        group = group
    )
}

fun PsiElement.exprWrapAround(
    group: FoldingGroup? = null,
    textBefore: String? = null,
    foldPrevWhiteSpace: Boolean = false,
) =
    WrapAroundExpression(
        this,
        textRange,
        group = group,
        textBefore = textBefore,
        foldPrevWhiteSpace = foldPrevWhiteSpace,
    )

fun List<Expression?>.exprWrap(
    parent: PsiElement,
    group: FoldingGroup? = null,
): Expression? {
    val chain = when {
        group != null -> applyGroup(group)
        else -> this
    }.filterNotNull()
    if (chain.isEmpty()) {
        return null
    }
    chain.singleOrNull()?.run {
        return this
    }
    return WrapperExpression(parent, chain = chain)
}

fun exprList(vararg elements: Expression?) = mutableListOf(*elements)
fun foldingList(vararg elements: FoldingDescriptor) = mutableListOf(*elements)
fun List<Expression?>.applyGroup(group: FoldingGroup): List<Expression?> {
    filterIsInstance<FastExpression>().apply {
        forEach {
            it.group = group
        }
    }
    return this
}

inline fun MutableList<Expression?>.forwardIfEnabled(featureFlag: Boolean, function: (MutableList<Expression?>) -> Unit) {
    if (featureFlag) {
        function(this)
    }
}
inline fun MutableList<Expression?>.addIfEnabled(featureFlag: Boolean, function: () -> Expression?) {
    if (featureFlag) {
        add(function.invoke())
    }
}
inline fun PsiModifierListOwner.hideAnnotation(
    list: MutableList<Expression?>,
    group: FoldingGroup,
    crossinline annotationFilter: PsiAnnotation.() -> Boolean,
) {
    (modifierList ?: return).annotations.asSequence().filter {
        annotationFilter.invoke(it)
    }.forEach {
        list.add(it.exprHide(group = group))
        list.add(it.prevWhiteSpace().exprHide(group = group))
        list.add(it.nextWhiteSpace().exprHide(group = group))
    }
}

fun KClass<*>.group(addon: String = ""): FoldingGroup = FoldingGroup.newGroup(qualifiedName + addon)
fun BaseExtension.group(addon: String = ""): FoldingGroup = this::class.group(addon)