package com.intellij.advancedExpressionFolding.expression.semantic.kotlin

import com.intellij.advancedExpressionFolding.expression.Expression
import com.intellij.advancedExpressionFolding.processor.*
import com.intellij.lang.folding.FoldingDescriptor
import com.intellij.openapi.editor.Document
import com.intellij.openapi.editor.FoldingGroup
import com.intellij.openapi.util.TextRange
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiKeyword
import com.intellij.psi.PsiTypeElement

class FieldConstExpression(
    private val typeElement: PsiTypeElement?,
    private val modifiers: PsiElement,
    private val typeSuffix: String,
) : Expression(modifiers, modifiers.textRange) {
    override fun supportsFoldRegions(document: Document, parent: Expression?): Boolean {
        return true
    }

    override fun buildFoldRegions(
        element: PsiElement,
        document: Document,
        parent: Expression?
    ): Array<FoldingDescriptor> {
        val group = FieldConstExpression::class.group()

        val keywords = modifiers.children.filterIsInstance<PsiKeyword>()
        if (keywords.isEmpty()) {
            return EMPTY_ARRAY
        }

        val firstFoldableIndex = keywords.indexOfFirst { !it.isPrivate() && !it.isProtected() }
        if (firstFoldableIndex == -1) {
            return EMPTY_ARRAY
        }

        val lastFoldableIndex = keywords.indexOfLast { !it.isPrivate() && !it.isProtected() }
        val startElement = keywords[firstFoldableIndex]
        val endElement = keywords[lastFoldableIndex]

        val default = keywords.none {
            it.isPublic() || it.isPrivate() || it.isProtected()
        }

        val baseTextRange = when {
            lastFoldableIndex == keywords.lastIndex -> TextRange(startElement.start(), modifiers.end())
            else -> TextRange(startElement.start(), endElement.end())
        }

        val textRange = if (typeElement == null) {
            baseTextRange
        } else {
            baseTextRange + (0..1)
        }

        val constText = typeSuffix.takeIf {
            default
        }?.let {
            "default $it"
        } ?: typeSuffix

        val placeholderText = buildPlaceholder(
            keywords,
            firstFoldableIndex,
            lastFoldableIndex,
            constText,
        )

        val typeSuffixFold =
            fold(modifiers, textRange, placeholderText, group)
        val elements = mutableListOf(typeSuffixFold)
        if (typeElement != null) {
            elements += fold(typeElement, typeElement.textRange, "", group)
        }

        return elements.toTypedArray()
    }

    private fun buildPlaceholder(
        keywords: List<PsiKeyword>,
        startIndex: Int,
        endIndex: Int,
        constText: String,
    ): String {
        val parts = mutableListOf<String>()
        var placeholderAdded = false
        for (index in startIndex..endIndex) {
            val keyword = keywords[index]
            if (keyword.isPrivate() || keyword.isProtected()) {
                parts += keyword.text
            } else if (!placeholderAdded) {
                parts += constText
                placeholderAdded = true
            }
        }

        if (!placeholderAdded) {
            parts += constText
        }

        return parts.joinToString(" ")
    }

    private fun fold(
        element: PsiElement,
        textRange: TextRange,
        placeholderText: String,
        group: FoldingGroup
    ): FoldingDescriptor {
        return FoldingDescriptor(element.node, textRange, group, placeholderText, true, emptySet<Any>())
    }

    override fun isNested(): Boolean = true
}
