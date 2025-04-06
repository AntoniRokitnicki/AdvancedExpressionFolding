package com.intellij.advancedExpressionFolding.extension

import com.intellij.advancedExpressionFolding.expression.Expression
import com.intellij.advancedExpressionFolding.expression.custom.HideExpression
import com.intellij.advancedExpressionFolding.expression.custom.SimpleExpression
import com.intellij.advancedExpressionFolding.expression.custom.WrapAroundExpression
import com.intellij.advancedExpressionFolding.expression.custom.WrapperExpression
import com.intellij.lang.folding.FoldingDescriptor
import com.intellij.openapi.editor.FoldingGroup
import com.intellij.openapi.util.TextRange
import com.intellij.psi.PsiElement
import kotlin.reflect.KClass

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

fun KClass<*>.group(addon: String = ""): FoldingGroup = FoldingGroup.newGroup(qualifiedName + addon)
