package com.intellij.advancedExpressionFolding.expression.literal

import com.intellij.advancedExpressionFolding.expression.Expression
import com.intellij.lang.folding.FoldingDescriptor
import com.intellij.openapi.editor.Document
import com.intellij.openapi.editor.FoldingGroup
import com.intellij.openapi.util.TextRange
import com.intellij.psi.PsiElement

class StringLiteral(
    element: PsiElement,
    textRange: TextRange,
    val string: String
) : Expression(element, textRange), CharSequenceLiteral {

    override fun supportsFoldRegions(document: Document, parent: Expression?): Boolean {
        return document.getText(textRange) != "\"$string\""
    }

    override fun buildFoldRegions(
        element: PsiElement,
        document: Document,
        parent: Expression?
    ): Array<FoldingDescriptor> {
        val group = FoldingGroup.newGroup(StringLiteral::class.java.name)
        val descriptors = arrayOf(
            FoldingDescriptor(element.node, element.textRange, group, "\"$string\"")
        )
        return descriptors
    }
}
