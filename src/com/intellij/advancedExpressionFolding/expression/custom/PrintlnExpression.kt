package com.intellij.advancedExpressionFolding.expression.custom

import com.intellij.advancedExpressionFolding.expression.Expression
import com.intellij.lang.folding.FoldingDescriptor
import com.intellij.openapi.editor.Document
import com.intellij.openapi.util.TextRange
import com.intellij.psi.PsiElement

class PrintlnExpression(
    element: PsiElement, textRange: TextRange, child: Expression?
) : AbstractSingleChildExpression(element, textRange, "", child) {

    override fun groupName(): String  = PrintlnExpression::class.java.name

    override fun buildFoldRegions(
        element: PsiElement,
        document: Document,
        parent: Expression?
    ): Array<FoldingDescriptor> {
        return super.buildFoldRegions(element, document, parent)
    }

}
