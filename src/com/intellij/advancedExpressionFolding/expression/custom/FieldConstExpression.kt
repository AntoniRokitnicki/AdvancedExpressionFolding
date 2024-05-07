package com.intellij.advancedExpressionFolding.expression.custom

import com.intellij.advancedExpressionFolding.expression.Expression
import com.intellij.advancedExpressionFolding.extension.plus
import com.intellij.lang.folding.FoldingDescriptor
import com.intellij.openapi.editor.Document
import com.intellij.openapi.editor.FoldingGroup
import com.intellij.openapi.util.TextRange
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiTypeElement

class FieldConstExpression(
    private val typeElement: PsiTypeElement?,
    private val annotationElement: PsiElement,
    private val typeSuffix: String,
    private val initializer: PsiElement? = null,
    private val initializerText: String? = null,
) : Expression(annotationElement, annotationElement.textRange) {
    override fun supportsFoldRegions(document: Document, parent: Expression?): Boolean {
        return true
    }

    override fun buildFoldRegions(
        element: PsiElement,
        document: Document,
        parent: Expression?
    ): Array<FoldingDescriptor> {
        val group = FoldingGroup.newGroup(FieldConstExpression::class.java.name)

        val textRange = if (typeElement == null) {
            annotationElement.textRange
        } else {
            annotationElement.textRange + (0..1)
        }
        val typeSuffix =
            fold(annotationElement, textRange, typeSuffix, group)
        val elements = mutableListOf(typeSuffix)
        if (typeElement != null) {
            elements += fold(typeElement, typeElement.textRange, "", group)
        }
        if (initializer != null && initializerText != null) {
            elements += fold(initializer, initializer.textRange, initializerText, group)
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
