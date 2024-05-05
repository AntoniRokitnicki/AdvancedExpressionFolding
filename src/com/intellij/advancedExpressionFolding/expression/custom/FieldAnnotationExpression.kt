package com.intellij.advancedExpressionFolding.expression.custom

import com.intellij.advancedExpressionFolding.expression.Expression
import com.intellij.lang.folding.FoldingDescriptor
import com.intellij.openapi.editor.Document
import com.intellij.openapi.editor.FoldingGroup
import com.intellij.openapi.util.TextRange
import com.intellij.psi.PsiAnnotation
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiTypeElement
import com.intellij.refactoring.suggested.endOffset

class FieldAnnotationExpression(
    private val typeElement: PsiTypeElement,
    private val annotationElement: PsiAnnotation,
    private val typeSuffix: String
)
    : Expression(typeElement, typeElement.textRange) {
    override fun supportsFoldRegions(document: Document, parent: Expression?): Boolean {
        return true
    }

    override fun buildFoldRegions(element: PsiElement, document: Document, parent: Expression?): Array<FoldingDescriptor> {
        val group = FoldingGroup.newGroup(FieldAnnotationExpression::class.java.name)
        return arrayOf(
            fold(typeElement, TextRange(typeElement.endOffset, typeElement.endOffset+1), "$typeSuffix ", group),
            fold(annotationElement, annotationElement.textRange, "", group),
        )
    }

    private fun fold(element: PsiElement, textRange: TextRange, placeholderText: String, group: FoldingGroup): FoldingDescriptor {
        return FoldingDescriptor(element.node, textRange, group, placeholderText, true, emptySet<Any>())
    }

    override fun isNested(): Boolean = true
}
