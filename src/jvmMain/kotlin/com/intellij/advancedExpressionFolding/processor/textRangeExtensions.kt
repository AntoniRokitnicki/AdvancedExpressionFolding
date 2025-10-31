package com.intellij.advancedExpressionFolding.processor

import com.intellij.openapi.util.TextRange
import com.intellij.psi.PsiElement

inline fun PsiElement.textRangeChar(positionMethod: PsiElement.() -> Int, startOffset: Int, endOffset: Int): TextRange {
    val position = positionMethod()
    return TextRange(position + startOffset, position + endOffset)
}

fun PsiElement.trailingCharsRange(charCount: Int): TextRange = textRangeChar(PsiElement::end, -charCount, 0)
fun PsiElement.leadingCharsRange(charCount: Int): TextRange = textRangeChar(PsiElement::start, 0, charCount)

fun Pair<Int, Int>.toTextRange() = TextRange(first, second)
fun IntRange.toTextRange() = TextRange(this.first, this.last)
operator fun TextRange.plus(string: String): TextRange =
    TextRange.create(startOffset + string.length, endOffset + string.length)

operator fun TextRange.plus(addon: IntRange): TextRange =
    TextRange.create(startOffset + addon.first, endOffset + addon.last)

fun PsiElement.start(): Int = textRange.startOffset
fun PsiElement.end(): Int = textRange.endOffset
