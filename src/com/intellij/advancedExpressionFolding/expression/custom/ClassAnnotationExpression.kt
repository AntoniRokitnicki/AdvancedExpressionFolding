package com.intellij.advancedExpressionFolding.expression.custom

import com.intellij.advancedExpressionFolding.expression.Expression
import com.intellij.advancedExpressionFolding.extension.CustomClassAnnotation
import com.intellij.lang.folding.FoldingDescriptor
import com.intellij.openapi.editor.Document
import com.intellij.openapi.editor.FoldingGroup
import com.intellij.openapi.util.TextRange
import com.intellij.psi.PsiClass
import com.intellij.psi.PsiElement

class ClassAnnotationExpression(
        element: PsiClass,
        private val customClassAnnotations: List<CustomClassAnnotation>,
        private val elementsToFold: List<PsiElement>
) : Expression(element, element.textRange) {
    override fun supportsFoldRegions(document: Document, parent: Expression?): Boolean {
        return true
    }

    override fun buildFoldRegions(element: PsiElement, document: Document, parent: Expression?): Array<FoldingDescriptor> {
        return elementsToFold
            .filter {
                !it.textRange.isEmpty
            }
            .map {
                fold(it, it.textRange, "")
            }
            .plus(mapClass(element, document))
            .toTypedArray()
    }

    private fun mapClass(element: PsiElement, document: Document): FoldingDescriptor {
        val range = element.textRange
        val newRange = TextRange.create(range.startOffset, range.startOffset + 1)
        val firstChar = newRange.substring(document.text)
        return fold(element, newRange, "${customClassAnnotations.joinToString(" ")} $firstChar")
    }

    private fun fold(element: PsiElement, textRange: TextRange, placeholderText: String): FoldingDescriptor {
        return FoldingDescriptor(element.node, textRange, FoldingGroup.newGroup(ClassAnnotationExpression::class.java.name), placeholderText)
    }

    override fun isNested(): Boolean = true
}
