package com.intellij.advancedExpressionFolding.expression.operation.optional

import com.intellij.advancedExpressionFolding.expression.Expression
import com.intellij.lang.folding.FoldingDescriptor
import com.intellij.openapi.editor.Document
import com.intellij.openapi.editor.FoldingGroup
import com.intellij.openapi.util.TextRange
import com.intellij.psi.PsiElement
import java.util.ArrayList

class OptionalMapSafeCallParam(
    element: PsiElement,
    textRange: TextRange,
    private val string: String
) : Expression(element, textRange) {
    fun getString(): String = string

    override fun buildFoldRegions(
        element: PsiElement,
        document: Document,
        parent: Expression?
    ): Array<FoldingDescriptor> {
        val group = FoldingGroup.newGroup(OptionalMapSafeCallParam::class.java.name)
        val descriptors = ArrayList<FoldingDescriptor>()
        descriptors.add(FoldingDescriptor(element.node, element.textRange, group, string))
        return descriptors.toTypedArray()
    }

    override fun isCollapsedByDefault(): Boolean = true

    override fun supportsFoldRegions(document: Document, parent: Expression?): Boolean = true
}
