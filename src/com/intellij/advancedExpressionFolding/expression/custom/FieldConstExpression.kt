package com.intellij.advancedExpressionFolding.expression.custom

import com.intellij.advancedExpressionFolding.expression.Expression
import com.intellij.advancedExpressionFolding.extension.*
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
        
        val sortedKeywords = keywords.map {
            if (it.isPrivate() || it.isProtected()) {
                null
            } else {
                it
            }
        }
        val default = !sortedKeywords.any {
            it == null || it.isPublic()
        }

        val baseTextRange = if (sortedKeywords.firstOrNull() == null) {
            TextRange(keywords.first().end() + 1, modifiers.end())
        } else {
            if (sortedKeywords.any { it == null }) {
                //TODO: add folding for private, protected not being first
                //TODO: fold when in the middle and when its last
                null
            }  else { // public
                TextRange(keywords.first().start() , modifiers.end())
            }

        }

        if (baseTextRange != null) {
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

            val typeSuffix =
                fold(modifiers, textRange, constText, group)
            val elements = mutableListOf(typeSuffix)
            if (typeElement != null) {
                elements += fold(typeElement, typeElement.textRange, "", group)
            }

            return elements.toTypedArray()
        }

        return EMPTY_ARRAY
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
