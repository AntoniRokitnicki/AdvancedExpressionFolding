package com.intellij.advancedExpressionFolding.expression.custom

import com.intellij.advancedExpressionFolding.expression.Expression
import com.intellij.lang.folding.FoldingDescriptor
import com.intellij.openapi.editor.Document
import com.intellij.openapi.editor.FoldingGroup
import com.intellij.openapi.util.TextRange
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiTypeElement
import com.intellij.refactoring.suggested.endOffset

class FieldAnnotationExpression(
    private val typeToAppend: PsiTypeElement,
    private val annotationToHide: PsiElement?,
    val typeSuffix: String
) : Expression(typeToAppend, typeToAppend.textRange) {
    override fun supportsFoldRegions(document: Document, parent: Expression?): Boolean {
        return true
    }

    override fun buildFoldRegions(
        element: PsiElement,
        document: Document,
        parent: Expression?
    ): Array<FoldingDescriptor> {
        val group = FoldingGroup.newGroup(FieldAnnotationExpression::class.java.name)

        val typeSuffix =
            fold(typeToAppend, TextRange(typeToAppend.endOffset, typeToAppend.endOffset + 1), "$typeSuffix ", group)
        if (annotationToHide != null) {
            return arrayOf(
                typeSuffix,
                fold(annotationToHide, annotationToHide.textRange, "", group),
            )
        }
        return arrayOf(typeSuffix)
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
