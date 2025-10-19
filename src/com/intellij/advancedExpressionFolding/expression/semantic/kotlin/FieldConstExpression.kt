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

        val firstKeyword = keywords.minByOrNull { it.start() } ?: return EMPTY_ARRAY
        val baseTextRange = TextRange(firstKeyword.start(), modifiers.end())

        val visibilityPrefix = when {
            keywords.any { it.isPrivate() } -> "private"
            keywords.any { it.isProtected() } -> "protected"
            keywords.any { it.isPublic() } -> null
            else -> "default"
        }

        val constText = listOfNotNull(visibilityPrefix, typeSuffix).joinToString(" ")

        val textRange = if (typeElement == null) {
            baseTextRange
        } else {
            baseTextRange + (0..1)
        }

        val modifiersFold =
            fold(modifiers, textRange, constText, group)
        val elements = mutableListOf(modifiersFold)
        if (typeElement != null) {
            elements += fold(typeElement, typeElement.textRange, "", group)
        }

        return elements.toTypedArray()
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
