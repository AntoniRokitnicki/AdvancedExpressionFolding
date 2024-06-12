package com.intellij.advancedExpressionFolding.expression.custom

import com.intellij.advancedExpressionFolding.expression.Expression
import com.intellij.advancedExpressionFolding.extension.end
import com.intellij.advancedExpressionFolding.extension.foldingList
import com.intellij.advancedExpressionFolding.extension.prevWhiteSpace
import com.intellij.lang.folding.FoldingDescriptor
import com.intellij.openapi.editor.Document
import com.intellij.openapi.editor.FoldingGroup
import com.intellij.openapi.util.TextRange
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiTypeElement

class NullAnnotationExpression(
    private val typeToAppend: PsiTypeElement,
    private val annotationToHide: PsiElement?,
    val typeSuffix: String,
    private val foldPrevWhiteSpace: Boolean = false,
) : Expression(typeToAppend, typeToAppend.textRange) {
    override fun supportsFoldRegions(document: Document, parent: Expression?): Boolean {
        return true
    }

    override fun buildFoldRegions(
        element: PsiElement,
        document: Document,
        parent: Expression?
    ): Array<FoldingDescriptor> {
        val group = FoldingGroup.newGroup(NullAnnotationExpression::class.java.name)
        val list = foldingList(fold(typeToAppend, TextRange(typeToAppend.end(), typeToAppend.end() + 1), "$typeSuffix ", group))
        typeToAppend.prevWhiteSpace()?.takeIf {
            foldPrevWhiteSpace
        }?.let {
            list += fold(it, it.textRange, "", group)
        }
        if (annotationToHide != null) {
            list += fold(annotationToHide, annotationToHide.textRange, "", group)
         }
        return list.toTypedArray()
    }

    private fun fold(
        element: PsiElement,
        textRange: TextRange,
        placeholderText: String,
        group: FoldingGroup
    ): FoldingDescriptor {
        return FoldingDescriptor(element.node, textRange, group, placeholderText, true, emptySet<Any>())
    }

    override fun isNested() = true
}
