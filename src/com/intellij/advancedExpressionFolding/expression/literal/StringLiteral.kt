package com.intellij.advancedExpressionFolding.expression.literal

import com.intellij.advancedExpressionFolding.expression.Expression
import com.intellij.lang.folding.FoldingDescriptor
import com.intellij.openapi.editor.Document
import com.intellij.openapi.editor.FoldingGroup
import com.intellij.openapi.util.TextRange
import com.intellij.psi.PsiElement
import java.util.ArrayList

class StringLiteral(
    element: PsiElement,
    textRange: TextRange,
    string: String
) : Expression(element, textRange), CharSequenceLiteral {
    private var string: String = string

    fun getString(): String {
        return string
    }

    override fun supportsFoldRegions(document: Document, parent: Expression?): Boolean {
        return !document.getText(getTextRange()).equals("\"" + string + "\"")
    }

    override fun buildFoldRegions(
        element: PsiElement,
        document: Document,
        parent: Expression?
    ): Array<FoldingDescriptor> {
        val group = FoldingGroup.newGroup(StringLiteral::class.java.getName())
        val descriptors = ArrayList<FoldingDescriptor>()
        descriptors.add(
            FoldingDescriptor(
                element.getNode(),
                element.getTextRange(),
                group,
                "\"" + string + "\""
            )
        )
        return descriptors.toArray(arrayOfNulls<FoldingDescriptor>(0))
    }
}

